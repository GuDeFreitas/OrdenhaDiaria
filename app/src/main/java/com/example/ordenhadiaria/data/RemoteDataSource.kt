package com.example.ordenhadiaria.data

import com.example.ordenhadiaria.data.model.Ordenha
import com.google.api.client.googleapis.auth.oauth2.GoogleCredential
import com.google.api.client.http.javanet.NetHttpTransport
import com.google.api.client.json.JsonFactory
import com.google.api.client.json.jackson2.JacksonFactory
import com.google.api.services.sheets.v4.Sheets
import com.google.api.services.sheets.v4.SheetsScopes
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.lang.Exception


private val JSON_FACTORY: JsonFactory = JacksonFactory.getDefaultInstance()
private val SCOPES = listOf(SheetsScopes.SPREADSHEETS_READONLY)

open class RemoteDataSource(
    private val credentials: GoogleCredential
) {

    suspend fun fetch(): List<Ordenha> {
        return try {
            withContext(Dispatchers.IO) {
                val items = mutableListOf<Ordenha>()

                val httpTransport = NetHttpTransport()

                val service =
                    Sheets.Builder(httpTransport, JSON_FACTORY, credentials.createScoped(SCOPES))
                        .build()

                val spreadsheetId = "1EaIHGN3GxGoBKIjA4Nk5qauCAU_MVoobgTzdAAUOxK0"
                val range = "data!A1:G"

                val response = service.spreadsheets().values()[spreadsheetId, range]
                    .execute()

                val values = response.getValues()

                if (values == null || values.isEmpty()) {
                    println("Nenhum dado encontrado na planilha.")
                } else {
                    println("Dados encontrados na planilha:")
                    values.forEachIndexed { index, row ->
                        if (index != 0) {
                            val ID = row[0]
                            val total = row[4]
                            val date = row[6]
                            items.add(
                                Ordenha(
                                    ID = ID.toString(),
                                    total = total.toString().replace(",", ".").toFloat(),
                                    date = date.toString(),
                                    rowIndex = index
                                )
                            )
                            println(row)
                        }
                    }
                }
                items
            }
        } catch (ex: Exception) {
            ex.printStackTrace()
            emptyList()
        }
    }
}
package com.example.mylibrary.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.example.mylibrary.model.Book
import com.example.mylibrary.model.CoverColors
import com.example.mylibrary.model.Genres
import com.example.mylibrary.model.ReadingStatus
import com.example.mylibrary.ui.components.AppTopBar
import com.example.mylibrary.ui.components.DropdownField
import com.example.mylibrary.ui.components.FormField
import com.example.mylibrary.ui.components.ScreenContainer

/** Sirve para agregar (existing == null) y para editar un libro. */
@Composable
fun BookFormScreen(
    existing: Book?,
    onBack: () -> Unit,
    onSave: (Book) -> Unit
) {
    var title by rememberSaveable { mutableStateOf(existing?.title ?: "") }
    var author by rememberSaveable { mutableStateOf(existing?.author ?: "") }
    var genre by rememberSaveable { mutableStateOf(existing?.genre ?: Genres.first()) }
    var pages by rememberSaveable { mutableStateOf(existing?.pages?.toString() ?: "") }
    var status by rememberSaveable { mutableStateOf(existing?.status ?: ReadingStatus.PENDIENTE) }
    var synopsis by rememberSaveable { mutableStateOf(existing?.synopsis ?: "") }
    var submitted by rememberSaveable { mutableStateOf(false) }

    val pagesNumber = pages.toIntOrNull()
    val pagesValid = pagesNumber != null && pagesNumber > 0
    val valid = title.isNotBlank() && author.isNotBlank() && pagesValid

    Scaffold(
        topBar = {
            AppTopBar(
                title = if (existing == null) "Agregar libro" else "Editar libro",
                onBack = onBack
            )
        }
    ) { inner ->
        ScreenContainer(Modifier.padding(inner)) {
            FormField(
                title, { title = it }, "Título",
                error = if (submitted && title.isBlank()) "Campo obligatorio" else null
            )
            FormField(
                author, { author = it }, "Autor",
                error = if (submitted && author.isBlank()) "Campo obligatorio" else null
            )
            Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                DropdownField(
                    label = "Género", value = genre, options = Genres,
                    onSelect = { genre = it }, modifier = Modifier.weight(1f)
                )
                FormField(
                    pages, { pages = it.filter(Char::isDigit) }, "N.º páginas",
                    modifier = Modifier.weight(1f),
                    error = if (submitted && !pagesValid) "Número no válido" else null,
                    keyboardType = KeyboardType.Number
                )
            }
            DropdownField(
                label = "Estado de lectura",
                value = status.label,
                options = ReadingStatus.values().map { it.label },
                onSelect = { sel -> status = ReadingStatus.values().first { it.label == sel } },
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(Modifier.height(8.dp))
            FormField(
                synopsis, { synopsis = it }, "Sinopsis (opcional)",
                singleLine = false, minLines = 3
            )
            Spacer(Modifier.height(16.dp))
            Button(
                onClick = {
                    submitted = true
                    if (valid) {
                        onSave(
                            Book(
                                id = existing?.id ?: 0, // 0 = libro nuevo; el id real lo asigna la navegación
                                title = title.trim(),
                                author = author.trim(),
                                genre = genre,
                                pages = pagesNumber ?: 0,
                                status = status,
                                rating = existing?.rating ?: 0,
                                favorite = existing?.favorite ?: false,
                                synopsis = synopsis.trim(),
                                coverColor = existing?.coverColor ?: CoverColors[0]
                            )
                        )
                    }
                },
                modifier = Modifier.fillMaxWidth()
            ) { Text("Guardar libro") }
        }
    }
}

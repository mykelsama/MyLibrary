package com.example.mylibrary.ui.screens

import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.FilterChip
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.mylibrary.model.Book
import com.example.mylibrary.model.ReadingStatus
import com.example.mylibrary.ui.components.AppTopBar
import com.example.mylibrary.ui.components.BookCover
import com.example.mylibrary.ui.components.RatingBar
import com.example.mylibrary.ui.components.ScreenContainer
import com.example.mylibrary.ui.theme.Terracotta

@Composable
fun BookDetailScreen(
    book: Book?,
    onBack: () -> Unit,
    onEdit: () -> Unit,
    onUpdate: (Book) -> Unit,
    onDelete: () -> Unit
) {
    // Mientras se cierra la pantalla tras eliminar, el libro ya no existe.
    if (book == null) {
        Box(Modifier.fillMaxSize())
        return
    }
    var confirmDelete by rememberSaveable { mutableStateOf(false) }

    Scaffold(
        topBar = {
            AppTopBar(
                title = "Detalle",
                onBack = onBack,
                actions = {
                    IconButton(onClick = { onUpdate(book.copy(favorite = !book.favorite)) }) {
                        Icon(
                            imageVector = if (book.favorite) Icons.Filled.Favorite else Icons.Filled.FavoriteBorder,
                            contentDescription = "Marcar como favorito",
                            tint = if (book.favorite) Terracotta else LocalContentColor.current
                        )
                    }
                }
            )
        }
    ) { inner ->
        ScreenContainer(Modifier.padding(inner)) {
            BookCover(book, Modifier.width(160.dp), titleStyle = MaterialTheme.typography.titleLarge)
            Spacer(Modifier.height(16.dp))
            Text(
                book.title, style = MaterialTheme.typography.headlineMedium,
                textAlign = TextAlign.Center
            )
            Text(
                "${book.author} · ${book.genre}",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                textAlign = TextAlign.Center
            )
            Spacer(Modifier.height(8.dp))
            RatingBar(rating = book.rating) { onUpdate(book.copy(rating = it)) }
            Spacer(Modifier.height(8.dp))

            Text(
                "Estado de lectura", style = MaterialTheme.typography.labelLarge,
                modifier = Modifier.align(Alignment.Start)
            )
            Row(
                Modifier.fillMaxWidth().horizontalScroll(rememberScrollState()),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                ReadingStatus.values().forEach { s ->
                    FilterChip(
                        selected = book.status == s,
                        onClick = { onUpdate(book.copy(status = s)) },
                        label = { Text(s.label) }
                    )
                }
            }

            if (book.synopsis.isNotBlank()) {
                Spacer(Modifier.height(16.dp))
                Text(
                    "Sinopsis", style = MaterialTheme.typography.titleMedium,
                    modifier = Modifier.align(Alignment.Start)
                )
                Text(
                    book.synopsis, style = MaterialTheme.typography.bodyMedium,
                    modifier = Modifier.fillMaxWidth()
                )
            }
            Spacer(Modifier.height(12.dp))
            Text(
                "Páginas: ${book.pages}", style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier.align(Alignment.Start)
            )

            Spacer(Modifier.height(24.dp))
            Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                Button(onClick = onEdit, modifier = Modifier.weight(1f)) {
                    Icon(Icons.Filled.Edit, contentDescription = null)
                    Spacer(Modifier.width(8.dp))
                    Text("Editar")
                }
                OutlinedButton(
                    onClick = { confirmDelete = true },
                    modifier = Modifier.weight(1f),
                    colors = ButtonDefaults.outlinedButtonColors(contentColor = MaterialTheme.colorScheme.error)
                ) {
                    Icon(Icons.Filled.Delete, contentDescription = null)
                    Spacer(Modifier.width(8.dp))
                    Text("Eliminar")
                }
            }
        }
    }

    if (confirmDelete) {
        AlertDialog(
            onDismissRequest = { confirmDelete = false },
            title = { Text("Eliminar libro") },
            text = { Text("¿Seguro que quieres eliminar \"${book.title}\" de tu biblioteca?") },
            confirmButton = {
                TextButton(onClick = {
                    confirmDelete = false
                    onDelete()
                }) { Text("Eliminar") }
            },
            dismissButton = {
                TextButton(onClick = { confirmDelete = false }) { Text("Cancelar") }
            }
        )
    }
}

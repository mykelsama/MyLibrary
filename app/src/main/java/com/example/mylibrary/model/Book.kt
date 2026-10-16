package com.example.mylibrary.model

import androidx.compose.ui.graphics.Color

enum class ReadingStatus(val label: String) {
    PENDIENTE("Pendiente"),
    LEYENDO("Leyendo"),
    TERMINADO("Terminado")
}

enum class SortOrder(val label: String) {
    TITULO("Título"),
    AUTOR("Autor"),
    RECIENTES("Recientes")
}

data class Book(
    val id: Int,
    val title: String,
    val author: String,
    val genre: String,
    val pages: Int,
    val status: ReadingStatus,
    val rating: Int = 0,
    val favorite: Boolean = false,
    val synopsis: String = "",
    val coverColor: Color
)

val Genres = listOf("Novela", "Ciencia ficción", "Fantasía", "Historia", "Ensayo", "Poesía", "Otro")

val CoverColors = listOf(
    Color(0xFF2D5130), Color(0xFFB5472F), Color(0xFFC9A227),
    Color(0xFF2F4B6B), Color(0xFF5B3A5E), Color(0xFF3F6B4A)
)

package com.example.mylibrary.data

import androidx.compose.runtime.mutableStateListOf
import com.example.mylibrary.model.Book
import com.example.mylibrary.model.CoverColors
import com.example.mylibrary.model.ReadingStatus
import kotlin.collections.get

/** Datos de ejemplo en memoria para el MVP (sin base de datos todavía). */
object SampleData {
    val books = mutableStateListOf<Book>().apply {
        addAll(
            listOf(
                Book(1, "Cien años de soledad", "Gabriel García Márquez", "Novela", 471,
                    ReadingStatus.LEYENDO, 4, true,
                    "La historia de la familia Buendía a lo largo de siete generaciones en Macondo.", CoverColors[0]),
                Book(2, "El Principito", "Antoine de Saint-Exupéry", "Novela", 96,
                    ReadingStatus.TERMINADO, 5, true,
                    "Un piloto varado en el desierto conoce a un pequeño príncipe de otro planeta.", CoverColors[1]),
                Book(3, "1984", "George Orwell", "Ciencia ficción", 328,
                    ReadingStatus.PENDIENTE, 0, false,
                    "Una sociedad vigilada por el Gran Hermano donde pensar distinto es un delito.", CoverColors[2]),
                Book(4, "Rayuela", "Julio Cortázar", "Novela", 600,
                    ReadingStatus.PENDIENTE, 0, false,
                    "Una novela que se puede leer en más de un orden, entre París y Buenos Aires.", CoverColors[3]),
                Book(5, "La Odisea", "Homero", "Poesía", 416,
                    ReadingStatus.LEYENDO, 0, false,
                    "El largo viaje de Ulises de regreso a Ítaca tras la guerra de Troya.", CoverColors[4]),
                Book(6, "Fahrenheit 451", "Ray Bradbury", "Ciencia ficción", 249,
                    ReadingStatus.TERMINADO, 4, false,
                    "En un futuro donde los libros están prohibidos, un bombero empieza a dudar.", CoverColors[5])
            )
        )
    }

    fun nextId(): Int = (books.maxOfOrNull { it.id } ?: 0) + 1
}

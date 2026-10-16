package com.example.mylibrary.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.mylibrary.data.SampleData
import com.example.mylibrary.model.CoverColors
import com.example.mylibrary.model.SortOrder
import com.example.mylibrary.ui.screens.BookDetailScreen
import com.example.mylibrary.ui.screens.BookFormScreen
import com.example.mylibrary.ui.screens.LibraryScreen
import com.example.mylibrary.ui.screens.LoginScreen
import com.example.mylibrary.ui.screens.RegisterScreen
import com.example.mylibrary.ui.screens.SettingsScreen
import com.example.mylibrary.ui.screens.SplashScreen

@Composable
fun AppNavigation(darkTheme: Boolean, onDarkThemeChange: (Boolean) -> Unit) {
    val navController = rememberNavController()
    val books = SampleData.books
    var sortOrder by rememberSaveable { mutableStateOf(SortOrder.RECIENTES) }
    var language by rememberSaveable { mutableStateOf("Español") }
    var userName by rememberSaveable { mutableStateOf("") }
    var userEmail by rememberSaveable { mutableStateOf("") }

    val goToLibrary: () -> Unit = {
        navController.navigate(Routes.LIBRARY) {
            popUpTo(Routes.LOGIN) { inclusive = true }
        }
    }

    NavHost(navController = navController, startDestination = Routes.SPLASH) {

        composable(Routes.SPLASH) {
            SplashScreen(onFinished = {
                navController.navigate(Routes.LOGIN) {
                    popUpTo(Routes.SPLASH) { inclusive = true }
                }
            })
        }

        composable(Routes.LOGIN) {
            LoginScreen(
                onLogin = { email ->
                    if (email != userEmail) {
                        userEmail = email
                        userName = email.substringBefore("@").replaceFirstChar { it.uppercase() }
                    }
                    goToLibrary()
                },
                onGoToRegister = { navController.navigate(Routes.REGISTER) }
            )
        }

        composable(Routes.REGISTER) {
            RegisterScreen(
                onBack = { navController.popBackStack() },
                onRegistered = { name, email ->
                    userName = name
                    userEmail = email
                    goToLibrary()
                }
            )
        }

        composable(Routes.LIBRARY) {
            LibraryScreen(
                books = books,
                sortOrder = sortOrder,
                onBookClick = { navController.navigate(Routes.detail(it)) },
                onAddBook = { navController.navigate(Routes.form(-1)) },
                onOpenSettings = { navController.navigate(Routes.SETTINGS) }
            )
        }

        composable(
            route = Routes.DETAIL,
            arguments = listOf(navArgument("bookId") { type = NavType.IntType })
        ) { entry ->
            val id = entry.arguments?.getInt("bookId") ?: -1
            BookDetailScreen(
                book = books.find { it.id == id },
                onBack = { navController.popBackStack() },
                onEdit = { navController.navigate(Routes.form(id)) },
                onUpdate = { updated ->
                    val i = books.indexOfFirst { it.id == updated.id }
                    if (i >= 0) books[i] = updated
                },
                onDelete = {
                    books.removeAll { it.id == id }
                    navController.popBackStack()
                }
            )
        }

        composable(
            route = Routes.FORM,
            arguments = listOf(navArgument("bookId") { type = NavType.IntType })
        ) { entry ->
            val id = entry.arguments?.getInt("bookId") ?: -1
            BookFormScreen(
                existing = books.find { it.id == id },
                onBack = { navController.popBackStack() },
                onSave = { book ->
                    val i = books.indexOfFirst { it.id == book.id }
                    if (i >= 0) {
                        books[i] = book
                    } else {
                        val newId = SampleData.nextId()
                        books.add(book.copy(id = newId, coverColor = CoverColors[newId % CoverColors.size]))
                    }
                    navController.popBackStack()
                }
            )
        }

        composable(Routes.SETTINGS) {
            SettingsScreen(
                userName = userName,
                userEmail = userEmail,
                darkTheme = darkTheme,
                onDarkThemeChange = onDarkThemeChange,
                sortOrder = sortOrder,
                onSortOrderChange = { sortOrder = it },
                language = language,
                onLanguageChange = { language = it },
                onBack = { navController.popBackStack() },
                onLogout = {
                    navController.navigate(Routes.LOGIN) {
                        popUpTo(Routes.LIBRARY) { inclusive = true }
                    }
                }
            )
        }
    }
}
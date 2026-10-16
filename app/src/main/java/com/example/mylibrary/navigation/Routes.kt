package com.example.mylibrary.navigation

object Routes {
    const val SPLASH = "splash"
    const val LOGIN = "login"
    const val REGISTER = "register"
    const val LIBRARY = "library"
    const val SETTINGS = "settings"

    const val DETAIL = "detail/{bookId}"
    fun detail(bookId: Int) = "detail/$bookId"

    /** bookId = -1 significa "libro nuevo". */
    const val FORM = "form/{bookId}"
    fun form(bookId: Int) = "form/$bookId"
}

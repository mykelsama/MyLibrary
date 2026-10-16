package com.example.mylibrary.ui.screens

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AutoStories
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.example.mylibrary.ui.components.FormField
import com.example.mylibrary.ui.components.PasswordField
import com.example.mylibrary.ui.components.ScreenContainer

@Composable
fun LoginScreen(onLogin: (String) -> Unit, onGoToRegister: () -> Unit) {
    var email by rememberSaveable { mutableStateOf("") }
    var password by rememberSaveable { mutableStateOf("") }
    var submitted by rememberSaveable { mutableStateOf(false) }

    Scaffold { inner ->
        ScreenContainer(Modifier.padding(inner)) {
            Spacer(Modifier.height(32.dp))
            Icon(
                Icons.Filled.AutoStories, contentDescription = null,
                modifier = Modifier.size(56.dp), tint = MaterialTheme.colorScheme.primary
            )
            Spacer(Modifier.height(12.dp))
            Text("Bienvenido de vuelta", style = MaterialTheme.typography.headlineMedium)
            Text(
                "Ingresa a tu biblioteca personal",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
            Spacer(Modifier.height(24.dp))
            FormField(
                value = email, onValueChange = { email = it }, label = "Correo electrónico",
                error = if (submitted && email.isBlank()) "Campo obligatorio" else null,
                keyboardType = KeyboardType.Email
            )
            PasswordField(
                value = password, onValueChange = { password = it }, label = "Contraseña",
                error = if (submitted && password.isBlank()) "Campo obligatorio" else null
            )
            Spacer(Modifier.height(16.dp))
            Button(
                onClick = {
                    submitted = true
                    if (email.isNotBlank() && password.isNotBlank()) onLogin(email.trim())
                },
                modifier = Modifier.fillMaxWidth()
            ) { Text("Ingresar") }
            Spacer(Modifier.height(8.dp))
            Text("¿No tienes cuenta?", style = MaterialTheme.typography.bodyMedium)
            TextButton(onClick = onGoToRegister) { Text("Crear cuenta") }
        }
    }
}
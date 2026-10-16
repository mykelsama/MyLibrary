package com.example.mylibrary.ui.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.MaterialTheme
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
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.example.mylibrary.ui.components.AppTopBar
import com.example.mylibrary.ui.components.FormField
import com.example.mylibrary.ui.components.PasswordField
import com.example.mylibrary.ui.components.ScreenContainer

@Composable
fun RegisterScreen(onBack: () -> Unit, onRegistered: (String, String) -> Unit) {
    var name by rememberSaveable { mutableStateOf("") }
    var email by rememberSaveable { mutableStateOf("") }
    var password by rememberSaveable { mutableStateOf("") }
    var confirm by rememberSaveable { mutableStateOf("") }
    var accepted by rememberSaveable { mutableStateOf(false) }
    var submitted by rememberSaveable { mutableStateOf(false) }

    val nameError = if (submitted && name.isBlank()) "Campo obligatorio" else null
    val emailError = if (submitted && !email.contains("@")) "Correo no válido" else null
    val passError = if (submitted && password.length < 8) "Mínimo 8 caracteres" else null
    val confirmError = if (submitted && confirm != password) "Las contraseñas no coinciden" else null
    val termsError = submitted && !accepted

    Scaffold(topBar = { AppTopBar(title = "", onBack = onBack) }) { inner ->
        ScreenContainer(Modifier.padding(inner)) {
            Text("Crea tu cuenta", style = MaterialTheme.typography.headlineMedium)
            Text(
                "Empieza a organizar tu biblioteca",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
            Spacer(Modifier.height(24.dp))
            FormField(name, { name = it }, "Nombre", error = nameError)
            FormField(email, { email = it }, "Correo electrónico", error = emailError, keyboardType = KeyboardType.Email)
            PasswordField(password, { password = it }, "Contraseña", error = passError)
            PasswordField(confirm, { confirm = it }, "Confirmar contraseña", error = confirmError)
            Row(
                modifier = Modifier.fillMaxWidth().clickable { accepted = !accepted },
                verticalAlignment = Alignment.CenterVertically
            ) {
                Checkbox(checked = accepted, onCheckedChange = { accepted = it })
                Text(
                    "Acepto los términos y condiciones",
                    style = MaterialTheme.typography.bodyMedium,
                    color = if (termsError) MaterialTheme.colorScheme.error else MaterialTheme.colorScheme.onSurface
                )
            }
            Spacer(Modifier.height(16.dp))
            Button(
                onClick = {
                    submitted = true
                    if (name.isNotBlank() && email.contains("@") && password.length >= 8 &&
                        confirm == password && accepted
                    ) onRegistered(name.trim(), email.trim())
                },
                modifier = Modifier.fillMaxWidth()
            ) { Text("Crear cuenta") }
            Spacer(Modifier.height(8.dp))
            Text("¿Ya tienes una cuenta?", style = MaterialTheme.typography.bodyMedium)
            TextButton(onClick = onBack) { Text("Iniciar sesión") }
        }
    }
}
package com.example.mylibrary.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ExitToApp
import androidx.compose.material3.FilterChip
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.ListItem
import androidx.compose.material3.ListItemDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.mylibrary.model.SortOrder
import com.example.mylibrary.ui.components.AppTopBar
import com.example.mylibrary.ui.components.ScreenContainer

@Composable
fun SettingsScreen(
    userName: String,
    userEmail: String,
    darkTheme: Boolean,
    onDarkThemeChange: (Boolean) -> Unit,
    sortOrder: SortOrder,
    onSortOrderChange: (SortOrder) -> Unit,
    language: String,
    onLanguageChange: (String) -> Unit,
    onBack: () -> Unit,
    onLogout: () -> Unit
) {
    val transparent = ListItemDefaults.colors(containerColor = Color.Transparent)

    Scaffold(topBar = { AppTopBar(title = "Configuración", onBack = onBack) }) { inner ->
        ScreenContainer(Modifier.padding(inner)) {
            Box(
                modifier = Modifier
                    .size(72.dp)
                    .clip(CircleShape)
                    .background(MaterialTheme.colorScheme.secondaryContainer),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    userName.firstOrNull()?.uppercase() ?: "?",
                    style = MaterialTheme.typography.titleLarge
                )
            }
            Spacer(Modifier.height(8.dp))
            Text(userName, style = MaterialTheme.typography.titleMedium)
            Text(
                userEmail, style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )

            SectionTitle("PREFERENCIAS")
            ListItem(
                headlineContent = { Text("Tema oscuro") },
                supportingContent = { Text("Cambia la apariencia de la app") },
                trailingContent = { Switch(checked = darkTheme, onCheckedChange = onDarkThemeChange) },
                colors = transparent
            )
            HorizontalDivider()
            ListItem(
                headlineContent = { Text("Orden por defecto") },
                supportingContent = { Text("Cómo se ordena tu biblioteca") },
                colors = transparent
            )
            Row(
                Modifier.fillMaxWidth().horizontalScroll(rememberScrollState()).padding(horizontal = 16.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                SortOrder.values().forEach { o ->
                    FilterChip(
                        selected = sortOrder == o,
                        onClick = { onSortOrderChange(o) },
                        label = { Text(o.label) }
                    )
                }
            }
            Spacer(Modifier.height(8.dp))
            HorizontalDivider()
            ListItem(
                headlineContent = { Text("Idioma") },
                supportingContent = { Text("Idioma de la aplicación") },
                colors = transparent
            )
            Row(
                Modifier.fillMaxWidth().horizontalScroll(rememberScrollState()).padding(horizontal = 16.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                listOf("Español", "English").forEach { l ->
                    FilterChip(
                        selected = language == l,
                        onClick = { onLanguageChange(l) },
                        label = { Text(l) }
                    )
                }
            }

            SectionTitle("CUENTA")
            ListItem(
                headlineContent = { Text("Cerrar sesión") },
                leadingContent = { Icon(Icons.AutoMirrored.Filled.ExitToApp, contentDescription = null) },
                modifier = Modifier.clickable(onClick = onLogout),
                colors = transparent
            )
        }
    }
}

@Composable
private fun SectionTitle(text: String) {
    Text(
        text = text,
        style = MaterialTheme.typography.labelMedium,
        color = MaterialTheme.colorScheme.primary,
        modifier = Modifier.fillMaxWidth().padding(top = 24.dp, bottom = 4.dp)
    )
}
package com.example.mylibrary.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AutoStories
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.mylibrary.ui.theme.BrandGreen
import com.example.mylibrary.ui.theme.Cream
import com.example.mylibrary.ui.theme.Gold
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(onFinished: () -> Unit) {
    val currentOnFinished by rememberUpdatedState(onFinished)
    LaunchedEffect(Unit) {
        delay(2000)
        currentOnFinished()
    }
    Column(
        modifier = Modifier.fillMaxSize().background(BrandGreen),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Icon(Icons.Filled.AutoStories, contentDescription = null, modifier = Modifier.size(88.dp), tint = Gold)
        Spacer(Modifier.height(16.dp))
        Text("MyLibrary", style = MaterialTheme.typography.headlineMedium, color = Cream)
        Spacer(Modifier.height(4.dp))
        Text(
            "Tu biblioteca personal, siempre a mano",
            style = MaterialTheme.typography.bodyMedium,
            color = Cream.copy(alpha = 0.8f)
        )
    }
}

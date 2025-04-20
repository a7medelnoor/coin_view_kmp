package com.a7medelnoor.coin_view_kmp.presentation.screens.composables

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

@Composable
fun ErrorView(
    message: String,
    onRetry: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {



        Text(
            text = message,
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.h6
        )


        Button(onClick = onRetry) {
            Text("Try Again")
        }

        // Add debug details

        Text(
            text = "Debug Details",
            style = MaterialTheme.typography.subtitle1,
            fontWeight = FontWeight.Bold
        )


        Text(
            text = "Error Message: $message",
            style = MaterialTheme.typography.caption,
            fontFamily = FontFamily.Monospace
        )

        // Extract serialization error details if possible
        if (message.contains("serialization") || message.contains("illegal input")) {
            Text(
                text = "Possible serialization error detected. Check your DTO classes against the actual API response.",
                style = MaterialTheme.typography.caption,
                color = Color.Red,
                fontFamily = FontFamily.Monospace
            )
        }
    }
}
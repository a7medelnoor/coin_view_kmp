package com.a7medelnoor.coin_view_kmp.presentation.screens.composables

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import com.a7medelnoor.coin_view_kmp.util.Dimensions
import com.a7medelnoor.coin_view_kmp.util.NumberFormatter
import com.a7medelnoor.coin_view_kmp.util.StringResources.PRICE_DECREASED_DESCRIPTION
import com.a7medelnoor.coin_view_kmp.util.StringResources.PRICE_INCREASED_DESCRIPTION
import kotlin.math.abs

@Composable
fun PriceChangeIndicator(
    priceChange: Double,
    modifier: Modifier = Modifier
) {
    val isPositive = priceChange >= 0
    val backgroundColor = if (isPositive) {
        Color(0xFF1B5E20).copy(alpha = Dimensions.alpha_large)
    } else {
        Color(0xFFB71C1C).copy(alpha = Dimensions.alpha_large)
    }
    val textColor = if (isPositive) {
        Color(0xFF1B5E20)
    } else {
        Color(0xFFB71C1C)
    }

    Surface(
        color = backgroundColor,
        shape = MaterialTheme.shapes.small,
        modifier = modifier
    ) {
        Row(
            modifier = Modifier.padding(horizontal = Dimensions.spacing_8, vertical = Dimensions.spacing_4),
            horizontalArrangement = Arrangement.spacedBy(Dimensions.spacing_4),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = if (isPositive) {
                    Icons.Default.KeyboardArrowUp
                } else {
                    Icons.Default.KeyboardArrowDown
                },
                contentDescription = if (isPositive) PRICE_INCREASED_DESCRIPTION else PRICE_DECREASED_DESCRIPTION,
                tint = textColor,
                modifier = Modifier.size(Dimensions.spacing_16)
            )

            Text(
                text = NumberFormatter.formatPercentage(abs(priceChange)),
                style = MaterialTheme.typography.caption,
                color = textColor,
                fontWeight = FontWeight.Medium
            )
        }
    }
}
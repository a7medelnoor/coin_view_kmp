package com.a7medelnoor.coin_view_kmp.presentation.screens.composables

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.Stroke
import com.a7medelnoor.coin_view_kmp.domain.model.PricePoint
import com.a7medelnoor.coin_view_kmp.util.Dimensions


@Composable
fun PriceHistoryChart(
    priceHistory: List<PricePoint>,
    modifier: Modifier = Modifier
) {
    if (priceHistory.isEmpty()) return

    val lineColor = MaterialTheme.colors.primary
    
    Canvas(
        modifier = modifier
            .fillMaxWidth()
            .height(Dimensions.chart_height)
            .padding(vertical = Dimensions.spacing_8)
    ) {
        val prices = priceHistory.map { it.price }
        val minPrice = prices.minOrNull() ?: 0.0
        val maxPrice = prices.maxOrNull() ?: 0.0
        val priceRange = maxPrice - minPrice

        val path = Path()
        val points = priceHistory.mapIndexed { index, point ->
            Offset(
                x = size.width * (index.toFloat() / (priceHistory.size - 1)),
                y = size.height * (Dimensions.alpha_large_1 - ((point.price - minPrice) / priceRange).toFloat())
            )
        }

        // Draw the line
        if (points.isNotEmpty()) {
            path.moveTo(points.first().x, points.first().y)
            points.drop(1).forEach { point ->
                path.lineTo(point.x, point.y)
            }

            drawPath(
                path = path,
                color = lineColor,
                style = Stroke(width = Dimensions.spacing_2.toPx())
            )
        }
    }
} 
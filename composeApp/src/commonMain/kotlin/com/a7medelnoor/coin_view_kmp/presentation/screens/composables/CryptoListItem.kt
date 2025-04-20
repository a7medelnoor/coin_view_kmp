package com.a7medelnoor.coin_view_kmp.presentation.screens.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import com.skydoves.landscapist.coil3.CoilImage
import com.skydoves.landscapist.ImageOptions
import com.a7medelnoor.coin_view_kmp.domain.model.Crypto
import com.a7medelnoor.coin_view_kmp.util.Dimensions
import com.a7medelnoor.coin_view_kmp.util.Dimensions.alpha_disabled
import com.a7medelnoor.coin_view_kmp.util.Dimensions.crypto_image_size
import com.a7medelnoor.coin_view_kmp.util.Dimensions.icon_size_medium
import com.a7medelnoor.coin_view_kmp.util.NumberFormatter


@Composable
fun CryptoListItem(
    crypto: Crypto,
    onItemClick: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = Dimensions.spacing_16, vertical = Dimensions.spacing_8)
            .clickable { 
                println("Clicked on crypto:")
                println("ID: ${crypto.id}")
                println("Name: ${crypto.name}")
                println("Symbol: ${crypto.symbol}")
                println("Price: ${NumberFormatter.formatPrice(crypto.price)}")
                println("24h Change: ${crypto.price}%")
                onItemClick(crypto.id)
            },
        elevation = Dimensions.spacing_2,
        backgroundColor = MaterialTheme.colors.surface,
        shape = MaterialTheme.shapes.medium
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(Dimensions.spacing_16),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Left section - Image, Rank and Name
            Row(
                modifier = Modifier.weight(1f),
                horizontalArrangement = Arrangement.spacedBy(Dimensions.spacing_12),
                verticalAlignment = Alignment.CenterVertically
            ) {
                // Crypto Image
                Box(
                    modifier = Modifier
                        .size(crypto_image_size)
                        .background(
                            color = MaterialTheme.colors.surface,
                            shape = CircleShape
                        ),
                    contentAlignment = Alignment.Center
                ) {
                    CoilImage(
                        imageModel = { crypto.imageUrl },
                        imageOptions = ImageOptions(
                            contentScale = ContentScale.Fit,
                            alignment = Alignment.Center
                        ),
                        loading = {
                            CircularProgressIndicator(
                                modifier = Modifier.size(icon_size_medium),
                                strokeWidth = Dimensions.spacing_2
                            )
                        },
                        failure = {
                            Text(
                                text = crypto.symbol.first().toString(),
                                style = MaterialTheme.typography.subtitle1,
                                color = MaterialTheme.colors.primary
                            )
                        },
                        modifier = Modifier.fillMaxSize()
                    )
                }

                // Crypto info (symbol, name)
                Column {
                    Text(
                        text = crypto.name,
                        style = MaterialTheme.typography.subtitle1,
                        fontWeight = FontWeight.Bold,
                        maxLines = Dimensions.max_line,
                        overflow = TextOverflow.Ellipsis
                    )
                    Text(
                        text = crypto.symbol.uppercase(),
                        style = MaterialTheme.typography.caption,
                        color = MaterialTheme.colors.onSurface.copy(alpha = alpha_disabled)
                    )
                }
            }

            // Right section - Price and Change
            Column(
                horizontalAlignment = Alignment.End
            ) {
                Text(
                    text = NumberFormatter.formatPrice(crypto.price),
                    style = MaterialTheme.typography.subtitle1,
                    fontWeight = FontWeight.Bold
                )

                PriceChangeIndicator(
                    priceChange = crypto.price,
                    modifier = Modifier.padding(top = Dimensions.spacing_4)
                )
            }
        }
    }
}


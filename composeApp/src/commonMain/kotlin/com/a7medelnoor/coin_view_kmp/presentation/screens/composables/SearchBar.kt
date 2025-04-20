package com.a7medelnoor.coin_view_kmp.presentation.screens.composables

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.a7medelnoor.coin_view_kmp.util.Dimensions
import com.a7medelnoor.coin_view_kmp.util.StringResources.SEARCH_ICON_DESCRIPTION
import com.a7medelnoor.coin_view_kmp.util.StringResources.SEARCH_PLACEHOLDER

@Composable
 fun SearchBar(
    query: String,
    onQueryChange: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    Surface(
        modifier = modifier,
        shape = MaterialTheme.shapes.medium,
        color = MaterialTheme.colors.surface,
        border = BorderStroke(
            width = Dimensions.border_width,
            color = MaterialTheme.colors.onSurface.copy(alpha = Dimensions.alpha_medium_012)
        )
    ) {
        TextField(
            value = query,
            onValueChange = onQueryChange,
            modifier = Modifier.fillMaxWidth(),
            placeholder = {
                Text(
                    SEARCH_PLACEHOLDER,
                    style = MaterialTheme.typography.body2
                )
            },
            leadingIcon = {
                Icon(
                    imageVector = Icons.Default.Search,
                    contentDescription = SEARCH_ICON_DESCRIPTION,
                    tint = MaterialTheme.colors.onSurface.copy(alpha = Dimensions.alpha_disabled),
                    modifier = Modifier.size(Dimensions.loading_indicator_size)
                )
            },
            colors = TextFieldDefaults.textFieldColors(
                backgroundColor = Color.Transparent,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                cursorColor = MaterialTheme.colors.primary
            ),
            textStyle = MaterialTheme.typography.body2,
            singleLine = true
        )
    }
}
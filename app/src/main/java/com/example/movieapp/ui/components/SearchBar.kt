package com.example.movieapp.ui.components

import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.movieapp.R

@Composable
fun SearchBar(
    modifier: Modifier = Modifier,
    search: String = "",
    onSearch: (String) -> Unit
) {
    TextField(
        value = search,
        onValueChange = { onSearch(it) },
        modifier = modifier
            .padding(
                horizontal = dimensionResource(id = R.dimen.dp_24),
                vertical = dimensionResource(id = R.dimen.dp_12)
            )
            .width(dimensionResource(id = R.dimen.dp_327))
            .height(dimensionResource(id = R.dimen.dp_50)),
        placeholder = { Text(text = "Search", color = Color.Gray) },
        trailingIcon = {
            Icon(
                painter = painterResource(id = R.drawable.baseline_search_24),
                contentDescription = null,
                tint = Color.Gray
            )
        },
        shape = RoundedCornerShape(dimensionResource(id = R.dimen.dp_16)),
        colors = TextFieldDefaults.colors(
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            disabledIndicatorColor = Color.Transparent
        )
    )
}

@Preview
@Composable
private fun SearchBarPreview() {
    SearchBar(onSearch = {})
}
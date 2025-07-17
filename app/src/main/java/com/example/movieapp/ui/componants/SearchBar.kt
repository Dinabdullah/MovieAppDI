package com.example.movieapp.ui.componants

import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
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
            .padding(horizontal = 24.dp, vertical = 12.dp)
            .width(327.dp)
            .height(50.dp),
        placeholder = { Text(text = "Search", color = Color.Gray) },
        trailingIcon = {
            Icon(
                painter = painterResource(id = R.drawable.baseline_search_24),
                contentDescription = null,
                tint = Color.Gray
            )
        },
        shape = RoundedCornerShape(16.dp),
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
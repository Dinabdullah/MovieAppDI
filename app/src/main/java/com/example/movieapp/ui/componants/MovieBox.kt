package com.example.movieapp.ui.componants

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage

@Composable
fun MovieBox(
    img: String,
    title: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .padding(8.dp)
            .clickable { onClick() }
    ) {
        AsyncImage(
            model = img,
            contentDescription = null,
            modifier = Modifier.fillMaxSize()
        )
        Text(
            text = title,
            modifier = Modifier
                .shadow(4.dp)
                .clip(shape = RoundedCornerShape(4.dp))
                .padding(vertical = 12.dp)
                .width(144.dp),
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.Bold,
            maxLines = 1,
        )
    }
}


@Preview
@Composable
private fun MoviePrev() {
    MovieBox(
        img = "https://via.placeholder.com/150",
        onClick = {},
        title = "Title"
    )
}

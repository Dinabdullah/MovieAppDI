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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.movieapp.R
import com.example.movieapp.data.model.Movie

@Composable
fun MovieBox(
    movie: Movie,
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
            model = movie.posterPath?.getFullPosterUrl(),
            contentDescription = null,
            modifier = Modifier.fillMaxSize(),
            placeholder = painterResource(id = R.drawable.posterplaceholder),
        )
        Text(
            text = movie.title ?: "Unknown Title",
            modifier = Modifier
                .background(color = Color.Gray.copy(alpha = 0.5f))
                .shadow(dimensionResource(id = R.dimen.dp_4))
                .clip(shape = RoundedCornerShape(dimensionResource(id = R.dimen.dp_4)))
                .padding(vertical = dimensionResource(id = R.dimen.dp_12))
                .width(dimensionResource(id = R.dimen.dp_144)),
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.Bold,
            overflow = TextOverflow.Ellipsis,
            maxLines = 1,
        )
    }
}


fun String?.getFullPosterUrl(): String {
    return "https://image.tmdb.org/t/p/w500$this"
}
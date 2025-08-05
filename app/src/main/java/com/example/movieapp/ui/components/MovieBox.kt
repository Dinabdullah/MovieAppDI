package com.example.movieapp.ui.components

import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
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
import com.example.movieapp.domain.model.Movie

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun SharedTransitionScope.MovieBox(
    movie: Movie,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    animatedVisibilityScope: AnimatedVisibilityScope
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
            modifier = Modifier
                .sharedElement(
                    state = rememberSharedContentState(key = movie.id.toString()),
                    animatedVisibilityScope = animatedVisibilityScope,
                ),
            placeholder = painterResource(id = R.drawable.posterplaceholder),
        )

        Text(
            text = movie.title ?: "Unknown Title",
            modifier = Modifier
                .background(color = Color.Gray.copy(alpha = 0.5f))
                .shadow(dimensionResource(id = R.dimen.dp_4))
                // .clip(shape = RoundedCornerShape(dimensionResource(id = R.dimen.dp_4)))
                // .padding(vertical = dimensionResource(id = R.dimen.dp_12))
                .fillMaxWidth()
                .sharedElement(
                    state = rememberSharedContentState(key = movie.id.toString() + "title"),
                    animatedVisibilityScope = animatedVisibilityScope,
                ),
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
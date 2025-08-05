package com.example.movieapp.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.movieapp.R

@Composable
fun BottomBar(
    homeClicked: () -> Unit,
    searchClicked: () -> Unit,
    watchListClicked: () -> Unit
) {
    Row(
        modifier = Modifier
            .padding(bottom = dimensionResource(id = R.dimen.dp_24))
            .width(dimensionResource(id = R.dimen.dp_375))
            .height(dimensionResource(id = R.dimen.dp_78)),
        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalAlignment = Alignment.CenterVertically,

        ) {
        Icon(
            painter = painterResource(id = R.drawable.baseline_home_24),
            contentDescription = null,
            modifier = Modifier.clickable { homeClicked() })
        Icon(
            painter = painterResource(id = R.drawable.baseline_search_24),
            contentDescription = null,
            modifier = Modifier.clickable { searchClicked() }
        )
        Icon(
            painter = painterResource(id = R.drawable.outline_favorite_24),
            contentDescription = null,
            modifier = Modifier.clickable { watchListClicked() }
        )
    }
}


@Preview
@Composable
private fun BottomBarPreview() {
    BottomBar(homeClicked = {}, searchClicked = {}, watchListClicked = {})
}
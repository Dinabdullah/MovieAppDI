package com.example.movieapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import com.example.movieapp.ui.screens.HomeScreen
import com.example.movieapp.ui.theme.MovieAppTheme
import com.example.movieapp.viewmodel.HomeScreenViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MovieAppTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { _ ->
                    val viewModel: HomeScreenViewModel by viewModel()
                    val state by viewModel.uiState.collectAsState()
                    HomeScreen(
                        onEvent = viewModel::onEvent,
                        state = state
                    )
                }
            }
        }
    }
}

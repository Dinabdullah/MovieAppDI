package com.example.movieapp.di

import com.example.movieapp.ui.screens.details.DetailsViewModel
import com.example.movieapp.ui.screens.home.HomeScreenViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel<HomeScreenViewModel> { HomeScreenViewModel(get()) }
    viewModel<DetailsViewModel> { DetailsViewModel(get()) }
}
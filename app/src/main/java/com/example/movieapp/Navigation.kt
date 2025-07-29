package com.example.movieapp
//
//import androidx.compose.runtime.Composable
//import androidx.compose.ui.Modifier
//import androidx.hilt.navigation.compose.hiltViewModel
//import androidx.navigation.NavHostController
//import androidx.navigation.compose.NavHost
//import androidx.navigation.compose.composable
//import com.example.movieapp.ui.screens.HomeScreen
//import com.example.movieapp.viewmodel.HomeScreenViewModel
//
//sealed class Screen(val route: String) {
//    object Home : Screen("home")
//}
//
//@Composable
//fun AppNavHost(navController: NavHostController, modifier: Modifier = Modifier) {
//    NavHost(
//        navController = navController,
//        startDestination = Screen.Home.route,
//        modifier = modifier
//    ) {
//        composable(Screen.Home.route) {
//            val viewModel: HomeScreenViewModel = hiltViewModel()
//            HomeScreen(
//                navController = navController,
//                onEvent = viewModel::onEvent,
//            )
//        }
//
//
//    }
//
//}
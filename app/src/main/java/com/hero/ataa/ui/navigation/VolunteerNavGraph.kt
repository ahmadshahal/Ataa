package com.hero.ataa.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

@Composable
fun VolunteerNavGraph(
    // TODO: InjectViewModel.
    outerNavController: NavController,
) {
    val innerNavController = rememberNavController()
    NavHost(navController = innerNavController, startDestination = Screen.VolunteerScreenOne.route) {
        composable(route = Screen.VolunteerScreenOne.route) {

        }
        composable(route = Screen.VolunteerScreenTwo.route) {

        }
        composable(route = Screen.VolunteerScreenThree.route) {

        }
    }
}
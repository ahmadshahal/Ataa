package com.hero.ataa.ui.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.hero.ataa.ui.screens.volunteer_screen.VolunteerScreenOne
import com.hero.ataa.ui.screens.volunteer_screen.VolunteerScreenThree
import com.hero.ataa.ui.screens.volunteer_screen.VolunteerScreenTwo
import com.hero.ataa.ui.screens.volunteer_screen.VolunteerViewModel

@Composable
fun VolunteerNavGraph(
    viewModel: VolunteerViewModel = hiltViewModel(),
    outerNavController: NavController,
) {
    val innerNavController = rememberNavController()
    NavHost(navController = innerNavController, startDestination = Screen.VolunteerScreenOne.route) {
        composable(route = Screen.VolunteerScreenOne.route) {
            VolunteerScreenOne(
                outerNavController = outerNavController,
                innerNavController = innerNavController,
                viewModel = viewModel
            )
        }
        composable(route = Screen.VolunteerScreenTwo.route) {
            VolunteerScreenTwo(innerNavController = innerNavController, viewModel = viewModel)
        }
        composable(route = Screen.VolunteerScreenThree.route) {
            VolunteerScreenThree(
                innerNavController = innerNavController,
                outerNavController = outerNavController,
                viewModel = viewModel
            )
        }
    }
}
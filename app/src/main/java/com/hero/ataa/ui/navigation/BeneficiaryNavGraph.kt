package com.hero.ataa.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

@Composable
fun BeneficiaryNavGraph(
    // TODO: InjectViewModel.
    outerNavController: NavController,
) {
    val innerNavController = rememberNavController()
    NavHost(navController = innerNavController, startDestination = Screen.BeneficiaryScreenOne.route) {
        composable(route = Screen.BeneficiaryScreenOne.route) {

        }
        composable(route = Screen.BeneficiaryScreenTwo.route) {

        }
        composable(route = Screen.BeneficiaryScreenThree.route) {

        }
    }
}
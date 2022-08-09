package com.hero.ataa.ui.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.hero.ataa.ui.screens.beneficiary_screen.BeneficiaryScreenOne
import com.hero.ataa.ui.screens.beneficiary_screen.BeneficiaryScreenTwo
import com.hero.ataa.ui.screens.beneficiary_screen.BeneficiaryViewModel

@Composable
fun BeneficiaryNavGraph(
    viewModel: BeneficiaryViewModel = hiltViewModel(),
    outerNavController: NavController,
) {
    val innerNavController = rememberNavController()
    NavHost(navController = innerNavController, startDestination = Screen.BeneficiaryScreenOne.route) {
        composable(route = Screen.BeneficiaryScreenOne.route) {
            BeneficiaryScreenOne(
                outerNavController = outerNavController,
                innerNavController = innerNavController,
                viewModel = viewModel,
            )
        }
        composable(route = Screen.BeneficiaryScreenTwo.route) {
            BeneficiaryScreenTwo(innerNavController = innerNavController, viewModel = viewModel)
        }
        composable(route = Screen.BeneficiaryScreenThree.route) {

        }
    }
}
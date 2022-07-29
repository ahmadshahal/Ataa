package com.hero.ataa.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.hero.ataa.domain.models.Project
import com.hero.ataa.shared.Constants
import com.hero.ataa.ui.screens.edit_name_screen.EditNameScreen
import com.hero.ataa.ui.screens.edit_password_screen.EditPasswordScreen
import com.hero.ataa.ui.screens.edit_profile_screen.EditProfileScreen
import com.hero.ataa.ui.screens.home_screen.HomeScreen
import com.hero.ataa.ui.screens.language_screen.LanguageScreen
import com.hero.ataa.ui.screens.login_screen.LoginScreen
import com.hero.ataa.ui.screens.miskeen_project_screen.MiskeenProjectScreen
import com.hero.ataa.ui.screens.payment_screen.PaymentScreen
import com.hero.ataa.ui.screens.profile_screen.ProfileScreen
import com.hero.ataa.ui.screens.project_screen.ProjectScreen
import com.hero.ataa.ui.screens.projects_screen.ProjectsScreen
import com.hero.ataa.ui.screens.receipts_screen.ReceiptsScreen
import com.hero.ataa.ui.screens.register_screen.RegisterScreen
import com.hero.ataa.ui.screens.sacrifice_project_screen.SacrificeProjectScreen
import com.hero.ataa.ui.screens.sadaka_project_screen.SadakaProjectScreen
import com.hero.ataa.ui.screens.search_screen.SearchScreen
import com.hero.ataa.ui.screens.settings_screen.SettingsScreen
import com.hero.ataa.ui.screens.verification_screen.VerificationScreen
import com.hero.ataa.ui.screens.volunteer_screen.VolunteerScreen
import com.hero.ataa.ui.screens.zakat_project_screen.ZakatProjectScreen

@Composable
fun NavGraph() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Screen.LoginScreen.route) {
        composable(route = Screen.LoginScreen.route) {
            LoginScreen(navController = navController)
        }
        composable(route = Screen.RegisterScreen.route) {
            RegisterScreen(navController = navController)
        }
        composable(route = Screen.HomeScreen.route) {
            HomeScreen(navController = navController)
        }
        composable(
            route = Screen.ProjectsScreen.route + "/{${Constants.NavArgs.CATEGORY_KEY}}/{${Constants.NavArgs.CATEGORY_API_KEY_KEY}}",
            arguments = listOf(
                navArgument(Constants.NavArgs.CATEGORY_KEY) {
                    this.nullable = false
                    this.type = NavType.StringType
                },
                navArgument(Constants.NavArgs.CATEGORY_API_KEY_KEY) {
                    this.nullable = false
                    this.type = NavType.StringType
                },
            )
        ) { navBackStack ->
            ProjectsScreen(
                navController = navController,
                category = navBackStack.arguments!!.getString(Constants.NavArgs.CATEGORY_KEY)!!
            )
        }
        composable(route = Screen.ProjectScreen.route) { _ ->
            val project: Project? =
                navController.previousBackStackEntry?.savedStateHandle?.get<Project>(Constants.NavArgs.PROJECT_KEY)
            project?.let { it ->
                ProjectScreen(project = it, navController = navController)
            }
        }
        composable(route = Screen.VolunteerScreen.route) {
            VolunteerScreen(navController = navController)
        }
        composable(
            route = Screen.LanguageScreen.route,
        ) {
            LanguageScreen(navController = navController)
        }
        composable(
            route = Screen.SadakaProjectScreen.route,
        ) {
            SadakaProjectScreen(navController = navController)
        }
        composable(
            route = Screen.MiskeenProjectScreen.route,
        ) {
            MiskeenProjectScreen(navController = navController)
        }
        composable(
            route = Screen.SacrificeProjectScreen.route,
        ) {
            SacrificeProjectScreen(navController = navController)
        }
        composable(
            route = Screen.SettingsScreen.route,
        ) {
            SettingsScreen(navController = navController)
        }
        composable(
            route = Screen.VerificationScreen.route + "/{${Constants.NavArgs.EMAIL_KEY}}",
            arguments = listOf(
                navArgument(Constants.NavArgs.EMAIL_KEY) {
                    this.nullable = false
                    this.type = NavType.StringType
                }
            )
        ) { navBackStack ->
            val email = navBackStack.arguments!!.getString(Constants.NavArgs.EMAIL_KEY)!!
            VerificationScreen(navController = navController, email = email)
        }
        composable(route = Screen.ProfileScreen.route) {
            ProfileScreen(navController = navController)
        }
        composable(route = Screen.EditProfileScreen.route) {
            EditProfileScreen(navController = navController)
        }
        composable(route = Screen.ReceiptsScreen.route) {
            ReceiptsScreen(navController = navController)
        }
        composable(route = Screen.ZakatProjectScreen.route) {
            ZakatProjectScreen(navController = navController)
        }
        composable(
            route = Screen.PaymentScreen.route + "/{${Constants.NavArgs.DONATION_VALUE_KEY}}/{${Constants.NavArgs.PROJECT_ID_KEY}}",
            arguments = listOf(
                navArgument(Constants.NavArgs.DONATION_VALUE_KEY) {
                    this.nullable = false
                    this.type = NavType.StringType
                },
                navArgument(Constants.NavArgs.PROJECT_ID_KEY) {
                    this.nullable = false
                    this.type = NavType.StringType
                },
            )
        ) {
            PaymentScreen(navController = navController)
        }
        composable(route = Screen.EditNameScreen.route) {
            EditNameScreen(navController = navController)
        }
        composable(route = Screen.EditPasswordScreen.route) {
            EditPasswordScreen(navController = navController)
        }
        composable(route = Screen.SearchScreen.route) {
            SearchScreen(navController = navController)
        }
    }
}
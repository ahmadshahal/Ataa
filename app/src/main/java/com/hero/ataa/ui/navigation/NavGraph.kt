package com.hero.ataa.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.hero.ataa.domain.models.Project
import com.hero.ataa.shared.Constants
import com.hero.ataa.ui.screens.home_screen.HomeScreen
import com.hero.ataa.ui.screens.language_screen.LanguageScreen
import com.hero.ataa.ui.screens.login_screen.LoginScreen
import com.hero.ataa.ui.screens.project_screen.ProjectScreen
import com.hero.ataa.ui.screens.projects_screen.ProjectsScreen
import com.hero.ataa.ui.screens.register_screen.RegisterScreen
import com.hero.ataa.ui.screens.volunteer_screen.VolunteerScreen

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
            val project: Project? = navController.previousBackStackEntry?.savedStateHandle?.get<Project>(Constants.NavArgs.PROJECT_KEY)
            project?.let { it ->
                ProjectScreen(project = it, navController = navController)
            }
        }
        composable(route = Screen.VolunteerScreen.route) {
            VolunteerScreen(navController = navController)
        }
        composable(route = Screen.LanguageScreen.route) {
            LanguageScreen(navController = navController)
        }
    }
}
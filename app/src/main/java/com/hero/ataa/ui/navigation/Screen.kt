package com.hero.ataa.ui.navigation

sealed class Screen(val route: String) {
    object LoginScreen : Screen("/login_screen")
    object RegisterScreen : Screen("/register_screen")
    object HomeScreen : Screen("/home_screen")
    object ProjectsScreen : Screen("/projects_screen")
    object ProjectScreen : Screen("/project_screen")
    object VolunteerScreen : Screen("/volunteer_screen")
    object LanguageScreen : Screen("/language_screen")
    object SadakaProjectScreen : Screen("/sadaka_project_screen")
    object MiskeenProjectScreen : Screen("/miskeen_project_screen")
    object SacrificeProjectScreen : Screen("/sacrifice_project_screen")
    object SettingsScreen : Screen("/settings_screen")
    object VerificationScreen : Screen("/verification_screen")
    object ProfileScreen : Screen("/profile_screen")
    object EditProfileScreen : Screen("/edit_profile_screen")
    object ReceiptsScreen : Screen("/receipts_screen")
    object ZakatProjectScreen : Screen("/zakat_screen")
    object PaymentScreen : Screen("/payment_screen")
}
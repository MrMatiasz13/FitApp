package pl.mrmatiasz.fitapp.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import pl.mrmatiasz.fitapp.presentation.screens.auth.login_screen.LoginScreen
import pl.mrmatiasz.fitapp.presentation.screens.auth.registration_screen.RegistrationScreen

@Composable
fun Navigation(navHostController: NavHostController) {
    NavHost(
        navController = navHostController,
        startDestination = RegistrationScreenRoute
    ) {
        composable<RegistrationScreenRoute> {
            RegistrationScreen(navHostController)
        }

        composable<LoginScreenRoute> {
            LoginScreen(navHostController)
        }
    }
}
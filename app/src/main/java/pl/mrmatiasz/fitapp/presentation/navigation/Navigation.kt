package pl.mrmatiasz.fitapp.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import pl.mrmatiasz.fitapp.presentation.screens.auth.login_screen.LoginScreen
import pl.mrmatiasz.fitapp.presentation.screens.auth.registration_screen.RegistrationScreen
import pl.mrmatiasz.fitapp.presentation.screens.calorie_counter_screen.CalorieCounterScreen
import pl.mrmatiasz.fitapp.presentation.screens.dashboard_screen.DashboardScreen

@Composable
fun Navigation(navHostController: NavHostController) {
    NavHost(
        navController = navHostController,
        startDestination = LoginScreenRoute
    ) {
        composable<RegistrationScreenRoute> {
            RegistrationScreen(navHostController)
        }

        composable<LoginScreenRoute> {
            LoginScreen(navHostController)
        }

        composable<DashboardScreenRoute> {
            DashboardScreen(navHostController)
        }

        composable<CalorieCounterScreenRoute> {
            CalorieCounterScreen()
        }
    }
}
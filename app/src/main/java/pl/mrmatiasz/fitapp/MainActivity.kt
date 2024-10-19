package pl.mrmatiasz.fitapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.navigation.compose.rememberNavController
import dagger.hilt.android.AndroidEntryPoint
import pl.mrmatiasz.fitapp.presentation.navigation.Navigation
import pl.mrmatiasz.fitapp.presentation.ui.theme.FitAppTheme

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val navHostController = rememberNavController()

            FitAppTheme(darkTheme = true, dynamicColor = false) {
                Navigation(navHostController)
            }
        }
    }
}
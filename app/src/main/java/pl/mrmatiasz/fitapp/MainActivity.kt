package pl.mrmatiasz.fitapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import pl.mrmatiasz.fitapp.presentation.screens.registration_screen.RegistrationScreen
import pl.mrmatiasz.fitapp.presentation.ui.theme.FitAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            FitAppTheme(darkTheme = true, dynamicColor = false) {
                RegistrationScreen()
            }
        }
    }
}
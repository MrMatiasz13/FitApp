package pl.mrmatiasz.fitapp.presentation.screens.registration_screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import pl.mrmatiasz.fitapp.presentation.ui.theme.FitAppTheme

@Composable
fun RegistrationScreen() {
    FitAppTheme(darkTheme = true, dynamicColor = false) {
        Surface(
            color = MaterialTheme.colorScheme.background
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.fillMaxSize()
            ) {
                Text(
                    text = "Hello World!"
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun RegistrationScreenPreview() {
    RegistrationScreen()
}
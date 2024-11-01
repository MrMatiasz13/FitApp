package pl.mrmatiasz.fitapp.presentation.screens.calorie_counter_screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import pl.mrmatiasz.fitapp.presentation.components.CalorieBar
import pl.mrmatiasz.fitapp.presentation.components.MacroTrackingBox

@Composable
fun CalorieCounterScreen() {
    Surface(
        color = MaterialTheme.colorScheme.background
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top,
            modifier = Modifier
                .fillMaxSize()
                .padding(8.dp)
        ) {
            MacroTrackingBox()
        }
    }
}

@Preview
@Composable
private fun CalorieCounterScreenPreview() {
    CalorieCounterScreen()
}
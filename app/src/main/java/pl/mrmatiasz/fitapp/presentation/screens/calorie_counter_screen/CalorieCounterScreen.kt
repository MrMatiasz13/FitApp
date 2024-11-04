package pl.mrmatiasz.fitapp.presentation.screens.calorie_counter_screen

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.ArrowDropUp
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import pl.mrmatiasz.fitapp.presentation.components.MacroTrackingBox
import pl.mrmatiasz.fitapp.presentation.ui.theme.FitAppTheme

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
                .padding(vertical = 32.dp, horizontal = 4.dp)
        ) {
            Text(
                text = "Daily Macro's",
                fontSize = 32.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .align(Alignment.Start)
                    .padding(8.dp)
            )
            MacroTrackingBox()

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = "Meals:",
                fontSize = 32.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .align(Alignment.Start)
                    .padding(horizontal = 8.dp)
            )

            MealEntryDropdown(false)
        }
    }
}

@Composable
fun MealEntryDropdown (
    isExtended: Boolean
) {
    var isDropDownExtended by remember { mutableStateOf(isExtended) }

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .border(2.dp, Color.Black, RoundedCornerShape(4.dp))
            .clickable {
                isDropDownExtended = !isDropDownExtended
            }
    ) {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = if(!isDropDownExtended) Icons.Filled.ArrowDropUp
                        else Icons.Filled.ArrowDropDown,
                    contentDescription = "Arrow Drop Up/Down",
                    modifier = Modifier
                        .size(32.dp)
                )

                Text(
                    text = "Breakfast",
                    fontSize = 24.sp,
                    fontWeight = FontWeight.SemiBold
                )
            }

            Button(
                onClick = { /*TODO*/ },
                colors = ButtonDefaults.buttonColors(
                    containerColor = MaterialTheme.colorScheme.tertiary,
                    contentColor = Color.White
                ),
                border = BorderStroke(1.dp, Color.Black)
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        imageVector = Icons.Filled.Add,
                        contentDescription = "Add product",
                    )

                    Text(text = "Add product")
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun CalorieCounterScreenPreview() {
    FitAppTheme(darkTheme = true, dynamicColor = false) {
        MealEntryDropdown(false)
    }
}
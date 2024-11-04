package pl.mrmatiasz.fitapp.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.material.icons.filled.SignalCellularAlt
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import pl.mrmatiasz.fitapp.presentation.ui.theme.FitAppTheme

@Composable
fun MacroTrackingBox() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(245.dp)
            .background(
                color = Color(61, 61, 59),
                shape = RoundedCornerShape(45.dp)
            )
            .border(3.dp, Color.LightGray, RoundedCornerShape(45.dp))
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxSize()
                .padding(8.dp)
        ) {
            CalorieBar(
                maxCalories = 3000,
                currentCalories = 765,
                modifier = Modifier
                    .size(200.dp)
            )

            Column(
                horizontalAlignment = Alignment.End,
                verticalArrangement = Arrangement.Center,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp)
            ) {
                MacroText(text = "90(g) protein")
                Spacer(modifier = Modifier.height(8.dp))

                MacroText(text = "55(g) fat")
                Spacer(modifier = Modifier.height(8.dp))

                MacroText(text = "250(g) carbs")
                Spacer(modifier = Modifier.height(8.dp))

                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center,
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(
                            color = MaterialTheme.colorScheme.tertiary,
                            shape = RoundedCornerShape(10.dp)
                        )
                        .padding(4.dp)
                ) {
                    Icon(
                        imageVector = Icons.Filled.SignalCellularAlt,
                        contentDescription = "Signal Cellular Alt",
                        modifier = Modifier.size(18.dp)
                    )

                    Text(
                        text = "Target cals: 3000",
                        color = Color.White,
                        fontSize = 14.sp
                    )
                }
            }
        }
    }
}

@Composable
fun MacroText(text: String) {
    Text(
        text = text,
        color = Color.White,
        fontSize = 14.sp,
        modifier = Modifier
            .background(
                color = MaterialTheme.colorScheme.tertiary,
                shape = RoundedCornerShape(10.dp)
            )
            .padding(6.dp)
    )
}


@Preview(showBackground = true)
@Composable
private fun MacroTrackingBoxPreview() {
    FitAppTheme(darkTheme = true, dynamicColor = false) {
        MacroTrackingBox()
    }
}
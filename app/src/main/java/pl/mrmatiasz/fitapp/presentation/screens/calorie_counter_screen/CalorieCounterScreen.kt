package pl.mrmatiasz.fitapp.presentation.screens.calorie_counter_screen

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
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
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.ArrowDropUp
import androidx.compose.material.icons.filled.LocalFireDepartment
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
import pl.mrmatiasz.fitapp.data.model.Product
import pl.mrmatiasz.fitapp.presentation.components.MacroTrackingBox
import pl.mrmatiasz.fitapp.presentation.ui.theme.FitAppTheme

val testProductList = listOf(
    Product(
        name = "Apple",
        calories = 95,
        protein = 0,
        fat = 0,
        carbs = 10
    ),

    Product(
        name = "Banana",
        calories = 105,
        protein = 1,
        fat = 0,
        carbs = 15
    )
)

@Composable
fun CalorieCounterScreen() {
    val listOfMeals = listOf(
        "Breakfast",
        "Lunch",
        "Dinner",
        "Snacks",
        "Supper"
    )

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

            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
            ) {
                items(listOfMeals) { meal ->
                    MealEntryDropdown(meal)
                }
            }
        }
    }
}

@Composable
fun MealEntryDropdown(name: String) {
    var isDropDownExtended by remember { mutableStateOf(false) }

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(4.dp)
            .border(2.dp, Color.Black, RoundedCornerShape(4.dp))
            .clickable {
                isDropDownExtended = !isDropDownExtended
            }
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        ) {
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
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
                        text = name,
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

            if(isDropDownExtended) {
                LazyColumn(
                    modifier = Modifier
                        .fillMaxWidth()
                        .heightIn(max = 300.dp)
                        .padding(8.dp)
                ) {
                    items(testProductList) { product ->
                        ProductRow(product)
                        Spacer(modifier = Modifier.height(2.dp))
                    }
                }
            }
        }
    }
}

@Composable
fun ProductRow(product: Product) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier
            .fillMaxWidth()
            .border(2.dp, Color.Black, RoundedCornerShape(16.dp))
            .padding(horizontal = 16.dp, vertical = 8.dp)
    ) {
        Text(
            text = product.name
        )

        Box(
            modifier = Modifier
                .background(Color.Gray, RoundedCornerShape(16.dp))
                .border(2.dp, Color.Black, RoundedCornerShape(16.dp))
                .padding(4.dp)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(horizontal = 4.dp)
            ) {
                // change to theme
                val fireIconColor = Color(242, 125, 12)

                Icon(
                    imageVector = Icons.Filled.LocalFireDepartment,
                    contentDescription = "Local Fire Department",
                    tint = fireIconColor
                )

                Text(text = "${product.calories}")
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun CalorieCounterScreenPreview() {
    FitAppTheme(darkTheme = true, dynamicColor = false) {
        CalorieCounterScreen()
    }
}
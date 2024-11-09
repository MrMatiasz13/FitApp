package pl.mrmatiasz.fitapp.presentation.screens.calorie_counter.add_food_screen

import android.annotation.SuppressLint
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
import androidx.compose.material.icons.filled.ChevronLeft
import androidx.compose.material.icons.filled.LocalFireDepartment
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Button
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
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
import androidx.navigation.NavController
import pl.mrmatiasz.fitapp.data.model.Product
import pl.mrmatiasz.fitapp.presentation.ui.theme.FitAppTheme

private val testList = listOf(
    Product(
        name = "Product 1",
        calories = 100,
        protein = 10,
        fat = 10,
        carbs = 10
    ),
    Product(
        name = "Product 2",
        calories = 200,
        protein = 20,
        fat = 20,
        carbs = 20

    )
)

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun AddFoodScreen(
    navController: NavController?
) {
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { 
                    Text(
                        text = "Add Food",
                        fontSize = 32.sp
                    )
                },
                navigationIcon = {
                    Icon(
                        imageVector = Icons.Filled.ChevronLeft,
                        contentDescription = "Chevron left",
                        modifier = Modifier
                            .size(42.dp)
                            .clickable {
                                navController!!.popBackStack()
                            }
                    )
                }
            )
        }
    ) { paddingValues ->
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            var search by remember { mutableStateOf("") }

            OutlinedTextField(
                value = search,
                onValueChange = { search = it },
                singleLine = true,
                shape = RoundedCornerShape(16.dp),
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Filled.Search,
                        contentDescription = "Search"
                    )
                },
                placeholder = { Text(text = "Search") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            )

            Spacer(modifier = Modifier.height(16.dp))

            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth()
                    .heightIn(max = 500.dp)
                    .padding(8.dp)
            ) {
                items(testList) { product ->
                    SearchListItem(product)
                }
            }

            Button(onClick = { /*TODO*/ }) {
                Text(text = "Add personal product")
            }
        }
    }
}

@Composable
fun SearchListItem(product: Product) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .border(2.dp, Color.Black, RoundedCornerShape(12.dp))
            .padding(8.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Column(
                modifier = Modifier
            ) {
                Text(
                    text = product.name,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.SemiBold
                )

                Text(
                    text = "Protein: ${product.protein}, Fat: ${product.fat}, Carbs: ${product.carbs}",
                    fontSize = 12.sp
                )
            }

            Row(
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .border(2.dp, Color.Black, RoundedCornerShape(12.dp))
                    .padding(horizontal = 8.dp, vertical = 4.dp)
            ) {
                Icon(
                    imageVector = Icons.Filled.LocalFireDepartment,
                    contentDescription = "Fire"
                )

                Text(text = "${product.calories} cals")
            }
        }
    }
    
    Spacer(modifier = Modifier.height(2.dp))
}

@Preview(showBackground = true)
@Composable
private fun AddFoodPreview() {
    FitAppTheme(darkTheme = true, dynamicColor = false) {
        AddFoodScreen(null)
    }
}
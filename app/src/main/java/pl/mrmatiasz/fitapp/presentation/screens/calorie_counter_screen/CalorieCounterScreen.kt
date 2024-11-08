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
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.AlertDialogDefaults
import androidx.compose.material3.BasicAlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
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
import androidx.hilt.navigation.compose.hiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import pl.mrmatiasz.fitapp.data.model.Product
import pl.mrmatiasz.fitapp.presentation.components.MacroTrackingBox
import pl.mrmatiasz.fitapp.presentation.ui.theme.FitAppTheme

val fireIconColor = Color(242, 125, 12)

@Composable
fun CalorieCounterScreen(
    viewModel: CalorieCounterViewModel = hiltViewModel()
) {
    val breakfastList = viewModel.breakfastList
    val snackList = viewModel.snackList
    val lunchList = viewModel.lunchList
    val secondSnackList = viewModel.secondSnackList
    val dinnerList = viewModel.dinnerList

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
                item {
                    MealEntryDropdown(name = "Breakfast", listOfProducts = breakfastList)
                    MealEntryDropdown(name = "Snack I", listOfProducts = snackList)
                    MealEntryDropdown(name = "Lunch", listOfProducts = lunchList)
                    MealEntryDropdown(name = "Snack II", listOfProducts = secondSnackList)
                    MealEntryDropdown(name = "Dinner", listOfProducts = dinnerList)
                }
            }
        }
    }
}

@Composable
fun MealEntryDropdown(
    name: String,
    listOfProducts: MutableStateFlow<List<Product>>
) {
    var isDropDownExtended by remember { mutableStateOf(false) }
    var isDialogOpen by remember { mutableStateOf(false) }

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
                    onClick = { isDialogOpen = true },
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
                    items(listOfProducts.value) { product ->
                        ProductRow(product)
                        Spacer(modifier = Modifier.height(2.dp))
                    }
                }
            }
        }
    }

    if(isDialogOpen) {
        AddProductDialog(
            onCancel = { isDialogOpen = false },
        )
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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddProductDialog(
    onCancel: () -> Unit,
) {
    var searchValue by remember { mutableStateOf("") }

    BasicAlertDialog(
        onDismissRequest = {
            searchValue = ""
            onCancel()
        }
    ) {
        Surface(
            modifier = Modifier
                .fillMaxWidth()
                .padding(24.dp),
            tonalElevation = AlertDialogDefaults.TonalElevation,
            shape = RoundedCornerShape(12.dp)
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .fillMaxWidth()
                    .border(2.dp, Color.Black, RoundedCornerShape(12.dp))
                    .padding(8.dp)
            ) {

                val listOfLastProducts = listOf(
                    Product(
                        name = "Banana",
                        calories = 100,
                        protein = 10,
                        fat = 10,
                        carbs = 10
                    ),

                    Product(
                        name = "Apple",
                        calories = 59,
                        protein = 10,
                        fat = 10,
                        carbs = 10
                    )
                )

                Text(
                    text = "Add Product",
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(8.dp)
                )

                OutlinedTextField(
                    value = searchValue,
                    onValueChange = { searchValue = it },
                    singleLine = true,
                    leadingIcon = {
                        Icon(imageVector = Icons.Filled.Search, contentDescription = "Search")
                    },
                    placeholder = { Text(text = "Search for product") },
                    shape = RoundedCornerShape(16.dp),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp)
                )

                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                ) {
                    Column(
                        modifier = Modifier.padding(horizontal = 8.dp)
                    ) {
                        Text(
                            text = "Last products:"
                        )

                        LazyColumn(
                            modifier = Modifier
                                .fillMaxWidth()
                                .heightIn(max = 200.dp)
                                .border(1.dp, Color.White, RoundedCornerShape(4.dp))
                                .padding(4.dp)
                        ) {
                            items(listOfLastProducts) { product ->
                                Row(
                                    horizontalArrangement = Arrangement.SpaceBetween,
                                    verticalAlignment = Alignment.CenterVertically,
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(horizontal = 4.dp, vertical = 8.dp)
                                        .clickable {
                                            // TODO: Add product to list
                                            searchValue = ""
                                            onCancel()
                                        }
                                ) {
                                    Text(
                                        text = product.name,
                                        fontSize = 18.sp,
                                        fontWeight = FontWeight.SemiBold
                                    )

                                    Box(modifier = Modifier) {
                                        Row {
                                            Icon(
                                                imageVector = Icons.Filled.LocalFireDepartment,
                                                contentDescription = "Local Fire Department",
                                                tint = fireIconColor
                                            )

                                            Text(
                                                text = "${product.calories} cals",
                                                fontSize = 18.sp,
                                                fontWeight = FontWeight.SemiBold
                                            )
                                        }
                                    }
                                }
                                if(product != listOfLastProducts.last()) {
                                    HorizontalDivider()
                                }
                            }
                        }
                    }
                }

                Row(
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    modifier = Modifier
                        .fillMaxWidth()
                ) {
                    Button(
                        onClick = {
                            searchValue = ""
                            onCancel()
                        },
                        modifier = Modifier
                            .weight(1f)
                    ) {
                        Text(text = "Cancel")
                    }

                    Button(
                        onClick = {
                            // TODO: Add product to list
                            searchValue = ""
                            onCancel()
                        },
                        modifier = Modifier
                            .weight(1f)
                    ) {
                        Text(text = "Add")
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun CalorieCounterScreenPreview() {
    FitAppTheme(darkTheme = true, dynamicColor = false) {
        AddProductDialog(onCancel = {})
    }
}
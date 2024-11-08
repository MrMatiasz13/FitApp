package pl.mrmatiasz.fitapp.presentation.screens.calorie_counter_screen

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import pl.mrmatiasz.fitapp.data.model.Product
import javax.inject.Inject

@HiltViewModel
class CalorieCounterViewModel @Inject constructor(): ViewModel() {
    var breakfastList = MutableStateFlow<List<Product>>(emptyList())
    var snackList = MutableStateFlow<List<Product>>(emptyList())
    var lunchList = MutableStateFlow<List<Product>>(emptyList())
    var secondSnackList = MutableStateFlow<List<Product>>(emptyList())
    var dinnerList = MutableStateFlow<List<Product>>(emptyList())

    fun addProductToList(list: MutableStateFlow<List<Product>> ,product: Product) {
        list.value += product
    }
}
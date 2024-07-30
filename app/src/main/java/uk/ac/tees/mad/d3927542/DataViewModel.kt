package uk.ac.tees.mad.d3927542

import android.content.Context
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import uk.ac.tees.mad.d3927542.interfaces.IRestaurantRepo
import javax.inject.Inject

@HiltViewModel
class DataViewModel @Inject constructor(private val repository : IRestaurantRepo): ViewModel() {
    fun deleteAllCartItems() {
        cartData.value = emptyList()
    }

    var categoryPicked = mutableStateOf(Category())
    var currentProduct = mutableStateOf(Product())
    var products = MutableStateFlow(listOf<Product>())
    var categories = MutableStateFlow(listOf<Category>())
    var cartData = mutableStateOf(listOf<Product>())


    init {
        viewModelScope.launch { async { repository.runMigration() }.await() }
        getProducts()
        getCategories()
    }

    fun getProducts(){
        viewModelScope.launch {
            repository.getProducts().collectLatest{
                products.tryEmit(it)
            }
        }
    }

    fun getCategories(){
        viewModelScope.launch {
            repository.getCategories().collectLatest{
                categories.tryEmit(it)
            }
        }
    }
}
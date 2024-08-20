package uk.ac.tees.mad.d3927542

import android.content.Context
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseUser
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import uk.ac.tees.mad.d3927542.Repository.IAuthRepo
import uk.ac.tees.mad.d3927542.interfaces.IRestaurantRepo
import java.util.Properties
import javax.inject.Inject
import javax.mail.Authenticator
import javax.mail.Message
import javax.mail.MessagingException
import javax.mail.PasswordAuthentication
import javax.mail.Session
import javax.mail.Transport
import javax.mail.internet.InternetAddress
import javax.mail.internet.MimeMessage

@HiltViewModel
class DataViewModel @Inject constructor(private val repository : IRestaurantRepo, private val authRepo: IAuthRepo): ViewModel() {
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

    fun getEmail(): String? {
        return authRepo.getCurrentuser()?.email
    }

    fun sendEmail() {
        viewModelScope.launch {
            repository.sendEmail(getEmail())
        }
    }
}
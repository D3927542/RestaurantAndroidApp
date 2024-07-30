package uk.ac.tees.mad.d3927542
import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import kotlinx.coroutines.ExperimentalCoroutinesApi

@OptIn(ExperimentalCoroutinesApi::class)
@Composable
fun Category(dataVM : DataViewModel, navHostController: NavHostController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top,
    ){
        Spacer(modifier = Modifier.height(60.dp))
        Box(Modifier.fillMaxWidth(), contentAlignment = Alignment.CenterStart){
            BackButton({ navHostController.navigateUp() })
        }
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = "Shop by Categories", fontWeight = FontWeight.Bold,
            fontSize = 32.sp, textAlign = TextAlign.Start, modifier = Modifier.fillMaxWidth()
        )
        val cat = dataVM.categories.value
        cat?.let {
            LazyColumn(Modifier.fillMaxSize()) {
                items(items = it){
                    CategoryLargeItem(category = it, dataVM, navHostController) {
                        dataVM.categoryPicked.value = it
                        navHostController.navigate(Route.ProductPerCategory.name)
                    }
                }
            }

        }

    }
}
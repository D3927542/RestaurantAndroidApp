package uk.ac.tees.mad.d3927542
import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import kotlinx.coroutines.ExperimentalCoroutinesApi

@SuppressLint("StateFlowValueCalledInComposition")
@OptIn(ExperimentalCoroutinesApi::class)
@Composable
fun Products(
    dataVM: DataViewModel,
    navHostController: NavHostController,
    sourceFromCategory : Boolean = false
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top,
    ){
        Spacer(modifier = Modifier.height(60.dp))
        Box(Modifier.fillMaxWidth(), contentAlignment = Alignment.CenterStart){
            BackButton({
                navHostController.navigateUp()
            })
        }
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = if(!sourceFromCategory) "Buy here"
            else dataVM.categoryPicked.value.name!!, fontWeight = FontWeight.Bold,
            fontSize = 32.sp, textAlign = TextAlign.Start, modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(16.dp))
        val data = if(!sourceFromCategory) dataVM.products.value
        else dataVM.products.value.filter {
            s -> s.category!!.equals(dataVM.categoryPicked.value.name) }
        data?.let {
            LazyVerticalGrid(
                contentPadding = PaddingValues(horizontal = 8.dp),
                columns = GridCells.Fixed(2),
                horizontalArrangement = Arrangement.SpaceBetween
            ){
                items(items = it){
                    Item(product = it) {
                        dataVM.currentProduct.value = it
                        navHostController.navigate(Route.ProductDetail.name)
                    }
                }
            }
        }
    }
}
package uk.ac.tees.mad.d3927542
import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import kotlinx.coroutines.flow.asStateFlow
import uk.ac.tees.mad.d3927542.ui.theme.appBgColor

@SuppressLint("StateFlowValueCalledInComposition")
@Composable
fun Home(navHostController: NavHostController, dataVM : DataViewModel) {
    val products = dataVM.products.asStateFlow().value
    val categories = dataVM.categories.asStateFlow().value
    Column(
        Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
            .background(appBgColor)
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.Top,
    ){
        Header(rightlogo = R.drawable.baseline_add_shopping_cart_24){
            navHostController.navigate(Route.Cart.name)
        }
        Spacer(modifier = Modifier.height(8.dp))

        OutlinedButton(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(20.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = appBgColor,
            ),
            onClick = {navHostController.navigate(Route.Search.name)}
        ){
            Text(text = "Search", color = Color.Black)
        }
        Spacer(modifier = Modifier.height(8.dp))

        GetTopicName("Categories"){
            navHostController.navigate(Route.Categories.name)
        }
        //get categories
        LazyRow (
            Modifier
                .fillMaxWidth()
                .padding(horizontal = 12.dp)) {
            items(items = categories) {
                CategoryItem(it){
                    dataVM.categoryPicked.value = it
                    navHostController.navigate(Route.ProductPerCategory.name)
                }
                Spacer(modifier = Modifier.width(8.dp))
            }
        }
        Spacer(modifier = Modifier.height(8.dp))

        GetTopicName("Explore All"){
            navHostController.navigate(Route.AllProducts.name)
        }
        //get restaurant list
        LazyRow (
            Modifier
                .fillMaxWidth()
                .padding(horizontal = 12.dp)){
            items(items = products){
                Item(it){
                    dataVM.currentProduct.value = it
                    navHostController.navigate(Route.ProductDetail.name)
                }
                Spacer(modifier = Modifier.width(16.dp))
            }
        }
        Spacer(modifier = Modifier.height(8.dp))

        GetTopicName("Quickly Deliverable")
        //get restaurant list
        LazyRow (
            Modifier
                .fillMaxWidth()
                .padding(horizontal = 12.dp)){
            items(items = products){
                Item(it)
                Spacer(modifier = Modifier.width(16.dp))
            }
        }
        Spacer(modifier = Modifier.height(8.dp))

    }
}

@Composable
fun GetTopicName(mainText : String = "", rightText : String = "Swipe to see all ->", onclick : ()-> Unit = {}){
    Row(
        Modifier
            .fillMaxWidth()
            .background(Color.White),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically){
        Text(text = mainText, fontWeight = FontWeight.Bold, fontSize = 18.sp)
        TextButton(onClick = onclick) {
            Text(text = rightText,
                fontWeight = FontWeight.Normal,
                fontSize = 14.sp,
                color = Color.Black)
        }
    }
}
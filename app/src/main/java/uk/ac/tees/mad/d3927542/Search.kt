package uk.ac.tees.mad.d3927542
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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.toLowerCase
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import uk.ac.tees.mad.d3927542.ui.theme.appBgColor
import java.util.Locale

@Composable
fun Search(dataViewModel: DataViewModel, navHostController: NavHostController) {
  val scroll = rememberScrollState()
    val searchData = remember {
        mutableStateOf<List<Product>>(
            dataViewModel.products.value)
    }

    Column(
        Modifier
            .fillMaxSize()
            .padding(horizontal = 8.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally) {
        Box(Modifier.fillMaxWidth(), contentAlignment = Alignment.CenterStart){
            BackButton({
                navHostController.navigateUp()
            })
        }
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = "Search", fontWeight = FontWeight.Bold,
            fontSize = 32.sp, textAlign = TextAlign.Start, modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(16.dp))
        var search by remember { mutableStateOf("") }
        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = search,
            onValueChange = {
                search = it
                searchData.value = dataViewModel.products.value.filter {
                        s -> s.name?.toLowerCase(Locale.ROOT)?.contains(search.toLowerCase(Locale.ROOT))!! }
                            },
            leadingIcon = { Icon(imageVector = Icons.Default.Search, contentDescription = "search") },
            shape = RoundedCornerShape(20.dp),
            colors = OutlinedTextFieldDefaults.colors(
                focusedContainerColor = Color.White,
                unfocusedContainerColor = appBgColor,
            ),
            label = { Text(text = "Search") },
            singleLine = true,
        )
        Spacer(modifier = Modifier.height(8.dp))
        searchData.let {
            LazyVerticalGrid(
                contentPadding = PaddingValues(horizontal = 8.dp),
                columns = GridCells.Fixed(2),
                horizontalArrangement = Arrangement.SpaceBetween
            ){
                items(items = it.value){
                    Item(product = it) {
                        dataViewModel.currentProduct.value = it
                        navHostController.navigate(Route.ProductDetail.name)
                    }
                }
            }
        }

    }
}
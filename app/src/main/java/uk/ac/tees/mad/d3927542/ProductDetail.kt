package uk.ac.tees.mad.d3927542
import android.annotation.SuppressLint
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import coil.compose.rememberAsyncImagePainter
import uk.ac.tees.mad.d3927542.ui.theme.appBgColor

@SuppressLint("UnrememberedMutableState")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProductDetail(
    navHostController: NavHostController,
    dataVM : DataViewModel
) {
    val product = dataVM.currentProduct.value
    var isLiked by remember {
        mutableStateOf(product.isLiked)
    }
    val scope = rememberCoroutineScope()
    var isBottomSheetVisible by rememberSaveable { mutableStateOf(false) }
    val sheetState = rememberModalBottomSheetState(
        skipPartiallyExpanded = true
    )
    var quantities by remember {
        mutableStateOf(1)
    }
    val icon = if(isLiked) Icons.Filled.Favorite else Icons.Outlined.FavoriteBorder
    Column (
        Modifier
            .fillMaxSize()
            .padding(vertical = 4.dp, horizontal = 12.dp)){
        Column(
            Modifier
                .fillMaxHeight(0.9f)
                .fillMaxWidth()
                .verticalScroll(rememberScrollState()))
        {
            Spacer(modifier = Modifier.height(16.dp))
            Row(
                Modifier
                    .fillMaxWidth()
                    .height(70.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically) {
                BackButton(onClick = {navHostController.navigateUp()})
                Card(shape = CircleShape,colors = CardDefaults.cardColors(
                    containerColor = appBgColor,
                ), modifier = Modifier.size(48.dp),
                    onClick = {
                        isLiked = !isLiked
                    }){
                    Box(modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center){
                        Icon(
                            imageVector = icon,
                            contentDescription = "like",
                        )
                    }
                }
            }
            Spacer(modifier = Modifier.height(16.dp))
            //todo image
            Image(
                painter = rememberAsyncImagePainter(product.image),
                contentDescription = "main",
                modifier = Modifier
                    .height(250.dp)
                    .fillMaxWidth()
                    .padding(12.dp),
                contentScale = ContentScale.Fit,
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text("${product.name}", fontWeight = FontWeight.Bold, fontSize = 18.sp, modifier = Modifier.padding(4.dp))
            Spacer(modifier = Modifier.height(8.dp))
            Text("₹ ${product.price}.00", fontWeight = FontWeight.Bold, fontSize = 18.sp, modifier = Modifier.padding(4.dp), color = Color.Black)
            Spacer(modifier = Modifier.height(12.dp))

            Spacer(modifier = Modifier.height(16.dp))

            Text(text = stringResource(id = product.description!!.toInt()),color = Color.Black,
                fontSize = 16.sp, modifier = Modifier.padding(4.dp))

            Spacer(modifier = Modifier.height(16.dp))
        }

        OutlinedButton(
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.Blue,
            ),
            border = BorderStroke(0.dp, Color.Transparent),
            onClick = {
                dataVM.cartData.value += product
                   navHostController.navigate(Route.Cart.name)

            },
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(16.dp),
        ) {
            Row (
                Modifier
                    .fillMaxWidth()
                    .height(50.dp)
                    .padding(horizontal = 8.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween){
                Text(text = "₹ ${product.price}.00", fontWeight = FontWeight.Bold, color = Color.White, fontSize = 18.sp, modifier = Modifier.padding(4.dp))
                Text(text = "Add to bag",color = Color.White, fontSize = 18.sp, modifier = Modifier.padding(4.dp))
            }
        }
    }
}
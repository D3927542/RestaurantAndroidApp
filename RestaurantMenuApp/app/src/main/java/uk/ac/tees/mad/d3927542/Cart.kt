package uk.ac.tees.mad.d3927542

import android.annotation.SuppressLint
import android.graphics.BitmapFactory
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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import coil.compose.rememberAsyncImagePainter
import uk.ac.tees.mad.d3927542.ui.theme.appBgColor

@SuppressLint("MutableCollectionMutableState")
@Composable
fun Cart(
    navHostController: NavHostController, dataViewModel: DataViewModel
) {
    Column(
        Modifier
            .fillMaxSize()
            .background(Color.White),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(16.dp))
        Box(
            Modifier
                .fillMaxWidth()
                .padding(horizontal = 8.dp),
            contentAlignment = Alignment.Center
        ) {
            BackButton(
                onClick = { navHostController.navigateUp() }, modifier = Modifier.align(
                    Alignment.CenterStart
                )
            )
            Text(text = "Cart", fontWeight = FontWeight.Bold, color = Color.Black)
        }
        Spacer(modifier = Modifier.height(16.dp))
        if (dataViewModel.cartData.value.isEmpty())
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                GetEmptyPage(
                    text = "Your cart is empty.",
                    onClickAction = {navHostController.navigateUp()})
            }
        else {
            Text(text = "Remove All", fontWeight = FontWeight.Medium, color = Color.Black,
                modifier = Modifier.clickable {
                    dataViewModel.deleteAllCartItems()
                })
            Spacer(modifier = Modifier.height(8.dp))
            LazyColumn(
                Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(0.6f)
                    .padding(horizontal = 8.dp)
            ) {
                items(items = dataViewModel.cartData.value) {
                    CartOrderUI(cartData = it, dataViewModel)
                }
            }
            Spacer(modifier = Modifier.height(16.dp))
            var subtotal = dataViewModel.cartData.value.sumOf{ s -> (s.price?.toInt()/*?.times(s.quantity?.value?.toInt()!!)*/)!! }
            getRow("Subtotal", "₹ $subtotal")
            getRow(dataLeft = "Shipping Cost (10%)", dataRight = "₹ ${(subtotal.toDouble()  / 0.1)/100}")
            getRow(dataLeft = "Tax", dataRight = "₹ 0.0")
            getRow(dataLeft = "Total", dataRight = "₹ ${subtotal + ((subtotal.toDouble()  / 0.1)/100)}")
            Spacer(modifier = Modifier.height(16.dp))

            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.BottomCenter){
                OutlinedButton(
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.Blue,
                    ),
                    border = BorderStroke(0.dp, Color.Transparent),
                    onClick = {
                        navHostController.navigate(Route.OrderPlaced.name)
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(50.dp)
                        .padding(horizontal = 8.dp),
                    shape = RoundedCornerShape(16.dp),
                ){
                    Text(text = "Checkout")
                }
            }

        }
    }
}

@Composable
fun getRow(dataLeft : String, dataRight: String){
    Row(
        Modifier
            .fillMaxWidth()
            .padding(horizontal = 8.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween) {
        Text(text = dataLeft, color = Color.Black)
        Text(text = dataRight, color = Color.Black, fontWeight = FontWeight.Bold)
    }
    Spacer(modifier = Modifier.height(8.dp))
}
@Composable
fun CartOrderUI(cartData: Product, dataViewModel: DataViewModel){
    Card(modifier = Modifier
        .fillMaxWidth()
        .height(100.dp)
        .padding(vertical = 4.dp, horizontal = 4.dp),colors = CardDefaults.cardColors(
        containerColor = appBgColor,
    ),
        shape = RoundedCornerShape(16.dp)
    ){
        Row (
            Modifier
                .fillMaxSize()
                .padding(horizontal = 8.dp, vertical = 8.dp,),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start){

            Image(
                painter = rememberAsyncImagePainter(cartData.image),
                contentDescription = "",
                Modifier
                    .fillMaxHeight()
                    .width(64.dp))

            Column(
                Modifier
                    .fillMaxHeight()
                    .padding(vertical = 4.dp, horizontal = 12.dp),
                verticalArrangement = Arrangement.SpaceBetween,
                horizontalAlignment = Alignment.CenterHorizontally) {
                Row(Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween) {
                    Text(text = cartData.name.toString(),
                        overflow = TextOverflow.Ellipsis,
                       modifier =  Modifier.width(60.dp), color = Color.Black, )
                    Text(text = "₹ ${cartData.price}.00",
                        fontWeight = FontWeight.Bold, color = Color.Black)
                }
            }
        }
    }
}
package uk.ac.tees.mad.d3927542
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import uk.ac.tees.mad.d3927542.ui.theme.appBgColor

@Composable
fun OrderPlaced(navHostController: NavHostController, dataViewModel: DataViewModel) {
    LaunchedEffect(key1 = true) {
        dataViewModel.cartData.value = emptyList<Product>()
        dataViewModel.sendEmail()
    }
    Column(
        Modifier
            .fillMaxSize()
            .background(appBgColor)) {
        Card(modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(0.6f),
            colors = CardDefaults.cardColors(
                containerColor = Color.Gray,
            ) ) {
            Spacer(modifier = Modifier.weight(0.3f))
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center){
                Image(painter = painterResource(id = R.drawable.orderplaced),
                    contentDescription = "", Modifier.size(200.dp))
            }
        }
        Card(modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(),
            colors = CardDefaults.cardColors(
                containerColor = appBgColor,
            ), shape = RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp)){
            Column(Modifier.fillMaxSize(), verticalArrangement = Arrangement.SpaceEvenly,
                 horizontalAlignment = Alignment.CenterHorizontally) {
                Text("Order Placed Successfully.", fontWeight = FontWeight.Bold, color = Color.Black,
                    fontSize = 30.sp, modifier = Modifier
                        .align(Alignment.CenterHorizontally)
                        .padding(horizontal = 12.dp))
                Text("You will receive an email confirmation on this email: "+ dataViewModel.getEmail())

                OutlinedButton(
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.Blue,
                    ),
                    border = BorderStroke(0.dp, Color.Transparent),
                    onClick = {
                        navHostController.popBackStack()
                        navHostController.navigate(Route.Home.name)
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(50.dp)
                        .padding(horizontal = 8.dp),
                    shape = CircleShape,
                ){
                    Text(text = "See order details", color = Color.White)
                }
            }
        }
    }

}
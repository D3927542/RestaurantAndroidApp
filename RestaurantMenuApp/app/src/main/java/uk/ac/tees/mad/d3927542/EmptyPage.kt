package uk.ac.tees.mad.d3927542
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
@Preview
fun GetEmptyPage(image: Int = R.drawable.baseline_add_shopping_cart_24,
                 text : String = "No Notifications yet",
                 onClickAction: ()-> Unit = {},
                 buttonText : String = "Explore Categories"){
    Column(
        Modifier
            .height(270.dp)
            .fillMaxWidth()
            .padding(vertical = 8.dp)
            .background(Color.White),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween) {

        Image(painter = painterResource(id = image),
            modifier = Modifier.size(100.dp),
            contentDescription = "notifications")

        Text(text = text, fontSize = 20.sp)

        Button(
            onClick = onClickAction,
            modifier = Modifier.fillMaxWidth(0.5f),
            shape = RoundedCornerShape(16.dp)
        ) {
            Text(text = buttonText)
        }
    }
}
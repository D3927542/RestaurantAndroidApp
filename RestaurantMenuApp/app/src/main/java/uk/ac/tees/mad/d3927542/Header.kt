package uk.ac.tees.mad.d3927542
import android.media.Image
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Paint
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

//make sure to add dark background
@Preview(showBackground = true)
@Composable
fun Header(title: String = "Restaurant Menu", logo : Int= R.drawable.baseline_restaurant_menu_24, rightlogo : Int= R.drawable.baseline_restaurant_menu_24,
           rightLogoOnClick :() -> Unit = {}) {
    Row (modifier = Modifier
        .height(70.dp)
        .fillMaxWidth()
        .background(Color.Unspecified)
        .padding(horizontal = 8.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically){

        Icon(painter = painterResource(id = logo), contentDescription = "logo", tint = Color.Black)

        Text(text = title, fontWeight = FontWeight.Bold, color = Color.Black, fontSize = 18.sp)

        IconButton(onClick = rightLogoOnClick) {
            Icon(painter = painterResource(id = rightlogo), contentDescription = "logo", tint = Color.Black)
        }


    }

}
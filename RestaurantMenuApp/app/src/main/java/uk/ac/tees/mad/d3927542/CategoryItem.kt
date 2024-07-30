package uk.ac.tees.mad.d3927542
import android.graphics.BitmapFactory
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CategoryItem(category: Category, onclick: () -> Unit = {}) {
    Column (
        Modifier
            .height(100.dp)
            .padding(horizontal = 12.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center){
        Card(shape = CircleShape, onClick = onclick){
            Image(
                painter = rememberAsyncImagePainter(category.image),
                contentDescription = "back",
                contentScale = ContentScale.Fit,            // crop the image if it's not a square
                modifier = Modifier
                    .size(48.dp)
                    .background(Color.Unspecified)
                    .clip(CircleShape)                       // clip to the circle shape
                    .border(0.dp, Color.Unspecified, CircleShape)   // add a border (optional)
            )
        }
        Text(
            category.name.toString(), fontWeight = FontWeight.Bold,
            fontSize = 12.sp)
    }
}
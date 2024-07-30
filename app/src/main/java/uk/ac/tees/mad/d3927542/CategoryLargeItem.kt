package uk.ac.tees.mad.d3927542
import android.graphics.BitmapFactory
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
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
import androidx.navigation.NavHostController
import coil.compose.rememberAsyncImagePainter
import uk.ac.tees.mad.d3927542.ui.theme.appBgColor

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CategoryLargeItem(category: Category, dataViewModel: DataViewModel, navHostController: NavHostController, onclick: () -> Unit) {
    Card(modifier = Modifier
        .fillMaxWidth()
        .padding(horizontal = 16.dp, vertical = 8.dp)
        .height(80.dp)
        ,colors = CardDefaults.cardColors(
            containerColor = appBgColor,
        ),
        onClick = onclick,
        shape = RoundedCornerShape(16.dp)
    ){
        Row (
            Modifier
                .height(100.dp)
                .padding(horizontal = 12.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center){
            Card(shape = CircleShape,colors = CardDefaults.cardColors(
                containerColor = Color.Unspecified)){
                Image(
                    painter = rememberAsyncImagePainter(category.image),
                    contentDescription = "back",
                    contentScale = ContentScale.Fit,            // crop the image if it's not a square
                    modifier = Modifier
                        .size(48.dp)
                        .clip(CircleShape)                       // clip to the circle shape
                        .border(0.dp, Color.Unspecified, CircleShape)   // add a border (optional)
                )
            }
            Text(
                category.name.toString(), fontWeight = FontWeight.Bold,
                fontSize = 18.sp, modifier = Modifier.padding(start = 16.dp))

        }
    }

}
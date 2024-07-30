package uk.ac.tees.mad.d3927542

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import uk.ac.tees.mad.d3927542.ui.theme.appBgColor

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Item(product: Product, onClick: ()-> Unit = {}, ) {
    var isLiked by remember {
        mutableStateOf(product.isLiked)
    }
    val icon = if(isLiked) Icons.Filled.Favorite else Icons.Outlined.FavoriteBorder
    Card(modifier = Modifier
        .width(150.dp)
        .height(280.dp)
        .padding(vertical = 4.dp, horizontal = 4.dp),
        colors = CardDefaults.cardColors(
            containerColor = appBgColor,
        ),
        onClick = onClick,
        shape = RoundedCornerShape(12.dp))
    {
        Box(modifier = Modifier
            .fillMaxWidth(),
            contentAlignment = Alignment.TopEnd)
        {
            Image(
                painter = rememberAsyncImagePainter(product.image),
                contentDescription = "main",
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp),
                contentScale = ContentScale.Fit,
            )
        }
        Text(product.name.toString(), fontSize = 18.sp, maxLines = 1, modifier = Modifier.padding(4.dp))
        Text("₹ ${product.price}.00", fontWeight = FontWeight.Bold, fontSize = 18.sp, modifier = Modifier.padding(4.dp))
    }
}
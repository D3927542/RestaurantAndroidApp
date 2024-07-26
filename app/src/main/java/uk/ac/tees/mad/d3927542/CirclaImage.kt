package uk.ac.tees.mad.d3927542

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CirclaImage(image : Int,
                onClick: ()-> Unit = {},
                contentScale: ContentScale = ContentScale.Inside,
                background : Color = Color.Gray, size : Dp = 48.dp,
                modifier: Modifier = Modifier) {
    Card(shape = CircleShape, modifier = modifier, colors = CardDefaults.cardColors(
        containerColor = background,
    ),
        onClick = onClick){
        Image(
            painter = painterResource(image),
            contentDescription = "back",
            contentScale = contentScale,            // crop the image if it's not a square
            modifier = Modifier
                .size(size)
                .clip(CircleShape)                       // clip to the circle shape
                .border(0.dp, Color.Unspecified, CircleShape)   // add a border (optional)
        )
    }
}
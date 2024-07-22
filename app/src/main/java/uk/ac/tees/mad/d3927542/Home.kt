package uk.ac.tees.mad.d3927542

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import uk.ac.tees.mad.d3927542.ui.theme.appBgColor

@Composable
@Preview
fun Home() {
    Column(
        Modifier
            .fillMaxWidth()
            .background(appBgColor)
            .padding(horizontal = 16.dp)
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.Top,
    ){
        Header()
        Spacer(modifier = Modifier.height(8.dp))

        OutlinedButton(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(20.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = appBgColor,
            ),
            onClick = {}
        ){
            Text(text = "Search", color = Color.Black)
        }
        Spacer(modifier = Modifier.height(8.dp))

        GetTopicName("Categories")
        //get categories

        Spacer(modifier = Modifier.height(8.dp))

        GetTopicName("Explore All")
        //get restaurant list

        Spacer(modifier = Modifier.height(8.dp))

    }
}

@Composable
fun GetTopicName(mainText : String = "", rightText : String = "See all", onclick : ()-> Unit = {}){
    Row(
        Modifier
            .fillMaxWidth()
            .background(Color.White),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically){
        Text(text = mainText, fontWeight = FontWeight.Bold, fontSize = 18.sp)
        TextButton(onClick = onclick) {
            Text(text = rightText,
                fontWeight = FontWeight.Normal,
                fontSize = 18.sp,
                color = Color.Black)
        }
    }
}
package com.hdd.businesscard

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Email
import androidx.compose.material.icons.rounded.Person
import androidx.compose.material.icons.rounded.Phone
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.hdd.businesscard.ui.theme.BusinessCardTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BusinessCardTheme {
                // A surface container using the 'background' color from the theme
                Surface {
                    BusinessCardScreen()
                }
            }
        }
    }
}

@Composable
fun BusinessCardScreen() {
    Column(Modifier.fillMaxSize()) {
        Spacer(modifier = Modifier.weight(2f))
        ProfileView()
        Spacer(modifier = Modifier.weight(1f))
        InformationView()
    }
}

@Composable
fun ProfileView() {
    Column(
        Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        val image = painterResource(id = R.drawable.hongjongpyo)
        Image(
            painter = image,
            contentDescription = null,
            modifier = Modifier
                .width(354.dp)
                .height(472.dp)
        )
        Text(stringResource(R.string.my_name), fontSize = 32.sp, fontWeight = FontWeight.Bold)
        Text(stringResource(R.string.my_job), fontWeight = FontWeight.Bold)
    }
}

@Composable
fun InformationView() {
    Column(
        Modifier
            .fillMaxWidth()
            .padding(vertical = 32.dp)
    ) {
        InformationTile(Icons.Rounded.Phone, stringResource(R.string.my_phone))
        InformationTile(Icons.Rounded.Person, text = stringResource(R.string.my_tweet))
        InformationTile(Icons.Rounded.Email, stringResource(R.string.my_email))
    }
}

@Composable
fun InformationTile(icon: ImageVector, text: String) {
    Row(
        Modifier
            .fillMaxWidth()
            .padding(horizontal = 32.dp, vertical = 8.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Icon(icon, contentDescription = null, tint = Color.Blue)
        Text(text, color = Color(0xFF8b00ff))
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    BusinessCardTheme {
        BusinessCardScreen()
    }
}
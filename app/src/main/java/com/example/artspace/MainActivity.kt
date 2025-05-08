package com.example.artspace

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.Button
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.artspace.ui.theme.ArtSpaceTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ArtSpaceTheme {
                ArtSpaceApp()
            }
        }
    }
}

@Composable
fun ArtSpaceApp() {
    var currentArtIndex by remember { mutableStateOf(0) }

    val artworks = listOf(
        ArtData(R.drawable.mona_lisa, "La Joconde", "Leonardo da Vinci", "1503"),
        ArtData(R.drawable.starry_night, "La Nuit étoilée", "Vincent Van Gogh", "1889"),
        ArtData(R.drawable.the_scream, "Le Cri", "Edvard Munch", "1893")
    )

    val artwork = artworks[currentArtIndex]

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = artwork.imageRes),
            contentDescription = null,
            modifier = Modifier
                .fillMaxWidth()
                .height(300.dp)
                .clip(RoundedCornerShape(8.dp))
                .border(2.dp, MaterialTheme.colorScheme.primary, RoundedCornerShape(8.dp))
        )
        Spacer(modifier = Modifier.height(24.dp))

        Text(
            text = artwork.title,
            fontWeight = FontWeight.Bold,
            fontSize = 24.sp
        )
        Text(
            text = "${artwork.artist} - ${artwork.year}",
            fontStyle = FontStyle.Italic
        )

        Spacer(modifier = Modifier.height(24.dp))

        Row {
            Button(onClick = {
                currentArtIndex = (currentArtIndex - 1 + artworks.size) % artworks.size
            }) {
                Text("Précédent")
            }
            Spacer(modifier = Modifier.width(16.dp))
            Button(onClick = {
                currentArtIndex = (currentArtIndex + 1) % artworks.size
            }) {
                Text("Suivant")
            }
        }
    }
}

data class ArtData(
    val imageRes: Int,
    val title: String,
    val artist: String,
    val year: String
)

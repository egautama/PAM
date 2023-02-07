package uksw.fti.pam_activity

import android.graphics.Paint.Align
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.R
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import uksw.fti.pam_activity.ui.theme.PAM_ActivityTheme
import uksw.fti.pam_activity.ui.theme.Purple700
import uksw.fti.pam_activity.ui.theme.Teal200
import uksw.fti.pam_activity.ui.theme.Textwhite

class HomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PAM_ActivityTheme() {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    val username = getIntent().getStringExtra("username") ?: ""
                    Greeting(username)
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

@OptIn(ExperimentalFoundationApi::class)
@Preview(showBackground = true)
@Composable
fun HomeScreen() {
    Box(modifier = Modifier
        .fillMaxSize()
    ) {
        Column() {
            GreetingSection()
            ChipSection(chips = listOf("Check In", "Check Out", "Tamu"))
            CurrentMeditation()
            PaketSection()
            FeatureSection(
                features = listOf(
                    Feature(
                        title = "gambar1",
                        uksw.fti.pam_activity.R.drawable.gambar1,
                    ),
                    Feature(
                        title = "gambar2",
                        uksw.fti.pam_activity.R.drawable.gambar2,
                    )
                )
            )
            HotelSection()
            FeatureSection2(
                features = listOf(
                    Feature(
                        title = "gambar3",
                        uksw.fti.pam_activity.R.drawable.gambar3,
                    ),
                    Feature(
                        title = "gambar4",
                        uksw.fti.pam_activity.R.drawable.gambar4,
                    )
                )
            )
        }
    }
}

@Composable
fun GreetingSection(
    name: String = "Ega"
) {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .padding(15.dp)
    ) {
        Column(
            verticalArrangement = Arrangement.Center
        ) {
            Text(text = "$name",
                style = MaterialTheme.typography.h4
            )
            Text(text = "cek yang tersedia",
                style = MaterialTheme.typography.body1
            )
        }
        Image(
            painter = painterResource(id = uksw.fti.pam_activity.R.drawable.profile),
            contentDescription = "Logo",
            modifier = Modifier
                .size(50.dp)
        )

    }
}


@Composable
fun ChipSection(
    chips: List<String>
) {
    var selectdChipIndex by remember {
        mutableStateOf(0)
    }
    LazyRow {
        items(chips.size) {
            Box(
                contentAlignment = Alignment.CenterEnd,
                modifier = Modifier
                    .padding(start = 40.dp, top = 40.dp, bottom = 20.dp)
                    .clickable {
                        selectdChipIndex = it
                    }
                    .clip(RoundedCornerShape(15.dp))
                    .background(color = Color.Yellow)
                    .padding(15.dp)

            ) {
                Text(text = chips[it], color = Purple700)
            }
        }
    }
}

@Composable
fun CurrentMeditation(
    color: Color = Teal200
) {
    Row(
        modifier = Modifier
            .padding(15.dp)
            .clip(RoundedCornerShape(10.dp))
            .background(color)
            .padding(horizontal = 10.dp, vertical = 10.dp)
            .fillMaxWidth()
    ) {
            Text(
                text = "Cari",
                style = MaterialTheme.typography.body1
            )
        }
    }


@Composable
fun PaketSection() {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .padding(25.dp)
    ) {
        Column(
            verticalArrangement = Arrangement.Center
        ) {
            Text(text = "Paket Terbaik",
                style = MaterialTheme.typography.body1
            )
        }
        Column(
            verticalArrangement = Arrangement.Center
        ) {
            Text(text = "Lihat Semua >",
                style = MaterialTheme.typography.body2
            )
        }
    }
}

@ExperimentalFoundationApi
@Composable
fun FeatureSection(features: List<Feature>) {
    Column {
        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            contentPadding = PaddingValues(start = 7.5.dp, end = 7.5.dp, bottom = 15.dp),
            modifier = Modifier.fillMaxWidth()
        ) {
            items(features.size) {
                FeatureItem(feature = features[it])
            }
        }
    }
}


@Composable
fun FeatureItem(
    feature: Feature
) {
    BoxWithConstraints(
        modifier = Modifier
            .padding(5.dp)
            .aspectRatio(1f)
            .clip(RoundedCornerShape(30.dp))
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
        ) {
            Text(
                text = feature.title,
                style = MaterialTheme.typography.body1,
                modifier = Modifier.align(Alignment.TopStart)
            )
            Image(
                painter = painterResource(id = feature.iconId),
                contentDescription = feature.title,
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxSize()
                    .clip(RoundedCornerShape(16.dp)))
        }
    }
}

@Composable
fun HotelSection() {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .padding(25.dp)
    ) {
        Column(
            verticalArrangement = Arrangement.Center
        ) {
            Text(text = "Hotel Terbaik",
                style = MaterialTheme.typography.body1
            )
        }
        Column(
            verticalArrangement = Arrangement.Center
        ) {
            Text(text = "Lihat Semua >",
                style = MaterialTheme.typography.body2
            )
        }
    }
}

@ExperimentalFoundationApi
@Composable
fun FeatureSection2(features: List<Feature>) {
    Column {
        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            contentPadding = PaddingValues(start = 7.5.dp, end = 7.5.dp, bottom = 25.dp),
            modifier = Modifier.fillMaxWidth()
        ) {
            items(features.size) {
                FeatureItem2(feature = features[it])
            }
        }
    }
}


@Composable
fun FeatureItem2(
    feature: Feature
) {
    BoxWithConstraints(
        modifier = Modifier
            .padding(5.dp)
            .aspectRatio(1f)
            .clip(RoundedCornerShape(30.dp))
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
        ) {
            Text(
                text = feature.title,
                style = MaterialTheme.typography.body1,
                modifier = Modifier.align(Alignment.TopStart)
            )
            Image(
                painter = painterResource(id = feature.iconId),
                contentDescription = feature.title,
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxSize()
                    .clip(RoundedCornerShape(16.dp)))
        }
    }
}




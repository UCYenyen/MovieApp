package com.example.movieappnew.ui.view

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.filled.StarBorder
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.rememberAsyncImagePainter
import com.example.movieappnew.ui.viewmodel.AddMovieViewModel

@Composable
@Preview(showBackground = true, showSystemUi = true)
fun AddMovieView(
    modifier: Modifier = Modifier,
    viewModel: AddMovieViewModel = viewModel()
) {

    val movie by viewModel.movie.collectAsState()
    var showDialog by remember { mutableStateOf(false) }
    val scrollState = rememberScrollState()

    val galleryLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()
    ) { uri: Uri? ->
        viewModel.onImageSelected(uri)
    }

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(scrollState),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Text(
            text = "Add New Movie",
            style = MaterialTheme.typography.headlineSmall.copy(fontSize = 22.sp)
        )

        // Image Picker
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(180.dp)
                .background(Color.LightGray, RoundedCornerShape(8.dp))
                .clickable { galleryLauncher.launch("image/*") },
            contentAlignment = Alignment.Center
        ) {
            if (movie.posterPath.isNotBlank()) {
                Image(
                    painter = rememberAsyncImagePainter(movie.posterPath),
                    contentDescription = "Selected Image",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .fillMaxSize()
                        .clip(RoundedCornerShape(8.dp))
                )
            } else {
                Text("Tap to choose poster", color = Color.DarkGray)
            }
        }

        OutlinedTextField(
            value = movie.title,
            onValueChange = viewModel::onTitleChange,
            label = { Text("Title") },
            modifier = Modifier.fillMaxWidth()
        )

        OutlinedTextField(
            value = movie.description,
            onValueChange = viewModel::onDescriptionChange,
            label = { Text("Description") },
            modifier = Modifier
                .fillMaxWidth()
                .heightIn(min = 120.dp), // Make it taller
            maxLines = 10, // Allow more lines
            singleLine = false
        )

        OutlinedTextField(
            value = movie.genre,
            onValueChange = viewModel::onGenreChange,
            label = { Text("Genre") },
            modifier = Modifier.fillMaxWidth()
        )

        OutlinedTextField(
            value = movie.director,
            onValueChange = viewModel::onDirectorChange,
            label = { Text("Director") },
            modifier = Modifier.fillMaxWidth()
        )

        OutlinedTextField(
            value = if (movie.releaseYear == 0) "" else movie.releaseYear.toString(),
            onValueChange = viewModel::onReleaseYearChange,
            label = { Text("Release Year") },
            modifier = Modifier.fillMaxWidth(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
        )
        // Rating Dropdown
        Text(text = "Rating", modifier = Modifier.padding(top = 8.dp))

        StarRatingBar(
            rating = movie.rating.toInt(),
            onRatingChange = viewModel::onRatingChange
        )

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = {
                viewModel.onSubmit()
                showDialog = true
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Submit")
        }
    }

    // Dialog to show movie details
    if (showDialog) {
        AlertDialog(onDismissRequest = { showDialog = false }, confirmButton = {
            TextButton(onClick = { showDialog = false }) {
                Text("Close")
            }
        }, title = { Text(movie.title.ifBlank { "Movie Detail" }) }, text = {
            Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                Image(
                    painter = rememberAsyncImagePainter(movie.posterPath),
                    contentDescription = movie.title,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(150.dp)
                        .clip(RoundedCornerShape(8.dp))
                )
                Text("Description: ${movie.description}")
                Text("Rating: ${movie.rating}")
                Text("Genre: ${movie.genre}")
                Text("Director: ${movie.director}")
                Text("Year: ${movie.releaseYear}")
            }
        })
    }
}

@Composable
fun StarRatingBar(
    rating: Int, onRatingChange: (Int) -> Unit, maxRating: Int = 5
) {
    Row(horizontalArrangement = Arrangement.Center, modifier = Modifier.fillMaxWidth()) {
        for (i in 1..maxRating) {
            Icon(
                imageVector = if (i <= rating) Icons.Default.Star else Icons.Default.StarBorder,
                contentDescription = "Star $i",
                tint = Color(0xFFFFC107),
                modifier = Modifier
                    .size(40.dp)
                    .clickable { onRatingChange(i) })
        }
    }
}
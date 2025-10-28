package com.example.movieappnew.ui.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SmallFloatingActionButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.key
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.evan.movieapp2025.ui.viewmodel.MovieDetailViewModel
import com.example.movieappnew.data.container.MovieServerContainer

@Composable
@Preview(showBackground = true, showSystemUi = true)
fun MovieDetailView(
    viewModel: MovieDetailViewModel = viewModel(),
    id: Int = 0,
    modifier: Modifier = Modifier
) {
    LaunchedEffect(id) {
        viewModel.getMovie(id)
    }
    val scrollState = rememberScrollState()
    val movie by viewModel.movie.collectAsState()

    when{
        movie == null ->{
            Text("Loading...", modifier = Modifier.padding(16.dp).fillMaxSize(), textAlign = TextAlign.Center)
        }

        else -> {
            Column(
                modifier = modifier
                    .fillMaxSize()
                    .verticalScroll(scrollState)
            ) {
                // Poster
//                val painter = painterResource(id = movie!!.posterResId)
//                val intrinsic = painter.intrinsicSize
//                val aspectRatio = if (intrinsic.width > 0 && intrinsic.height > 0)
//                    intrinsic.width / intrinsic.height
                val aspectRatio = 2f / 3f

                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .aspectRatio(aspectRatio)
                        .background(Color.Black)
                ) {
                    AsyncImage(
                        model = ImageRequest.Builder(LocalContext.current)
                            .data(MovieServerContainer.BASE_IMG_URL + movie!!.posterPath)
                            .crossfade(true)
                            .build(),
                        contentDescription = movie!!.title,
                        contentScale = ContentScale.Fit,
                        modifier = Modifier.matchParentSize()
                    )
                    // Gradient bawah
                    Box(
                        Modifier
                            .matchParentSize()
                            .background(
                                Brush.verticalGradient(
                                    listOf(Color.Transparent, Color.Black.copy(alpha = 0.65f))
                                )
                            )
                    )
                    // Judul + Rating
                    Column(
                        modifier = Modifier
                            .align(Alignment.BottomStart)
                            .padding(16.dp)
                    ) {
                        Text(
                            text = movie!!.title,
                            style = MaterialTheme.typography.headlineSmall.copy(
                                fontWeight = FontWeight.Bold,
                                color = Color.White
                            ),
                            maxLines = 2,
                            overflow = TextOverflow.Ellipsis
                        )
                        Spacer(Modifier.height(6.dp))
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Icon(
                                imageVector = Icons.Default.Star,
                                contentDescription = null,
                                tint = Color(0xFFFFC107),
                                modifier = Modifier.size(18.dp)
                            )
                            Text(
                                text = "  ${"%.1f".format(movie!!.rating)} / 5",
                                style = MaterialTheme.typography.bodySmall.copy(
                                    fontWeight = FontWeight.Medium,
                                    color = Color.White
                                )
                            )
                        }
                    }

                    SmallFloatingActionButton(
                        onClick = { viewModel.toggleIsLiked() },
                        modifier = Modifier
                            .align(Alignment.TopEnd)
                            .padding(12.dp),
                        containerColor = if (movie!!.isFavorite) Color(0xFFFF4081) else Color.White.copy(alpha = 0.92f),
                        contentColor = if (movie!!.isFavorite) Color.White else Color(0xFF555555),
                        elevation = FloatingActionButtonDefaults.elevation(defaultElevation = 6.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Default.Favorite,
                            contentDescription = if (movie!!.isFavorite) "Unlike" else "Like"
                        )
                    }
                }

                // Konten Detail
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    verticalArrangement = Arrangement.spacedBy(14.dp)
                ) {
                    Text(
                        text = "${movie!!.genre} • ${movie!!.releaseYear} • ${movie!!.director}",
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )

                    HorizontalDivider()

                    Text(
                        text = "Synopsis",
                        style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.SemiBold)
                    )

                    Text(
                        text = movie!!.description,
                        style = MaterialTheme.typography.bodyMedium,
                        lineHeight = 20.sp
                    )

                    Spacer(Modifier.height(40.dp))
                }
            }
        }
    }
}
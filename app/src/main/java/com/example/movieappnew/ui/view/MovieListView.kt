package com.example.movieappnew.ui.view

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.repeatOnLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.movieappnew.ui.route.AppView
import com.example.movieappnew.ui.viewmodel.MovieListViewModel

@Composable
fun MovieListView(viewModel: MovieListViewModel = viewModel(), navController: NavController = rememberNavController()) {
//    val movies by viewModel.movies.collectAsState()
    val lifecycleOwner = LocalLifecycleOwner.current
    LaunchedEffect(lifecycleOwner) {
        lifecycleOwner.lifecycle.repeatOnLifecycle(Lifecycle.State.RESUMED) {
            viewModel.loadMovie() // runs once per resume
        }
    }

    val movies by viewModel.movies.collectAsStateWithLifecycle()
    Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
//        Text("Movie List", style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Bold))
        LazyColumn(modifier = Modifier.fillMaxSize(), contentPadding = PaddingValues(4.dp)) {
            items(movies) { currentMovie ->
                MovieCard(movie = currentMovie, onToggleLike = {viewModel.toggleIsLiked(currentMovie)}, onCardClick = {navController.navigate("${AppView.MovieDetail.name}/${currentMovie.id}")})
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun MovieListPreview() {
    MovieListView()
}
@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.retrofit_example.View

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import coil.size.Scale
import coil.transform.CircleCropTransformation
import com.example.retrofit_example.Model.Movie
import com.example.retrofit_example.R
import com.example.retrofit_example.ViewModel.MovieViewModel

@Composable
fun CreditCardScreen(viewModel: MovieViewModel) {
    val mList by viewModel.movieList.observeAsState(null)

    LaunchedEffect(Unit) {
        viewModel.fetchMovies()
    }

    Column {
        if (mList == null) {
            // Show loading indicator or placeholder
            Text(text = "Loading...")
        } else {
            // Display the list of credit cards
            //CreditCardItem(creditCards!!)
            MovieList(movieList = mList!!)
        }
    }
}
@Composable
fun MovieList(movieList:List<Movie>){
    LazyColumn(){
        itemsIndexed(items = movieList) {
                index, item ->
            movieItem(movie = item)
        }
    }
}

@Composable
fun movieItem(movie: Movie){
    Card(modifier = Modifier
        .padding(8.dp, 4.dp)
        .fillMaxWidth()
        .height(110.dp)
        , shape = RoundedCornerShape(8.dp), elevation = CardDefaults.cardElevation(5.dp)) {
Surface() {
    Row(modifier= Modifier
        .padding(4.dp)
        .fillMaxSize()) {
        Image(
            painter = rememberImagePainter(
                data = movie.imageUrl,
                builder = {
                scale(Scale.FILL)
                placeholder(coil.compose.base.R.drawable.notification_action_background)
                    error(R.drawable.ic_launcher_background)
                transformations(CircleCropTransformation())
            }
            ), contentDescription = movie.desc, modifier = Modifier
                .fillMaxHeight()
                .weight(0.2f)
        )
        Column(
                modifier = Modifier
                    .padding(4.dp)
                    .fillMaxHeight()
                    .weight(0.8f),
        verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = movie.name,
                style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .background(Color.LightGray)
                    .padding(4.dp)
            )
            Text(
                text = movie.category,
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier
                    .background(Color.LightGray)
                    .padding(4.dp)
            )
            Text(
                text = movie.desc,
                style = MaterialTheme.typography.titleMedium,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier
                    .background(Color.LightGray)
                    .padding(4.dp)
            )
    }
    }
}
    }
}
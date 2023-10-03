package com.example.details

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import javax.inject.Inject

@Composable
fun ScreenDetails(animeId:Int) {
    val viewModel:ViewModelDetails = hiltViewModel()
    viewModel.setAnimeId(animeId)
    LazyColumn() {
        item {
            TopBar(viewModel = viewModel)
        }
    }
}

@Composable
private fun TopBar(viewModel: ViewModelDetails) {
    Row(modifier = Modifier.background(MaterialTheme.colorScheme.onSecondary)) {
        LeftTopBar(viewModel)
        RightTopBar(viewModel)
    }

}

@Composable
private fun LeftTopBar(viewModel: ViewModelDetails) {
    val view by viewModel.anime.collectAsState()
    Column {
        Text(text = view?.nameModels?.first()?.ru?:"111111")
    }
}
//12321

@Composable
private fun RightTopBar(viewModel: ViewModelDetails) {

}


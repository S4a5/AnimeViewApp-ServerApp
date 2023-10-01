package com.example.details

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import javax.inject.Inject

@Composable
fun ScreenDetails(navController: NavController,animeId:Int) {
//    val viewModel = ViewModelProvider(LocalContext.current, viewModelFactory).get(ViewModelDetails::class.java)
    val viewModel:ViewModelDetails = hiltViewModel()
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
    Column {
        Text(text = "111")
    }
}

@Composable
private fun RightTopBar(viewModel: ViewModelDetails) {

}


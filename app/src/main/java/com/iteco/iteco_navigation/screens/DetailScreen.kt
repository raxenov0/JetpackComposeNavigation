package com.iteco.iteco_navigation.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.iteco.iteco_navigation.api.ws.AuthorizationViewModel

@Composable
fun DetailScreen(navController: NavController, name: String?, viewModel: AuthorizationViewModel){
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier.fillMaxSize()
    ){
        Column {
            Text(text = "Detail screen :)")
            if (name != null) {
                Text(text = name ?: "Empty arguments")
            }

            Spacer(modifier = Modifier.height(10.dp))
            Button(onClick = {
                navController.popBackStack()
            }) {
                Text(text = "Go back...")
            }
        }
        
    }
}
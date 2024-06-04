package com.iteco.iteco_navigation.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.iteco.iteco_navigation.api.ws.AuthorizationViewModel
import com.iteco.iteco_navigation.navigation.Screen

@Composable
fun MainScreen(navController: NavController, viewModel: AuthorizationViewModel){
    var stringValue by remember {
        mutableStateOf("")
    }

    Surface {
        Column(
            verticalArrangement = Arrangement.Center,
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 5.dp)
        ) {
            Text(text = "Main screens")
            Spacer(Modifier.height(5.dp))
            TextField(value = stringValue, onValueChange = {
                stringValue = it
            },
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(5.dp))
            Button(onClick = {
                    navController.navigate(Screen.DetailScreen.withArgs(stringValue))
                },
                modifier = Modifier.align(Alignment.End)
            ) {
                Text(text = "On next screen...")
            }
        }

    }
}
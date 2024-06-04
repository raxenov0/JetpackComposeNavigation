package com.iteco.iteco_navigation.screens.Authorization.components

import android.content.Intent
import android.net.Uri
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.iteco.iteco_navigation.R

@Preview(showBackground = true)
@Composable
fun NoConnectionFragment(
    title: String = "Нет подключения",
    description: String = "Проверьте подключение к сети или перезагрузите приложение",
    onPress: () -> Unit = {}
) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp)
    ) {
        Column(
            modifier = Modifier
                .weight(1.0f)
                .padding(horizontal = 12.dp)
            ,
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Image(
                imageVector = ImageVector.vectorResource(R.drawable.ic_no_connection),
                contentDescription = null,
                modifier = Modifier
                    .padding(bottom = 55.dp)
            )

            Text(
                text = stringResource(R.string.server_error_title),
                fontSize = 20.sp,
                fontWeight = FontWeight(600),
                color = colorResource(R.color.black),
                modifier = Modifier
                    .padding(bottom = 15.dp)
            )
            Text(
                text = stringResource(R.string.server_error_description),
                fontSize = 16.sp,
                color = colorResource(R.color.black),
                modifier = Modifier
                    .padding(bottom = 15.dp),
                textAlign = TextAlign.Center
            )
        }

        Button(
            colors = ButtonDefaults.buttonColors(containerColor = colorResource(id = R.color.transparent_green)),
            shape = RoundedCornerShape(4.dp),
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 24.dp),
            enabled = true,
            onClick = {},
        ) {

            Text(
                text = stringResource(R.string.try_again),
                color = colorResource(R.color.green),
                fontSize = 16.sp,
                modifier = Modifier
                    .padding(vertical = 6.dp, horizontal = 8.dp)
            )
        }

    }
}
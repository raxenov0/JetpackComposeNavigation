package com.iteco.iteco_navigation.screens.Authorization.components

import android.content.Intent
import android.net.Uri
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import androidx.navigation.NavController
import com.iteco.iteco_navigation.R
import com.iteco.iteco_navigation.api.ws.AuthorizationViewModel
import com.lightspark.composeqr.QrCodeView

@Composable
fun AuthFragment(navController: NavController? = null, viewModel: AuthorizationViewModel) {

    Column(
    modifier = Modifier
    .fillMaxSize()
    .padding(horizontal = 12.dp)
    ) {
        Text(
            text = stringResource(R.string.auth_title),
            fontSize = 22.sp,
            color = colorResource(R.color.black),
            modifier = Modifier
                .padding(top = 12.dp)
        )
        Column(
            modifier = Modifier
                .fillMaxHeight(),
            verticalArrangement = Arrangement.Center
        ) {
            Box(
                modifier = Modifier
                    .offset(y = 10.dp)
                    .clip(RoundedCornerShape(12.dp))
                    .background(colorResource(R.color.green))
                    .padding(vertical = 4.dp, horizontal = 16.dp)
                    .align(Alignment.CenterHorizontally)
                    .zIndex(999.0f),
            ) {
                Text(
                    text = "Способ 1",
                    textAlign = TextAlign.Center,
                    fontWeight = FontWeight(500),
                    color = colorResource(R.color.white)
                )
            }
            Box(
                modifier = Modifier
                    .padding(bottom = 46.dp)
                    .clip(RoundedCornerShape(8.dp))
                    .background(colorResource(R.color.white))
                    .padding(10.dp)
            ) {
                Column {

                    Text(
                        text = stringResource(R.string.auth_1theme_title),
                        textAlign = TextAlign.Center,
                        fontWeight = FontWeight(500),
                        modifier = Modifier
                            .padding(bottom = 12.dp, top = 8.dp)
                    )

                    Button(
                        colors = ButtonDefaults.buttonColors(containerColor = colorResource(id = R.color.transparent_green)),
                        shape = RoundedCornerShape(4.dp),
                        modifier = Modifier
                            .fillMaxWidth(),
                        enabled = true,
                        onClick = {
                            try {
                                Log.d("NaviKit", "${viewModel.error}")
                                viewModel.setQrToken("SDFSDFS")
                                val uri = Uri.parse("itecodriver://auth/navigator")
                                val intent = Intent(Intent.ACTION_VIEW, uri)

                                intent.setPackage("com.iteco.drivers");
                                //context.startActivity(intent)
                            } catch (e: Throwable) {
                                Log.d("Navigator", "Empty activity...")
                            }
                        },
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.logo),
                            contentDescription = null
                        )
                        Text(
                            text = stringResource(R.string.auth_with_app_title),
                            color = colorResource(R.color.green),
                            fontSize = 16.sp,
                            modifier = Modifier
                                .padding(vertical = 6.dp, horizontal = 8.dp)
                        )
                    }
                }
            }

            Box(
                modifier = Modifier
                    .offset(y = 10.dp)
                    .clip(RoundedCornerShape(12.dp))
                    .background(colorResource(R.color.green))
                    .padding(vertical = 4.dp, horizontal = 16.dp)
                    .align(Alignment.CenterHorizontally)
                    .zIndex(999.0f),
            ) {
                Text(
                    text = "Способ 2",
                    textAlign = TextAlign.Center,
                    fontWeight = FontWeight(500),
                    color = colorResource(R.color.white)
                )
            }
            Box(
                modifier = Modifier
                    .clip(RoundedCornerShape(8.dp))
                    .background(colorResource(R.color.white))
                    .padding(10.dp)
            ) {
                Column(
                    modifier = Modifier,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = stringResource(R.string.auth_2theme_title),
                        textAlign = TextAlign.Center,
                        fontWeight = FontWeight(500),
                        modifier = Modifier
                            .padding(bottom = 12.dp)
                    )
                    if(viewModel.qrToken.value !== null)
                        QrCodeView(
                            data = "https://github.com/lightsparkdev/compose-qr-code",
                            modifier = Modifier
                                .padding(bottom = 32.dp)
                                .size(200.dp)
                        )
                        {
//                        RotatingIcon()
                        }
                    Text(
                        text = stringResource(R.string.auth_description),
                        textAlign = TextAlign.Center,
                        fontWeight = FontWeight(400),
                        modifier = Modifier
                            .padding(bottom = 12.dp)
                    )
                }
            }
        }
    }
}
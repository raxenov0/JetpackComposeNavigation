package com.iteco.iteco_navigation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStoreOwner
import androidx.lifecycle.get
import com.iteco.iteco_navigation.api.ws.AuthorizationViewModel
import com.iteco.iteco_navigation.api.ws.WebSocketListener
import com.iteco.iteco_navigation.navigation.Navigation
import com.iteco.iteco_navigation.ui.theme.NavigationTheme
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.WebSocket

class MainActivity : ComponentActivity() {

    private lateinit var webSocketListener: WebSocketListener
    private val okHttpClient = OkHttpClient()

    private var webSocket: WebSocket? = null
    private val viewModel: AuthorizationViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen()

        webSocketListener = WebSocketListener(viewModel)
        webSocket = okHttpClient.newWebSocket(createRequest(), webSocketListener)

        setContent {
            NavigationTheme {
                Navigation(viewModel)
            }
        }
    }

    private  fun createRequest():Request{
        val webSocketUrl = "ws://192.168.5.184:8080/ws"
        return  Request.Builder().url(webSocketUrl).build()
    }
}


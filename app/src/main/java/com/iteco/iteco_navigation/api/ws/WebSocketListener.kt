package com.iteco.iteco_navigation.api.ws

import android.util.Log
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import okhttp3.Response
import okhttp3.WebSocket
import okhttp3.WebSocketListener
import okio.ByteString
class WebSocketListener(viewModel: AuthorizationViewModel): WebSocketListener() {

    val viewModel = viewModel
    override fun onOpen(webSocket: WebSocket, response: Response) {
        super.onOpen(webSocket, response)
        switchMachine(webSocket, viewModel.stateIntMachine, null)
       // viewModel.setStatus(true)
        Log.d("Websocket", "Connect")
    }

    override fun onMessage(webSocket: WebSocket, text: String) {
        super.onMessage(webSocket, text)
        Log.d("Websocket", "onMessage")
        switchMachine(webSocket, viewModel.stateIntMachine, text)
    }

    override fun onClosing(webSocket: WebSocket, code: Int, reason: String) {
        super.onClosing(webSocket, code, reason)
        //_status.value = false
        Log.d("Websocket", "onClosing")
    }

    override fun onClosed(webSocket: WebSocket, code: Int, reason: String) {
        super.onClosed(webSocket, code, reason)
        //_status.value = false
        Log.d("Websocket", "onClosed")
    }

    override fun onFailure(webSocket: WebSocket, t: Throwable, response: Response?) {
        super.onFailure(webSocket, t, response)
        Log.d("Websocket", "onFailure ${response?.message}")
        Log.d("NaviKit", response?.code.toString())
        viewModel.setError(StatusCode.SERVER_ERROR)
        Log.d("NaviKit", "viewModel created: ${viewModel.qrToken}")
        if(response?.code != null){
            viewModel.setError(response.code as StatusCode)
        }
        // Обработка ошибок
    }


    fun switchMachine(ws: WebSocket, int: StateInt, data: String?) {
        outPut("ROOT")
        when (int) {
            StateInt.CHECK_INSTALL -> {
                if (viewModel.atToken == null) {
                    outPut("CHECK INSTALL WITH EMPTY TOKEN")
                    ws.send("REG 111")
                   // viewModel.setStateInt(StateInt.DEVICE_REGISTR)
                } else {
                    outPut("CHECK INSTALL WITH TOKEN")
                    ws.send("AUTH ${viewModel.atToken}")
                    //viewModel.setStateInt(StateInt.AUTH_DEVICE)
                }
                return
            }
            StateInt.DEVICE_REGISTR -> {
                outPut("QR TOKEN: $data")
                val splittedStroke = data?.split(" ")
                val prefixCode =  if(!splittedStroke.isNullOrEmpty()) splittedStroke[0] else ""
                when (prefixCode) {
                    "DEVICE_AUTH_CODE" -> {
                        val token =  if(!splittedStroke.isNullOrEmpty()) splittedStroke[1] else ""
                        viewModel.setQrToken(token)
                       // viewModel.setStateInt(StateInt.AUTH_DEVICE)
                    }
                    else -> {
                        outPut("INVALID STATE(DEVICE_AUTH_CODE):$prefixCode!")
                    }
                }
                return
            }
            StateInt.AUTH_DEVICE -> {
                outPut("NEW AUTH TOKEN:$data")
                val splittedStroke = data?.split(" ")
                val prefixCode =  if(!splittedStroke.isNullOrEmpty()) splittedStroke[0] else ""
                when (prefixCode) {
                    "AUTH_CODE" -> {
                        val token =  if(!splittedStroke.isNullOrEmpty()) splittedStroke[1] else ""
                        viewModel.setAtToken(token)
                       // viewModel.setStateInt(StateInt.DATE_RECEIVE)
                    }
                    else -> {
                        outPut("INVALID STATE(AUTH_CODE):$prefixCode")
                    }
                }
                return
            }
            StateInt.DATE_RECEIVE -> {
                outPut("DATE: $data")
                return
            }
            else -> {
                outPut("DEFAULT SWITCH: $data")
                return
            }
        }
    }

    fun outPut(text: String){
        Log.d("Websocket", text)
    }
}




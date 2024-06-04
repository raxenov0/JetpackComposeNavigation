package com.iteco.iteco_navigation.api.ws


import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

enum class StateInt {
    CHECK_INSTALL,
    DEVICE_REGISTR,
    AUTH_DEVICE,
    DATE_RECEIVE
}

enum class StatusCode(val code: Int){
    SERVER_ERROR(500),
    TO_MANY_REQUEST(429),
    NOT_FOUND(404),
    FORBIDDEN(403),
    UNAUTHORIZED(401),
    BAD_REQUEST(400),
    SUCCESS_POST(401),
    SUCCESS_GET(200)
}
class AuthorizationViewModel: ViewModel() {

     val status = mutableStateOf<Int?>(null)
     val qrToken = mutableStateOf<String?>(null)
     val atToken = mutableStateOf<String?>(null)

    val stateIntMachine by mutableStateOf<StateInt>(StateInt.CHECK_INSTALL)
    var error =  mutableStateOf<StatusCode?>(null)

    //val stateIntMachine = MutableStateFlow<StateInt>(StateInt.CHECK_INSTALL)

    fun setError(code: StatusCode?){
        if(code != null && code > StatusCode.SUCCESS_POST){
           error.value = StatusCode.SERVER_ERROR
        } else {
            error.value = null
        }
    }
    fun setStatus(value: Int?){
        status.value = value
    }
    fun setQrToken(value: String?){
        qrToken.value = value
    }
    fun setAtToken(value: String?){
        atToken.value = value
    }
}
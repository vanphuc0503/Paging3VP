package com.vanphuc.pagingdata3vp.ui.base

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.*
import javax.inject.Inject

@HiltViewModel
open class BaseViewModel @Inject constructor() : ViewModel() {
    private val _errorMessage = MutableLiveData<String>()
    val errorMessage = _errorMessage

    private val isLoading = SingleLiveData(false)

    private val exceptionHandler = CoroutineExceptionHandler { _, throwable ->
        viewModelScope.launch {
            onLoadFail(throwable)
        }
    }

    /**
     * handle throwable when load fail
     */
    open suspend fun onLoadFail(throwable: Throwable) {
        throwable.printStackTrace()
        withContext(Dispatchers.Main) {
            throwable.message?.let {
                errorMessage.value = it
            }
        }
    }

    protected val viewModelScopeExceptionHandler = viewModelScope + exceptionHandler

    protected fun <T> callResult(
        call: suspend () -> T,
        needBlocking: Boolean = false,
        result: CoroutineScope.(T) -> Unit = {}
    ) {
        viewModelScopeExceptionHandler.launch {
            if (needBlocking) {
                isLoading.postValue(true)
            }
            withContext(Dispatchers.IO) {
                val response = call()
                result(response)
            }
            if (needBlocking) isLoading.postValue(false)
        }
    }

    protected fun <T> callApiResult(
        call: suspend () -> T,
        needBlocking: Boolean = false,
        result: (T) -> Unit
    ) {
        try {
            callResult(call, needBlocking) { data ->
                result.invoke(data)
            }
        } catch (ex: Exception) {
            errorMessage.postValue(ex.toString())
        }
    }
}
package com.akv.pixlock.ui.lockscreen.viewmodels

import android.content.Context
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.akv.pixlock.ui.lockscreen.LockscreenErrorType
import com.akv.pixlock.utils.getValue
import com.akv.pixlock.utils.hapticFeedBack
import com.akv.pixlock.utils.saveValue
import kotlinx.coroutines.launch

class LockScreenViewModel : ViewModel() {

    private val _password = mutableStateOf("")
    val password: State<String> get() = _password

    private val _errorState = mutableStateOf(LockscreenErrorType.NO_ERROR)
    val errorState: State<LockscreenErrorType> get() = _errorState

    fun updatePassword(key: String) {
        _errorState.value = LockscreenErrorType.NO_ERROR
        if (_password.value.length < 6)
            _password.value += key
    }

    fun passNotCreated(ctx: Context): Boolean {
        return getValue(key = "password", ctx = ctx) == null
    }

    fun delKey() {
        if (_errorState.value != LockscreenErrorType.NO_ERROR) {
            _password.value = ""
        }
        if (_password.value.isNotEmpty()) {
            _password.value = _password.value.dropLast(1)
        }
        _errorState.value = LockscreenErrorType.NO_ERROR
    }

    fun validate(pass: String): Boolean {
        return if (pass.length != 6) {
            _errorState.value = LockscreenErrorType.PASSCODE_TOO_SHORT
            false
        } else {
            _errorState.value = LockscreenErrorType.NO_ERROR
            true
        }
    }

    private fun confirmPass(pass: String): Boolean {
        return if (pass == _password.value) {
            _errorState.value = LockscreenErrorType.PASSCODE_CORRECT
            true
        } else {
            _errorState.value = LockscreenErrorType.PASSCODE_DOES_NOT_MATCH
            false
        }
    }

    fun savePassword(ctx: Context, pass: String): Boolean {
        return if (confirmPass(pass = pass) && validate(pass = pass)) {
            viewModelScope.launch {
                saveValue(ctx = ctx, key = "password", value = _password.value)
            }
            true
        } else {
            false
        }
    }

    fun clearError() {
        _errorState.value = LockscreenErrorType.NO_ERROR
    }

    fun enableHaptics(ctx: Context) {
        hapticFeedBack(ctx)
    }

    fun authenticatePass(ctx: Context): Boolean {
        return if (_password.value == getValue(ctx = ctx, key = "password")) {
            _errorState.value = LockscreenErrorType.PASSCODE_CORRECT
            true
        } else {
            _errorState.value = LockscreenErrorType.PASSCODE_INCORRECT
            false
        }
    }
}
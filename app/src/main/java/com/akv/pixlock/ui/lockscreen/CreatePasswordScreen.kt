package com.akv.pixlock.ui.lockscreen

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.akv.pixlock.ui.lockscreen.viewmodels.LockScreenViewModel

class CreatePasswordScreen : Screen {
    @Composable
    override fun Content() {

        val lockScreenVm = viewModel<LockScreenViewModel>()
        val navigator = LocalNavigator.currentOrThrow
        val pass = lockScreenVm.password.value

        BaseLockScreen(
            title = "Create Your Password",
            lockScreenVm = lockScreenVm
        ) { key ->
            when (key) {
                "del" -> {
                    lockScreenVm.delKey()
                }
                "next" -> {
                    if (lockScreenVm.validate(pass)) {
                        navigator.push(ConfirmPasswordScreen(pass))
                    }
                }

                else -> {
                    lockScreenVm.updatePassword(key)
                }
            }
        }
    }
}
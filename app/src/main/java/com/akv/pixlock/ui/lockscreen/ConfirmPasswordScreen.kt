package com.akv.pixlock.ui.lockscreen

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.akv.pixlock.ui.lockscreen.viewmodels.LockScreenViewModel

data class ConfirmPasswordScreen(val pass: String) : Screen {
    @Composable
    override fun Content() {

        val lockScreenVm = viewModel<LockScreenViewModel>()
        val navigator = LocalNavigator.currentOrThrow
        val ctx = LocalContext.current

        BaseLockScreen(
            title = "Confirm Your Password",
            lockScreenVm = lockScreenVm
        ) { key ->
            when (key) {
                "del" -> {
                    lockScreenVm.delKey()
                }

                "next" -> {
                    if (lockScreenVm.savePassword(ctx, pass)) {
                        navigator.replaceAll(PasswordInputScreen())
                    }
                }
                else -> {
                    lockScreenVm.updatePassword(key)
                }
            }
        }
    }
}
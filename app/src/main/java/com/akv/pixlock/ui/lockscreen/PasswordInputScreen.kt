package com.akv.pixlock.ui.lockscreen

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.akv.pixlock.ui.homescreen.HomeScreen
import com.akv.pixlock.ui.lockscreen.viewmodels.LockScreenViewModel

class PasswordInputScreen : Screen {
    @Composable
    override fun Content() {

        val context = LocalContext.current
        val lockScreenVm = LockScreenViewModel()
        val navigator = LocalNavigator.currentOrThrow

        BaseLockScreen(
            title = "Enter Your Password",
            lockScreenVm = lockScreenVm
        ) { key ->
            when (key) {
                "del" -> {
                    lockScreenVm.delKey()
                }
                "next" -> {
                    if (lockScreenVm.authenticatePass(context)) {
                        navigator.push(HomeScreen())
                    }
                }
                else -> {
                    lockScreenVm.updatePassword(key)
                }
            }
        }
    }
}
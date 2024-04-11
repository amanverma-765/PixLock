package com.akv.pixlock

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import cafe.adriel.voyager.navigator.Navigator
import cafe.adriel.voyager.transitions.SlideTransition
import com.akv.pixlock.theme.PixLockTheme
import com.akv.pixlock.ui.lockscreen.CreatePasswordScreen
import com.akv.pixlock.ui.lockscreen.PasswordInputScreen
import com.akv.pixlock.ui.lockscreen.viewmodels.LockScreenViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            PixLockTheme {

                val lockScreenVm = viewModel<LockScreenViewModel>()
                val ctx = LocalContext.current

                Navigator(
                    if (lockScreenVm.passNotCreated(ctx)) {
                        CreatePasswordScreen()
                    }
                    else {
                        PasswordInputScreen()
                    }
                ) { navigator ->
                    SlideTransition(
                        navigator = navigator,
                        animationSpec = tween(
                            easing = FastOutSlowInEasing,
                            durationMillis = 500
                        )
                    )
                }
            }
        }
    }
}

package com.akv.pixlock

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import cafe.adriel.voyager.navigator.Navigator
import cafe.adriel.voyager.transitions.SlideTransition
import com.akv.pixlock.theme.PixLockTheme
import com.akv.pixlock.ui.homescreen.HomeScreen
import com.akv.pixlock.ui.lockscreen.BaseLockScreen
import com.akv.pixlock.ui.lockscreen.PasswordInputScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            PixLockTheme {
                Navigator(PasswordInputScreen()) { navigator ->
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

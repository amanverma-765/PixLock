package com.akv.pixlock.ui.homescreen.tabs

import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import cafe.adriel.voyager.navigator.Navigator
import cafe.adriel.voyager.navigator.tab.Tab
import cafe.adriel.voyager.navigator.tab.TabOptions
import cafe.adriel.voyager.transitions.SlideTransition
import com.akv.pixlock.ui.homescreen.MediaScreen
import compose.icons.FeatherIcons
import compose.icons.feathericons.Image

object MediaTab: Tab {
    private fun readResolve(): Any = MediaTab
    override val options: TabOptions
        @Composable
        get() {
            val title = "Media"
            val icon = rememberVectorPainter(FeatherIcons.Image)

            return remember {
                TabOptions(
                    index = 0u,
                    title = title,
                    icon = icon
                )
            }
        }

    @Composable
    override fun Content() {
        Navigator(screen = MediaScreen()) { navigator ->
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
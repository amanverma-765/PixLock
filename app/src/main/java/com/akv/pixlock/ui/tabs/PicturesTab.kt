package com.akv.pixlock.ui.tabs

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import cafe.adriel.voyager.navigator.tab.Tab
import cafe.adriel.voyager.navigator.tab.TabOptions
import compose.icons.FeatherIcons
import compose.icons.feathericons.Image

object PicturesTab : Tab {
    private fun readResolve(): Any = PicturesTab
    override val options: TabOptions
        @Composable
        get() {
            val title = "Pictures"
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

        Text(text = "Pictures Tab Content")
    }
}
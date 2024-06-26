package com.akv.pixlock.ui.homescreen.tabs

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import cafe.adriel.voyager.navigator.tab.Tab
import cafe.adriel.voyager.navigator.tab.TabOptions
import compose.icons.FeatherIcons
import compose.icons.feathericons.Slack

object CollectionsTab : Tab {
    private fun readResolve(): Any = CollectionsTab
    override val options: TabOptions
        @Composable
        get() {
            val title = "Collections"
            val icon = rememberVectorPainter(FeatherIcons.Slack)

            return remember {
                TabOptions(
                    index = 1u,
                    title = title,
                    icon = icon
                )
            }
        }

    @Composable
    override fun Content() {

        Text(text = "Collections Tab Content")
    }
}
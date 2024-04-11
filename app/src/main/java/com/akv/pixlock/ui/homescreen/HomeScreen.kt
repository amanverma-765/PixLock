package com.akv.pixlock.ui.homescreen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.tab.CurrentTab
import cafe.adriel.voyager.navigator.tab.LocalTabNavigator
import cafe.adriel.voyager.navigator.tab.Tab
import cafe.adriel.voyager.navigator.tab.TabNavigator
import com.akv.pixlock.ui.components.PixLockTopBar
import com.akv.pixlock.ui.tabs.CollectionsTab
import com.akv.pixlock.ui.tabs.PicturesTab
import com.akv.pixlock.ui.tabs.SearchTab

class HomeScreen(): Screen {
    @Composable
    override fun Content() {
        TabNavigator(tab = PicturesTab) {
            Scaffold(
                topBar = { PixLockTopBar() },
                floatingActionButton = {
                    FloatingActionButton(onClick = { /*TODO*/ }) {
                        Icon(
                            imageVector = Icons.Filled.Add,
                            contentDescription = "add"
                        )
                    }
                },
                bottomBar = {
                    NavigationBar {
                        TabNavigationItem(PicturesTab)
                        TabNavigationItem(CollectionsTab)
                        TabNavigationItem(SearchTab)
                    }
                }
            ) { paddingValues ->
                Column(Modifier.padding(paddingValues)) {
                    CurrentTab()
                }
            }
        }
    }
}

@Composable
private fun RowScope.TabNavigationItem(tab: Tab) {
    val tabNavigator = LocalTabNavigator.current
    NavigationBarItem(
        selected = tabNavigator.current == tab,
        onClick = { tabNavigator.current = tab },
        icon = {
            tab.options.icon?.let {
                Icon(
                    painter = it,
                    contentDescription = tab.options.title
                )
            }
        },
        label = {
            tab.options.title.let { Text(it) }
        }
    )
}

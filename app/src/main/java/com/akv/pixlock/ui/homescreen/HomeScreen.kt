package com.akv.pixlock.ui.homescreen

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxSize
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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.tab.CurrentTab
import cafe.adriel.voyager.navigator.tab.LocalTabNavigator
import cafe.adriel.voyager.navigator.tab.Tab
import cafe.adriel.voyager.navigator.tab.TabNavigator
import com.akv.pixlock.ui.homescreen.components.PixLockTopBar
import com.akv.pixlock.ui.homescreen.tabs.CollectionsTab
import com.akv.pixlock.ui.homescreen.tabs.MediaTab
import com.akv.pixlock.ui.homescreen.tabs.SearchTab
import com.akv.pixlock.ui.homescreen.viewmodels.MediaScreenViewModel
import kotlinx.coroutines.delay

class HomeScreen() : Screen {
    @Composable
    override fun Content() {

        val mediaScreenVm = viewModel<MediaScreenViewModel>()
        val context = LocalContext.current

        val selectedImages = remember {
            mutableStateOf<List<Uri>>(emptyList())
        }

        val mediaPicker = rememberLauncherForActivityResult(
            contract = ActivityResultContracts.PickMultipleVisualMedia(),
            onResult = { uris ->
                selectedImages.value = uris
            }
        )

        LaunchedEffect(key1 = selectedImages.value) {
            mediaScreenVm.saveMedia(context, selectedImages.value)
        }

        TabNavigator(tab = MediaTab) {
            Scaffold(
                topBar = { PixLockTopBar() },
                floatingActionButton = {
                    FloatingActionButton(
                        onClick = {
                            mediaPicker.launch(
                                PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly)
                            )
                        }
                    ) {
                        Icon(
                            imageVector = Icons.Filled.Add,
                            contentDescription = "add"
                        )
                    }
                },
                bottomBar = {
                    NavigationBar {
                        TabNavigationItem(MediaTab)
                        TabNavigationItem(CollectionsTab)
                        TabNavigationItem(SearchTab)
                    }
                }
            ) { paddingValues ->
                Box(Modifier.fillMaxSize().padding(paddingValues)) {
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

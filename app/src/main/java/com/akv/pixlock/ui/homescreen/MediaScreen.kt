package com.akv.pixlock.ui.homescreen

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import cafe.adriel.voyager.core.screen.Screen
import com.akv.pixlock.ui.homescreen.viewmodels.MediaScreenViewModel
import com.akv.pixlock.utils.CoilImageLoader

class MediaScreen : Screen {

    @Composable
    override fun Content() {

        val lazyState = rememberLazyGridState()
        val mediaScreenVm = viewModel<MediaScreenViewModel>()
        val media = mediaScreenVm.mediaList.collectAsState()
        val context = LocalContext.current

        LaunchedEffect(key1 = Unit) {
            mediaScreenVm.updateMedia(context)
        }

        LazyVerticalGrid(
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(12.dp),
            state = lazyState,
            columns = GridCells.Fixed(count = 2),
            verticalArrangement = Arrangement.spacedBy(12.dp),
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {

            items(media.value.sortedDescending()) { imgUri ->

                CoilImageLoader(
                    modifier = Modifier
                        .height(200.dp)
                        .clip(RoundedCornerShape(12.dp))
                        .background(MaterialTheme.colorScheme.surfaceContainer),
                    imgUri = imgUri
                )
            }
        }
    }
}


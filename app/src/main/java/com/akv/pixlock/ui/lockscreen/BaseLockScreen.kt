package com.akv.pixlock.ui.lockscreen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import com.akv.pixlock.ui.components.PixLockLogo
import com.akv.pixlock.ui.lockscreen.components.PassInputField
import com.akv.pixlock.ui.lockscreen.components.PasswordButtons
import com.akv.pixlock.ui.lockscreen.viewmodels.LockScreenViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BaseLockScreen(
    title: String,
    lockScreenVm: LockScreenViewModel,
    onClick: (String) -> Unit
) {

    val ctx = LocalContext.current

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    PixLockLogo()
                }
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
            verticalArrangement = Arrangement.Bottom
        ) {

            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .weight(1f),
                contentAlignment = Alignment.Center
            ) {
                PassInputField(title, lockScreenVm)
            }

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(0.5f)
                    .clip(RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp))
                    .background(MaterialTheme.colorScheme.surfaceContainerHigh)
            ) {
                Box(
                    modifier = Modifier
                        .padding(8.dp)
                        .width(170.dp)
                        .height(4.dp)
                        .clip(CircleShape)
                        .background(MaterialTheme.colorScheme.onBackground.copy(alpha = 0.2f))
                        .align(Alignment.CenterHorizontally)
                )

                LazyVerticalGrid(
                    modifier = Modifier.fillMaxSize(),
                    contentPadding = PaddingValues(4.dp),
                    columns = GridCells.Fixed(3),
                ) {
                    items(buttons) { button ->
                        PasswordButtons(
                            modifier = Modifier.padding(4.dp),
                            passButton = button
                        ) { key ->
                            lockScreenVm.enableHaptics(ctx)
                            onClick(key)
                        }
                    }
                }
            }
        }
    }

}


val buttons = listOf(
    PasswordButtons(content = "1"),
    PasswordButtons(content = "2"),
    PasswordButtons(content = "3"),
    PasswordButtons(content = "4"),
    PasswordButtons(content = "5"),
    PasswordButtons(content = "6"),
    PasswordButtons(content = "7"),
    PasswordButtons(content = "8"),
    PasswordButtons(content = "9"),
    PasswordButtons(
        content = "del",
        icons = Icons.Default.Clear,
    ),
    PasswordButtons(content = "0"),
    PasswordButtons(
        content = "next",
        icons = Icons.AutoMirrored.Filled.ArrowForward,
    )
)
package com.akv.pixlock.ui.lockscreen.components

import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun PasswordButtons(
    modifier: Modifier = Modifier,
    color: Color = MaterialTheme.colorScheme.background,
    contentColor: Color = MaterialTheme.colorScheme.onBackground,
    passButton: PasswordButtons,
    onClick: (String) -> Unit
) {

    val displaySize = LocalConfiguration.current.screenHeightDp.dp
    val btnHeight = ((displaySize / 2) / 5 - 4.dp)

    LaunchedEffect(key1 = btnHeight) {
        Log.e("TAG", "PasswordButtons: $btnHeight", )
    }

    Surface(
        modifier = modifier
            .height(btnHeight)
            .heightIn(min = 40.dp)
            .clip(RoundedCornerShape(45.dp))
            .clickable {
                onClick(passButton.content)
            },
        color = color,
        contentColor = contentColor,
    ) {

        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(12.dp),
            contentAlignment = Alignment.Center

        ) {
            if (passButton.icons == null) {
                Text(
                    text = passButton.content,
                    fontSize = (btnHeight.value / 2).sp
                )
            } else {
                Icon(
                    modifier = Modifier
                        .scale(1.5f)
                        .height(btnHeight),
                    imageVector = passButton.icons,
                    contentDescription = null,
                    tint = if (passButton.content == "del") {
                        MaterialTheme.colorScheme.error.copy(red = 1f)
                    } else {
                        MaterialTheme.colorScheme.primary
                    }
                )
            }
        }

    }
}


data class PasswordButtons(
    val content: String,
    val icons: ImageVector? = null,
)




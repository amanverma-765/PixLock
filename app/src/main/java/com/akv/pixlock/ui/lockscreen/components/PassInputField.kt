package com.akv.pixlock.ui.lockscreen.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.akv.pixlock.ui.lockscreen.LockscreenErrorType
import com.akv.pixlock.ui.lockscreen.viewmodels.LockScreenViewModel
import javax.security.auth.login.LoginException

@Composable
fun PassInputField(title: String, lockScreenVm: LockScreenViewModel) {

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Text(
            modifier = Modifier.width(300.dp),
            text = title,
            style = MaterialTheme.typography.headlineSmall,
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.height(30.dp))

        Row(
            modifier = Modifier
                .wrapContentSize(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {

            for (i in 0..< 6) {
                PassInputBox(
                    key = lockScreenVm.password.value.getOrNull(i),
                    borderColor = when (lockScreenVm.errorState.value) {
                        LockscreenErrorType.NO_ERROR -> MaterialTheme.colorScheme.onSurface.copy(alpha = .2f)
                        LockscreenErrorType.PASSCODE_CORRECT -> MaterialTheme.colorScheme.primary
                        else -> MaterialTheme.colorScheme.error
                    }
                )
            }
        }

        Spacer(modifier = Modifier.height(20.dp))

        Text(
            text = when(lockScreenVm.errorState.value) {
                LockscreenErrorType.NO_ERROR -> ""
                LockscreenErrorType.PASSCODE_CORRECT -> ""
                LockscreenErrorType.PASSCODE_TOO_SHORT -> "Passcode length is too short"
                LockscreenErrorType.PASSCODE_DOES_NOT_MATCH -> "Passcode does not match"
                LockscreenErrorType.PASSCODE_INCORRECT -> "Incorrect Passcode"
            },
            style = MaterialTheme.typography.bodyLarge,
            color = MaterialTheme.colorScheme.error
        )
    }
}


@Composable
private fun PassInputBox(key: Char?, borderColor: Color) {

    Box(
        modifier = Modifier
            .wrapContentHeight()
            .defaultMinSize(
                minWidth = 45.dp,
                minHeight = 55.dp
            )
            .clip(RoundedCornerShape(15.dp))
            .background(MaterialTheme.colorScheme.surfaceContainer)
            .border(
                1.dp,
                borderColor,
                RoundedCornerShape(15.dp)
            ),
        contentAlignment = Alignment.Center
    ) {
        AnimatedVisibility(
            visible = key != null,
            enter = scaleIn(),
            exit = scaleOut()
        ) {
            Text(
                text = key?.toString() ?: "",
                style = MaterialTheme.typography.headlineLarge,
                maxLines = 1,
            )
        }
    }

}
package com.am.nativeprogressindicator.ui

import android.widget.ProgressBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.viewinterop.AndroidView

// Actual implementation for Android
@Composable
actual fun PlatformSpecificProgressIndicator() {
    AndroidProgressIndicator()
}

@Composable
fun AndroidProgressIndicator() {
    AndroidView(factory = { context ->
        ProgressBar(context).apply {
            isIndeterminate = true // Makes it a spinner
        }
    })
}

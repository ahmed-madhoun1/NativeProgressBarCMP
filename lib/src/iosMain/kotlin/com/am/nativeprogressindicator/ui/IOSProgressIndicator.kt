package com.am.nativeprogressindicator.ui

import androidx.compose.runtime.Composable
import androidx.compose.ui.interop.UIKitView
import platform.UIKit.UIActivityIndicatorView
import platform.UIKit.UIActivityIndicatorViewStyleMedium
import platform.UIKit.startAnimating

// Actual implementation for iOS
@Composable
actual fun PlatformSpecificProgressIndicator() {
    IOSProgressIndicator()
}

@Composable
fun IOSProgressIndicator() {
    UIKitView(factory = {
        UIActivityIndicatorView(UIActivityIndicatorViewStyleMedium).apply {
            startAnimating() // Starts the spinner animation
        }
    })
}

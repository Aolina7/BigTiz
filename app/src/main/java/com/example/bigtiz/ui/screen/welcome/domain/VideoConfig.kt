package com.example.bigtiz.ui.screen.welcome.domain

import android.media.MediaPlayer

data class VideoConfig(
    val videoResId: Int,
    val isLooping: Boolean = true,
    val volume: Float = 0f,
    val scalingMode: Int = MediaPlayer.VIDEO_SCALING_MODE_SCALE_TO_FIT_WITH_CROPPING,
    val overlayAlpha: Float = 0.45f,
    val fallbackOverlayAlpha: Float = 0.6f
)
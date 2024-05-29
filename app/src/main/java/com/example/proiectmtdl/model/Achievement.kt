package com.example.proiectmtdl.model

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import com.example.proiectmtdl.R
import kotlin.random.Random

data class Badge(
    val iconId: Int = R.drawable.streaming,
    val name: String = "",
    val count: Int = 0
) {
    var color = Color(Random.nextInt(256), Random.nextInt(256), Random.nextInt(256))
}
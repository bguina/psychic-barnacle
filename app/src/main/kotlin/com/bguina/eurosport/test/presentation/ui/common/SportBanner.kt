package com.bguina.eurosport.test.presentation.ui.common

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import java.util.Locale

@Composable
fun SportBanner(
    sport: String,
    bannerContent: @Composable () -> Unit
) {
    Box(
        modifier = Modifier
            .height(216.dp)
            .fillMaxWidth(),
    ) {
        Surface(
            modifier = Modifier
                .height(200.dp)
                .fillMaxWidth(),
            content = bannerContent,
        )
        Text(
            modifier = Modifier
                .padding(12.dp, 0.dp)
                .clip(RoundedCornerShape(8.dp))
                .background(Color(0xFF141B4D))
                .align(Alignment.BottomStart)
                .padding(8.dp),
            color = Color.White,
            text = sport.uppercase(Locale.getDefault()),
            style = MaterialTheme.typography.bodySmall,
            fontWeight = FontWeight.SemiBold,
        )
    }
}

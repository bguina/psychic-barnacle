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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.bguina.eurosport.test.presentation.ui.theme.EurosportTheme
import java.util.Locale

@Preview
@Composable
fun Preview() {
    EurosportTheme {
        SportBanner(sport = "Football") {
        }
    }
}

@Composable
fun SportBanner(
    sport: String,
    height: Dp = 200.dp,
    bannerContent: @Composable () -> Unit
) {
    val sportTextHeight = 32.dp
    Box(
        modifier = Modifier
            .height(height + sportTextHeight / 2)
            .fillMaxWidth(),
    ) {
        Surface(
            modifier = Modifier
                .height(height)
                .fillMaxWidth(),
            content = bannerContent,
        )
        Text(
            modifier = Modifier
                .align(Alignment.BottomStart)
                .padding(16.dp, 0.dp)
                .height(sportTextHeight)
                .clip(RoundedCornerShape(8.dp))
                .background(Color(0xFF141B4D))
                .padding(8.dp),
            color = Color.White,
            text = sport.uppercase(Locale.getDefault()),
            style = MaterialTheme.typography.bodySmall,
            fontWeight = FontWeight.SemiBold,
        )
    }
}

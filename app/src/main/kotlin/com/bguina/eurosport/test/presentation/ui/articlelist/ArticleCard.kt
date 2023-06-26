package com.bguina.eurosport.test.presentation.ui.articlelist

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.bguina.eurosport.test.presentation.ui.common.SportBanner

@Composable
fun ArticleCard(
    sport: String,
    title: String,
    mediaInfo: String,
    onArticleClicked: () -> Unit = {},
    bannerContent: @Composable () -> Unit,
) {
    Card(
        modifier = Modifier
            .padding(8.dp)
            .clickable { onArticleClicked() },
        shape = RoundedCornerShape(8.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
    ) {
        SportBanner(
            sport = sport,
            bannerContent = bannerContent,
        )
        Column(modifier = Modifier.padding(12.dp, 0.dp)) {

            Text(
                text = title,
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold,
                lineHeight = 18.sp,
            )
            Text(
                modifier=Modifier.padding(0.dp, 0.dp, 0.dp, 8.dp),
                text = mediaInfo,
                style = MaterialTheme.typography.bodySmall,
                color = Color.Gray,
            )
        }
    }
}

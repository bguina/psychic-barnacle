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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.bguina.eurosport.test.presentation.ui.common.SportBanner
import com.bguina.eurosport.test.presentation.ui.theme.EurosportTheme

@Preview
@Composable
fun PreviewArticleCard() {
    EurosportTheme {
        ArticleCard(
            sport = "football",
            title = "Title which is a little too long to be displayed on a single line",
            articleInfo = "article info",
        ) {

        }
    }
}

@Composable
fun ArticleCard(
    sport: String,
    title: String,
    articleInfo: String,
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
        Column(modifier = Modifier.padding(16.dp, 0.dp)) {
            Text(
                text = title,
                style = MaterialTheme.typography.titleLarge,
            )
            Text(
                modifier = Modifier.padding(0.dp, 0.dp, 0.dp, 8.dp),
                text = articleInfo,
                style = MaterialTheme.typography.bodySmall,
                color = Color.Gray,
            )
        }
    }
}

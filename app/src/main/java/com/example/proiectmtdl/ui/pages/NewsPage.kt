package com.example.proiectmtdl.ui.pages

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.semantics.selected
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import androidx.compose.ui.unit.dp
import com.example.proiectmtdl.model.NewsItem
import com.example.proiectmtdl.model.User
import com.example.proiectmtdl.model.UserType

//one news card
@Composable
fun HelpNPlayNewsItem(
    modifier: Modifier = Modifier,
    isSelected: Boolean = false,
    isOpened: Boolean = false,
    newsItem: NewsItem
){
    Card (
        modifier = modifier
            .padding(horizontal = 16.dp, vertical = 4.dp)
            .semantics { selected = isSelected }
            .clip(CardDefaults.shape),
        colors = CardDefaults.cardColors(
            containerColor = if (isSelected) MaterialTheme.colorScheme.primaryContainer
            else if (isOpened) MaterialTheme.colorScheme.secondaryContainer
            else MaterialTheme.colorScheme.surfaceVariant
        )
    ){
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp)
        ) {
            Row(modifier = Modifier.fillMaxWidth()){
                Column(
                    modifier = Modifier
                        .weight(1f)
                        .padding(horizontal = 12.dp, vertical = 4.dp),
                    verticalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = "First name",
                        style = MaterialTheme.typography.labelMedium
                    )
                    Text(
                        text = "Last name",
                        style = MaterialTheme.typography.labelMedium,
                    )
                }
                IconButton(
                    onClick = { /*TODO*/ },
                    modifier = Modifier
                        .clip(CircleShape)
//                        .background(MaterialTheme.colorScheme.surfaceContainerHigh)
                ) {
                    Icon(
                        imageVector = Icons.Default.Star,
                        contentDescription = "Favorite",
                        tint = MaterialTheme.colorScheme.outline
                    )
                }
            }

            Text(
                text = "Email subject",
                style = MaterialTheme.typography.bodyLarge,
                modifier = Modifier.padding(top = 12.dp, bottom = 8.dp),
            )
            Text(
                text = "Email body",
                style = MaterialTheme.typography.bodyMedium,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis
            )
        }
    }
}

//the entire news list
@Composable
fun HelpNPlayNewsList(
    modifier: Modifier = Modifier,
    newsLazyListState: LazyListState = LazyListState(0, 3),
    news: List<NewsItem> = newsListPreview
){
    Box(modifier = modifier.windowInsetsPadding(WindowInsets.statusBars)){
        LazyColumn(
            modifier = modifier
                .fillMaxWidth(),
            state = newsLazyListState
        ) {
            items(news){ item: NewsItem ->
                HelpNPlayNewsItem(
                    modifier =modifier,
                    newsItem = item
                )
            }
        }
    }
}

@Preview
@Composable
fun HelpNPlayNewsItemPreview(
    modifier: Modifier = Modifier,
    isSelected: Boolean = false,
    isOpened: Boolean = false,
    newsItem: NewsItem = NewsItem(User("creator_username", "creator_email", UserType.ADMIN, "creator_last_name"), 20)
){
    Card (
        modifier = modifier
            .padding(horizontal = 16.dp, vertical = 4.dp)
            .semantics { selected = isSelected }
            .clip(CardDefaults.shape),
        colors = CardDefaults.cardColors(
            containerColor = if (isSelected) MaterialTheme.colorScheme.primaryContainer
            else if (isOpened) MaterialTheme.colorScheme.secondaryContainer
            else MaterialTheme.colorScheme.surfaceVariant
        )
    ){
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp)
        ) {
            Row(modifier = Modifier.fillMaxWidth()){
                Column(
                    modifier = Modifier
                        .weight(1f)
                        .padding(horizontal = 12.dp, vertical = 4.dp),
                    verticalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = "First Name",
                        style = MaterialTheme.typography.labelMedium
                    )
                    Text(
                        text = "Last name",
                        style = MaterialTheme.typography.labelMedium,
                    )
                }
                IconButton(
                    onClick = { /*TODO*/ },
                    modifier = Modifier
                        .clip(CircleShape)
//                        .background(MaterialTheme.colorScheme.surfaceContainerHigh)
                ) {
                    Icon(
                        imageVector = Icons.Default.Star,
                        contentDescription = "Favorite",
                        tint = MaterialTheme.colorScheme.outline
                    )
                }
            }

            Text(
                text = "Email subject",
                style = MaterialTheme.typography.bodyLarge,
                modifier = Modifier.padding(top = 12.dp, bottom = 8.dp),
            )
            Text(
                text = "Email body",
                style = MaterialTheme.typography.bodyMedium,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis
            )
        }
    }
}


val newsListPreview : List<NewsItem> = kotlin.collections.listOf(
    NewsItem(), NewsItem(), NewsItem(), NewsItem(), NewsItem(), NewsItem(), NewsItem(), NewsItem(), NewsItem(), NewsItem()
)

class newsListParameterProvider : PreviewParameterProvider<List<NewsItem>>{
    override val values: Sequence<List<NewsItem>>
        get() = listOf(newsListPreview).asSequence()
}

@Preview
@Composable
fun HelpNPlayNewsListPreview(
    modifier: Modifier = Modifier,
    newsLazyListState: LazyListState = LazyListState(0, 3),
){
    Box(modifier = modifier.windowInsetsPadding(WindowInsets.statusBars)){
        LazyColumn(
            modifier = modifier
                .fillMaxWidth(),
            state = newsLazyListState
        ) {
            items(newsListPreview){ item: NewsItem ->
                HelpNPlayNewsItem(
                    modifier =modifier,
                    newsItem = item
                )
            }
        }
    }
}
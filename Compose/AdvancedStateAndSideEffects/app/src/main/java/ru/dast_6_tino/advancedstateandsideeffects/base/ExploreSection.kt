package ru.dast_6_tino.advancedstateandsideeffects.base

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImagePainter
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest.Builder
import kotlinx.coroutines.launch
import ru.dast_6_tino.advancedstateandsideeffects.R
import ru.dast_6_tino.advancedstateandsideeffects.data.ExploreModel
import ru.dast_6_tino.advancedstateandsideeffects.home.OnExploreItemClicked
import ru.dast_6_tino.advancedstateandsideeffects.ui.BottomSheetShape
import ru.dast_6_tino.advancedstateandsideeffects.ui.crane_caption
import ru.dast_6_tino.advancedstateandsideeffects.ui.crane_divider_color

@Composable
fun ExploreSection(
    modifier: Modifier = Modifier,
    title: String,
    exploreList: List<ExploreModel>,
    onItemClicked: OnExploreItemClicked,
) = Surface(modifier = modifier.fillMaxSize(), color = Color.White, shape = BottomSheetShape) {
    Column(modifier = Modifier.padding(start = 24.dp, top = 20.dp, end = 24.dp)) {
        Text(
            text = title,
            style = MaterialTheme.typography.caption.copy(color = crane_caption),
        )
        Spacer(Modifier.height(8.dp))
        Box(modifier = Modifier.weight(1f)) {
            // Show the button if the first visible item is past
            // the first item. We use a remembered derived state to
            // minimize unnecessary recompositions
            val listState = rememberLazyListState()
            ExploreList(exploreList, onItemClicked, listState = listState)

            val showButton by remember {
                derivedStateOf {
                    listState.firstVisibleItemIndex > 0
                }
            }
            if (showButton) {
                val coroutineScope = rememberCoroutineScope()
                FloatingActionButton(
                    backgroundColor = MaterialTheme.colors.primary,
                    modifier = Modifier
                        .align(Alignment.BottomEnd)
                        .navigationBarsPadding()
                        .padding(bottom = 8.dp),
                    onClick = {
                        coroutineScope.launch { listState.scrollToItem(0) }
                    },
                ) {
                    Text("Up")
                }
            }
        }
    }
}

@Composable
private fun ExploreList(
    exploreList: List<ExploreModel>,
    onItemClicked: OnExploreItemClicked,
    modifier: Modifier = Modifier,
    listState: LazyListState = rememberLazyListState(),
) = LazyColumn(
    modifier = modifier,
    contentPadding = WindowInsets.navigationBars.asPaddingValues(),
    state = listState,
) {
    items(exploreList) { exploreItem ->
        Column(Modifier.fillParentMaxWidth()) {
            ExploreItem(
                modifier = Modifier.fillParentMaxWidth(),
                item = exploreItem,
                onItemClicked = onItemClicked,
            )
            Divider(color = crane_divider_color)
        }
    }
}

@Composable
private fun ExploreItem(
    modifier: Modifier = Modifier,
    item: ExploreModel,
    onItemClicked: OnExploreItemClicked,
) = Row(
    modifier = modifier
        .clickable { onItemClicked(item) }
        .padding(top = 12.dp, bottom = 12.dp),
) {
    ExploreImageContainer {
        Box {
            val painter = rememberAsyncImagePainter(
                model = Builder(LocalContext.current)
                    .data(item.imageUrl)
                    .crossfade(true)
                    .build(),
            )
            Image(
                painter = painter,
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxSize(),
            )

            if (painter.state is AsyncImagePainter.State.Loading) {
                Image(
                    painter = painterResource(id = R.drawable.ic_crane_logo),
                    contentDescription = null,
                    modifier = Modifier
                        .size(36.dp)
                        .align(Alignment.Center),
                )
            }
        }
    }
    Spacer(Modifier.width(24.dp))
    Column {
        Text(
            text = item.city.nameToDisplay,
            style = MaterialTheme.typography.h6,
        )
        Spacer(Modifier.height(8.dp))
        Text(
            text = item.description,
            style = MaterialTheme.typography.caption.copy(color = crane_caption),
        )
    }
}

@Composable
private fun ExploreImageContainer(content: @Composable () -> Unit) = Surface(
    modifier = Modifier.size(width = 60.dp, height = 60.dp),
    shape = RoundedCornerShape(4.dp),
) {
    content.invoke()
}

package com.nitinpatil.bookanimation.ui.components

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowOutward
import androidx.compose.material3.FilledIconButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.util.lerp
import com.nitinpatil.bookanimation.R
import com.nitinpatil.bookanimation.data.CollectionItem
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.time.Duration.Companion.milliseconds

@Composable
fun FurnitureCollectionList(
    items: List<CollectionItem>,
    startAnimation: Boolean,
    skipAnimation: Boolean,
    onArrowClick: (CollectionItem) -> Unit = {}
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(340.dp)
            .padding(vertical = 30.dp)
    ) {
        items.forEachIndexed { index, item ->
            AnimatedFurnitureCard(
                item = item,
                index = index,
                startAnimation = startAnimation,
                skipAnimation = skipAnimation,
                onArrowClick = onArrowClick
            )
        }
    }
}

@Composable
fun AnimatedFurnitureCard(
    item: CollectionItem,
    index: Int,
    startAnimation: Boolean,
    skipAnimation: Boolean,
    onArrowClick: (CollectionItem) -> Unit = {}
) {
    val context = LocalDensity.current
    val screenWidthPx = with(context) {
        LocalConfiguration.current.screenWidthDp.dp.toPx()
    }
    val cardWidthPx = with(context) { 220.dp.toPx() }
    val cardHeightPx = with(context) { 280.dp.toPx() }

    val centerX = (screenWidthPx - cardWidthPx) / 2f
    val peekVisibleFraction = 0.35f

    val finalX = if (index == 0) {
        -(cardWidthPx * (1f - peekVisibleFraction))
    } else {
        screenWidthPx - (cardWidthPx * peekVisibleFraction)
    }

    val targetRotation = if (index == 0) -6f else 6f

    val progress = remember { Animatable(if (skipAnimation) 1f else 0f) }
    val rotationValue = lerp(0f, targetRotation, progress.value)
    val offsetXValue = lerp(centerX, finalX, progress.value)

    val translationsY = remember { Animatable(if (skipAnimation) 0f else cardHeightPx) }
    val alpha = remember { Animatable(if (skipAnimation) 1f else 0f) }
    val darken = remember { Animatable(if (skipAnimation) 0.35f else 0f) }

    val initialDelay = 0L
    val perItemDuration = 1000L
    val itemDelay = initialDelay + index * perItemDuration

    LaunchedEffect(startAnimation) {
        if (startAnimation && !skipAnimation) {
            delay(itemDelay.milliseconds)
            alpha.snapTo(0f)

            if (index == 2) {
                translationsY.snapTo(cardHeightPx)
                alpha.animateTo(1f, tween(900, easing = FastOutSlowInEasing))
                translationsY.animateTo(0f, tween(1300, easing = FastOutSlowInEasing))
            } else {
                progress.snapTo(0f)
                darken.snapTo(0f)
                alpha.animateTo(1f, tween(500))
                launch { darken.animateTo(0.35f, tween(1000, easing = FastOutSlowInEasing)) }
                progress.animateTo(1f, tween(1000, easing = FastOutSlowInEasing))
            }
        }
    }

    val targetScale = if (index == 2) 1f else 0.85f

    Box(
        modifier = Modifier
            .width(220.dp)
            .height(280.dp)
            .graphicsLayer {
                if (index == 2) {
                    translationX = centerX
                    translationY = translationsY.value
                    rotationZ = 0f
                } else {
                    translationX = offsetXValue
                    rotationZ = rotationValue
                }
                scaleX = targetScale
                scaleY = targetScale
                this.alpha = alpha.value
            }
            .clip(RoundedCornerShape(32.dp)),
        contentAlignment = Alignment.Center
    ) {
        Image(
            painter = painterResource(id = item.imageRes),
            contentDescription = item.title,
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize()
        )

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.BottomCenter),
            contentAlignment = Alignment.Center
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 15.dp, vertical = 10.dp)
                    .clip(RoundedCornerShape(30.dp))
                    .background(Color.White)
                    .padding(horizontal = 10.dp, vertical = 8.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Column(modifier = Modifier.weight(1f)) {
                    Text(text = item.title, fontSize = 13.sp, color = Color(0xFF4A4A4A))
                    Spacer(modifier = Modifier.height(2.dp))
                    Text(
                        text = item.discount,
                        fontSize = 15.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.Black
                    )
                }

                FilledIconButton(
                    onClick = { onArrowClick(item) },
                    shape = RoundedCornerShape(100.dp),
                    modifier = Modifier
                        .size(42.dp)
                        .semantics { contentDescription = "Navigate" },
                    colors = IconButtonDefaults.filledIconButtonColors(containerColor = Color.Black)
                ) {
                    Icon(
                        imageVector = Icons.Default.ArrowOutward,
                        contentDescription = null,
                        tint = Color.White,
                        modifier = Modifier.size(20.dp)
                    )
                }
            }
        }
    }
}

@Preview
@Composable
fun PreviewFurnitureCard() {
    AnimatedFurnitureCard(
        item = CollectionItem(
            title = "Special Choice",
            discount = "Up to 30% OFF",
            imageRes = R.drawable.yellow
        ),
        index = 0,
        startAnimation = true,
        skipAnimation = false,
        onArrowClick = {}
    )
}

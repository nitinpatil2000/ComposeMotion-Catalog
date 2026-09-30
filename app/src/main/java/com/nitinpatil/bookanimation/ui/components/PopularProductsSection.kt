package com.nitinpatil.bookanimation.ui.components

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
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
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.nitinpatil.bookanimation.data.ProductItem
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.collections.forEachIndexed
import kotlin.collections.lastIndex
import kotlin.time.Duration.Companion.milliseconds

@Composable
fun PopularProductsSection(
    products: List<ProductItem>,
    startAnimation: Boolean,
    skipAnimation: Boolean
) {

    val headerDelay = 0L
    val seeAllDelay = headerDelay + 300L
    val firstCardDelay = seeAllDelay + 600L
    val perCardStagger = 250L


    val headerOffsetX = remember { Animatable(-100f) }
    val headerAlpha = remember { Animatable(0f) }
    val seeAllAlpha = remember { Animatable(0f) }

    LaunchedEffect(startAnimation) {
        if (startAnimation) {
            if (skipAnimation) {
                // rotation: snap everything to final state, no delay/animate
                headerOffsetX.snapTo(0f)
                headerAlpha.snapTo(1f)
                seeAllAlpha.snapTo(1f)
            } else {
                launch {
                    delay(headerDelay.milliseconds)
                    launch { headerAlpha.animateTo(1f, tween(400, easing = FastOutSlowInEasing)) }
                    headerOffsetX.animateTo(0f, tween(400, easing = FastOutSlowInEasing))
                }
                launch {
                    delay(seeAllDelay.milliseconds)
                    seeAllAlpha.animateTo(1f, tween(400, easing = LinearEasing))
                }
            }
        }
    }

    Column(modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp)) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Popular Products",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black,
                modifier = Modifier.graphicsLayer {
                    translationX = headerOffsetX.value
                    alpha = headerAlpha.value
                }
            )
            Text(
                text = "See All",
                fontSize = 14.sp,
                color = Color.Gray,
                modifier = Modifier.graphicsLayer { alpha = seeAllAlpha.value }
            )

        }

        Spacer(modifier = Modifier.height(16.dp))

        products.forEachIndexed { index, product ->
            BottomFadeSlideInCard(
                visible = startAnimation,
                delayMillis = if (skipAnimation) 0L else (firstCardDelay + index * perCardStagger),
                skipAnimation = skipAnimation
            ) {
                ProductCard(product)
            }
            if (index != products.lastIndex) Spacer(modifier = Modifier.height(12.dp))
        }
    }
}


@Composable
fun BottomFadeSlideInCard(
    visible: Boolean,
    delayMillis: Long,
    skipAnimation: Boolean,
    startOffsetDp: Dp = 60.dp,
    content: @Composable () -> Unit
) {
    val density = LocalDensity.current
    val startOffsetPx = with(density) { startOffsetDp.toPx() }

    val translationsY = remember { Animatable(startOffsetPx) }
    val alpha = remember { Animatable(0f) }

    LaunchedEffect(visible) {
        if (visible) {
            if (skipAnimation) {
                translationsY.snapTo(0f)
                alpha.snapTo(1f)
                return@LaunchedEffect
            }

            translationsY.snapTo(startOffsetPx)
            alpha.snapTo(0f)
            delay(delayMillis.milliseconds)
            alpha.animateTo(1f, tween(600, easing = FastOutSlowInEasing))
            translationsY.animateTo(0f, tween(900, easing = FastOutSlowInEasing))
        }
    }

    Box(
        modifier = Modifier.graphicsLayer {
            this.alpha = alpha.value
            translationY = translationsY.value
        }
    ) {
        content()
    }
}


@Composable
fun ProductCard(product: ProductItem) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(24.dp))
            .background(Color.White)
            .padding(5.dp)
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Image(
                painter = painterResource(id = product.imageRes),
                contentDescription = product.title,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .width(100.dp)
                    .height(150.dp)
                    .clip(RoundedCornerShape(24.dp))
            )

            Spacer(modifier = Modifier.width(16.dp))

            Column(modifier = Modifier.weight(1f)) {
                Text(product.title, fontSize = 16.sp, fontWeight = FontWeight.Bold)
                Spacer(modifier = Modifier.height(4.dp))
                Text(product.description, fontSize = 13.sp, color = Color.Gray)
                Spacer(modifier = Modifier.height(8.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(product.price, fontSize = 16.sp, fontWeight = FontWeight.Bold)
                    Box(
                        modifier = Modifier
                            .clip(RoundedCornerShape(20.dp))
                            .background(Color(0xFF1E1E1E))
                            .padding(horizontal = 16.dp, vertical = 8.dp)
                    ) {
                        Text("Buy Now", color = Color.White, fontSize = 13.sp)
                    }
                }
            }
        }
    }
}
package com.nitinpatil.bookanimation.ui.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Notifications
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonColors
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.nitinpatil.bookanimation.R

@Composable
fun HeaderSection(startAnimation: Boolean) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(15.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        AnimatedVisibility(
            visible = startAnimation,
            enter = fadeIn(animationSpec = tween(durationMillis = 900, delayMillis = 500))
        ) {
            Image(
                painter = painterResource(R.drawable.book_evelyn_hugo),
                contentDescription = null,
                modifier = Modifier
                    .size(50.dp)
                    .clip(CircleShape),
                contentScale = ContentScale.Crop
            )
        }

        Spacer(modifier = Modifier.width(10.dp))

        AnimatedVisibility(
            visible = startAnimation,
            enter = fadeIn(
                animationSpec = tween(durationMillis = 900, delayMillis = 500)
            )
        ) {
            Column {
                Text(text = "Hello!!", fontSize = 14.sp, color = Color.DarkGray)
                Text(text = "Nitin Patil", fontSize = 18.sp, color = Color.Black)
            }
        }

        Spacer(modifier = Modifier.weight(1f))


        AnimatedVisibility(
            visible = startAnimation,
            enter = fadeIn(animationSpec = tween(durationMillis = 1000, delayMillis = 1000))
        ) {
            IconButton(
                onClick = {},
                modifier = Modifier
                    .size(45.dp)
                    .background(color = Color.White, shape = CircleShape),
                colors = IconButtonDefaults.iconButtonColors(
                    containerColor = Color.White,
                    contentColor = Color.Black
                )
            ) {
                Icon(
                    imageVector = Icons.Outlined.Notifications,
                    contentDescription = "Notification",
                    tint = Color.Black
                )
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun PreviewHeaderSection() {
    HeaderSection(true)
}



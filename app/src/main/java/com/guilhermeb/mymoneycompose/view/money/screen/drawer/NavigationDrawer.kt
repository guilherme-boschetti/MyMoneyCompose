package com.guilhermeb.mymoneycompose.view.money.screen.drawer

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Outline
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.*
import com.guilhermeb.mymoneycompose.R

// https://stackoverflow.com/questions/65305734/how-to-set-the-scaffold-drawer-width-in-jetpackcompose
class NavShape(
    private val widthOffset: Dp,
    private val scale: Float
) : Shape {

    override fun createOutline(
        size: Size,
        layoutDirection: LayoutDirection,
        density: Density
    ): Outline {
        return Outline.Rectangle(
            Rect(
                Offset.Zero,
                Offset(
                    size.width * scale + with(density) { widthOffset.toPx() },
                    size.height
                )
            )
        )
    }
}

// https://semicolonspace.com/jetpack-compose-navigation-drawer/
// https://www.youtube.com/watch?v=JLICaBEiJS0

@Composable
fun DrawerContent(
    items: List<DrawerItem>,
    modifier: Modifier = Modifier,
    itemTextStyle: TextStyle = TextStyle(fontSize = 16.sp),
    onItemClick: (DrawerItem) -> Unit,
    drawerWidth: Dp
) {
    Column(Modifier.fillMaxHeight().fillMaxWidth()) {
        DrawerHeader(drawerWidth)
        DrawerBody(
            items = items,
            modifier = modifier,
            itemTextStyle = itemTextStyle,
            onItemClick = onItemClick,
            drawerWidth = drawerWidth
        )
    }
}

@Composable
fun DrawerHeader(drawerWidth: Dp) {
    Box(
        modifier = Modifier.width(drawerWidth).background(color = colorResource(R.color.colorPrimary))
            .padding(vertical = 105.dp),
        contentAlignment = Alignment.Center
    )
    {
        Text(text = "Header", fontSize = 32.sp, color = Color.White)
    }
}

@Composable
fun DrawerBody(
    items: List<DrawerItem>,
    modifier: Modifier = Modifier,
    itemTextStyle: TextStyle = TextStyle(fontSize = 16.sp),
    onItemClick: (DrawerItem) -> Unit,
    drawerWidth: Dp
) {
    LazyColumn(modifier.width(drawerWidth)) {
        itemsIndexed(items) { index, item ->
            Row(modifier = Modifier.fillParentMaxWidth()
                .clickable { onItemClick(item) }
                .padding(12.dp)
            ) {
                Icon(imageVector = item.icon, contentDescription = item.title)
                Spacer(modifier = Modifier.width(10.dp))
                Text(text = item.title, style = itemTextStyle, modifier = Modifier.weight(1f))
            }

        }
    }
}
package com.example.flexiblegrid.lib

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

enum class GridFillDirection {
    RowWise,
    ColumnWise
}

enum class GridScrollType {
    Vertical,
    Horizontal
}

data class GridItemSize(
    val width: Dp,
    val height: Dp
)

@OptIn(ExperimentalFoundationApi::class)
private fun Modifier.flexibleGridItemInteractions(
    onClick: (() -> Unit)? = null,
    onDoubleClick: (() -> Unit)? = null,
    onLongClick: (() -> Unit)? = null
): Modifier {
    if (onClick == null && onDoubleClick == null && onLongClick == null) {
        return this
    }

    return combinedClickable(
        onClick = { onClick?.invoke() },
        onDoubleClick = onDoubleClick,
        onLongClick = onLongClick
    )
}

fun calculateAdaptiveItemSize(
    columns: Int,
    rows: Int,
    scrollType: GridScrollType,
    screenWidthDp: Int,
    screenHeightDp: Int,
    contentPaddingDp: Dp = 16.dp,
    itemSpacingDp: Dp = 8.dp
): GridItemSize {
    val padding = contentPaddingDp * 2
    val spacing = itemSpacingDp * (columns - 1)
    
    val itemWidth = ((screenWidthDp.dp - padding - spacing) / columns).coerceAtLeast(60.dp)
    
    val itemHeight = if (scrollType == GridScrollType.Vertical) {
        ((screenHeightDp.dp - padding - (itemSpacingDp * (rows - 1))) / rows).coerceAtLeast(60.dp)
    } else {
        ((screenHeightDp.dp - padding - (itemSpacingDp * (rows - 1))) / rows).coerceAtLeast(80.dp)
    }
    
    return GridItemSize(width = itemWidth, height = itemHeight)
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun <T> FlexibleGrid(
    items: List<T>,
    rows: Int,
    columns: Int,
    modifier: Modifier = Modifier,
    fillDirection: GridFillDirection = GridFillDirection.RowWise,
    scrollType: GridScrollType = GridScrollType.Vertical,
    contentPadding: PaddingValues = PaddingValues(0.dp),
    verticalArrangement: Arrangement.Vertical = Arrangement.spacedBy(8.dp),
    horizontalArrangement: Arrangement.Horizontal = Arrangement.spacedBy(8.dp),
    itemSpacing: Dp = 8.dp,
    onItemClick: ((item: T, index: Int) -> Unit)? = null,
    onItemDoubleClick: ((item: T, index: Int) -> Unit)? = null,
    onItemLongPress: ((item: T, index: Int) -> Unit)? = null,
    itemContent: @Composable (T, Int) -> Unit
) {
    require(rows > 0) { "rows must be greater than zero" }
    require(columns > 0) { "columns must be greater than zero" }

    val pageSize = rows * columns
    val pageCount = ((items.size + pageSize - 1) / pageSize)

    when (scrollType) {
        GridScrollType.Vertical -> {
            LazyColumn(
                modifier = modifier,
                contentPadding = contentPadding,
                verticalArrangement = verticalArrangement
            ) {
                items(count = pageCount) { pageIndex ->
                    val pageStart = pageIndex * pageSize
                    Column(
                        modifier = Modifier.fillMaxWidth(),
                        verticalArrangement = verticalArrangement
                    ) {
                        for (rowIndex in 0 until rows) {
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = horizontalArrangement
                            ) {
                                for (columnIndex in 0 until columns) {
                                    val flatIndex = if (fillDirection == GridFillDirection.RowWise) {
                                        rowIndex * columns + columnIndex
                                    } else {
                                        columnIndex * rows + rowIndex
                                    }
                                    val globalIndex = pageStart + flatIndex
                                    val item = items.getOrNull(globalIndex)
                                    Box(
                                        modifier = Modifier
                                            .weight(1f)
                                            .padding(itemSpacing / 2)
                                            .flexibleGridItemInteractions(
                                                onClick = item?.let { currentItem ->
                                                    { onItemClick?.invoke(currentItem, globalIndex) }
                                                },
                                                onDoubleClick = item?.let { currentItem ->
                                                    { onItemDoubleClick?.invoke(currentItem, globalIndex) }
                                                },
                                                onLongClick = item?.let { currentItem ->
                                                    { onItemLongPress?.invoke(currentItem, globalIndex) }
                                                }
                                            )
                                    ) {
                                        if (item != null) {
                                            itemContent(item, globalIndex)
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }

        GridScrollType.Horizontal -> {
            LazyRow(
                modifier = modifier,
                contentPadding = contentPadding,
                horizontalArrangement = horizontalArrangement
            ) {
                items(count = pageCount) { pageIndex ->
                    val pageStart = pageIndex * pageSize
                    Column(
                        modifier = Modifier.wrapContentWidth(),
                        verticalArrangement = verticalArrangement
                    ) {
                        for (rowIndex in 0 until rows) {
                            Row(
                                modifier = Modifier.wrapContentWidth(),
                                horizontalArrangement = horizontalArrangement
                            ) {
                                for (columnIndex in 0 until columns) {
                                    val flatIndex = if (fillDirection == GridFillDirection.RowWise) {
                                        rowIndex * columns + columnIndex
                                    } else {
                                        columnIndex * rows + rowIndex
                                    }
                                    val globalIndex = pageStart + flatIndex
                                    val item = items.getOrNull(globalIndex)
                                    Box(
                                        modifier = Modifier
                                            .padding(itemSpacing / 2)
                                            .flexibleGridItemInteractions(
                                                onClick = item?.let { currentItem ->
                                                    { onItemClick?.invoke(currentItem, globalIndex) }
                                                },
                                                onDoubleClick = item?.let { currentItem ->
                                                    { onItemDoubleClick?.invoke(currentItem, globalIndex) }
                                                },
                                                onLongClick = item?.let { currentItem ->
                                                    { onItemLongPress?.invoke(currentItem, globalIndex) }
                                                }
                                            )
                                    ) {
                                        if (item != null) {
                                            itemContent(item, globalIndex)
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}

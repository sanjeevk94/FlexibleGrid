# FlexibleGrid

A powerful, flexible Android Jetpack Compose composable for creating adaptive grid layouts with minimal code.

## Features

✅ **Vertical & Horizontal Scrolling** - Switch between scroll directions easily  
✅ **Row-wise & Column-wise Fill** - Control how items are arranged  
✅ **Fully Responsive** - Auto-adapts to any screen size and orientation  
✅ **Generic Type Support** - Works with any data type  
✅ **Automatic Pagination** - Handles large datasets efficiently  
✅ **Customizable Spacing** - Fine-tune padding and item spacing  
✅ **Built-in Item Gestures** - Single tap, double tap, and long press callbacks  
✅ **Adaptive Sizing Helper** - Built-in function for responsive item sizes  

## Installation

### Local Dependency (Development)
```kotlin
// In your module's build.gradle.kts
dependencies {
    implementation(project(":flexiblegrid"))
}
```

### From Maven Central (Coming Soon)
```kotlin
dependencies {
    implementation("com.example:flexiblegrid:1.0.0")
}
```

### From JitPack
```kotlin
repositories {
    maven { url 'https://jitpack.io' }
}

dependencies {
    implementation("com.github.sanjeevk94:FlexibleGrid:v2.0.0")
}
```

## Quick Start

```kotlin
import com.example.flexiblegrid.lib.FlexibleGrid
import com.example.flexiblegrid.lib.GridFillDirection
import com.example.flexiblegrid.lib.GridScrollType

@Composable
fun MyGridScreen(items: List<String>) {
    FlexibleGrid(
        items = items,
        rows = 2,
        columns = 3,
        fillDirection = GridFillDirection.RowWise,
        scrollType = GridScrollType.Vertical,
        modifier = Modifier.fillMaxSize(),
        onItemClick = { item, index ->
            println("Tapped $item at $index")
        }
    ) { item, _ ->
        Card(modifier = Modifier.fillMaxSize()) {
            Text(text = item)
        }
    }
}
```

## Usage Examples

### 1. E-Commerce Product Grid
```kotlin
FlexibleGrid(
    items = products,
    rows = 2,
    columns = 2,
    scrollType = GridScrollType.Vertical,
    itemContent = { product, _ ->
        ProductCard(product)
    }
)
```

### 2. Horizontal Carousel
```kotlin
FlexibleGrid(
    items = featuredItems,
    rows = 1,
    columns = 3,
    scrollType = GridScrollType.Horizontal,
    onItemLongPress = { item, _ ->
        showContextMenu(item)
    }
) { item, _ ->
    FeaturedCard(item)
}
```

### 3. Photo Gallery
```kotlin
FlexibleGrid(
    items = photos,
    rows = 3,
    columns = 3,
    fillDirection = GridFillDirection.RowWise,
    scrollType = GridScrollType.Vertical
) { photo, _ ->
    AsyncImage(model = photo.url)
}
```

### 4. With Responsive Sizing
```kotlin
val configuration = LocalConfiguration.current
val itemSize = calculateAdaptiveItemSize(
    columns = 2,
    rows = 2,
    scrollType = GridScrollType.Vertical,
    screenWidthDp = configuration.screenWidthDp,
    screenHeightDp = configuration.screenHeightDp
)

FlexibleGrid(
    items = items,
    rows = 2,
    columns = 2,
    scrollType = GridScrollType.Vertical,
    modifier = Modifier.fillMaxSize()
) { item, _ ->
    Card(
        modifier = Modifier
            .width(itemSize.width)
            .height(itemSize.height)
    ) {
        ItemContent(item)
    }
}
```

## API Reference

### FlexibleGrid Composable
```kotlin
@Composable
fun <T> FlexibleGrid(
    items: List<T>,                              // Data to display
    rows: Int,                                   // Number of rows per page
    columns: Int,                                // Number of columns
    modifier: Modifier = Modifier,               // Layout modifier
    fillDirection: GridFillDirection = RowWise, // How to fill the grid
    scrollType: GridScrollType = Vertical,       // Scroll direction
    contentPadding: PaddingValues = PaddingValues(0.dp),
    verticalArrangement: Arrangement.Vertical = Arrangement.spacedBy(8.dp),
    horizontalArrangement: Arrangement.Horizontal = Arrangement.spacedBy(8.dp),
    itemSpacing: Dp = 8.dp,
    onItemClick: ((T, Int) -> Unit)? = null,       // Single tap callback
    onItemDoubleClick: ((T, Int) -> Unit)? = null, // Double tap callback
    onItemLongPress: ((T, Int) -> Unit)? = null,   // Long press callback
    itemContent: @Composable (T, Int) -> Unit   // Item renderer
)
```

### GridFillDirection Enum
- `RowWise` - Fill left-to-right, top-to-bottom
- `ColumnWise` - Fill top-to-bottom, left-to-right

### GridScrollType Enum
- `Vertical` - Scroll top-to-bottom (default)
- `Horizontal` - Scroll left-to-right

### calculateAdaptiveItemSize Function
```kotlin
fun calculateAdaptiveItemSize(
    columns: Int,
    rows: Int,
    scrollType: GridScrollType,
    screenWidthDp: Int,
    screenHeightDp: Int,
    contentPaddingDp: Dp = 16.dp,
    itemSpacingDp: Dp = 8.dp
): GridItemSize
```

Returns `GridItemSize` with optimal `width` and `height` for responsive layouts.

## Best Practices

1. **Always use `calculateAdaptiveItemSize`** for responsive grids
2. **Use `LocalConfiguration`** to get screen dimensions
3. **Provide meaningful item renderers** - the composable is flexible!
4. **Set proper modifiers** on your item cards for size constraints
5. **Use built-in callbacks for card interactions** instead of duplicating gesture handling
6. **Handle empty states** - check `items.isEmpty()` before rendering

## Real-World Use Cases

- ✅ E-commerce apps (product listings)
- ✅ Photo galleries and social media feeds
- ✅ Dashboard widgets and analytics
- ✅ Contact/user directories
- ✅ Menu grids (restaurants, apps)
- ✅ Emoji/sticker pickers
- ✅ Search result displays
- ✅ Icon grids and app launchers

## Contributing

Contributions welcome! Please open an issue or submit a PR.

## License

Apache License 2.0 - See LICENSE file for details

## Support

For issues, questions, or suggestions:
- Open an issue on GitHub
- Check the documentation
- Review the example app

---

**Made with ❤️ for developers who value clean, reusable code.**

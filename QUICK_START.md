# FlexibleGrid - Quick Start Guide

Get started with FlexibleGrid in 5 minutes! 🚀

## Installation

### Step 1: Add JitPack Repository

In your project's `settings.gradle.kts`:

```kotlin
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
        maven { url = uri("https://jitpack.io") }  // ← Add this line
    }
}
```

### Step 2: Add Dependency

In your module's `build.gradle.kts`:

```kotlin
dependencies {
    // Add FlexibleGrid
    implementation("com.github.sanjeevk94:FlexibleGrid:v2.0.0")
}
```

That's it! No authentication needed. 🎉

## Basic Usage

### Vertical Grid (Most Common)

```kotlin
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.flexiblegrid.lib.FlexibleGrid
import com.example.flexiblegrid.lib.GridFillDirection
import com.example.flexiblegrid.lib.GridScrollType

@Composable
fun MyVerticalGrid() {
    val items = (1..20).toList()
    
    FlexibleGrid(
        items = items,
        rows = 3,
        columns = 2,
        fillDirection = GridFillDirection.RowWise,
        scrollType = GridScrollType.Vertical,
        modifier = Modifier.fillMaxSize()
    ) { item, _ ->
        Card(
            modifier = Modifier
                .padding(8.dp)
                .fillMaxSize()
        ) {
            Text("Item $item", modifier = Modifier.padding(16.dp))
        }
    }
}
```

**Result:**
```
Grid Layout (3 rows × 2 columns):
┌─────────┐ ┌─────────┐
│ Item 1  │ │ Item 2  │
├─────────┼─┼─────────┤
│ Item 3  │ │ Item 4  │
├─────────┼─┼─────────┤
│ Item 5  │ │ Item 6  │
└─────────┘ └─────────┘
(Scrolls vertically for more items)
```

### Horizontal Grid (Product Carousel)

```kotlin
@Composable
fun MyHorizontalGrid() {
    val products = listOf(
        Product("Nike Shoes", 99.99),
        Product("Adidas Shoes", 89.99),
        Product("Puma Shoes", 79.99),
        // ... more products
    )
    
    FlexibleGrid(
        items = products,
        rows = 2,
        columns = 4,  // Shows 4 products per page in horizontal scroll
        fillDirection = GridFillDirection.RowWise,
        scrollType = GridScrollType.Horizontal,
        spacing = 8.dp
    ) { product, _ ->
        ProductCard(product)
    }
}
```

**Result:**
```
Horizontal Scrolling:
┌────────┐ ┌────────┐ ┌────────┐ ┌────────┐
│ Prod 1 │ │ Prod 2 │ │ Prod 3 │ │ Prod 4 │
├────────┼─┼────────┼─┼────────┼─┼────────┤
│ Prod 5 │ │ Prod 6 │ │ Prod 7 │ │ Prod 8 │
└────────┘ └────────┘ └────────┘ └────────┘
← Scroll →
```

## Key Parameters

| Parameter | Type | Required | Description |
|-----------|------|----------|-------------|
| `items` | `List<T>` | ✅ | Your data items |
| `rows` | `Int` | ✅ | Number of rows per page |
| `columns` | `Int` | ✅ | Number of columns |
| `fillDirection` | `GridFillDirection` | ✅ | How to fill: `RowWise` or `ColumnWise` |
| `scrollType` | `GridScrollType` | ✅ | Scroll direction: `Vertical` or `Horizontal` |
| `itemContent` | `@Composable (T) -> Unit` | ✅ | How to render each item |
| `modifier` | `Modifier` | ❌ | Layout modifiers (default: `Modifier`) |
| `spacing` | `Dp` | ❌ | Space between items (default: `8.dp`) |

## Common Examples

### 1. Photo Gallery (3×3 Vertical Grid)

```kotlin
@Composable
fun PhotoGallery(photos: List<Photo>) {
    FlexibleGrid(
        items = photos,
        rows = 3,
        columns = 3,
        fillDirection = GridFillDirection.RowWise,
        scrollType = GridScrollType.Vertical,
        spacing = 4.dp
    ) { photo, _ ->
        Image(
            painter = rememberAsyncImagePainter(photo.url),
            contentDescription = null,
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )
    }
}
```

### 2. Product Carousel (Horizontal)

```kotlin
@Composable
fun ProductCarousel(products: List<Product>) {
    FlexibleGrid(
        items = products,
        rows = 2,
        columns = 4,
        scrollType = GridScrollType.Horizontal,
        spacing = 12.dp
    ) { product, _ ->
        ProductCard(
            product = product,
            onAddCart = { /* Handle add to cart */ }
        )
    }
}
```

### 3. Contact Directory (Vertical with Avatar Cards)

```kotlin
@Composable
fun ContactDirectory(contacts: List<Contact>) {
    FlexibleGrid(
        items = contacts,
        rows = 2,
        columns = 3,
        fillDirection = GridFillDirection.RowWise,
        scrollType = GridScrollType.Vertical
    ) { contact, _ ->
        ContactCard(contact)
    }
}
```

### 4. Dashboard Widgets (Column-wise Fill)

```kotlin
@Composable
fun Dashboard(widgets: List<Widget>) {
    FlexibleGrid(
        items = widgets,
        rows = 4,
        columns = 2,
        fillDirection = GridFillDirection.ColumnWise,  // Fill columns first!
        scrollType = GridScrollType.Vertical,
        spacing = 16.dp
    ) { widget, _ ->
        WidgetCard(widget)
    }
}
```

## Fill Direction Explained

### Row-Wise Fill (Default)
Items fill left-to-right, top-to-bottom:
```
1 2 3
4 5 6
7 8 9
```

### Column-Wise Fill
Items fill top-to-bottom, left-to-right:
```
1 4 7
2 5 8
3 6 9
```

## Responsive Behavior

FlexibleGrid automatically adapts to:
- ✅ Different screen sizes (phone, tablet, desktop)
- ✅ Portrait and landscape orientations
- ✅ Device configuration changes
- ✅ Any number of rows/columns you specify

Items are **automatically sized** to fit the available space based on:
- Screen width/height
- Number of rows/columns
- Spacing between items

## Tips & Best Practices

1. **Choose the right fill direction:**
   - Use `RowWise` for most use cases (e.g., products, photos)
   - Use `ColumnWise` for dashboards or complex layouts

2. **Optimize for your content:**
   - Smaller items? More columns
   - Larger items? Fewer columns
   - Long content? More rows

3. **Mobile-first approach:**
   ```kotlin
   // For phones (portrait): 2 columns
   // For tablets (landscape): 4 columns
   // Adjust rows/columns based on screen size:
   val columns = if (isLandscape) 4 else 2
   FlexibleGrid(
       items = items,
       rows = 3,
       columns = columns,
       // ...
   )
   ```

4. **Performance:**
   - FlexibleGrid uses `LazyColumn`/`LazyRow` for efficiency
   - Only visible items are rendered (no performance issues with 1000+ items)

## Troubleshooting

### Grid not showing items?
- Check that `itemContent` lambda is provided
- Verify `rows` × `columns` makes sense for your data count

### Items too small/large?
- Adjust `rows` and `columns`
- Use the `spacing` parameter for more control

### Scrolling direction wrong?
- Change `scrollType` to `GridScrollType.Horizontal` or `Vertical`

### Need responsive sizing?
- Use `calculateAdaptiveItemSize()` helper:
  ```kotlin
  val itemSize = calculateAdaptiveItemSize(
      rows = 3,
      columns = 2,
      screenWidth = LocalConfiguration.current.screenWidthDp.dp
  )
  ```

## Next Steps

- 📖 Read the [full documentation](flexiblegrid/README.md)
- 🔗 Check [real-world examples](app/src/main/java/com/example/flexiblegrid/)
- 🆘 Open an [issue on GitHub](https://github.com/sanjeevk94/FlexibleGrid/issues)

## Questions?

- Check the [FAQ](flexiblegrid/README.md#faq) section
- Review [example code](app/)
- Open a GitHub issue with details

**Happy coding! 🎉**

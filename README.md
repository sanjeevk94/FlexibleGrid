# FlexibleGrid 🎯

A powerful, flexible, and reusable Jetpack Compose grid layout composable for Android. Perfect for creating adaptive grids that work across all device sizes and orientations.

[![Build & Test](https://github.com/saisanjeevkolasani/FlexibleGrid/actions/workflows/build.yml/badge.svg)](https://github.com/saisanjeevkolasani/FlexibleGrid/actions/workflows/build.yml)
[![Publish Library](https://github.com/saisanjeevkolasani/FlexibleGrid/actions/workflows/publish.yml/badge.svg)](https://github.com/saisanjeevkolasani/FlexibleGrid/actions/workflows/publish.yml)
[![License: Apache 2.0](https://img.shields.io/badge/License-Apache%202.0-blue.svg)](LICENSE)

## ✨ Features

- 📱 **Fully Responsive** - Adapts to any screen size and orientation (portrait/landscape)
- 🔄 **Flexible Fill Direction** - Row-wise or column-wise item arrangement
- 📜 **Dual Scroll Support** - Vertical or horizontal scrolling
- 🎨 **Generic Rendering** - Render any data type with custom composables
- ⚙️ **Configurable** - Customize rows, columns, spacing, and more
- 🚀 **High Performance** - Uses LazyColumn/LazyRow for efficient rendering
- 📦 **Easy to Integrate** - Single composable, zero complexity
- 🛡️ **Production Ready** - Apache 2.0 licensed, well-documented

## 📦 Installation

### Via JitPack (Recommended - No credentials needed!)

Add to your `settings.gradle.kts`:
```kotlin
dependencyResolutionManagement {
    repositories {
        google()
        mavenCentral()
        maven { url = uri("https://jitpack.io") }
    }
}
```

Add to your module `build.gradle.kts`:
```kotlin
dependencies {
    implementation("com.github.saisanjeevkolasani:FlexibleGrid:v1.0.0")
}
```

**That's it!** JitPack automatically builds the library from GitHub releases. No setup required on your end.

### Release Flow

See [PUBLISHING.md](PUBLISHING.md) for the tag-and-release workflow used to publish through JitPack.

## 🚀 Quick Start

```kotlin
import com.example.flexiblegrid.lib.FlexibleGrid
import com.example.flexiblegrid.lib.GridFillDirection
import com.example.flexiblegrid.lib.GridScrollType

// Simple vertical grid
FlexibleGrid(
    items = (1..20).toList(),
    rows = 3,
    columns = 2,
    fillDirection = GridFillDirection.RowWise,
    scrollType = GridScrollType.Vertical,
    itemContent = { item, _ ->
        Card(
            modifier = Modifier
                .padding(8.dp)
                .fillMaxSize()
        ) {
            Text("Item $item", modifier = Modifier.padding(16.dp))
        }
    }
)

// Horizontal scrolling grid
FlexibleGrid(
    items = (1..20).toList(),
    rows = 2,
    columns = 4,
    fillDirection = GridFillDirection.RowWise,
    scrollType = GridScrollType.Horizontal,
    itemContent = { item, _ ->
        ProductCard(item)
    }
)
```

## 📚 Documentation

### Library Documentation
See [`flexiblegrid/README.md`](flexiblegrid/README.md) for:
- Complete API reference
- More examples
- Best practices
- 10+ real-world use cases

### Publishing Guide
See [`PUBLISHING.md`](PUBLISHING.md) for:
- How to publish new versions
- Installation instructions for users
- Release workflow
- CI/CD setup details

## 💡 Use Cases

✅ E-commerce product grids  
✅ Photo galleries  
✅ Media carousels  
✅ Dashboard widgets  
✅ Social media feeds  
✅ Contact/user directories  
✅ Menu layouts  
✅ App launcher grids  
✅ Emoji pickers  
✅ Search result grids  

## 🎯 Key Advantages

| Feature | FlexibleGrid | LazyVerticalGrid | LazyHorizontalGrid |
|---------|--------------|------------------|--------------------|
| Row-wise fill | ✅ | ❌ | ❌ |
| Column-wise fill | ✅ | ✅ | ✅ |
| Horizontal scroll | ✅ | ❌ | ✅ |
| Vertical scroll | ✅ | ✅ | ❌ |
| Generic `<T>` | ✅ | ✅ | ✅ |
| Adaptive sizing | ✅ | ❌ | ❌ |
| Paging built-in | ✅ | ❌ | ❌ |

## 🏗️ Architecture

- **GridFillDirection**: Controls whether items fill row-wise or column-wise
- **GridScrollType**: Determines if scrolling is vertical or horizontal
- **GridItemSize**: Computed item dimensions based on screen and grid configuration
- **Adaptive Sizing**: Automatically calculates optimal item width/height
- **Lazy Pagination**: Efficient rendering using LazyColumn/LazyRow

## 📝 Examples

### E-commerce Product Grid (Horizontal Scroll)
```kotlin
data class Product(val id: Int, val name: String, val price: Double)

val products = listOf(
    Product(1, "Product A", 29.99),
    Product(2, "Product B", 39.99),
    // ...
)

FlexibleGrid(
    items = products,
    rows = 2,
    columns = 4,
    fillDirection = GridFillDirection.RowWise,
    scrollType = GridScrollType.Horizontal
) { product, _ ->
    ProductCard(product)
}
```

### Photo Gallery (Vertical Scroll)
```kotlin
FlexibleGrid(
    items = photoList,
    rows = 3,
    columns = 3,
    fillDirection = GridFillDirection.ColumnWise,
    scrollType = GridScrollType.Vertical,
    spacing = 4.dp
) { photo, _ ->
    Image(
        painter = rememberAsyncImagePainter(photo.url),
        contentDescription = photo.title,
        modifier = Modifier.fillMaxSize(),
        contentScale = ContentScale.Crop
    )
}
```

## 📊 Demo App

Run the included demo app to see all features in action:

```bash
./gradlew :app:installDebug
```

The demo includes:
- Vertical scroll with row-wise fill
- Vertical scroll with column-wise fill
- Horizontal scroll with product cards (2 rows × 4 columns)
- Responsive sizing examples

## 🛠️ Project Structure

```
FlexibleGrid/
├── flexiblegrid/                     ← Library module
│   ├── src/main/java/
│   │   └── com/example/flexiblegrid/lib/
│   │       └── FlexibleGrid.kt       ← Main composable
│   ├── build.gradle.kts
│   └── README.md
├── app/                              ← Demo application
│   ├── src/main/java/...
│   ├── build.gradle.kts
│   └── ...
├── .github/workflows/                ← CI/CD
│   ├── build.yml
│   └── publish.yml
├── LICENSE
└── PUBLISHING.md
```

## 🔄 Publishing Workflow

### For Contributors:

1. Make changes to the code
2. Commit to main branch
3. Create a release tag:
   ```bash
   git tag -a v1.0.0 -m "Release version 1.0.0"
   git push origin v1.0.0
   ```
4. GitHub Actions automatically:
   - ✅ Builds and tests the library
   - ✅ Creates release notes
   - ✅ Makes it available on JitPack instantly

See [PUBLISHING.md](PUBLISHING.md) for detailed instructions.

## 📄 License

This project is licensed under the Apache License 2.0 - see [LICENSE](LICENSE) file for details.

## 👨‍💻 Author

**Sai Sanjeev Kolasani**
- GitHub: [@saisanjeevkolasani](https://github.com/saisanjeevkolasani)

## 🤝 Contributing

Contributions are welcome! Feel free to:
- Report bugs
- Suggest features
- Submit pull requests
- Improve documentation

## 📞 Support

For questions or issues:
1. Check the [documentation](flexiblegrid/README.md)
2. Review [examples](app/src/main/java/com/example/flexiblegrid/)
3. Open a GitHub issue

## 🎓 Learning Resources

- [Jetpack Compose Documentation](https://developer.android.com/jetpack/compose)
- [LazyColumn/LazyRow Docs](https://developer.android.com/jetpack/compose/lists)
- [Responsive Design in Compose](https://developer.android.com/jetpack/compose/responsive)

---

**⭐ If you find this library useful, please star the repository!**

package com.example.flexiblegrid

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.flexiblegrid.ui.theme.FlexibleGridTheme
import com.example.flexiblegrid.lib.FlexibleGrid
import com.example.flexiblegrid.lib.GridFillDirection
import com.example.flexiblegrid.lib.GridScrollType
import com.example.flexiblegrid.lib.calculateAdaptiveItemSize

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            FlexibleGridTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    HorizontalScrollDemo(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun FlexibleGridDemo(modifier: Modifier = Modifier) {
    val items = (1..20).map { "Item $it" }
    val configuration = LocalConfiguration.current
    val screenWidthDp = configuration.screenWidthDp
    val screenHeightDp = configuration.screenHeightDp
    
    val verticalItemSize = calculateAdaptiveItemSize(
        columns = 4,
        rows = 2,
        scrollType = GridScrollType.Vertical,
        screenWidthDp = screenWidthDp,
        screenHeightDp = screenHeightDp
    )
    
    val horizontalItemSize = calculateAdaptiveItemSize(
        columns = 4,
        rows = 2,
        scrollType = GridScrollType.Horizontal,
        screenWidthDp = screenWidthDp,
        screenHeightDp = screenHeightDp
    )

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(24.dp)
    ) {
        Text("Row-wise fill", style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Bold)
        FlexibleGrid(
            items = items,
            rows = 2,
            columns = 4,
            fillDirection = GridFillDirection.RowWise,
            scrollType = GridScrollType.Vertical,
            modifier = Modifier
                .fillMaxWidth()
                .height(verticalItemSize.height * 2 + 24.dp)
        ) { item, _ ->
            Card(
                shape = RoundedCornerShape(12.dp),
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.primaryContainer),
                modifier = Modifier.fillMaxSize()
            ) {
                Text(
                    text = item,
                    modifier = Modifier.padding(12.dp),
                    color = MaterialTheme.colorScheme.onPrimaryContainer,
                    fontWeight = FontWeight.Medium
                )
            }
        }

        Text("Column-wise fill", style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Bold)
        FlexibleGrid(
            items = items,
            rows = 2,
            columns = 4,
            fillDirection = GridFillDirection.ColumnWise,
            scrollType = GridScrollType.Vertical,
            modifier = Modifier
                .fillMaxWidth()
                .height(verticalItemSize.height * 2 + 24.dp)
        ) { item, _ ->
            Card(
                shape = RoundedCornerShape(12.dp),
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.secondaryContainer),
                modifier = Modifier.fillMaxSize()
            ) {
                Text(
                    text = item,
                    modifier = Modifier.padding(12.dp),
                    color = MaterialTheme.colorScheme.onSecondaryContainer,
                    fontWeight = FontWeight.Medium
                )
            }
        }

        Text("Horizontal scroll", style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Bold)
        FlexibleGrid(
            items = items,
            rows = 2,
            columns = 4,
            fillDirection = GridFillDirection.RowWise,
            scrollType = GridScrollType.Horizontal,
            modifier = Modifier
                .fillMaxWidth()
                .height(horizontalItemSize.height * 2 + 24.dp)
        ) { item, _ ->
            Card(
                shape = RoundedCornerShape(12.dp),
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.tertiaryContainer),
                modifier = Modifier
                    .width(horizontalItemSize.width)
                    .height(horizontalItemSize.height)
            ) {
                Text(
                    text = item,
                    modifier = Modifier.padding(12.dp),
                    color = MaterialTheme.colorScheme.onTertiaryContainer,
                    fontWeight = FontWeight.Medium
                )
            }
        }
    }
}

data class ProductItem(
    val id: Int,
    val name: String,
    val price: String,
    val rating: Float,
    val color: Color
)

fun generateSampleProducts(): List<ProductItem> {
    val colors = listOf(
        Color(0xFFE57373),
        Color(0xFF64B5F6),
        Color(0xFF81C784),
        Color(0xFFFFD54F),
        Color(0xFFBA68C8),
        Color(0xFF4DD0E1),
        Color(0xFFFFB74D),
        Color(0xFF90CAF9)
    )
    
    return (1..20).map { index ->
        ProductItem(
            id = index,
            name = "Product $index",
            price = "$${(index * 12 + 15)}",
            rating = (3.5f + (index % 2) * 0.5f),
            color = colors[index % colors.size]
        )
    }
}

@Composable
fun ProductCard(product: ProductItem, modifier: Modifier = Modifier) {
    Card(
        shape = RoundedCornerShape(16.dp),
        modifier = modifier,
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
                    .background(product.color)
            )
            
            Column(modifier = Modifier.padding(12.dp)) {
                Text(
                    text = product.name,
                    fontWeight = FontWeight.Bold,
                    fontSize = 12.sp,
                    maxLines = 1
                )
                Text(
                    text = product.price,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.primary,
                    fontSize = 14.sp,
                    modifier = Modifier.padding(top = 4.dp)
                )
                Text(
                    text = "⭐ ${product.rating}",
                    fontSize = 11.sp,
                    color = Color.Gray,
                    modifier = Modifier.padding(top = 4.dp)
                )
            }
        }
    }
}

@Composable
fun HorizontalScrollDemo(modifier: Modifier = Modifier) {
    val products = generateSampleProducts()
    val configuration = LocalConfiguration.current
    val screenWidthDp = configuration.screenWidthDp
    val screenHeightDp = configuration.screenHeightDp
    
    val horizontalItemSize = calculateAdaptiveItemSize(
        columns = 4,
        rows = 2,
        scrollType = GridScrollType.Horizontal,
        screenWidthDp = screenWidthDp,
        screenHeightDp = screenHeightDp
    )
    
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text("Featured Products", style = MaterialTheme.typography.headlineSmall, fontWeight = FontWeight.Bold)
        Text("Swipe to explore more products →", style = MaterialTheme.typography.bodySmall, color = Color.Gray)
        
        FlexibleGrid(
            items = products,
            rows = 2,
            columns = 4,
            fillDirection = GridFillDirection.RowWise,
            scrollType = GridScrollType.Horizontal,
            modifier = Modifier
                .fillMaxWidth()
                .height(horizontalItemSize.height * 2 + 32.dp)
        ) { product, _ ->
            ProductCard(
                product = product,
                modifier = Modifier
                    .width(horizontalItemSize.width)
                    .height(horizontalItemSize.height)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun FlexibleGridPreview() {
    FlexibleGridTheme {
        FlexibleGridDemo()
    }
}

@Preview(showBackground = true)
@Composable
fun HorizontalScrollPreview() {
    FlexibleGridTheme {
        HorizontalScrollDemo()
    }
}
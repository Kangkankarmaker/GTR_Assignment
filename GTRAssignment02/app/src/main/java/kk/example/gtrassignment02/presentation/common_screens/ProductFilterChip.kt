package kk.example.jetpackcomposesturacturalapp.presentation.common_screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp

data class ProductFilterChipModel(
    val name: String,
    val subList: List<String>? = null,
    val textExpanded: String? = null,
    val leadingIcon: ImageVector,
    val trailingIcon: ImageVector? = null,
    val selectedIcon: ImageVector? = null,
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AssistChip(
    assistList:List<ProductFilterChipModel>,
    isItemSelected:(String)->Unit
) {


    var selectedItem by remember { mutableStateOf("") }
    var isSelected by remember { mutableStateOf(false) }

    LazyRow {
        items(assistList) { item ->
            isSelected = selectedItem == item.name
            Spacer(modifier = Modifier.padding(5.dp))
            AssistChip(
                onClick = {
                    selectedItem = if (selectedItem != item.name) item.name else ""
                    isItemSelected( item.name)
                },
                label = { Text(text = item.name) },
                leadingIcon = {
                    if (selectedItem==item.name) Icon(item.leadingIcon, contentDescription = "")
                },
                colors = AssistChipDefaults.assistChipColors(
                    containerColor = if (isSelected) MaterialTheme.colorScheme.primaryContainer else Color.Transparent,
                    labelColor = Color.Black,
                    leadingIconContentColor = if (isSelected) MaterialTheme.colorScheme.onPrimaryContainer else Color.LightGray,
                )
            )
        }
    }
}
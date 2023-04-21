package kk.example.jetpackcomposesturacturalapp.presentation.common_screens

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material.icons.outlined.Check
import androidx.compose.material.icons.outlined.Place
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp

data class ChipsModel(
    val name: String,
    val subList: List<String>? = null,
    val textExpanded: String? = null,
    val leadingIcon: ImageVector? = null,
    val trailingIcon: ImageVector? = null,
    val selectedIcon: ImageVector? = null
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TutorialAssistChip() {
    val assistList = listOf(
        ChipsModel(
            name = "Call to my friend",
            leadingIcon = Icons.Default.Face
        ),
        ChipsModel(
            name = "Share the item",
            leadingIcon = Icons.Default.Share
        )
    )

    var selectedItem by remember { mutableStateOf("") }
    var isSelected by remember { mutableStateOf(false) }

    LazyRow {
        items(assistList) { item ->
            isSelected = selectedItem == item.name
            Spacer(modifier = Modifier.padding(5.dp))
            AssistChip(
                onClick = {
                    selectedItem = if (selectedItem != item.name) item.name else ""
                },
                label = {
                    Text(text = item.name)
                },
                leadingIcon = {
                    if (item.leadingIcon != null)
                        Icon(item.leadingIcon, contentDescription = "")
                },
                colors = AssistChipDefaults.assistChipColors(
                    containerColor = if (isSelected)
                        MaterialTheme.colorScheme.primaryContainer else Color.Transparent,
                    labelColor = Color.Black,
                    leadingIconContentColor = if (isSelected)
                        MaterialTheme.colorScheme.onPrimaryContainer else Color.LightGray,
                )
            )
        }
    }
}

@SuppressLint("UnrememberedMutableState")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TutorialFilterChip() {
    val filterList = listOf(
        ChipsModel(
            name = "Cart",
            leadingIcon = Icons.Default.ShoppingCart,
            selectedIcon = Icons.Default.ShoppingCart
        ),
        ChipsModel(
            name = "Phone",
            subList = listOf("Asus", "Pixel", "Apple"),
            trailingIcon = Icons.Default.ArrowDropDown,
            selectedIcon = Icons.Default.Check
        ),
        ChipsModel(
            name = "Tablet",
            selectedIcon = Icons.Default.Check
        ),
        ChipsModel(
            name = "Cart",
            leadingIcon = Icons.Default.ShoppingCart,
            selectedIcon = Icons.Default.Search,
            trailingIcon = Icons.Default.Close
        )
    )

    val selectedItems = mutableStateListOf<String>()
    var isSelected by remember { mutableStateOf(false) }

    LazyRow {
        items(filterList) { item ->
            isSelected = selectedItems.contains(item.name)
            Spacer(modifier = Modifier.padding(5.dp))
            if (item.subList != null) {
                ChipWithSubItems(chipLabel = item.name, chipItems = item.subList)
            } else {
                FilterChip(
                    selected = isSelected,
                    onClick = {
                        when (selectedItems.contains(item.name)) {
                            true -> selectedItems.remove(item.name)
                            false -> selectedItems.add(item.name)
                        }
                    },
                    label = { Text(text = item.name) },
                    leadingIcon = {
                        if (item.leadingIcon != null)
                            Icon(item.leadingIcon, contentDescription = item.name)
                    },
                    /*selectedIcon = {
                        if (item.selectedIcon != null) {
                            Icon(item.selectedIcon, contentDescription = item.name)
                        }
                    },*/
                    trailingIcon = {
                        if (item.trailingIcon != null && isSelected)
                            Icon(item.trailingIcon, contentDescription = item.name)
                    }
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ChipWithSubItems(chipLabel: String, chipItems: List<String>) {
    var isSelected by remember { mutableStateOf(false) }
    var showSubList by remember { mutableStateOf(false) }
    var filterName by remember { mutableStateOf("") }

    ExposedDropdownMenuBox(
        expanded = showSubList,
        onExpandedChange = { showSubList = !showSubList }
    ) {
        FilterChip(
            selected = isSelected,
            onClick = {
                isSelected = true
            },
            label = { Text(text = filterName.ifEmpty { chipLabel }) },
            trailingIcon = {
                Icon(
                    modifier = Modifier.rotate(if (showSubList) 180f else 0f),
                    imageVector = Icons.Default.ArrowDropDown,
                    contentDescription = "List"
                )
            }
        )
        ExposedDropdownMenu(
            expanded = showSubList,
            onDismissRequest = { showSubList = false },
        ) {
            chipItems.forEach { subListItem ->
                TextButton(
                    modifier = Modifier.fillMaxWidth(),
                    onClick = {
                        filterName = subListItem
                        showSubList = false
                    },
                    colors = ButtonDefaults.textButtonColors(
                        containerColor = if (subListItem == filterName || subListItem == chipLabel) {
                            MaterialTheme.colorScheme.primaryContainer
                        } else { Color.Transparent }
                    )
                ) {
                    Text(text = subListItem, color = Color.Black)
                }
            }
        }
    }
}

@SuppressLint("UnrememberedMutableState")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TutorialInputChip() {
    val inputList = listOf(
        ChipsModel(
            name = "Crowley Junior",
            textExpanded = "crowleyjunir@gmail.com",
            leadingIcon = Icons.Default.Person,
            trailingIcon = Icons.Default.Close
        ),
        ChipsModel(
            name = "John Dee",
            textExpanded = "johndee@gmail.com",
            leadingIcon = Icons.Default.Add,
            trailingIcon = Icons.Default.Close
        )
    )

    val selectedItems = mutableStateListOf<String>()
    var isTextExpanded by remember { mutableStateOf(false) }

    LazyRow {
        items(inputList) { item ->
            isTextExpanded = selectedItems.contains(item.name)
            Spacer(modifier = Modifier.padding(5.dp))
            if (isTextExpanded && item.textExpanded != null) {
                Text(text = item.textExpanded)
            } else {
                InputChip(
                    onClick = { selectedItems.add(item.name) },
                    label = { Text(text = item.name) },
                    avatar = {
                        if (item.leadingIcon != null)
                            Icon(item.leadingIcon, contentDescription = item.name)
                    },
                    trailingIcon = {
                        if (item.trailingIcon != null)
                            Icon(item.trailingIcon, contentDescription = item.name)
                    },
                    selected = false
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TutorialSuggestionChip() {
    val suggestionList = listOf(
        ChipsModel(
            name = "Country Name",
            leadingIcon = Icons.Outlined.Place
        ),
        ChipsModel(
            name = "Art Name",
            leadingIcon = Icons.Outlined.Check
        )
    )

    LazyRow {
        items(suggestionList) { item ->
            Spacer(modifier = Modifier.padding(5.dp))
            SuggestionChip(
                enabled = suggestionList[0].name == item.name,
                onClick = { /*TODO*/ },
                label = { Text(text = item.name) },
                icon = {
                    if (item.leadingIcon != null)
                        Icon(item.leadingIcon, contentDescription = item.name)
                }
            )
        }
    }
}
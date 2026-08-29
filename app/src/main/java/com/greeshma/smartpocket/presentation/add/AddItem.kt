package com.greeshma.smartpocket.presentation.add

import android.content.Intent
import android.net.Uri
import android.util.Log
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.AddAPhoto
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.outlined.CalendarMonth
import androidx.compose.material.icons.outlined.DateRange
import androidx.compose.material.icons.outlined.Edit
import androidx.compose.material.icons.outlined.GridView
import androidx.compose.material.icons.outlined.LocalOffer
import androidx.compose.material.icons.outlined.Save
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.AsyncImage
import com.greeshma.smartpocket.R
import com.greeshma.smartpocket.presentation.home.toDateString
import com.greeshma.smartpocket.ui.theme.SmartPocketTheme


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddItemScreen(
    uiState: AddUiState,
    onNameChanged: (String) -> Unit,
    onCategoryClick: () -> Unit,
    onPurchaseDateClick: () -> Unit,
    onExpiryDateClick: () -> Unit,
    onNotesChanged: (String) -> Unit,
    onPickImage: () -> Unit,
    onSaveClick: () -> Unit,
    onBackClick: () -> Unit
) {

    val viewModel: AddViewModel = hiltViewModel()
    val context = LocalContext.current
    LaunchedEffect(Unit) {

        viewModel.events.collect { event ->

            when (event) {

                AddEvent.ItemAddedSuccessfully -> {
                    onBackClick()
                }

                is AddEvent.Error -> {
                    Toast.makeText(context, event.message,  Toast.LENGTH_SHORT)
                }
            }
        }
    }
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Add New Item",
                        style = MaterialTheme.typography.headlineSmall
                    )
                },
                navigationIcon = {
                    IconButton(onClick = onBackClick) {
                        Icon(
                            Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = null
                        )
                    }
                }
            )
        },
        bottomBar = {  }
    ) { padding ->

        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding),
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {


            item {

                SectionCard(title = "Item Details") {

                    SmartTextField(
                        value = uiState.documentName,
                        label = "Name",
                        placeholder = "Enter item name",
                        leadingIcon = Icons.Outlined.LocalOffer,
                        onValueChange = onNameChanged,
                        error = uiState.documentNameError
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    SmartDropdown(
                        value = uiState.category,
                        label = "Category",
                        placeholder = "Select Category",
                        leadingIcon = Icons.Outlined.GridView,
                        onClick = onCategoryClick,
                        items = listOf("Documents",
                            "Electronics",
                            "Warranty",
                            "Identity",
                            "Vehicle",
                            "Health",
                            "Finance",
                            "Others"),
                        onItemSelected = viewModel::onCategoryChanged,

                        )
                }
            }
            item {

                SectionCard(title = "Attachment") {

                    if (uiState.imageUri==null) {

                        Icon(
                            Icons.Default.AddAPhoto,
                            contentDescription = null,
                            modifier = Modifier.size(40.dp),
                            tint = MaterialTheme.colorScheme.primary
                        )

                        Spacer(Modifier.height(12.dp))

                        Text(
                            "Tap to upload image",
                            style = MaterialTheme.typography.titleMedium
                        )

                        Spacer(Modifier.height(20.dp))

                        OutlinedButton(
                            onClick =  onPickImage
                        ) {
                            Text("Choose Image")
                        }
                    }
                    else {

                        AsyncImage(
                            model = uiState.imageUri,
                            contentDescription = null,
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(180.dp)
                                .clip(RoundedCornerShape(12.dp)),
                            contentScale = ContentScale.Crop
                        )

                        Spacer(Modifier.height(16.dp))

                        OutlinedButton(
                            onClick = onPickImage
                        ) {

                            Icon(
                                Icons.Default.Edit,
                                contentDescription = null
                            )

                            Spacer(Modifier.width(8.dp))

                            Text("Change Image")
                        }
                    }
                    Spacer(modifier = Modifier.height(16.dp))

                }
            }
            item {

                SectionCard(title = "Important Dates") {

                    DatePickerField(
                        value = uiState.purchaseDate,
                        label = "Purchase Date",
                        onClick = onPurchaseDateClick
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    DatePickerField(
                        value = uiState.expiryDate,
                        label = "Expiry Date",
                        onClick = onExpiryDateClick
                    )
                }
            }

            item {

                SectionCard(title = "Notes") {

                    SmartTextField(
                        value = uiState.notes,
                        label = "Notes",
                        placeholder = "Enter notes",
                        leadingIcon = Icons.Outlined.Edit,
                        minLines = 4,
                        maxLines = 6,
                        onValueChange = onNotesChanged
                    )
                }
            }
            item{Button(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp),
                onClick = onSaveClick
            ) {

                Icon(
                    Icons.Outlined.Save,
                    contentDescription = null
                )

                Spacer(modifier = Modifier.width(8.dp))

                Text("Save Item")
            }}

        }
    }

}
@Composable
fun SectionCard(
    title: String,
    content: @Composable ColumnScope.() -> Unit
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(20.dp)
    ) {

        Column(
            modifier = Modifier.padding(20.dp)
        ) {

            Text(
                text = title,
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.SemiBold
            )

            Spacer(modifier = Modifier.height(20.dp))

            content()
        }
    }
}
@Composable
fun SmartTextField(
    value: String,
    label: String,
    placeholder: String,
    leadingIcon: ImageVector,
    error: String?=null,
    onValueChange: (String) -> Unit,
    minLines: Int = 1,
    maxLines: Int = 1
) {

    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        modifier = Modifier.fillMaxWidth(),
        label = {
            Text(label)
        },
        isError = error!=null,
        supportingText = {
            error?.let { Text(it) }
        },
        placeholder = {
            Text(placeholder)
        },
        leadingIcon = {
            Icon(
                leadingIcon,
                contentDescription = null
            )
        },
        shape = RoundedCornerShape(14.dp),
        minLines = minLines,
        maxLines = maxLines
    )
}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SmartDropdown(
    value: String,
    label: String,
    placeholder: String,
    leadingIcon: ImageVector,
    items:List<String>,
    onItemSelected:(String)-> Unit,
    onClick: () -> Unit
) {

    var expanded by remember { mutableStateOf(false)}
    ExposedDropdownMenuBox(expanded = expanded, onExpandedChange = {expanded =!expanded}, modifier = Modifier) {
        OutlinedTextField(
            value = value,
            onValueChange = {},
            modifier = Modifier
                .fillMaxWidth()
                .menuAnchor()
                .clickable { onClick() },
            readOnly = true,
            label = {
                Text(label)
            },
            placeholder = {
                Text(placeholder)
            },
            leadingIcon = {
                Icon(
                    leadingIcon,
                    contentDescription = null
                )
            },
            trailingIcon = {
                Icon(
                    Icons.Default.KeyboardArrowDown,
                    contentDescription = null
                )
            },
            shape = RoundedCornerShape(14.dp)
        )
        ExposedDropdownMenu(expanded = expanded, onDismissRequest = {expanded = false}) {
            items.forEach { category->
                    DropdownMenuItem(
                        text = { Text(text = category) },
                        onClick = {
                            onItemSelected(category)
                            expanded = false},
                    )

            }
        }
    }
}
@Composable
fun DatePickerField(
    value: Long?,
    label: String,
    error: String?=null,
    onClick: () -> Unit
) {

    OutlinedTextField(
        value = value?.toDateString() ?: "",
        onValueChange = {},
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() },
        readOnly = true,
        label = {
            Text(label)
        },
        isError = error!=null,
        supportingText = {error?.let { Text(it) }},
        placeholder = {
            Text("Select $label")
        },
        leadingIcon = {

            Icon(
                Icons.Outlined.CalendarMonth,
                contentDescription = null
            )
        },
        trailingIcon = {
            IconButton(onClick = onClick) {
            Icon(
                Icons.Outlined.DateRange,
                contentDescription = null
            )
            }
        },
        shape = RoundedCornerShape(14.dp)
    )

}
@Composable
fun AddItemRoute(
    onBackClick: () -> Unit,
    viewModel: AddViewModel = hiltViewModel()
) {
    Log.d("NAV", "ADD ITEM SCREEN")
    val context = LocalContext.current
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    var showPurchaseDatePicker by remember {
        mutableStateOf(false)
    }
    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.OpenDocument()
    ) { uri ->

        uri?.let {
            try {
                context.contentResolver.takePersistableUriPermission(
                    it,
                    Intent.FLAG_GRANT_READ_URI_PERMISSION
                )
            } catch (e: SecurityException) {
                Log.e("IMAGE", "Unable to persist permission", e)
            }

            viewModel.onUriChanged(it)
        }
    }
    var showExpiryDatePicker by remember {
        mutableStateOf(false)
    }
    AddItemScreen(
        uiState = uiState,
        onNameChanged = viewModel::onNameChanged,
//            onCategoryChanged = {viewModel::onCategoryChanged},
        onPurchaseDateClick = { showPurchaseDatePicker = true
            viewModel::onPurchaseDateChanged },
        onExpiryDateClick = { showExpiryDatePicker = true
            viewModel::onExpiryDateChanged},
        onNotesChanged = viewModel::onNotesChanged,
        onSaveClick = viewModel::saveItem,
        onBackClick = onBackClick,
        onPickImage = {
            launcher.launch(
                arrayOf("image/*")
            )
        },
        onCategoryClick = {}
    )
    SmartDatePicker(showDialog = showPurchaseDatePicker, onDismiss = { showPurchaseDatePicker = false }, onDateSelected = { viewModel.onPurchaseDateChanged(it) })
    SmartDatePicker(showDialog = showExpiryDatePicker, onDismiss = { showExpiryDatePicker = false }, onDateSelected = {viewModel.onExpiryDateChanged(it) })

}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SmartDatePicker(
    showDialog: Boolean,
    onDismiss: () -> Unit,
    onDateSelected: (Long) -> Unit
) {

    if (!showDialog) return

    val datePickerState = rememberDatePickerState()

    DatePickerDialog(
        onDismissRequest = onDismiss,

        confirmButton = {

            TextButton(
                onClick = {

                    datePickerState.selectedDateMillis?.let {
                        onDateSelected(it)
                    }

                    onDismiss()
                }
            ) {
                Text("OK")
            }
        },

        dismissButton = {

            TextButton(
                onClick = onDismiss
            ) {
                Text("Cancel")
            }
        }

    ) {

        DatePicker(
            state = datePickerState
        )
    }
}
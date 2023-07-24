package com.guilhermeb.mymoneycompose.view.money.screen.list

import android.annotation.SuppressLint
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.guilhermeb.mymoneycompose.R
import com.guilhermeb.mymoneycompose.model.data.local.room.entity.money.Money
import com.guilhermeb.mymoneycompose.view.money.activity.MoneyItemListInterface
import com.guilhermeb.mymoneycompose.view.money.activity.MyMoneyMenuInterface
import com.guilhermeb.mymoneycompose.view.money.screen.drawer.*
import com.guilhermeb.mymoneycompose.view.ui.components.Fab
import com.guilhermeb.mymoneycompose.view.ui.theme.MyMoneyComposeTheme
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterialApi::class)
@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun ListScreen(
    moneyItems: StateFlow<List<Money>>,
    moneyItemListInterface: MoneyItemListInterface,
    myMoneyMenuInterface: MyMoneyMenuInterface
) {

    val drawerItem1 = DrawerItem(
        id = "2023-04",
        title = "2023 Abril",
        icon = ImageVector.vectorResource(id = R.drawable.ic_baseline_calendar_month_24)
    )
    val drawerItem2 = DrawerItem(
        id = "2023-03",
        title = "2023 Março",
        icon = ImageVector.vectorResource(id = R.drawable.ic_baseline_calendar_month_24)
    )
    val drawerItem3 = DrawerItem(
        id = "2023-02",
        title = "2023 Fevereiro",
        icon = ImageVector.vectorResource(id = R.drawable.ic_baseline_calendar_month_24)
    )
    val drawerItem4 = DrawerItem(
        id = "2023-01",
        title = "2023 Janeiro",
        icon = ImageVector.vectorResource(id = R.drawable.ic_baseline_calendar_month_24)
    )

    val sheetState = rememberBottomSheetState(initialValue = BottomSheetValue.Collapsed)
    val scaffoldState = rememberBottomSheetScaffoldState(bottomSheetState = sheetState)
    val iconState = remember { mutableStateOf(false) }
    val coroutineScope = rememberCoroutineScope()
    val showMenu = remember { mutableStateOf(false) }

    // State change callback
    LaunchedEffect(sheetState) {
        snapshotFlow { sheetState.isCollapsed }.collect { isCollapsed ->
            iconState.value = !isCollapsed
        }
    }

    BottomSheetScaffold(
        scaffoldState = scaffoldState,
        topBar = {
            TopAppBar(
                title = { Text(text = stringResource(R.string.app_name)) },
                backgroundColor = MaterialTheme.colors.primary,
                contentColor = MaterialTheme.colors.onPrimary,
                navigationIcon = {
                    IconButton(onClick = {
                        coroutineScope.launch {
                            scaffoldState.drawerState.open()
                        }
                    }) {
                        Icon(
                            imageVector = Icons.Default.Menu,
                            contentDescription = stringResource(R.string.open_slide_menu)
                        )
                    }
                },
                actions = {
                    IconButton(onClick = { showMenu.value = true }) {
                        Icon(
                            imageVector = Icons.Filled.MoreVert,
                            contentDescription = stringResource(R.string.about)
                        )
                    }
                    DropdownMenu(
                        expanded = showMenu.value,
                        onDismissRequest = { showMenu.value = false },
                    ) {
                        DropdownMenuItem(onClick = { showMenu.value = false }) {
                            Icon(
                                imageVector = ImageVector.vectorResource(R.drawable.ic_baseline_info_24),
                                contentDescription = stringResource(R.string.about)
                            )
                            Text(
                                modifier = Modifier.padding(start = 8.dp),
                                text = stringResource(R.string.about)
                            )
                        }
                        DropdownMenuItem(onClick = { showMenu.value = false }) {
                            Icon(
                                imageVector = ImageVector.vectorResource(R.drawable.ic_baseline_person_24),
                                contentDescription = stringResource(R.string.account)
                            )
                            Text(
                                modifier = Modifier.padding(start = 8.dp),
                                text = stringResource(R.string.account)
                            )
                        }
                        DropdownMenuItem(onClick = { showMenu.value = false }) {
                            Icon(
                                imageVector = ImageVector.vectorResource(R.drawable.ic_baseline_settings_24),
                                contentDescription = stringResource(R.string.settings)
                            )
                            Text(
                                modifier = Modifier.padding(start = 8.dp),
                                text = stringResource(R.string.settings)
                            )
                        }
                        DropdownMenuItem(onClick = { showMenu.value = false }) {
                            Icon(
                                imageVector = ImageVector.vectorResource(R.drawable.ic_baseline_currency_exchange_24),
                                contentDescription = stringResource(R.string.currency_converter)
                            )
                            Text(
                                modifier = Modifier.padding(start = 8.dp),
                                text = stringResource(R.string.currency_converter)
                            )
                        }
                        DropdownMenuItem(onClick = {
                            showMenu.value = false
                            myMoneyMenuInterface.logout()
                        }) {
                            Icon(
                                imageVector = ImageVector.vectorResource(R.drawable.ic_baseline_logout_24),
                                contentDescription = stringResource(R.string.logout)
                            )
                            Text(
                                modifier = Modifier.padding(start = 8.dp),
                                text = stringResource(R.string.logout)
                            )
                        }
                    }
                }
            )
        },
        drawerContent = {
            DrawerContent(
                items = listOf(drawerItem1, drawerItem2, drawerItem3, drawerItem4),
                onItemClick = {
                    println("Test !!!")
                    coroutineScope.launch {
                        scaffoldState.drawerState.close()
                    }
                },
                drawerWidth = 320.dp
            )
        },
        drawerShape = NavShape(320.dp, 0f),
        content = {
            ContentList(moneyItems, moneyItemListInterface)
        },
        floatingActionButton = {
            Column {
                Fab(
                    onClick = {
                        moneyItemListInterface.goToDetail(null)
                    },
                    iconResource = R.drawable.ic_baseline_add_24,
                    contentDescriptionText = stringResource(R.string.add_item)
                )
                Spacer(modifier = Modifier.height(60.dp))
            }
        },
        sheetContent = {
            Column {
                Box(modifier = Modifier.fillMaxWidth().height(60.dp).clickable {
                    coroutineScope.launch {
                        if (sheetState.isCollapsed) {
                            sheetState.expand()
                            //iconState.value = true
                        } else {
                            sheetState.collapse()
                            //iconState.value = false
                        }
                    }
                }) {
                    Column(modifier = Modifier.fillMaxWidth()) {
                        Box(
                            modifier = Modifier.fillMaxWidth(),
                            contentAlignment = Alignment.Center
                        )
                        {
                            Icon(
                                imageVector = if (iconState.value) {
                                    ImageVector.vectorResource(R.drawable.ic_baseline_keyboard_arrow_down_24)
                                } else {
                                    ImageVector.vectorResource(R.drawable.ic_baseline_keyboard_arrow_up_24)
                                },
                                contentDescription = stringResource(R.string.title)
                            )
                        }
                        Text(
                            text = stringResource(R.string.app_name),
                            fontSize = 16.sp
                        )
                    }
                }
                Box(
                    modifier = Modifier.fillMaxWidth().height(300.dp),
                    contentAlignment = Alignment.CenterStart
                ) {
                    Text(
                        text = "Texto Teste !!! --- Texto Teste !!! --- Texto Teste !!!" +
                                "                                                      " +
                                "Texto Teste !!! --- Texto Teste !!! --- Texto Teste !!!\n" +
                                "Texto Teste !!! --- Texto Teste !!! --- Texto Teste !!!" +
                                "                                                       " +
                                "Texto Teste !!! --- Texto Teste !!! --- Texto Teste !!!",
                        fontSize = 16.sp
                    )
                }
            }
        },
        sheetBackgroundColor = colorResource(R.color.bottom_sheet_background),
        backgroundColor = colorResource(R.color.app_screen_background),
        sheetPeekHeight = 60.dp
    )
}

@Composable
fun ContentList(
    moneyItems: StateFlow<List<Money>>,
    moneyItemListInterface: MoneyItemListInterface
) {
    val moneyItensList by moneyItems.collectAsState()

    LazyColumn {
        itemsIndexed(moneyItensList) { _, item ->
            ItemView(
                item,
                moneyItemListInterface
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MoneyItemListPreview() {
    val mock = object : MoneyItemListInterface {
        override fun goToDetail(moneyItem: Money?) {
            // do nothing
        }
    }

    val mock2 = object : MyMoneyMenuInterface {
        override fun logout() {
            // do nothing
        }
    }

    MyMoneyComposeTheme {
        ListScreen(MutableStateFlow(emptyList()), mock, mock2)
    }
}
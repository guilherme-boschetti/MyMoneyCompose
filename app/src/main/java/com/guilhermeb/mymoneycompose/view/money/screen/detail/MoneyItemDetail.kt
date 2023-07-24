package com.guilhermeb.mymoneycompose.view.money.screen.detail

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.*
import androidx.compose.ui.text.TextRange
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.guilhermeb.mymoneycompose.R
import com.guilhermeb.mymoneycompose.common.enum.MoneyType
import com.guilhermeb.mymoneycompose.common.util.DateUtil
import com.guilhermeb.mymoneycompose.common.util.MaskUtil
import com.guilhermeb.mymoneycompose.model.data.local.room.entity.money.Money
import com.guilhermeb.mymoneycompose.view.money.activity.MoneyItemDetailInterface
import com.guilhermeb.mymoneycompose.view.ui.components.*
import com.guilhermeb.mymoneycompose.view.ui.theme.ColorPrimary
import com.guilhermeb.mymoneycompose.view.ui.theme.MyMoneyComposeTheme
import java.math.BigDecimal
import java.math.RoundingMode
import java.util.*
import kotlin.math.pow

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun DetailScreen(
    moneyItem: Money?,
    actionBack: DefaultActionBarInterface,
    moneyCallback: MoneyItemDetailInterface
) {

    val title = remember { mutableStateOf("") }
    val description = remember { mutableStateOf("") }
    val value = remember { mutableStateOf("") }
    val type = remember { mutableStateOf("") }
    val subtype = remember { mutableStateOf("") }
    val paydate = remember { mutableStateOf("") }
    val paid = remember { mutableStateOf(false) }
    val fixed = remember { mutableStateOf(false) }
    val dueDay = remember { mutableStateOf("") }

    val money: Money
    if (moneyItem != null) {
        money = moneyItem
    } else {
        money = Money(
            userEmail = "test@test.com",
            title = "",
            description = "",
            value = BigDecimal.ZERO,
            type = "",
            subtype = "",
            payDate = null,
            paid = false,
            fixed = false,
            dueDay = null
        )
    }

    moneyItem?.let {
        val moneyItemType =
            if (moneyItem.type.equals(MoneyType.INCOME.name))
                stringResource(R.string.income)
            else if (moneyItem.type.equals(MoneyType.EXPENSE.name))
                stringResource(R.string.expense)
            else
                ""

        title.value = moneyItem.title
        description.value = moneyItem.description ?: ""
        value.value = MaskUtil.mask(moneyItem.value.toString(), null)
        type.value = moneyItemType
        subtype.value = moneyItem.subtype ?: ""
        paydate.value = moneyItem.payDate?.let { date -> DateUtil.DAY_MONTH_YEAR.format(date) } ?: ""
        paid.value = moneyItem.paid
        fixed.value = moneyItem.fixed
        dueDay.value = moneyItem.dueDay.toString()
    }

    val incomeLabel = stringResource(R.string.income)
    val expenseLabel = stringResource(R.string.expense)

    Scaffold(
        topBar = {
            DefaultActionBar(stringResource(R.string.app_name), actionBack)
        },
        content = {
            ContentDetail(
                //moneyItem,
                title,
                description,
                value,
                type,
                subtype,
                paydate,
                paid,
                fixed,
                dueDay
            )
        },
        floatingActionButton = {
            Row(modifier = Modifier.fillMaxWidth()) {
                Spacer(modifier = Modifier.width(32.dp))
                Box(modifier = Modifier.weight(1f)) {
                    ExtendedFab(
                        onClick = {
                            actionBack.actionBack()
                        },
                        iconResource = R.drawable.ic_baseline_close_24,
                        text = stringResource(R.string.cancel)
                    )
                }
                Fab(
                    onClick = {

                        val moneyItemType =
                            if (type.value.equals(incomeLabel))
                                MoneyType.INCOME.name
                            else if (type.value.equals(expenseLabel))
                                MoneyType.EXPENSE.name
                            else
                                ""

                        money.title = title.value
                        money.description = description.value
                        money.value = BigDecimal(MaskUtil.parseValue(value.value).toString())
                        money.type = moneyItemType
                        money.subtype = subtype.value
                        money.paid = paid.value
                        money.fixed = fixed.value

                        if (moneyItem != null) {
                            moneyCallback.updateMoneyItem(money)
                        } else {
                            moneyCallback.addMoneyItem(money)
                        }
                        actionBack.actionBack()
                    },
                    iconResource = R.drawable.ic_baseline_edit_24,
                    contentDescriptionText = stringResource(R.string.save_or_edit)
                )
            }
        }
    )
}

@Composable
fun ContentDetail(
    //moneyItem: Money?,
    title: MutableState<String> = remember { mutableStateOf("") },
    description: MutableState<String> = remember { mutableStateOf("") },
    value: MutableState<String> = remember { mutableStateOf("") },
    type: MutableState<String> = remember { mutableStateOf("") },
    subtype: MutableState<String> = remember { mutableStateOf("") },
    payDate: MutableState<String> = remember { mutableStateOf("") }, // TODO: Remover payDate que não é mais usado
    paid: MutableState<Boolean> = remember { mutableStateOf(false) },
    fixed: MutableState<Boolean> = remember { mutableStateOf(false) },
    dueDay: MutableState<String> = remember { mutableStateOf("") }
) {
    var payDateTextFieldValueState by remember { mutableStateOf(TextFieldValue(text = payDate.value)) }

    Column(
        modifier = Modifier.fillMaxSize()
            .verticalScroll(rememberScrollState())
            .background(color = colorResource(R.color.app_screen_background))
            .padding(dimensionResource(R.dimen.screen_margin)),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        /*val title = remember { mutableStateOf("") }
        val description = remember { mutableStateOf("") }
        val value = remember { mutableStateOf("") }
        val type = remember { mutableStateOf("") }
        val subtype = remember { mutableStateOf("") }
        val paid = remember { mutableStateOf(false) }
        val fixed = remember { mutableStateOf(false) }*/

        /*moneyItem?.let {
            val moneyItemType =
                if (moneyItem.type.equals(MoneyType.INCOME.name))
                    stringResource(R.string.income)
                else if (moneyItem.type.equals(MoneyType.EXPENSE.name))
                    stringResource(R.string.expense)
                else
                    ""

            title.value = moneyItem.title
            description.value = moneyItem.description ?: ""
            value.value = MaskUtil.mask(moneyItem.value.toString(), null)
            type.value = moneyItemType
            subtype.value = moneyItem.subtype ?: ""
            paid.value = moneyItem.paid
            fixed.value = moneyItem.fixed
        }*/

        Spacer(modifier = Modifier.height(16.dp))

        InputTextField(
            label = stringResource(R.string.title),
            placeholder = stringResource(R.string.title),
            value = title.value,
            //errorMessage = emailError,
            onTextChanged = {
                title.value = it
                //isEmailError.value = ""
            },
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Text,
                imeAction = ImeAction.Next
            )
        )

        Spacer(modifier = Modifier.height(16.dp))

        InputTextField(
            label = stringResource(R.string.description),
            placeholder = stringResource(R.string.hint_description),
            value = description.value,
            //errorMessage = emailError,
            onTextChanged = {
                description.value = it
                //isEmailError.value = ""
            },
            textArea = true
        )

        Spacer(modifier = Modifier.height(16.dp))

        InputTextField(
            label = stringResource(R.string.value),
            placeholder = stringResource(R.string.hint_value),
            value = value.value,
            //errorMessage = emailError,
            onTextChanged = {
                //value.value = it
                //isEmailError.value = ""

                val str = it
                val mascara: String
                // get only the numbers
                var auxValue = str.replace("[^\\d]".toRegex(), "")
                if (auxValue.isEmpty()) {
                    auxValue = "0"
                }
                //Convert to BigDecimal
                var aux = try {
                    BigDecimal(auxValue)
                } catch (e: Exception) {
                    BigDecimal.ZERO
                }
                aux = aux.divide(
                    BigDecimal.valueOf(10.0.pow(2.0)),
                    2,
                    RoundingMode.HALF_EVEN
                )
                mascara = MaskUtil.getDecimalFormat().format(aux)
                value.value = mascara
            },
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Number,
                imeAction = ImeAction.Next
            )
        )

        Spacer(modifier = Modifier.height(16.dp))

        val radioOptions = listOf(stringResource(R.string.income), stringResource(R.string.expense))

        Row(modifier = Modifier.selectableGroup()) {
            radioOptions.forEach { label ->
                AppRadioButton(
                    modifier = Modifier.weight(1f),
                    selected = type.value.equals(label),
                    onClick = {
                        type.value = label
                    },
                    text = label
                )
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        InputTextField(
            label = stringResource(R.string.subtype),
            placeholder = stringResource(R.string.hint_subtype),
            value = subtype.value,
            //errorMessage = emailError,
            onTextChanged = {
                subtype.value = it
                //isEmailError.value = ""
            },
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Text,
                imeAction = ImeAction.Next
            )
        )

        Spacer(modifier = Modifier.height(16.dp))

        val maxChar = 10
        InputTextField(
            label = stringResource(R.string.pay_date),
            placeholder = stringResource(R.string.hint_date),
            value = payDateTextFieldValueState,
            //errorMessage = emailError,
            onTextChanged = {
                if (it.text.length > maxChar)
                    return@InputTextField

                //payDate.value = it
                //isEmailError.value = ""

                val old = payDateTextFieldValueState.text
                val mask = MaskUtil.FORMAT_DATE
                val str = MaskUtil.unmask(it.text)
                var result = ""

                if (str.length >= old.length) {
                    var i = 0
                    for (m in mask.toCharArray()) {
                        if (m != '#') {
                            result += m
                            continue
                        }
                        result += try {
                            str[i]
                        } catch (e: Exception) {
                            break
                        }
                        i++
                    }
                } else {
                    result = it.text
                }

                payDateTextFieldValueState = TextFieldValue(
                    text = result,
                    selection = TextRange(result.length)
                )
            },
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.NumberPassword,
                imeAction = ImeAction.Next
            ),
            trailingIcon = {
                val errorMessage: State<String?> = remember { mutableStateOf("") } // TODO: Exclude this mock error message here
                if ((errorMessage.value ?: "").isNotEmpty()) {
                    Icon(
                        ImageVector.vectorResource(id = R.drawable.ic_baseline_error_24),
                        stringResource(R.string.error),
                        tint = MaterialTheme.colors.error
                    )
                    return@InputTextField
                }

                val (icon, iconColor) = Pair(
                        painterResource(R.drawable.ic_baseline_calendar_month_24),
                        ColorPrimary
                    )

                val showPicker = remember { mutableStateOf(false) }
                if (showPicker.value)
                    AppDatePickerDialog(showPicker, object : DatePickerResult{
                        override fun onDatePick(selectedDateMillis: Long?) {
                            selectedDateMillis?.let {
                                val selectedDate = DateUtil.DAY_MONTH_YEAR.format(Date(it))
                                payDateTextFieldValueState = TextFieldValue(
                                    text = selectedDate,
                                    selection = TextRange(selectedDate.length)
                                )
                            }
                        }
                    })

                IconButton(onClick = {
                    showPicker.value = true
                }) {
                    Icon(
                        icon,
                        contentDescription = stringResource(R.string.pay_date),
                        tint = iconColor
                    )
                }
            }
        )

        Spacer(modifier = Modifier.height(16.dp))

        Row {
            AppCheckBox(
                modifier = Modifier.weight(1f),
                checked = paid.value,
                onCheckedChange = { _checked ->
                    paid.value = _checked
                },
                text = stringResource(R.string.paid)
            )

            AppCheckBox(
                modifier = Modifier.weight(1f),
                checked = fixed.value,
                onCheckedChange = { _checked ->
                    fixed.value = _checked
                },
                text = stringResource(R.string.fixed)
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        InputTextField(
            label = stringResource(R.string.due_day),
            placeholder = stringResource(R.string.hint_day),
            value = dueDay.value,
            //errorMessage = emailError,
            onTextChanged = {
                dueDay.value = it
                //isEmailError.value = ""
            },
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.NumberPassword,
                imeAction = ImeAction.Done
            )
        )

        Spacer(modifier = Modifier.height(72.dp))
    }
}

@Preview(showBackground = true)
@Composable
fun MoneyItemDetailPreview() {
    val mock = object : DefaultActionBarInterface {
        override fun actionBack() {
            // do nothing
        }
    }

    val mock2 = object : MoneyItemDetailInterface {
        override fun addMoneyItem(moneyItem: Money) {
            // do nothing
        }

        override fun updateMoneyItem(moneyItem: Money) {
            // do nothing
        }
    }

    MyMoneyComposeTheme {
        DetailScreen(null, mock, mock2)
    }
}
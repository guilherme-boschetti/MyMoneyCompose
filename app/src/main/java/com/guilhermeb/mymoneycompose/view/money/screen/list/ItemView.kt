package com.guilhermeb.mymoneycompose.view.money.screen.list

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.guilhermeb.mymoneycompose.R
import com.guilhermeb.mymoneycompose.common.enum.MoneyType
import com.guilhermeb.mymoneycompose.model.data.local.room.entity.money.Money
import com.guilhermeb.mymoneycompose.view.money.activity.MoneyItemListInterface
import com.guilhermeb.mymoneycompose.view.ui.theme.MyMoneyComposeTheme
import java.math.BigDecimal
import java.util.*

@Composable
fun ItemView(money: Money, moneyItemListInterface: MoneyItemListInterface) {
    Box(modifier = Modifier.padding(16.dp).background(color = colorResource(R.color.app_screen_background))) {
        Card(elevation = 4.dp,
            modifier = Modifier.fillMaxWidth()
                .clickable {
                    moneyItemListInterface.goToDetail(money)
                }
        ) {
            Text(text = money.title, modifier = Modifier.padding(16.dp))
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MoneyItemViewPreview() {
    val moneyItem = Money(
        userEmail = "test@test.com",
        title = "Título teste 1",
        description = "Descrição teste 1",
        value = BigDecimal("9.99"),
        type = MoneyType.EXPENSE.name,
        subtype = "Teste",
        payDate = Date(),
        paid = true,
        fixed = false,
        dueDay = null
    )

    val mock = object : MoneyItemListInterface {
        override fun goToDetail(moneyItem: Money?) {
            // do nothing
        }
    }

    MyMoneyComposeTheme {
        ItemView(moneyItem, mock)
    }
}
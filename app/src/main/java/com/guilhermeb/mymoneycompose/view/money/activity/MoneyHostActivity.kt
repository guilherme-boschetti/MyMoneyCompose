package com.guilhermeb.mymoneycompose.view.money.activity

import android.content.Intent
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.guilhermeb.mymoneycompose.model.data.local.room.entity.money.Money
import com.guilhermeb.mymoneycompose.view.app.activity.AbstractActivity
import com.guilhermeb.mymoneycompose.view.login.activity.LoginActivity
import com.guilhermeb.mymoneycompose.view.money.screen.detail.DetailScreen
import com.guilhermeb.mymoneycompose.view.money.screen.list.ListScreen
import com.guilhermeb.mymoneycompose.view.navigation.Constants.Companion.DETAIL_SCREEN
import com.guilhermeb.mymoneycompose.view.navigation.Constants.Companion.LIST_SCREEN
import com.guilhermeb.mymoneycompose.view.ui.theme.MyMoneyComposeTheme
import com.guilhermeb.mymoneycompose.viewmodel.money.MoneyViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@AndroidEntryPoint
class MoneyHostActivity : AbstractActivity(), MyMoneyMenuInterface {

    @Inject
    lateinit var moneyViewModel: MoneyViewModel

    private lateinit var navController: NavHostController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyMoneyComposeTheme {
                val moneyItems = MutableStateFlow<List<Money>>(emptyList())

                moneyViewModel.moneyItems.observe(this) {
                    moneyItems.value = it
                }

                moneyViewModel.getMoneyItems()

                var currentMoneyItem: Money? = null
                moneyViewModel.moneyItem.observe(this) {
                    currentMoneyItem = it
                }

                navController = rememberNavController()
                /*NavHost(navController = navController, startDestination = LIST_SCREEN) {
                    composable(LIST_SCREEN) {
                        ListScreen(moneyItems.asStateFlow(), object : MoneyItemListInterface {
                            override fun goToDetail(item: Money?) {
                                var id: Long = -1
                                item?.let {
                                    id = item.id
                                    moneyViewModel.getMoneyItemById(id)
                                }
                                navController.navigate(route = "${DETAIL_SCREEN}/${id}") {
                                    moneyItem = null
                                }
                            }
                        })
                    }
                    composable(route = "${DETAIL_SCREEN}/{id}", arguments = listOf(navArgument("id") { type = NavType.LongType })) {
                        DetailScreen(moneyItem)
                    }
                }*/
                // https://developer.android.com/jetpack/compose/navigation?hl=pt-br

                NavHost(navController = navController, startDestination = LIST_SCREEN) {
                    composable(LIST_SCREEN) {
                        ListScreen(moneyItems.asStateFlow(), object : MoneyItemListInterface {
                            override fun goToDetail(moneyItem: Money?) {
                                moneyItem?.let {
                                    moneyViewModel.getMoneyItemById(moneyItem.id)
                                }
                                navController.navigate(route = DETAIL_SCREEN) {
                                    currentMoneyItem = null
                                }
                            }
                        },
                        this@MoneyHostActivity)
                    }
                    composable(route = DETAIL_SCREEN) {
                        DetailScreen(currentMoneyItem, this@MoneyHostActivity, object : MoneyItemDetailInterface {
                            override fun addMoneyItem(moneyItem: Money) {
                                moneyViewModel.addMoneyItem(moneyItem)
                            }
                            override fun updateMoneyItem(moneyItem: Money) {
                                moneyViewModel.addMoneyItem(moneyItem)
                            }
                        })
                    }
                }
            }
        }
    }

    override fun logout() {
        moneyViewModel.signOut()
        goToLoginActivity()
    }

    private fun goToLoginActivity() {
        val intent = Intent(this, LoginActivity::class.java)
        intent.flags =
            Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK // close the activities on stack
        startActivity(intent)
    }

    override fun actionBack() {
        navController.navigateUp()
    }
}

interface MoneyItemListInterface {
    fun goToDetail(moneyItem: Money?)
}

interface MoneyItemDetailInterface {
    fun addMoneyItem(moneyItem: Money)
    fun updateMoneyItem(moneyItem: Money)
}

interface MyMoneyMenuInterface {
    fun logout()
}
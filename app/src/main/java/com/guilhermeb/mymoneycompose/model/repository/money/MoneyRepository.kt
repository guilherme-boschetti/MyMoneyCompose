package com.guilhermeb.mymoneycompose.model.repository.money

import com.guilhermeb.mymoneycompose.BuildConfig
import com.guilhermeb.mymoneycompose.model.data.local.room.dao.money.MoneyDao
import com.guilhermeb.mymoneycompose.model.data.local.room.entity.money.Money
import com.guilhermeb.mymoneycompose.model.data.local.room.entity.money.chart.ChartEntry
import com.guilhermeb.mymoneycompose.model.data.remote.firebase.rtdb.FirebaseRealTimeDataBase
import com.guilhermeb.mymoneycompose.model.repository.contract.AsyncProcess
import kotlinx.coroutines.flow.Flow
import java.math.BigDecimal

class MoneyRepository(
    private val dataSource: MoneyDao,
    private val dataBackup: FirebaseRealTimeDataBase
) {

    suspend fun insertOrReplaceLocal(moneyItem: Money) {
        dataSource.insert(moneyItem)
    }

    suspend fun insert(moneyItem: Money) {
        val id = dataSource.insert(moneyItem)
        if (!BuildConfig.IS_FREE) {
            moneyItem.id = id
            dataBackup.insert(moneyItem)
        }
    }

    suspend fun update(moneyItem: Money) {
        dataSource.update(moneyItem)
        if (!BuildConfig.IS_FREE) {
            dataBackup.update(moneyItem)
        }
    }

    suspend fun delete(moneyItem: Money) {
        dataSource.delete(moneyItem)
        if (!BuildConfig.IS_FREE) {
            dataBackup.delete(moneyItem)
        }
    }

    suspend fun removeAllMoneyItemsByUser(userEmail: String) {
        dataSource.removeAllMoneyItemsByUser(userEmail)
        if (!BuildConfig.IS_FREE) {
            dataBackup.removeAllMoneyItemsByUser()
        }
    }

    suspend fun getMoneyItemById(id: Long): Money? {
        return dataSource.getMoneyItemById(id)
    }

    fun getAllMoneyItemsByUserBetweenDates(
        userEmail: String,
        startDate: String,
        endDate: String
    ): Flow<List<Money>> {
        return dataSource.getAllMoneyItemsByUserBetweenDates(userEmail, startDate, endDate)
    }

    fun getSubtypesByUserAndFilter(userEmail: String, filter: String): Flow<List<String>> {
        return dataSource.getSubtypesByUserAndFilter(userEmail, filter)
    }

    fun getAllMonthsByUser(userEmail: String): Flow<List<String>> {
        return dataSource.getAllMonthsByUser(userEmail)
    }

    fun fetchDataFromFirebaseRTDB(asyncProcess: AsyncProcess<List<Money>>) {
        dataBackup.fetchDataFromFirebaseRTDB(asyncProcess)
    }

    fun observeMoneyItemsFirebaseRTDB(asyncProcess: AsyncProcess<List<Money>?>) {
        dataBackup.observeMoneyItemsFirebaseRTDB(asyncProcess)
    }

    fun getPreviousMonthBalance(
        userEmail: String,
        startDate: String,
        endDate: String
    ): Flow<BigDecimal> {
        return dataSource.getPreviousMonthBalance(userEmail, startDate, endDate)
    }

    fun getChartData(
        userEmail: String,
        type: String,
        startDate: String,
        endDate: String
    ): Flow<List<ChartEntry>> {
        return dataSource.getChartData(userEmail, type, startDate, endDate)
    }
}
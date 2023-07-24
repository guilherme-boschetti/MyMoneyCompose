package com.guilhermeb.mymoneycompose.model.data.remote.retrofit.currency.api

import com.guilhermeb.mymoneycompose.model.data.remote.retrofit.currency.model.Currency
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface CurrencyApi {

    @GET("json/{currency}")
    suspend fun getCurrency(@Path("currency") currency: String): Response<List<Currency>>
}
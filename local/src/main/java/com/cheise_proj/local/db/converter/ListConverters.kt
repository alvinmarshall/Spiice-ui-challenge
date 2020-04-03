package com.cheise_proj.local.db.converter

import androidx.room.TypeConverter
import com.cheise_proj.local.model.PortfolioLocal
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class ListConverters {
    @TypeConverter
    fun restoreList(listOfString: String?): List<String?>? {
        return Gson().fromJson(
            listOfString,
            object : TypeToken<List<String?>?>() {}.type
        )
    }

    @TypeConverter
    fun saveList(listOfString: List<String?>?): String? {
        return Gson().toJson(listOfString)
    }

}

class PortfolioConverter {
    @TypeConverter
    fun stringToPortfolio(data: String?): List<PortfolioLocal?> {
        return Gson().fromJson(
            data,
            object : TypeToken<List<PortfolioLocal?>?>() {}.type
        )
    }

    @TypeConverter
    fun saveListPortfolio(portfolio: List<PortfolioLocal?>?): String? {
        return Gson().toJson(portfolio)
    }
}
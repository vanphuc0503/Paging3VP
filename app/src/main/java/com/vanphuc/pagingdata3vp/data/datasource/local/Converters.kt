package com.vanphuc.pagingdata3vp.data.datasource.local

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.vanphuc.pagingdata3vp.data.model.Source

class Converters {
    @TypeConverter
    fun toSource(source: String): Source? {
        return Gson().fromJson(source, Source::class.java)
    }

    @TypeConverter
    fun fromSource(source: Source): String? {
        return Gson().toJson(source)
    }
}
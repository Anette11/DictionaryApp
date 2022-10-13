package com.example.dictionaryapp.data.local.utils

import androidx.room.ProvidedTypeConverter
import androidx.room.TypeConverter
import com.example.dictionaryapp.data.local.entities.LicenseDbo
import com.example.dictionaryapp.data.local.entities.MeaningDbo
import com.example.dictionaryapp.data.local.entities.PhoneticDbo
import com.google.gson.reflect.TypeToken
import java.util.*

@ProvidedTypeConverter
class Converters(
    private val jsonParser: JsonParser
) {
    @TypeConverter
    fun fromMeaningsDboListToJson(
        list: List<MeaningDbo>
    ): String = jsonParser.toJson(
        t = list,
        type = object : TypeToken<ArrayList<MeaningDbo>>() {}.type
    ) ?: "[]"

    @TypeConverter
    fun toMeaningsDboJsonFromList(
        json: String
    ): List<MeaningDbo> = jsonParser.fromJson(
        json = json,
        type = object : TypeToken<ArrayList<MeaningDbo>>() {}.type
    ) ?: emptyList()

    @TypeConverter
    fun fromPhoneticDboListToJson(
        list: List<PhoneticDbo>
    ): String = jsonParser.toJson(
        t = list,
        type = object : TypeToken<ArrayList<PhoneticDbo>>() {}.type
    ) ?: "[]"

    @TypeConverter
    fun toPhoneticDboJsonFromList(
        json: String
    ): List<PhoneticDbo> = jsonParser.fromJson(
        json = json,
        type = object : TypeToken<ArrayList<PhoneticDbo>>() {}.type
    ) ?: emptyList()

    @TypeConverter
    fun fromLicenseDboJson(
        licenseDbo: LicenseDbo
    ): String = jsonParser.toJson(
        t = licenseDbo,
        type = object : TypeToken<LicenseDbo>() {}.type
    ) ?: "{}"

    @TypeConverter
    fun toLicenseDboJsonFromList(
        json: String
    ): LicenseDbo? = jsonParser.fromJson(
        json = json,
        type = object : TypeToken<LicenseDbo>() {}.type
    )

    @TypeConverter
    fun fromStringsListToJson(
        list: List<String>
    ): String = jsonParser.toJson(
        t = list,
        type = object : TypeToken<ArrayList<String>>() {}.type
    ) ?: "[]"

    @TypeConverter
    fun fromJsonToStringsList(
        json: String
    ): List<String> = jsonParser.fromJson<List<String>>(
        json = json,
        type = object : TypeToken<ArrayList<String>>() {}.type
    ) ?: emptyList()

    @TypeConverter
    fun fromDateToLong(
        date: Date
    ): Long = date.time

    @TypeConverter
    fun fromLongToDate(
        time: Long
    ): Date = Date(time)
}
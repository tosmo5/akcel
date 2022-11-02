package com.tosmo.akcel.converters

import com.tosmo.akcel.converters.bigdecimal.BigDecimalBooleanConverter
import com.tosmo.akcel.converters.bigdecimal.BigDecimalNumberConverter
import com.tosmo.akcel.converters.bigdecimal.BigDecimalStringConverter
import com.tosmo.akcel.converters.biginteger.BigIntegerBooleanConverter
import com.tosmo.akcel.converters.biginteger.BigIntegerNumberConverter
import com.tosmo.akcel.converters.biginteger.BigIntegerStringConverter
import com.tosmo.akcel.converters.boolean.BooleanBooleanConverter
import com.tosmo.akcel.converters.boolean.BooleanNumberConverter
import com.tosmo.akcel.converters.boolean.BooleanStringConverter
import com.tosmo.akcel.converters.byte.ByteBooleanConverter
import com.tosmo.akcel.converters.byte.ByteNumberConverter
import com.tosmo.akcel.converters.byte.ByteStringConverter
import com.tosmo.akcel.converters.date.DateNumberConverter
import com.tosmo.akcel.converters.date.DateStringConverter
import com.tosmo.akcel.converters.double.DoubleBooleanConverter
import com.tosmo.akcel.converters.double.DoubleNumberConverter
import com.tosmo.akcel.converters.double.DoubleStringConverter
import com.tosmo.akcel.converters.float.FloatBooleanConverter
import com.tosmo.akcel.converters.float.FloatNumberConverter
import com.tosmo.akcel.converters.float.FloatStringConverter
import com.tosmo.akcel.converters.long.LongBooleanConverter
import com.tosmo.akcel.converters.long.LongNumberConverter
import com.tosmo.akcel.converters.long.LongStringConverter
import com.tosmo.akcel.converters.short.ShortBooleanConverter
import com.tosmo.akcel.converters.short.ShortNumberConverter
import com.tosmo.akcel.converters.short.ShortStringConverter
import com.tosmo.akcel.converters.string.StringBooleanConverter
import com.tosmo.akcel.converters.string.StringErrorConverter
import com.tosmo.akcel.converters.string.StringNumberConverter
import com.tosmo.akcel.converters.string.StringStringConverter


internal object DefaultConverterLoader {
    private val mDefaultWriteConverter: MutableMap<ConverterKey, Converter<*>> = mutableMapOf()
    private val mAllConverter: MutableMap<ConverterKey, Converter<*>> = mutableMapOf()
    
    val defaultReadConverters: Map<ConverterKey, Converter<*>> = mAllConverter
    
    val allConverters: Map<ConverterKey, Converter<*>> = mAllConverter
    
    init {
//        initDefaultWriteConverter()
        initAllConverter()
    }
    
    private fun initAllConverter() {
        putAllConverter(BigDecimalBooleanConverter)
        putAllConverter(BigDecimalNumberConverter)
        putAllConverter(BigDecimalStringConverter)
        putAllConverter(BigIntegerBooleanConverter)
        putAllConverter(BigIntegerNumberConverter)
        putAllConverter(BigIntegerStringConverter)
        putAllConverter(BooleanBooleanConverter)
        putAllConverter(BooleanNumberConverter)
        putAllConverter(BooleanStringConverter)
        putAllConverter(ByteBooleanConverter)
        putAllConverter(ByteNumberConverter)
        putAllConverter(ByteStringConverter)
        putAllConverter(DateNumberConverter)
        putAllConverter(DateStringConverter)
//        putAllConverter(LocalDateNumberConverter())
//        putAllConverter(LocalDateTimeStringConverter())
        putAllConverter(DoubleBooleanConverter)
        putAllConverter(DoubleNumberConverter)
        putAllConverter(DoubleStringConverter)
        putAllConverter(FloatBooleanConverter)
        putAllConverter(FloatNumberConverter)
        putAllConverter(FloatStringConverter)
//        putAllConverter(IntegerBooleanConverter())
//        putAllConverter(IntegerNumberConverter())
//        putAllConverter(IntegerStringConverter())
        putAllConverter(LongBooleanConverter)
        putAllConverter(LongNumberConverter)
        putAllConverter(LongStringConverter)
        putAllConverter(ShortBooleanConverter)
        putAllConverter(ShortNumberConverter)
        putAllConverter(ShortStringConverter)
        putAllConverter(StringBooleanConverter)
        putAllConverter(StringNumberConverter)
        putAllConverter(StringStringConverter)
        putAllConverter(StringErrorConverter)
        putAllConverter(BigIntegerStringConverter)
    }
    
    private fun putWriteStringConverter(converter: Converter<*>) {
        mDefaultWriteConverter[ConverterKey[converter]] = converter
    }
    
    /**
     * Load default read converter
     *
     * @return
     */
    fun loadDefaultReadConverter(): Map<ConverterKey, Converter<*>> {
        return loadAllConverter()
    }
    
    /**
     * Load all converter
     *
     * @return
     */
    fun loadAllConverter(): Map<ConverterKey, Converter<*>> {
        return mAllConverter
    }
    
    private fun putAllConverter(converter: Converter<*>) {
        mAllConverter[ConverterKey[converter]] = converter
    }
}

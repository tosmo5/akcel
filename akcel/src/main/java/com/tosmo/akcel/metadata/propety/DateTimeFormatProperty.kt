package com.tosmo.akcel.metadata.propety

import com.tosmo.akcel.metadata.GlobalConfiguration

data class DateTimeFormatProperty(
    var format: String = "",
    var use1904windowing: Boolean = GlobalConfiguration.use1904windowing
)

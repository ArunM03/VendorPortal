package com.vendorportal.app.data

data class GetReportData(
    val disabled: Boolean,
    val group: Any,
    val selected: Boolean,
    val text: String,
    val value: String
)
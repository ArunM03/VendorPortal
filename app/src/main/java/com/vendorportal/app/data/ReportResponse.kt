package com.vendorportal.app.data

data class ReportResponse(
    val `data`: List<ReportData>,
    val message: String,
    val status: Int
)
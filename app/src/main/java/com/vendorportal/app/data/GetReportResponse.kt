package com.vendorportal.app.data

data class GetReportResponse(
    val `data`: List<GetReportData>,
    val message: String,
    val status: Int
)
package com.vendorportal.app.api

import com.vendorportal.app.data.LoginResponseData
import com.vendorportal.app.data.ReportResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.HeaderMap
import retrofit2.http.POST
import retrofit2.http.Query

interface VendorPortalInterface {


    @POST("api/Login")
    suspend fun login(
        @Query("Logon")
        logon : String,
        @Query("Password")
        password : String,
        @Query("isAdmin")
        isAdmin : Boolean,
    ) : Response<LoginResponseData>



    @GET("api/Reports")
    suspend fun getReportsByGet(
        @HeaderMap headers: Map<String, String>
    ) : Response<ReportResponse>



    @POST("api/Reports")
    suspend fun getReportsByPost(
        @Query("Logon")
        logon : String,
        @Query("Password")
        password : String,
        @Query("isAdmin")
        isAdmin : Boolean,
    ) : Response<ReportResponse>







}
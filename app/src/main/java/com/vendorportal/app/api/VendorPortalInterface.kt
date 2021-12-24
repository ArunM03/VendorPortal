package com.vendorportal.app.api

import com.vendorportal.app.data.LoginResponseData
import com.vendorportal.app.data.ReportResponse
import retrofit2.Response
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



    @POST("api/Reports")
    suspend fun getReports(
        @Query("Logon")
        logon : String,
        @Query("Password")
        password : String,
        @Query("isAdmin")
        isAdmin : Boolean,
    ) : Response<ReportResponse>







}
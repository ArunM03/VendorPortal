package com.vendorportal.app.api

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vendorportal.app.data.GetReportResponse
import com.vendorportal.app.data.LoginResponseData
import com.vendorportal.app.data.ReportResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class VendorPortalViewmodel : ViewModel(){


    var loginLive : MutableLiveData<LoginResponseData> = MutableLiveData()
    var errorLoginLive : MutableLiveData<String> = MutableLiveData()

    var reportsLive : MutableLiveData<GetReportResponse> = MutableLiveData()
    var errorReportsLive : MutableLiveData<String> = MutableLiveData()

    var reportsByPostLive : MutableLiveData<ReportResponse> = MutableLiveData()
    var errorreportsByPostLive : MutableLiveData<String> = MutableLiveData()


    fun  login(logon : String, password : String, isAdmin : Boolean) = viewModelScope.launch(Dispatchers.IO) {
        try {
            val response = RetrofitInstance.api.login(logon, password, isAdmin)
            if (response.isSuccessful){
                response.body()?.let {
                    if (it.status == 200 && it.message == "User Available"){
                        loginLive.postValue(it)
                    }else{
                        errorLoginLive.postValue(it.message)
                    }
                }
            }else{
               errorLoginLive.postValue("Response unsuccessful")
            }
        }catch (e:Exception){
            errorLoginLive.postValue(e.message)
        }
    }

    fun  getReportsByGet(headers: Map<String, String>) = viewModelScope.launch(Dispatchers.IO) {
        try {
            val response = RetrofitInstance.api.getReportsByGet(headers)
            if (response.isSuccessful){
                response.body()?.let {
                    if (it.status == 200){
                        reportsLive.postValue(it)
                    }else{
                        errorReportsLive.postValue(it.message)
                    }
                }
            }else{
                errorReportsLive.postValue("Response unsuccessful")
            }
        }catch (e:Exception){
            errorReportsLive.postValue(e.message)
        }
    }

    fun  getReportsByPost( headers: Map<String, String>, id : Int) = viewModelScope.launch(Dispatchers.IO) {
        try {
            val response = RetrofitInstance.api.getReportsByPost(headers,id)
        /*    errorreportsByPostLive.postValue("Response unsuccessful ${response}")*/
            if (response.isSuccessful){
                response.body()?.let {
                    if (it.status == 200){
                        reportsByPostLive.postValue(it)
                    }else{
                        errorreportsByPostLive.postValue(it.message)
                    }
                }
            }else{
                errorreportsByPostLive.postValue("Response unsuccessful ${response.errorBody()}")
            }
        }catch (e:Exception){
            errorreportsByPostLive.postValue(e.message)
        }
    }
}
package com.vendorportal.app.api

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vendorportal.app.data.LoginResponseData
import com.vendorportal.app.data.ReportResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class VendorPortalViewmodel : ViewModel(){


    var loginLive : MutableLiveData<LoginResponseData> = MutableLiveData()
    var errorLoginLive : MutableLiveData<String> = MutableLiveData()

    var reportsLive : MutableLiveData<ReportResponse> = MutableLiveData()
    var errorReportsLive : MutableLiveData<String> = MutableLiveData()


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

    fun  getReports(logon : String, password : String, isAdmin : Boolean) = viewModelScope.launch(Dispatchers.IO) {
        try {
            val response = RetrofitInstance.api.getReports(logon, password, isAdmin)
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
}
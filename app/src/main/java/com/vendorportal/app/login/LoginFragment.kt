package com.vendorportal.app.login

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.vendorportal.app.R
import com.vendorportal.app.api.VendorPortalViewmodel
import com.vendorportal.app.databinding.FragmentLoginAdminBinding
import com.vendorportal.app.others.MyDialog
import com.vendorportal.app.others.MyToast
import com.vendorportal.app.sharedpref.SharedPref

class LoginFragment : Fragment(R.layout.fragment_login_admin) {

    lateinit var binding : FragmentLoginAdminBinding
    lateinit var viewmodel : VendorPortalViewmodel
    lateinit var myToast : MyToast
    lateinit var myDialog: MyDialog
    lateinit var sharedPref: SharedPref
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

       binding = FragmentLoginAdminBinding.bind(view)

        setUI(view)

    }

    private fun setUI(view: View) {

        viewmodel = ViewModelProvider(this).get(VendorPortalViewmodel::class.java)

        myToast = MyToast(requireContext())
        myDialog = MyDialog(requireContext(),this)
        sharedPref = SharedPref(requireContext())

        binding.cdLogin.setOnClickListener {
            login()
        }

        setCallbacks(view)

    }

    private fun setCallbacks(view: View) {

        viewmodel.loginLive.observe(viewLifecycleOwner, Observer {
            myToast.showToast("Logged In Successfully")
            myDialog.dismissProgressDialog()
            sharedPref.setUserAuthStatus(true)
            sharedPref.saveWebUrl(it.data)
            sharedPref.saveToken(it.token)
            Navigation.findNavController(view).navigate(R.id.action_loginFragment_to_dashboardFragment)
        })

        viewmodel.errorLoginLive.observe(viewLifecycleOwner, Observer {
            sharedPref.saveLoginCode("")
            sharedPref.savePassword("")
            sharedPref.saveToken("")
            myDialog.dismissProgressDialog()
            myDialog.showErrorAlertDialog(it)
        })
    }

    private fun login() {
        val loginCode = binding.edLogincode.text.toString()
        val password = binding.edPassword.text.toString()
        val isAdmin = binding.switchVendor.isChecked
        if (loginCode.isEmpty()){
            myToast.showToast("Please Enter Login Code")
            return
        }
        if (password.isEmpty()){
            myToast.showToast("Please Enter Password")
            return
        }
        sharedPref.saveLoginCode(loginCode)
        sharedPref.savePassword(password)
        myDialog.showProgressDialog("Logging In... Please wait")
        viewmodel.login(loginCode,password,isAdmin)
    }

}
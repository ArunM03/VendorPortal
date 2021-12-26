package com.vendorportal.app.ui.reports

import android.os.Bundle
import android.view.View
import android.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.vendorportal.app.R
import com.vendorportal.app.adapter.ReportAdapter
import com.vendorportal.app.api.VendorPortalViewmodel
import com.vendorportal.app.databinding.FragmentLoginAdminBinding
import com.vendorportal.app.databinding.FragmentReportsBinding
import com.vendorportal.app.others.MyToast
import com.vendorportal.app.sharedpref.SharedPref

class ReportsFragment : Fragment(R.layout.fragment_reports) {


    lateinit var binding : FragmentReportsBinding
    lateinit var viewmodel : VendorPortalViewmodel
    lateinit var sharedPref: SharedPref
    lateinit var reportAdapter: ReportAdapter
    lateinit var myToast : MyToast
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentReportsBinding.bind(view)

        setUI()

    }

    private fun setUI() {

        viewmodel = ViewModelProvider(this).get(VendorPortalViewmodel::class.java)

        setCallbacks()

        myToast = MyToast(requireContext())

        sharedPref = SharedPref(requireContext())



        val loginCode = sharedPref.getLoginCode()
        val password = sharedPref.getPassword()

      //  myToast.showToast("$loginCode and $password")

        viewmodel.getReports(loginCode.toString(),password.toString(),true)



    }


    private fun setCallbacks() {

        viewmodel.reportsLive.observe(viewLifecycleOwner, Observer {
            myToast.showToast("${it.data.size}")
            binding.progressbarReports.visibility = View.GONE
            reportAdapter = ReportAdapter(it.data.toMutableList(),requireContext())
            binding.rvReports.adapter = reportAdapter
            binding.rvReports.layoutManager = LinearLayoutManager(requireContext())
        })

        viewmodel.errorReportsLive.observe(viewLifecycleOwner, Observer {
            myToast.showToast(it)
            binding.progressbarReports.visibility = View.GONE
        })

    }


}




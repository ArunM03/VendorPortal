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
import com.vendorportal.app.adapter.ReportListAdapter
import com.vendorportal.app.api.VendorPortalViewmodel
import com.vendorportal.app.databinding.FragmentLoginAdminBinding
import com.vendorportal.app.databinding.FragmentReportsBinding
import com.vendorportal.app.others.Constants
import com.vendorportal.app.others.MyToast
import com.vendorportal.app.sharedpref.SharedPref

class ReportsListFragment : Fragment(R.layout.fragment_reports) {


    lateinit var binding : FragmentReportsBinding
    lateinit var viewmodel : VendorPortalViewmodel
    lateinit var sharedPref: SharedPref
    lateinit var reportAdapter: ReportListAdapter
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

        val token = sharedPref.getToken().toString()

        viewmodel.getReportsByPost(getHeaderMap(token), Constants.reportId)

        

//        myToast.showToast("${Constants.reportId}")

    }


    private fun getHeaderMap(token : String): Map<String, String> {
        val headerMap = mutableMapOf<String, String>()
        headerMap["Authorization"] = "Bearer $token"
        return headerMap
    }

    private fun setCallbacks() {

        viewmodel.reportsByPostLive.observe(viewLifecycleOwner, Observer {
         /*   myToast.showToast("${it.data.size}")*/
            binding.progressbarReports.visibility = View.GONE
            reportAdapter = ReportListAdapter(it.data.toMutableList(),requireContext())
            binding.rvReports.adapter = reportAdapter
            binding.rvReports.layoutManager = LinearLayoutManager(requireContext())
        })

        viewmodel.errorreportsByPostLive.observe(viewLifecycleOwner, Observer {
           // myToast.showToast(it)
            binding.progressbarReports.visibility = View.GONE
        })

    }


}




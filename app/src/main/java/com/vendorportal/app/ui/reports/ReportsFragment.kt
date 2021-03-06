package com.vendorportal.app.ui.reports

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.vendorportal.app.R
import com.vendorportal.app.adapter.ReportAdapter
import com.vendorportal.app.api.VendorPortalViewmodel
import com.vendorportal.app.databinding.FragmentReportsBinding
import com.vendorportal.app.others.Constants
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

        setUI(view)

    }

    private fun setUI(view: View) {

        viewmodel = ViewModelProvider(this).get(VendorPortalViewmodel::class.java)

        setCallbacks(view)

        myToast = MyToast(requireContext())

        sharedPref = SharedPref(requireContext())

        val token = sharedPref.getToken().toString()

        viewmodel.getReportsByGet(getHeaderMap(token))


    }

    private fun getHeaderMap(token : String): Map<String, String> {
        val headerMap = mutableMapOf<String, String>()
        headerMap["Authorization"] = "Bearer $token"
        return headerMap
    }

    private fun setCallbacks(view: View) {

        viewmodel.reportsLive.observe(viewLifecycleOwner, Observer {

            binding.progressbarReports.visibility = View.GONE
            reportAdapter = ReportAdapter(it.data.toMutableList(),requireContext())
            binding.rvReports.adapter = reportAdapter
            binding.rvReports.layoutManager = LinearLayoutManager(requireContext())
            reportAdapter.setOnItemClickListener {
                Constants.reportId = it.value.toInt()
                Constants.reportName = it.text
                Navigation.findNavController(view).navigate(R.id.action_reportsFragment_to_reportsListFragment)
            }
        })

        viewmodel.errorReportsLive.observe(viewLifecycleOwner, Observer {
            myToast.showToast(it)
            binding.progressbarReports.visibility = View.GONE
        })

    }


}




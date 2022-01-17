package com.vendorportal.app.ui.dashboard

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.vendorportal.app.R
import com.vendorportal.app.databinding.FragmentDashboardBinding
import com.vendorportal.app.databinding.FragmentReportsBinding
import com.vendorportal.app.sharedpref.SharedPref
import kotlinx.coroutines.flow.combine

class DashboardFragment : Fragment(R.layout.fragment_dashboard) {

    lateinit var binding : FragmentDashboardBinding

    lateinit var sharedPref : SharedPref
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        binding = FragmentDashboardBinding.bind(view)

        setUI(view)


    }

    @SuppressLint("SetJavaScriptEnabled")
    private fun setUI(view: View) {

        sharedPref = SharedPref(requireContext())

        val webUrl = sharedPref.getWebUrl()

        binding.webViewDashboard.settings.javaScriptEnabled = true
        binding.webViewDashboard.settings.builtInZoomControls = true
        binding.webViewDashboard.loadUrl(webUrl.toString())


    }


}
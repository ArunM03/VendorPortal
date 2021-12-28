package com.vendorportal.app.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.vendorportal.app.R
import com.vendorportal.app.data.GetReportData
import com.vendorportal.app.databinding.RvItemBinding
import com.vendorportal.app.databinding.RvReportBinding
import java.util.*


class ReportAdapter(var itemscopy : MutableList<GetReportData>, val context : Context)  : RecyclerView.Adapter<ReportAdapter.ReportViewHolder>() {

    class ReportViewHolder(val binding : RvReportBinding) : RecyclerView.ViewHolder(binding.root)

    private var onItemClickListener: ((GetReportData) -> Unit)? = null

    fun setOnItemClickListener(position: (GetReportData) -> Unit) {
        onItemClickListener = position
    }


    var items = mutableListOf<GetReportData>()

    init {
        items = itemscopy
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReportViewHolder {
        val binding = RvReportBinding.inflate(LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ReportViewHolder(binding)
    }


/*    fun filter(text: String) {
        var text = text
        items.clear()
        if (text.isEmpty()) {
            items.addAll(itemscopy)
        } else {
            text = text.lowercase(Locale.getDefault())
            for (item in itemscopy) {
                if (item.productDescription.lowercase(Locale.getDefault()).contains(text)
                ) {
                    items.add(item)
                }
            }
        }
      //  Toast.makeText(context,"items ${items.size} and itemscopy ${itemscopy.size}", Toast.LENGTH_SHORT).show()
        notifyDataSetChanged()
    }*/


    override fun onBindViewHolder(holder: ReportViewHolder, position: Int) {
        val report = items[position]
        holder.itemView.apply {
            with(holder){

                binding.tvReportname.text = report.text

            }


            setOnClickListener {
                onItemClickListener?.let {
                        click ->
                    click(report)
                }
            }
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }
}
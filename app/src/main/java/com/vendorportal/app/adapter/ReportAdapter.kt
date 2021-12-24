package com.vendorportal.app.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.vendorportal.app.R
import com.vendorportal.app.data.ReportData
import java.util.*


class ReportAdapter(var itemscopy : MutableList<ReportData>, val context : Context)  : RecyclerView.Adapter<ReportAdapter.ReportViewHolder>() {

    class ReportViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    private var onItemClickListener: ((ReportData) -> Unit)? = null

    fun setOnItemClickListener(position: (ReportData) -> Unit) {
        onItemClickListener = position
    }


    var items = mutableListOf<ReportData>()

    init {
        items = itemscopy
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReportViewHolder {
        return ReportViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.rv_item,
                parent,
                false
            )
        )
    }


    fun filter(text: String) {
        var text = text
        items.clear()
        if (text.isEmpty()) {
            items.addAll(itemscopy)
        } else {
            text = text.lowercase(Locale.getDefault())
            for (item in itemscopy) {
                if (item.text.lowercase(Locale.getDefault()).contains(text)
                ) {
                    items.add(item)
                }
            }
        }
      //  Toast.makeText(context,"items ${items.size} and itemscopy ${itemscopy.size}", Toast.LENGTH_SHORT).show()
        notifyDataSetChanged()
    }


    override fun onBindViewHolder(holder: ReportViewHolder, position: Int) {
        val report = items[position]
        holder.itemView.apply {



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
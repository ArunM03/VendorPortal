package com.vendorportal.app.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.vendorportal.app.data.ReportData
import com.vendorportal.app.databinding.RvItemBinding
import java.util.*


class ReportListAdapter(var itemscopy : MutableList<ReportData>, val context : Context)  : RecyclerView.Adapter<ReportListAdapter.ReportViewHolder>() {

    class ReportViewHolder(val binding : RvItemBinding) : RecyclerView.ViewHolder(binding.root)

    private var onItemClickListener: ((ReportData) -> Unit)? = null

    fun setOnItemClickListener(position: (ReportData) -> Unit) {
        onItemClickListener = position
    }


    var items = mutableListOf<ReportData>()

    init {
        items = itemscopy
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReportViewHolder {
        val binding = RvItemBinding.inflate(LayoutInflater.from(parent.context),
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
                if (!report.booth.isNullOrBlank()){
                    binding.booth.visibility = View.VISIBLE
                    binding.tvBoothData.text = report.booth
                }
                if (!report.productDescription.isNullOrBlank()){
                    binding.productdescription.visibility = View.VISIBLE
                    binding.tvProductDescriptionData.text = report.productDescription
                }
                if (!report.description .isNullOrBlank()){
                    binding.description.visibility = View.VISIBLE
                    binding.tvDescriptionData.text = report.description
                }
                if (!report.divisionName .isNullOrBlank()){
                    binding.divisioname.visibility = View.VISIBLE
                    binding.tvDivisionNameData.text = report.divisionName
                }
                if (!report.divisionNumber .isNullOrBlank()){
                    binding.divisionNumber.visibility = View.VISIBLE
                    binding.tvDivisionNameData.text = report.divisionNumber
                }
                if (!report.itemCode .isNullOrBlank()){
                    binding.itemcode.visibility = View.VISIBLE
                    binding.tvItemCodeData.text = report.itemCode
                }
                if (!report.mfgName .isNullOrBlank()){
                    binding.mfgname.visibility = View.VISIBLE
                    binding.tvMfgNameData.text = report.mfgName
                }
                if (!report.prodCode .isNullOrBlank()){
                    binding.prodcode.visibility = View.VISIBLE
                    binding.tvProdCodeData.text = report.prodCode
                }
                if (report.storeID.toInt() != 0){
                    binding.storeId.visibility = View.VISIBLE
                    binding.tvStoreIdData.text = report.storeID.toString()
                }
                if (!report.storeName .isNullOrBlank()){
                    binding.storename.visibility = View.VISIBLE
                    binding.tvStorenameIdData.text = report.storeName
                }
                if (!report.storeNumber .isNullOrBlank()){
                    binding.storeNumber.visibility = View.VISIBLE
                    binding.tvStoreNumberData.text = report.storeNumber
                }
                if (!report.totalQty.toString() .isNullOrBlank()){
                    binding.totalqty.visibility = View.VISIBLE
                    binding.tvTotalqtyIdData.text = report.totalQty.toString()
                }
                if (!report.totalSales .isNullOrBlank()){
                    binding.totalsales.visibility = View.VISIBLE
                    binding.tvSalesIdData.text = report.totalSales
                }
                if (report.division.toInt() != 0){
                    binding.division.visibility = View.VISIBLE
                    binding.tvDivisionData.text = report.division.toString()
                }


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
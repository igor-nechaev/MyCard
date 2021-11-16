package com.nechaev.mycard.model.card.adapters

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.nechaev.mycard.R
import com.nechaev.mycard.model.card.entities.Transaction
import com.nechaev.mycard.model.utils.ConvertUtils

class TransactionsAdapter(private val context: Context?, var transactions: List<Transaction>, var coeffConvert: Double, var charCodeConvert: String)
    : RecyclerView.Adapter<TransactionsAdapter.HistoryViewHolder>() {

    private val inflater = LayoutInflater.from(context)!!

    fun updateTransactions(transactionsList: List<Transaction>, charCodeConvert: String, coeffConvert: Double){
        transactions = transactionsList
        this.coeffConvert = coeffConvert
        this.charCodeConvert = charCodeConvert
        notifyDataSetChanged() //TODO DiffUtils
    }

    class HistoryViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        var tv_company : TextView = itemView.findViewById(R.id.tv_item_history_company_name)
        var tv_date : TextView
        var tv_cost_value_converted : TextView
        var tv_valute_converted : TextView
        var iv_icon : ImageView
        var tv_cost : TextView

        init {
            tv_company = itemView.findViewById(R.id.tv_item_history_company_name)
            tv_date = itemView.findViewById(R.id.tv_item_history_date)
            tv_cost_value_converted = itemView.findViewById(R.id.tv_item_history_cost_value_converted)
            tv_valute_converted = itemView.findViewById(R.id.tv_item_history_converted_valute)
            iv_icon = itemView.findViewById(R.id.item_history_iv_icon)
            tv_cost = itemView.findViewById(R.id.tv_item_history_cost)
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HistoryViewHolder {
        val itemView = inflater.inflate(R.layout.item_history, parent, false)
        return HistoryViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: HistoryViewHolder, position: Int) {
        holder.apply {
            tv_company.text = transactions[position].title

            //TODO минусы поставить перед значком валюты
            tv_cost.text =  "\$ ${transactions[position].amount.substring(1)}"

            if (!(coeffConvert - 0 < 0.001) && tv_cost.text.isNotEmpty()){
                tv_cost_value_converted.text = ConvertUtils.convertBalanceString(coeffConvert , tv_cost.text.toString())
                tv_valute_converted.text = ConvertUtils.valutePrefixForTransactions(charCodeConvert)
            }

            Log.d("rvCoff", coeffConvert.toString())
            Log.d("rvCoff", charCodeConvert.toString())


            tv_date.text= transactions[position].date

            if (context != null) {
                Glide.with(context)
                    .load(transactions[position].iconUrl)
                    .fitCenter()
                    .placeholder(R.drawable.ic_netflix)
                    .into(iv_icon)
            }
        }
    }



    override fun getItemCount() = transactions.size

}
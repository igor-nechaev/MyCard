package com.nechaev.mycard.data.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.nechaev.mycard.R
import com.nechaev.mycard.data.content.cardHolder.Transaction

class TransactionsAdapter(private val context : Context, var transactions : List<Transaction>)
    : RecyclerView.Adapter<TransactionsAdapter.HistoryViewHolder>() {

    private val inflater = LayoutInflater.from(context)!!

    class HistoryViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        var tv_company : TextView = itemView.findViewById(R.id.tv_item_history_company_name)
        var tv_date : TextView
        var tv_cost_value_converted : TextView
        var tv_valute_converted : TextView
        var icon : View
        var tv_cost : TextView

        init {
            tv_company = itemView.findViewById(R.id.tv_item_history_company_name)
            tv_date = itemView.findViewById(R.id.tv_item_history_date)
            tv_cost_value_converted = itemView.findViewById(R.id.tv_item_history_cost_value_converted)
            tv_valute_converted = itemView.findViewById(R.id.tv_item_history_converted_valute)
            icon = itemView.findViewById(R.id.item_history_icon)
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
            tv_cost.text =  "\$ ${transactions[position].amount}"
            tv_date.text= transactions[position].date
            Glide.with(context)
                .load(transactions[position].iconUrl ?: "F")
                .into(holder.icon as ImageView)
            //TODO converted and exeptions in load
        }
    }

    override fun getItemCount() = transactions.size

}
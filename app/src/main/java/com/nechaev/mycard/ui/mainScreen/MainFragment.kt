package com.nechaev.mycard.ui.mainScreen

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.nechaev.mycard.R
import com.nechaev.mycard.data.content.cardHolder.CardHolder
import com.nechaev.mycard.data.network.Status
import com.nechaev.mycard.data.content.cardHolder.ResourceCardHolders


class MainFragment : Fragment(), Observer<ResourceCardHolders> {
    lateinit var tv_holder : TextView
    lateinit var tv_num: TextView
    lateinit var tv_card_valid: TextView
    lateinit var tv_balance_orig_valute: TextView
    lateinit var tv_balance_converted_valute: TextView
    lateinit var iv_card_icon: ImageView
    lateinit var viewModel: MainViewModel
    lateinit var progressBar: ProgressBar

    companion object {

        fun newInstance(bundle: Bundle?): MainFragment{
            val fragment = MainFragment()
            fragment.arguments = bundle
            return fragment
        }

        fun newInstance(): MainFragment {
            return MainFragment()
        }

    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.main_fragment, container, false)

        tv_holder = view.findViewById(R.id.tv_item_card_person_name)
        tv_num = view.findViewById(R.id.tv_item_card_numer_card)
        tv_card_valid = view.findViewById(R.id.tv_item_card_valid_date)
        tv_balance_orig_valute = view.findViewById(R.id.tv_item_card_balance_short)
        tv_balance_converted_valute = view.findViewById(R.id.tv_item_card_balance_huge)
        iv_card_icon = view.findViewById(R.id.iv_card_icon)

        progressBar = view.findViewById(R.id.progressBar)


        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)

        return view
    }

    override fun onStart() {
        super.onStart()
        viewModel.cardHoldersResource.observe(this,this)
    }


    override fun onChanged(resourceCardHolders: ResourceCardHolders) {

        when (resourceCardHolders.status) {
            Status.LOADING -> showProgress()
            Status.SUCCES -> updateCardView(resourceCardHolders.data!!.cardHolderList[0])
            Status.ERROR -> showErrorCard()
        }
    }

    private fun showErrorCard() {
        //TODO Error!
    }

    private fun showProgress() {
        progressBar.visibility = View.VISIBLE
    }

    @SuppressLint("SetTextI18n")
    private fun updateCardView(cardHolderData: CardHolder) {
        tv_num.text = cardHolderData.cardNumber
        tv_holder.text = cardHolderData.cardHolder
        tv_card_valid.text = cardHolderData.valid
        tv_balance_orig_valute.text = "${resources.getString(R.string.dollar)} ${cardHolderData.balance}"
        tv_balance_converted_valute.text = "${resources.getString(R.string.dollar)} ${cardHolderData.balance}"  //TODO Convert this!

        when{
            cardHolderData.type.equals("mastercard") -> iv_card_icon.setBackgroundResource(R.drawable.ic_mastercard_icon)
        }

        hideProgress()
    }

    private fun hideProgress() {
        progressBar.visibility = View.INVISIBLE
    }

}


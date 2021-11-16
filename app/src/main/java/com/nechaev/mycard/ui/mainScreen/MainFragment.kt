package com.nechaev.mycard.ui.mainScreen

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.card.MaterialCardView
import com.nechaev.mycard.R
import com.nechaev.mycard.model.Constants
import com.nechaev.mycard.model.card.adapters.TransactionsAdapter
import com.nechaev.mycard.model.card.entities.CardHolder
import com.nechaev.mycard.model.card.entities.CardHolderList
import com.nechaev.mycard.model.card.entities.Transaction
import com.nechaev.mycard.model.card.network.CardHoldersResource
import com.nechaev.mycard.model.Status
import com.nechaev.mycard.model.utils.ConvertUtils
import com.nechaev.mycard.model.valute.network.ResourceValuteRoot
import android.net.ConnectivityManager
import androidx.appcompat.app.AlertDialog
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import kotlin.collections.ArrayList


class MainFragment : Fragment(), Observer<Any>{

    val STATE_CHARCODE = "charCode"
    lateinit var tv_holder : TextView
    lateinit var tv_num: TextView
    lateinit var tv_card_valid: TextView
    lateinit var tv_balance_orig_valute: TextView
    lateinit var tv_balance_converted_valute: TextView
    lateinit var iv_card_icon: ImageView
    lateinit var viewModel: MainViewModel
    lateinit var progressBar: ProgressBar
    lateinit var rv_history: RecyclerView
    lateinit var adapter : TransactionsAdapter

    lateinit var card_left : MaterialCardView
    lateinit var card_mid : MaterialCardView
    lateinit var card_right : MaterialCardView

    var convertCharCode: String = ""


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
        if (savedInstanceState != null){
            convertCharCode = savedInstanceState.getString(STATE_CHARCODE, Constants.convertStringRUB)
        }
        val view = inflater.inflate(R.layout.main_fragment, container, false)


        tv_holder = view.findViewById(R.id.tv_item_card_person_name)
        tv_num = view.findViewById(R.id.tv_item_card_numer_card)
        tv_card_valid = view.findViewById(R.id.tv_item_card_valid_date)
        tv_balance_orig_valute = view.findViewById(R.id.tv_item_card_balance_short)
        tv_balance_converted_valute = view.findViewById(R.id.tv_item_card_balance_huge)
        iv_card_icon = view.findViewById(R.id.iv_card_icon)

        card_left = view.findViewById(R.id.main_currency_left)
        card_mid = view.findViewById(R.id.main_currency_mid)
        card_right = view.findViewById(R.id.main_currency_right)

        progressBar = view.findViewById(R.id.progressBar)

        rv_history = view.findViewById(R.id.main_rv_history)
        rv_history.isNestedScrollingEnabled = true



        rv_history.layoutManager = LinearLayoutManager(context)

        adapter = TransactionsAdapter(context, emptyList(), 0.0, convertCharCode)
        rv_history.adapter = adapter

        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)

        card_left.setOnClickListener { onChangeCurrency(Constants.convertCardGBP) }
        card_mid.setOnClickListener { onChangeCurrency(Constants.convertCardEUR) }
        card_right.setOnClickListener { onChangeCurrency(Constants.convertCardRUB) }

        return view
    }

   private fun onChangeCurrency(indexCardClicked: Int) {

        when(indexCardClicked){

            Constants.convertCardGBP -> {
                viewModel.valuteRootResource.value?.status?.let { updateConvertData(Constants.convertStringGBP, it) }
                convertCharCode = Constants.convertStringGBP
            }


            Constants.convertCardEUR ->{
                viewModel.valuteRootResource.value?.status?.let { updateConvertData(Constants.convertStringEUR, it) }
                convertCharCode = Constants.convertStringEUR
            }



            Constants.convertCardRUB -> {
                viewModel.valuteRootResource.value?.status?.let { updateConvertData(Constants.convertStringRUB, it) }
                convertCharCode = Constants.convertStringRUB
            }

        }

    }

    override fun onStart() {
        super.onStart()
        viewModel.cardHoldersResource.observe(this,this)
        viewModel.valuteRootResource.observe(this,this)

        clickState(convertCharCode)

        showErrorCardIfNoConnect()

    }


    private fun onChangedCardHolders(cardHoldersResource: CardHoldersResource) {

        when (cardHoldersResource.status) {
            Status.LOADING -> showProgress()
            Status.SUCCES -> updateUserData(cardHoldersResource.data!!)
            Status.ERROR -> showErrorCardIfNoConnect()
        }

    }

    private fun updateUserData(data: CardHolderList) {

        updateCardView(data.cardHolderList[Constants.idCardHolder]) //TODO Choose cardHolder

        if(isValuteResourceReady()) updateTransactionHistory(data.cardHolderList[Constants.idCardHolder].history, convertCharCode, getConvertCoeff())

    }

    private fun updateTransactionHistory(history: List<Transaction>, charCodeConvert: String, coeff: Double) {

        val doubleList : MutableList<Transaction> = ArrayList()

        for (t : Transaction in history){
            doubleList.add(t)
        }
        for (t : Transaction in history){
            doubleList.add(t)
        }
        for (t : Transaction in history){
            doubleList.add(t)
        }

        adapter.updateTransactions(doubleList, charCodeConvert, coeffConvert = coeff )
    }

    private fun showErrorCardIfNoConnect() {
        if (!isNetworkConnected()) {
            val dialog: AlertDialog
            val dialogBuilder = context?.let { MaterialAlertDialogBuilder(it) }
            val dialogView: View = layoutInflater.inflate(R.layout.dialog_no_connection, null)
            dialogBuilder?.setView(dialogView)
            val btnReconnect: Button = dialogView.findViewById(R.id.btn_reconnect)
            dialog = dialogBuilder!!.create()
            btnReconnect.setOnClickListener { v ->
                viewModel.reconnect()
                dialog.dismiss()
            }
            dialog.show()
        }
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

        when (cardHolderData.type) {
            Constants.cardTypeMastercard -> iv_card_icon.setBackgroundResource(R.drawable.ic_mastercard_icon)
            Constants.cardTypeVisa -> iv_card_icon.setBackgroundResource(R.drawable.ic_visa)
            Constants.cardTypeUnionpay -> iv_card_icon.setBackgroundResource(R.drawable.ic_unionpay)

        }

        viewModel.valuteRootResource.value?.status?.let { updateConvertData(convertCharCode, it) }

        hideProgress()
    }

    private fun hideProgress() {
        progressBar.visibility = View.INVISIBLE
    }

    private fun updateConvertData(convertCharCode: String, status: Status) {

        if(status == Status.SUCCES){

            val cardCashRate = viewModel.valuteRootResource.value?.data?.infoValute?.get(Constants.cardValute)!!.value

            if (convertCharCode == Constants.cardBaseRateValute){
                val coeff = cardCashRate

                updateConvertedCashCardView(coeff, convertCharCode)

                if(isCardResourceReady()){
                    viewModel.cardHoldersResource.value?.data?.cardHolderList?.get(2)?.history?.let {
                        updateTransactionHistory(
                            it, convertCharCode, coeff)
                    }
                }

            }else{

                val convertCashRate = viewModel.valuteRootResource.value?.data?.infoValute?.get(convertCharCode)!!.value
                val coeff = ConvertUtils.convertCoeff(cardCashRate = cardCashRate,  convertCashRate = convertCashRate)

                updateConvertedCashCardView(coeff, convertCharCode)

                if(isCardResourceReady()){
                    viewModel.cardHoldersResource.value?.data?.cardHolderList?.get(2)?.history?.let {
                        updateTransactionHistory(
                            it, convertCharCode, coeff)
                    }
                }

            }

        }

    }

    private fun updateConvertedCashCardView(coeff: Double, convertCharCode: String) {
        if(tv_balance_orig_valute.text.isNotEmpty()){
            tv_balance_converted_valute.text = ConvertUtils.convertBalanceString(coeff, convertCharCode, tv_balance_orig_valute.text.toString())
        }
    }


    override fun onChanged(resource: Any?) {
        when(resource){
            is CardHoldersResource -> onChangedCardHolders(resource)
            is ResourceValuteRoot -> updateConvertData(convertCharCode, resource.status)
        }
    }



    private fun isValuteResourceReady(): Boolean{
        return viewModel.valuteRootResource.value?.status ?: Status.LOADING == Status.SUCCES
    }

    private fun isCardResourceReady(): Boolean{
        return viewModel.cardHoldersResource.value?.status ?: Status.LOADING == Status.SUCCES
    }

    private fun getCardCashRate(): Double{
        return viewModel.valuteRootResource.value?.data?.infoValute?.get(Constants.cardValute)!!.value
    }

    private fun getConvertCashRate(): Double{
        if(convertCharCode != Constants.convertStringRUB){
            return viewModel.valuteRootResource.value?.data?.infoValute?.get(convertCharCode)?.value!!
        }else{
            return 1.0;
        }

    }

    private fun getConvertCoeff(): Double{
        return ConvertUtils.convertCoeff(cardCashRate = getCardCashRate(), convertCashRate = getConvertCashRate())
    }


    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putString(STATE_CHARCODE, convertCharCode)
    }

    private fun clickState(convertCharCode: String){
        when(convertCharCode){
            Constants.convertStringEUR -> onChangeCurrency(Constants.convertCardEUR)
            Constants.convertStringGBP -> onChangeCurrency(Constants.convertCardGBP)
            else -> onChangeCurrency(Constants.convertCardRUB)
        }
    }


    private fun isNetworkConnected(): Boolean {
        val cm = context
            ?.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        return cm.activeNetworkInfo != null && cm.activeNetworkInfo!!.isConnected
    }





}


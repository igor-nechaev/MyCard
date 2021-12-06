package com.nechaev.mycard.model.utils

import com.nechaev.mycard.model.Constants

object ConvertUtils {

    private fun Double.format(digits: Int) = "%.${digits}f".format(this)




    /**
     * Метод возвращает чилсло, на которое умножается баланс, чтобы получить баланс в "другой" валюте
     * @param cardCashRate Курс "EUR" по отношению к "RUB"
     * @param convertCashRate Курс "другой" валюты по отношению к "RUB"
     */

    fun convertCoeff(cardCashRate: Double, convertCashRate: Double): Double{

        val coeff = cardCashRate / convertCashRate



        return coeff

    }

    fun convertBalanceString(coeff: Double, charCodeValute: String, balanceString: String): String{

        val convertedBalanceString = valutePrefix(charCodeValute) + " " + (balanceString.substring(2).toDouble() * coeff).format(2)

        return convertedBalanceString
    }

    fun convertBalanceString(coeff: Double, balanceString: String): String{

        val convertedBalanceString = (balanceString.substring(2).toDouble() * coeff).format(2)

        if(convertedBalanceString[0] == '-') return convertedBalanceString.substring(1)

        return convertedBalanceString
    }


    fun valutePrefix(charCodeValute: String): String{
        return when(charCodeValute){
            Constants.convertStringRUB -> "₽"
            Constants.convertStringEUR -> "€"
            else -> "£"
        }
    }

    fun valutePrefixForTransactions(charCodeValute: String): String{
        return when(charCodeValute){
            Constants.convertStringRUB -> "- ₽"
            Constants.convertStringEUR -> "- €"
            else -> "- £"
        }
    }



}
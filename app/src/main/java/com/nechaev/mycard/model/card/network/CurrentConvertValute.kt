package com.nechaev.mycard.model.card.network

import com.nechaev.mycard.model.Status
import com.nechaev.mycard.model.valute.entities.ValuteInfo

data class CurrentConvertValute(

    val status: Status,

    val data: ValuteInfo?,

    val message: String?

) {}
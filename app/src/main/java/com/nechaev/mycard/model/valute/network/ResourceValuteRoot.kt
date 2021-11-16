package com.nechaev.mycard.model.valute.network

import com.nechaev.mycard.model.Status
import com.nechaev.mycard.model.valute.entities.ValuteRoot

data class ResourceValuteRoot(

    val status: Status,

    val data: ValuteRoot?,

    val message: String?

) {}
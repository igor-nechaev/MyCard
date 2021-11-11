package com.nechaev.mycard.data.content.cardHolder

import com.nechaev.mycard.data.network.Status

data class ResourceCardHolders(val status: Status, val data: CardHolderList?, val message: String?) {}
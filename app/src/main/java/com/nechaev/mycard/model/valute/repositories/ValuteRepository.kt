package com.nechaev.mycard.model.valute.repositories


import androidx.lifecycle.MutableLiveData
import com.nechaev.mycard.model.Status
import com.nechaev.mycard.model.valute.entities.ValuteRoot
import com.nechaev.mycard.model.valute.network.ResourceValuteRoot
import com.nechaev.mycard.model.valute.network.RetrofitValue
import com.nechaev.mycard.model.valute.network.ValueService
import retrofit2.Call
import retrofit2.Response

object ValuteRepository {

    private var valuteService =
        RetrofitValue.getRetrofitValue().create(ValueService::class.java)

    fun loadValueInfo(valueRootResource: MutableLiveData<ResourceValuteRoot>){
        valueRootResource.postValue(ResourceValuteRoot(Status.LOADING, null, null))

        val call = valuteService.getValueRoot()

        call.enqueue(object : retrofit2.Callback<ValuteRoot> {

            override fun onResponse(
                call: Call<ValuteRoot>, response: Response<ValuteRoot>) {
                    valueRootResource.postValue(ResourceValuteRoot(Status.SUCCES, response.body()!!, null))
            }

            override fun onFailure(call: Call<ValuteRoot>, t: Throwable) {
                    valueRootResource.postValue(ResourceValuteRoot(Status.ERROR, null , null))
            }

        })

    }
}


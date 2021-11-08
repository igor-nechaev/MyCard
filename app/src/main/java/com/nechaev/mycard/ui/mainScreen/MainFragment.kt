package com.nechaev.mycard.ui.mainScreen

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.nechaev.mycard.R
import com.nechaev.mycard.data.model.user.UserList
import com.nechaev.mycard.data.network.user.RetrofitUser
import com.nechaev.mycard.data.network.user.UsersService
import retrofit2.Response
import retrofit2.Call


class MainFragment : Fragment(){
    lateinit var tv_holder : TextView
    lateinit var tv_num: TextView
    lateinit var api : UsersService

    companion object {

        fun newInstance(bundle: Bundle?): MainFragment{
            val fragment = MainFragment()
            fragment.arguments = bundle
            return fragment
        }

        fun newInstance(): MainFragment {
            return MainFragment()
        }

        const val LOG_TAG = "MainFragmentDebug"
    }

    private lateinit var viewModel: MainViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.main_fragment, container, false)

        tv_holder = view.findViewById(R.id.tv_item_card_person_name)
        tv_num = view.findViewById(R.id.tv_item_card_numer_card)

        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
       // viewModel = ViewModelProvider(this).get(MainViewModel::class.java)

    }
    override fun onStart() {
        super.onStart()
        api = RetrofitUser.getRetrofitUsers().create(UsersService::class.java)



        api.getUserList().enqueue(object : retrofit2.Callback<UserList>{

            override fun onResponse(call: Call<UserList>, response: Response<UserList>) {
                tv_holder.text = response.body()!!.userList[0].cardHolder
                tv_num.text = response.body()!!.userList[0].cardNumber
            }

            override fun onFailure(call: Call<UserList>, t: Throwable) {
                Log.d(LOG_TAG, t.message.toString())
                tv_holder.text = "Error"
                tv_num.text = "Error"
            }
        })

    }

}


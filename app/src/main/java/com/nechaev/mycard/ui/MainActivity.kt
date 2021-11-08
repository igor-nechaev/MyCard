package com.nechaev.mycard.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.nechaev.mycard.R
import com.nechaev.mycard.ui.mainScreen.MainFragment

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity)

        if(savedInstanceState == null){
            supportFragmentManager.beginTransaction()
                .add(R.id.container_for_fragments, MainFragment.newInstance())
                .addToBackStack(null)
                .commit()
        }

    }
}
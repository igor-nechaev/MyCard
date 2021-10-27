package com.nechaev.mycard

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.nechaev.mycard.mainScreen.MainFragment

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity)

        supportFragmentManager.beginTransaction()
            .add(R.id.container_for_fragments, MainFragment())
            .addToBackStack(null)
            .commit()
    }
}
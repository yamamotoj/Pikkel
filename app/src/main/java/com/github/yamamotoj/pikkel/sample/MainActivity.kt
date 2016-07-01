package com.github.yamamotoj.pikkel.sample

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.github.yamamotoj.pikkel.Pikkel
import com.github.yamamotoj.pikkel.PikkelDelegate

class MainActivity : AppCompatActivity(), Pikkel by PikkelDelegate() {

    var data by state<String?>(null)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        restoreInstanceState(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onSaveInstanceState(outState: Bundle?) {
        super.onSaveInstanceState(outState)
        saveInstanceState(outState)
    }
}

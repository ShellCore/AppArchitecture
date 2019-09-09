package com.shell.android.daggerlogin.login

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.shell.android.daggerlogin.R
import com.shell.android.daggerlogin.root.App
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        (application as App).component.inject(this)

        btnLogin.setOnClickListener {
            Toast.makeText(this, "Clicked", Toast.LENGTH_SHORT)
                .show()
        }
    }
}

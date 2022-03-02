package edu.umsl.corrina_lakin.project1

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnAndroidApps.setOnClickListener {

            val intent = Intent(this, Course1Activity::class.java)
            startActivity(intent)
        }

        btnSysProgTools.setOnClickListener {

            val intent = Intent(this, Course1Activity::class.java)
            startActivity(intent)
        }

    }
}

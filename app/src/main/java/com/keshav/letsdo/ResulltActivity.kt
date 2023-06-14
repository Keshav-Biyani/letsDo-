package com.keshav.letsdo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class ResulltActivity : AppCompatActivity() {
    private var mUserName : String? = null
    private var mTotalQuestion : Int? = null
    private var mTotalCorect : Int? = null
    private var txtUserName : TextView? = null
    private var txtScore : TextView? =null
    private var btnFinish :Button?= null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_resullt)
        if (supportActionBar != null) {
            supportActionBar!!.hide()
        }
        txtUserName = findViewById(R.id.txtUsername)
        txtScore = findViewById(R.id.txtScore)
        mUserName = intent.getStringExtra(Constants.USER_NAME)
        mTotalQuestion = intent.getIntExtra(Constants.TOTAL_QUESTION,0)
        mTotalCorect = intent.getIntExtra(Constants.CORRECT_ANSWER,0)
        txtUserName?.text = mUserName
        txtScore?.text = "You Score ${mTotalCorect} out of ${mTotalQuestion}"
        btnFinish = findViewById(R.id.btnFinish)
        btnFinish?.setOnClickListener{
          startActivity(Intent(this,MainActivity::class.java))
        }
    }
}
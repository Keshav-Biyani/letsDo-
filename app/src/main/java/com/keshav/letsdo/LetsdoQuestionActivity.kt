package com.keshav.letsdo

import android.content.Intent
import android.graphics.Color
import android.graphics.Typeface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.core.content.ContextCompat

class LetsdoQuestionActivity : AppCompatActivity(),View.OnClickListener {
    private var  mCurrentPostion :Int =1
    private var mQuestionlist : ArrayList<Questions>?=null
    private var mselectedPosition : Int =0
    private var mUserName :String? =null
    private var mCoreectAnswer : Int =0
    private var progressBar: ProgressBar? = null
    private var txtprogress : TextView? = null
    private var txtquestion : TextView? =null
    private var img_flag : ImageView? = null
    private var txtOption1 : TextView?= null
    private var txtOption2 : TextView?= null
    private var txtOption3 : TextView?= null
    private var txtOption4 : TextView?= null
    private var btnSubmit : Button?= null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_letsdo_question)
        if (supportActionBar != null) {
            supportActionBar!!.hide()
        }
        mUserName = intent.getStringExtra(Constants.USER_NAME)
        progressBar = findViewById(R.id.prgressBar)
        txtprogress = findViewById(R.id.txtprogress)
        txtquestion = findViewById(R.id.txtquestion)
        img_flag = findViewById(R.id.img_flag)
        txtOption1 = findViewById(R.id.txtOption1)
        txtOption2 = findViewById(R.id.txtOption2)
        txtOption3 = findViewById(R.id.txtOption3)
        txtOption4 = findViewById(R.id.txtOption4)
        btnSubmit = findViewById(R.id.btnSubmit)
        txtOption1?.setOnClickListener(this)
        txtOption2?.setOnClickListener(this)
        txtOption3?.setOnClickListener(this)
        txtOption4?.setOnClickListener(this)
        btnSubmit?.setOnClickListener(this)
        mQuestionlist = Constants.getQuestions()
        setquestion()



    }

    private fun setquestion() {

        //Log.i("Number of Questiom","${questionlist.size}")

        val question = mQuestionlist!!.get(mCurrentPostion-1)
        progressBar?.progress = mCurrentPostion
        txtprogress?.text = "$mCurrentPostion/${progressBar?.max}"
        txtquestion?.text = question.question
        img_flag?.setImageResource(question.image)
        txtOption1?.text = question.optionOne
        txtOption2?.text = question.optionTwo
        txtOption3?.text = question.optionThree
        txtOption4?.text = question.optionFour
        if(mCurrentPostion == mQuestionlist!!.size){
            btnSubmit?.text = "Finish"
        }
        else{
            btnSubmit?.text = "SUBMIT"
        }
    }
    private fun defaultOption(){
        val options = ArrayList<TextView>()
        txtOption1?.let {
            options.add(0, it)
        }
        txtOption2?.let {
            options.add(1, it)
        }
        txtOption3?.let {
            options.add(2, it)
        }
        txtOption4?.let {
            options.add(3, it)
        }
        for( option in options)
        {
         option.setTextColor(Color.parseColor("#7A8089"))
         option.typeface = Typeface.DEFAULT
            option.background = ContextCompat.getDrawable(
                this,
                R.drawable.default_option_border_bg
            )
        }

    }
    private fun selectedOption(tv :TextView,selectedOptionNum : Int){
        defaultOption()
        mselectedPosition =selectedOptionNum
        tv.setTextColor(Color.parseColor("#363A43"))
        tv.setTypeface(tv.typeface,Typeface.BOLD)
        tv.background = ContextCompat.getDrawable(
            this,
            R.drawable.selected_option_border_bg
        )

    }
    override fun onClick(v: View?) {
        //TODO("Not yet implemented")
        when(v?.id){
            R.id.txtOption1->{
                txtOption1?.let {
                    selectedOption(it,1)
                }
            }
            R.id.txtOption2->{
                txtOption2?.let {
                    selectedOption(it,2)
                }
            }
            R.id.txtOption3->{
                txtOption3?.let {
                    selectedOption(it,3)
                }
            }
            R.id.txtOption4->{
                txtOption4?.let {
                    selectedOption(it,4)
                }
            }
            R.id.btnSubmit->{
                if(mselectedPosition == 0){
                    defaultOption()
                    mCurrentPostion ++
                    when{
                        mCurrentPostion <= mQuestionlist!!.size->{
                            setquestion()
                        }
                        else->{
                            val intent = Intent(this,ResulltActivity::class.java)
                            intent.putExtra(Constants.USER_NAME,mUserName)
                            intent.putExtra(Constants.CORRECT_ANSWER,mCoreectAnswer)
                            intent.putExtra(Constants.TOTAL_QUESTION, mQuestionlist!!.size)
                            startActivity(intent)
                            finish()
                        }
                    }

                }
                else{
                    val question = mQuestionlist!!.get(mCurrentPostion-1)
                    if(question.correctAnswer != mselectedPosition){
                        answerView(mselectedPosition,R.drawable.wrong_option_border_bg)
                    }else{
                        mCoreectAnswer++
                    }
                    answerView(question.correctAnswer,R.drawable.coreect_option_border_bg)
                    if(mCurrentPostion != mQuestionlist!!.size){
                        btnSubmit!!.text = "Next"
                    }
                    mselectedPosition = 0

                }

            }
        }

    }
    private fun answerView(answer : Int , drwableView :Int){
        when(answer){
            1->{
                txtOption1?.background=ContextCompat.getDrawable(
                    this,
                    drwableView
                )
            }
            2->{
                txtOption2?.background=ContextCompat.getDrawable(
                    this,
                    drwableView
                )
            }
            3->{
                txtOption3?.background=ContextCompat.getDrawable(
                    this,
                    drwableView
                )
            }
            4->{
                txtOption4?.background=ContextCompat.getDrawable(
                    this,
                    drwableView
                )
            }
        }
    }
}
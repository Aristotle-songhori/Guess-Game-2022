package com.arastoo.guessgame.score

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ScoreViewModel(finalScore: Int) : ViewModel() {
    // ساخت یک اونت برای بازی مجدد
    private val _eventPlayAgain = MutableLiveData<Boolean>()
    val eventPlayAgain: LiveData<Boolean>
        get() = _eventPlayAgain
    fun onPlayAgain() {
        _eventPlayAgain.value = true
    }
    // اینم تکلیفش روشنه
    fun onPlayAgainComplete() {
        _eventPlayAgain.value = false
    }

////////////////////////////////////////////////////////////////////////////////////////

    //ساخت امتیاز که باید اینجا باشه که بتونیم ببینیم و از بیرون میگیریم
    private val _score = MutableLiveData<Int>()
    val score: LiveData<Int>
        get() = _score



    init {
        //این مادرجنده از قسمت فکتوری میاد و اینجا میرسه و اینم از این ورودی کلاس میگیره
        _score.value = finalScore
    }




}
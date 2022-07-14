package com.arastoo.guessgame.score

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import java.lang.IllegalArgumentException

//این بخش همیشه باید همینطوری باشه
//URL=https://www.section.io/engineering-education/safe-args-in-android/
//در آدرس بالا یاد میگیریم چطور چند پارامت و چطور حتی یک عکس یا آبجکت یا ... رو جابجا کنیم
//خیلی کاربردی برای انتقال عکس و نقشه و ...
// خطوط آخر مطالعه شود

class ScoreViewModelFactory (private val finalScore:Int) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(ScoreViewModel::class.java)) {
                return ScoreViewModel(finalScore) as T
            }
            throw IllegalArgumentException("Unknown ViewModel class")
        }

}
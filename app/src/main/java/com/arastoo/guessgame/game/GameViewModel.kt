package com.arastoo.guessgame.game

import android.os.CountDownTimer
import android.text.format.DateUtils
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel

class GameViewModel : ViewModel() {

    ///////////////////////////////////////////////////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////////////////////////////////////////////
// ============================   بخش مربوط به کلمه ها
    //یه داده تعریف میکنیم که کلمه رو بریزیم توش و اونطرف رصد کنیم
    private val _word = MutableLiveData<String>()
    val word :LiveData<String>
        get() = _word

//    android:text="@{gameViewModel.cuorrentWord}"
//    val cuorrentWord = Transformations.map(word) {
//       it
//    }


    //    یه لیست با قابلیت تغییر هم تعریف میکنیم که لیست کلمات رو بدیم بهش
    private lateinit var wordList :MutableList<String>
    ///////////////////////////////////////////////////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////////////////////////////////////////////
// ============================   بخش مربوط به کلمه ها
//   بسیار دقت کنید که این اینت برمیگردونه و در xml تکس قراره نشون بدیم
//    اومدیم در xml یه استرینگ تعریف کردیم که اینت بگیره با %d برید تو بخش استرینگ ها ببینید
// وگرنه خطا میده میگه پیشنهاد میکنیم از لایف سایکل و ... استفاده کنید
    private val _score = MutableLiveData<Int>()
    val score: LiveData<Int>
        get() = _score
//    val currentScore = Transformations.map(score) {
//       it
//    }
///////////////////////////////////////////////////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////////////////////////////////////////////

    ///////////////////////////////////////////////////////////////////////////////////////////////////////
//    ============تایمر بخش 1 از 2====================================
// ===بخش تایمر معکوس و نمایش آن در xml به صورت استرینگ===

    private lateinit var timer: CountDownTimer  // یه تایمر تعریف میکنیم از کلاس تایمر

    private val _currentTime = MutableLiveData<Long>() //یه مقدار کپسوله که لانک تایمر هست با بخش گت و ..
     private val currentTime: LiveData<Long>
        get() = _currentTime

    //یه مقداری تعریف میکنیم که لانگ تبدیل شده به استرینگ تروتمیز هست که فقط در xml از طریق لایو دیتا بایندینگ رصدمیشه
    //حتما پروژه را تمیز و بیلد کنید تا شناخته شود
    //این داخل استرینگ  xml گرفته میشه و حتما باید یک بار بیلد بشه تا بشناسه در xml

    // میدونیم که currentTime یه لایودیتا از نوع لانگه
    //ما میخواهیم یه لایو دیتا از نوع استرینگ داشته باشیم
    //که از داخل Xml اون رو رصد کنیم
    //میایم با یه متد خیلی باحال به اسم  Transformations.map
    //یه لایو دیتا از یه نوع رو به یه نوع دیگه لایو دیتا تبدیل میکنیم
    // اینجا لایو دیتا لانگ تاریخ به لایو دیتای استرینگ تبدیل میشه
    //و میتونیم از داخل لایوت راحت در تکس ویو نمایش بدیم
    val currentTimeString = Transformations.map(currentTime) {
        DateUtils.formatElapsedTime(it)
    }

    //اینشیالیز کردن تایمر با 2 متد هر ثانیه چه کند
    //تمام شود چه کند
    // در بخش اینشیالایز برنامه
//    ============پایاننن تایمر بخش 1 از 2====================================
//    ============پایاننن تایمر بخش 1 از 2====================================
///////////////////////////////////////////////////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////////////////////////////////////////////

    //    ------------------------------------------------------------------------------------
    //ساخت شبیه ساز اونت همیشه به این صورته که یه بولین داریم همیشه و یه متد فانکشن برای برگردوندن به فالس
    // مقدار رو همیشه بعد صدا کردن متد باید فالس کنیم تا به حالت اولیه برگرده
    private val _eventGameFinish = MutableLiveData<Boolean>()
    val eventGameFinish: LiveData<Boolean>
        get() = _eventGameFinish

    /** Methods for completed events **/
    fun onGameFinishComplete() {
        _eventGameFinish.value = false
    }
///////////////////////////////////////////////////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////////////////////////////////////////////


    init {
        _score.value=0
        _word.value="-----"

        resetList()
        nextWord()


        timerSetup()
        timer.start()
    }

    private fun nextWord() {
        if (wordList.isEmpty()) {
            resetList()
        }

        _word.value = wordList[0]
        wordList.removeFirst()

    }

    private fun resetList() {

        wordList = mutableListOf(
            "سیب1",
            "پرتقال2",
            "خیار3",
            "4آناناس",
            "خانه5",
            "6ماشین",
            "7دوچرخه",
            "8متور",
            "9زمین",
            "10خونه",
            "11عروسی",
            "12داماد",
            "13مرغ",
            "14گاو",
            "15پرنده",
            "16ماشین",
            "17پیچ",
            "18مادر",
            "19کیف",
            "20کمربند",
            "21چترنجات"
        )
        // بهم ریختن ترتیب یک میوتیبل لیست
        wordList.shuffle()
    }


    fun onCorrect() {
        _score.value = _score.value?.plus(1)
        nextWord()

    }

    fun onSkip() {
        _score.value = (_score.value)?.minus(1)
        nextWord()
    }


    private fun timerSetup() {

        timer = object : CountDownTimer(10000, 1000) {



            override fun onTick(millisUntilFinished: Long) {
                Log.i("xxx","${(millisUntilFinished / 1000)}")
                _currentTime.value = (millisUntilFinished / 1000)
            }


            override fun onFinish() {
                _currentTime.value = 0
                //_eventBuzz.value = BuzzType.GAME_OVER
                _eventGameFinish.value = true
            }



        }





    }


}
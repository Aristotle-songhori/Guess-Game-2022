package com.arastoo.guessgame.game

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.arastoo.guessgame.R
import com.arastoo.guessgame.databinding.FragmentGameBinding

class GameFragment : Fragment() {

    private lateinit var viewModel: GameViewModel
    private lateinit var binding: FragmentGameBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        //====================================================
        //این خط باید حتما اول باشه تا اینشیالایز بشه
        // گرفتن ویومدل همیشه هست
        ViewModelProvider(this)[GameViewModel::class.java].also { viewModel = it }
//====================================================
        //گرفتن لایوت همیشه هست
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_game, container, false)
        //سریع داده های داخل xml رو با بایندینگ ست میکنیم
        binding.gameViewModel=viewModel
        //ست کردن بایدینگ با کتابخونه لایف سایکل
        binding.lifecycleOwner = this
//====================================================
// ما در اینجا فقط باید اونت ها رو و مسیر دهی ها رو داشته باشیم
//        میریم اونطرف یه چیزی رو فالس و ترو میکنیم تا بریم ججای دیگه
        viewModel.eventGameFinish.observe(viewLifecycleOwner, Observer { bool1 ->
            if (bool1==true){
                val currentScore = viewModel.score.value ?: 0
                //هر دفه اینجا کنف نشید ورژن های جدید پارامتر اینجوری میگیره.setXXXX()
                //در پایان این برگه نوشته چطوری یه آبجکت مثل یه شخص یا عکس یا ... جابجا کنیم
                //URL=https://www.section.io/engineering-education/safe-args-in-android/
                val action = GameFragmentDirections.actionGameFragmentToScoreFragment().setScore(currentScore)
                findNavController().navigate (action)


                viewModel.onGameFinishComplete()
            }
        })


return  binding.root

    }

}
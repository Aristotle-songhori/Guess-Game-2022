package com.arastoo.guessgame.title

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.findNavController
import com.arastoo.guessgame.R
import com.arastoo.guessgame.databinding.FragmentTitleBinding

class TitleFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val binding:FragmentTitleBinding=DataBindingUtil.inflate(inflater,R.layout.fragment_title,container,false)

        binding.buttonStartGame.setOnClickListener(){
            //این خط 2 تا نیاز مندی داره در گریدر
            // اگر این 2 مورد رو در ریدر اضافه نکنید آدرس دهی و انتقال مقدار بین فرگمنتها شناخته نمیشه
            /**
             * //====1111111111111111111111111111===========================================================================================
            //برای آدرس دهی و انتقال داده بین فرگمنتها استفاده میشود
            //شما باید یه بخش دوم این کد هم در بعد پلاگین اضافه کنید وگرنه آدرس هار پیدا نمیکنه
            buildscript {
            repositories {
            google()
            }
            dependencies {

            classpath("androidx.navigation.safeargs:androidx.navigation.safeargs.gradle.plugin:2.5.0")

            }
            }
            //====111111111111111111111111111111111111============================================================================================



            apply plugin: "androidx.navigation.safeargs"
            //================================================================================================
             */
            val actionLocal = TitleFragmentDirections.actionTitleFragmentToGameFragment()
            findNavController().navigate (actionLocal)
        }

        return binding.root
    }



}
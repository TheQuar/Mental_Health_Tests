package com.quar.mentalhealthtests.feature.onboarding

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.quar.mentalhealthtests.databinding.OnboardingActivityBinding
import com.quar.mentalhealthtests.domain.OnBoardingPrefManager
import com.quar.mentalhealthtests.feature.home.MainActivity


class OnBoardingActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = OnboardingActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val pref = OnBoardingPrefManager(this)
        if (!pref.isFirstTimeLaunch) {
            startActivity(Intent(this, MainActivity::class.java))
            finishAffinity()
        }
    }


}

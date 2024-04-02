package com.quar.mentalhealthtests.feature.quiz

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ViewModelProvider
import com.quar.mentalhealthtests.R
import com.quar.mentalhealthtests.data.HomeRepository
import com.quar.mentalhealthtests.databinding.ActivityQuizBinding
import com.quar.mentalhealthtests.feature.ViewModelProviderFactory

@Suppress("DEPRECATION")
class QuizActivity : AppCompatActivity() {
    private lateinit var binding: ActivityQuizBinding
    private lateinit var quizViewModel: QuizViewModel

    private var quizId = 0
    private var categoryId = 0
    private var status: Boolean = true

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityQuizBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(binding.main) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val extras = intent.extras
        if (extras != null) {
            categoryId = extras.getInt("categoryId")
            binding.toolbar.setTitle(extras.getString("name"))
        }
        val factory = ViewModelProviderFactory(HomeRepository(this))
        quizViewModel = ViewModelProvider(this, factory)[QuizViewModel::class.java]

        binding.toolbar.setTitleTextColor(resources.getColor(R.color.white))
        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)
        //            toolbar.inflateMenu(R.menu.menu)

        binding.quizLayout.apply {
            rbYes.setOnClickListener {
                onClick(rgPoll.checkedRadioButtonId)
            }

            rbNo.setOnClickListener {
                onClick(rgPoll.checkedRadioButtonId)
            }

        }

        quizViewModel.quiz.observe(this) {
            binding.quizLayout.apply {
                rgPoll.clearCheck()
                tvQuestionName.text = it.quiz.question
                tvQuestionCount.text = "${it.number}/${it.count}"
                quizId = it.quiz.id
                status = it.status

                if (!it.status)
                    quizViewModel.getRayting(categoryId)
            }
        }

        quizViewModel.rayting.observe(this) {
            binding.quizLayout.root.visibility = View.INVISIBLE
            binding.resultLayout.apply {
                tvPoint.text = it.ball.toString()
                tvDescription.text = it.desc
                root.visibility = View.VISIBLE
                val color = when (it.color) {
                    0 -> R.color.green
                    1 -> R.color.orange
                    else -> R.color.red

                }

                tvPoint.setTextColor(resources.getColor(color))

                btnBackHome.setOnClickListener {
                    onBackPressed()
                }
            }
        }

        quizViewModel.setCategory(categoryId)
    }


    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return super.onSupportNavigateUp()
    }


    private fun onClick(checked: Int) {
        if (status) {
            val _checked = when (checked) {
                R.id.rb_yes -> 1
                R.id.rb_no -> 0
                else -> -1
            }
            quizViewModel.nextQuestions(_checked)
        }
    }
}
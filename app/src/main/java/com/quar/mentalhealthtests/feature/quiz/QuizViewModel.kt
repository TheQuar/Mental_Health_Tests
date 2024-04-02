package com.quar.mentalhealthtests.feature.quiz

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.quar.mentalhealthtests.data.HomeRepository
import com.quar.mentalhealthtests.data.model.QuizView
import com.quar.mentalhealthtests.data.model.ResultView
import com.quar.mentalhealthtests.data.room.table.Quiz
import kotlinx.coroutines.launch

class QuizViewModel(private val mainRepository: HomeRepository) : ViewModel() {
    private val questions = MutableLiveData<List<Quiz>>()
    private var quizNumber = 0
    private var questionCount = 0
    private val result: ArrayList<Int> = ArrayList()

    private val _quiz = MutableLiveData<QuizView>()
    val quiz: LiveData<QuizView>
        get() = _quiz

    private val _rayting = MutableLiveData<ResultView>()
    val rayting: LiveData<ResultView>
        get() = _rayting


    fun getRayting(categoryId: Int) = viewModelScope.launch {
        val rating = mainRepository.getRayting(categoryId)
        val ball = result.sum()
        var desc = ""
        var color = 0

        rating.forEach lit@{
            val a = it.ball.split("-")
            if (a[0].toInt() <= ball && a[1].toInt() >= ball) {
                desc = it.description
                color = it.color
                return@lit
            }
        }
        _rayting.postValue(ResultView(ball, desc, color))

    }


    fun setCategory(categoryId: Int) = viewModelScope.launch {
        questions.value = mainRepository.getQuiz(categoryId)
        questions.value?.let {
            questionCount = it.size
            _quiz.value = getQuestion(it[quizNumber])
        }
    }

    fun nextQuestions(selected: Int) {
        var status: Boolean = true
        if (selected != -1) {
            if (quizNumber < questionCount - 1) {
                result.add(selected)
                quizNumber++
            } else {
                status = false
            }
            questions.value?.let {
                _quiz.value = getQuestion(it[quizNumber], status)
            }
        }
    }


    private fun getQuestion(quiz: Quiz, status: Boolean = true): QuizView {
        return with(quiz) {
            QuizView(
                this,
                questionCount,
                quizNumber + 1,
                status
            )
        }
    }
}
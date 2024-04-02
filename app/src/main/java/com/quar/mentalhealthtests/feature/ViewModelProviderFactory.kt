package com.quar.mentalhealthtests.feature

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.quar.mentalhealthtests.data.HomeRepository
import com.quar.mentalhealthtests.feature.quiz.QuizViewModel

@Suppress("UNCHECKED_CAST")
class ViewModelProviderFactory(
    private val mainRepository: HomeRepository
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(HomeViewModel::class.java)) {
            return HomeViewModel(mainRepository) as T
        }
        if (modelClass.isAssignableFrom(QuizViewModel::class.java)) {
            return QuizViewModel(mainRepository) as T
        }
        throw IllegalArgumentException("Unknown class name")
    }
}
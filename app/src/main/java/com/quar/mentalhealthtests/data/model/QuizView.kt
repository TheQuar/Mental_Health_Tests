package com.quar.mentalhealthtests.data.model

import com.quar.mentalhealthtests.data.room.table.Quiz

data class QuizView(
    val quiz: Quiz,
    val count: Int,
    val number: Int,
    val status: Boolean = true
)

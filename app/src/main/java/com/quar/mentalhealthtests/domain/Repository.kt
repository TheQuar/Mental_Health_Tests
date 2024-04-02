package com.quar.mentalhealthtests.domain

import com.quar.mentalhealthtests.data.room.table.Category
import com.quar.mentalhealthtests.data.room.table.Quiz
import com.quar.mentalhealthtests.data.room.table.Rayting

interface Repository {
    suspend fun getQuiz(categoryId: Int): List<Quiz>
    suspend fun getCategory(): List<Category>
    suspend fun getRayting(categoryId: Int): List<Rayting>
}
package com.quar.mentalhealthtests.data.room

import androidx.room.Dao
import androidx.room.Query
import com.quar.mentalhealthtests.data.room.table.Category
import com.quar.mentalhealthtests.data.room.table.Quiz
import com.quar.mentalhealthtests.data.room.table.Rayting


@Dao
interface AppDao {
    @Query("SELECT * FROM category")
    suspend fun getCategory(): List<Category>

    @Query("SELECT * FROM quiz WHERE category_id=:categoryId")
    suspend fun getQuiz(categoryId: Int): List<Quiz>

    @Query("SELECT * FROM rayting where category_id=:categoryId ")
    suspend fun getRayting(categoryId: Int): List<Rayting>
}

package com.quar.mentalhealthtests.data

import android.content.Context
import com.quar.mentalhealthtests.data.room.AppDao
import com.quar.mentalhealthtests.data.room.AppDatabase
import com.quar.mentalhealthtests.data.room.table.Category
import com.quar.mentalhealthtests.data.room.table.Quiz
import com.quar.mentalhealthtests.data.room.table.Rayting
import com.quar.mentalhealthtests.domain.Repository

class HomeRepository(private val appDao: AppDao) : Repository {
    constructor(context: Context) : this(
        AppDatabase.invoke(context).appDao()
    )

    override suspend fun getQuiz(categoryId: Int): List<Quiz> {
        return appDao.getQuiz(categoryId)
    }

    override suspend fun getCategory(): List<Category> {
        return appDao.getCategory()
    }

    override suspend fun getRayting(categoryId: Int): List<Rayting> {
        return appDao.getRayting(categoryId)
    }


}
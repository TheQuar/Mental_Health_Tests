package com.quar.mentalhealthtests.data.room.table

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "quiz")
data class Quiz(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val question: String,
    val category_id: Int
)

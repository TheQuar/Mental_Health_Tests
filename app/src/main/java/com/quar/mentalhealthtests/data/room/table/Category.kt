package com.quar.mentalhealthtests.data.room.table

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "category")
data class Category(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val name: String
)

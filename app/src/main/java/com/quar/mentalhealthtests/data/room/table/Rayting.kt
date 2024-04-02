package com.quar.mentalhealthtests.data.room.table

import androidx.room.Entity
import androidx.room.PrimaryKey
import org.jetbrains.annotations.NotNull

@Entity(tableName = "rayting")
data class Rayting(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val category_id: Int,
    val ball: String,
    val description: String,
    val color: Int
)
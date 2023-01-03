package edu.mailman.a7minuteworkout

import androidx.room.Dao
import androidx.room.Insert

@Dao
interface HistoryDAO {
    @Insert
    suspend fun insert(historyEntity: HistoryEntity)
}
package com.samuelnunes.too_dooapp.data.local

import androidx.room.*
import com.samuelnunes.too_dooapp.domain.model.Todo
import kotlinx.coroutines.flow.Flow


@Dao
interface TodoDao {

    @Query("SELECT * FROM Todo")
    fun getTodos(): Flow<List<Todo>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllTodos(todos: List<Todo>)

    @Query("SELECT * FROM Todo WHERE id = :id")
    fun getTodo(id: Int): Flow<Todo>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTodo(todo: Todo)

    @Query("DELETE FROM Todo")
    suspend fun deleteAll()

}
package com.pranav.androidjetpackcourse.model.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.pranav.androidjetpackcourse.model.entities.FavDish
import kotlinx.coroutines.flow.Flow


//Dao must be either interface or an abstract class

@Dao
interface FavDishDao {

    @Insert
    suspend fun insertFavDishDetails(favDish: FavDish)

    @Query("SELECT * FROM FAV_DISHES_TABLE ORDER BY ID")
    fun getAllDishesList() : Flow<List<FavDish>>


}
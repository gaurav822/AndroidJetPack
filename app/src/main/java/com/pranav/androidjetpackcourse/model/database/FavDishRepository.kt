package com.pranav.androidjetpackcourse.model.database

import androidx.annotation.WorkerThread
import com.pranav.androidjetpackcourse.model.entities.FavDish
import kotlinx.coroutines.flow.Flow


class FavDishRepository(private val favDishDao: FavDishDao) {

    @WorkerThread
    suspend fun insertFavDishData(favDish: FavDish){
        favDishDao.insertFavDishDetails(favDish)
    }

    val allDishesList : Flow<List<FavDish>> = favDishDao.getAllDishesList()
}
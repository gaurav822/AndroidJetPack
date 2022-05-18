package com.pranav.androidjetpackcourse.application

import android.app.Application
import com.pranav.androidjetpackcourse.model.database.FavDishRepository
import com.pranav.androidjetpackcourse.model.database.FavDishRoomDatabase


class FavDishApplication: Application() {

    private val database by lazy { FavDishRoomDatabase.getDatabase((this@FavDishApplication))}

    val repository by lazy { FavDishRepository(database.favDishDao()) }

}
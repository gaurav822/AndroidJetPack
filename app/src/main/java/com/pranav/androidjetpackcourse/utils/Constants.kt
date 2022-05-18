package com.pranav.androidjetpackcourse.utils

 
object Constants{

    const val DISH_TYPE:String = "DishType"
    const val DISH_CATEGORY:String="DishCategory"
    const val DISH_COOKING_TIME:String = "DishCookingTime"

    const val DISH_IMAGE_SOURCE_LOCAL:String = "Local"
    const val DISH_IMAGE_SOURCE_ONLINE:String = "Online"

    fun dishTypes() : ArrayList<String>{
        val list = arrayListOf<String>()
        list.add("Breakfast")
        list.add("Lunch")
        list.add("Snacks")
        list.add("Dinner")
        list.add("Salad")
        list.add("side dish")
        list.add("desert")
        list.add("other")
        return list
    }


    fun dishCategories():ArrayList<String>{
        val list = arrayListOf<String>()
        list.add("Pizza")
        list.add("Bakery")
        list.add("Noodles")
        list.add("Burger")
        list.add("MoMo")
        list.add("Sandwich")
        list.add("Hot Dogs")
        list.add("Biryani")
        list.add("Juices")
        list.add("Tea & Coffee")
        list.add("Other")
        return list

    }

    fun dishCookTime(): ArrayList<String>{
        val list = arrayListOf<String>()
        list.add("10")
        list.add("15")
        list.add("20")
        list.add("25")
        list.add("30")
        list.add("45")
        list.add("50")
        list.add("60")
        list.add("90")
        list.add("120")
        list.add("150")
        list.add("180")

        return list
    }

}
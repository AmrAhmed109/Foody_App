package com.example.foodyapp.data

import com.example.foodyapp.data.network.FoodRecipesApi
import com.example.foodyapp.models.FoodJoke
import com.example.foodyapp.models.FoodRecipe
import retrofit2.Response
import javax.inject.Inject

class RemoteDataSource @Inject constructor(private val foodRecipesApi: FoodRecipesApi) {

    suspend fun getRecipes (queries : Map<String,String>) : Response<FoodRecipe> {
        return foodRecipesApi.getRecipes(queries)
    }

    suspend fun searchRecipes (searchQueries : Map<String,String>) : Response<FoodRecipe> {
        return foodRecipesApi.searchRecipes(searchQueries)
    }


    suspend fun getFoodJoke (searchQueries : String) : Response<FoodJoke> {
        return foodRecipesApi.getFoodJoke(searchQueries)
    }



}
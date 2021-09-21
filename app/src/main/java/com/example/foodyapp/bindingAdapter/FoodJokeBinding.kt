package com.example.foodyapp.bindingAdapter

import android.view.View
import android.widget.ProgressBar
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.example.foodyapp.database.entitiy.FoodJokeEntity
import com.example.foodyapp.models.FoodJoke
import com.example.foodyapp.utils.NetworkResult
import com.example.foodyapp.viewmodels.MainViewModel
import com.google.android.material.card.MaterialCardView

class FoodJokeBinding {
    companion object {

        @BindingAdapter("readApiResponseFoodJoke", "foodJokeEntity", requireAll = false)
        @JvmStatic
        fun View.setCardAndProgressVisibility(
            readApiResponse: NetworkResult<FoodJoke>?,
            foodJokeEntity: List<FoodJokeEntity>?
        ) {

            when (readApiResponse) {
                is NetworkResult.Loading -> {
                    when (this) {
                        is ProgressBar -> {
                            this.visibility = View.VISIBLE
                        }
                        is MaterialCardView -> {
                            this.visibility = View.INVISIBLE
                        }
                    }
                }

                is NetworkResult.Error -> {
                    when (this) {
                        is ProgressBar -> {
                            this.visibility = View.INVISIBLE
                        }
                        is MaterialCardView -> {
                            this.visibility = View.VISIBLE
                            if (foodJokeEntity.isNullOrEmpty()) {
                                this.visibility = View.INVISIBLE
                            }
                        }
                    }
                }

                is NetworkResult.Success -> {
                    when (this) {
                        is ProgressBar -> {
                            this.visibility = View.INVISIBLE
                        }
                        is MaterialCardView -> {
                            this.visibility = View.VISIBLE
                        }
                    }
                }

            }

        }

        @BindingAdapter("responseFoodJokeError", "foodJokeEntityError", requireAll = false)
        @JvmStatic
        fun View.setErrorVisibility(
            readApiResponse: NetworkResult<FoodJoke>?,
            database: List<FoodJokeEntity>?
        ) {

            if (database != null) {
                if (database.isEmpty()) {
                    this.visibility = View.VISIBLE
                    when (this) {
                        is TextView -> {
                            if (readApiResponse != null) {
                                this.text = readApiResponse.message.toString()
                            }
                        }
                    }
                }
            }


            if (readApiResponse is NetworkResult.Success) {
                this.visibility = View.INVISIBLE
            }
        }


    }
}
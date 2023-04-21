package kk.example.jetpackcomposesturacturalapp.data.remote.webapi


import kk.example.jetpackcomposesturacturalapp.data.remote.model.ModelProductList
import retrofit2.Response
import retrofit2.http.*

interface AuthApiService {



    //get products
    @Headers("Authorization:eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJVc2VySWQiOiI3OCIsIkN1cnJlbnRDb21JZCI6IjEiLCJuYmYiOjE2ODE3MDI5OTAsImV4cCI6MTY4MjMwNzc5MCwiaWF0IjoxNjgxNzAyOTkwfQ.JCU1MPH_SOJsHYpOn9GKrYx90N3Tsdtut3rTU3Hl09g")
    @GET("GetProductList")
    suspend fun getProductList(    ): Response<ModelProductList>
}
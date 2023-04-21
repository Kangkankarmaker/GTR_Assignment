package kk.example.jetpackcomposesturacturalapp.data.remote.repository


import kk.example.jetpackcomposesturacturalapp.data.remote.model.ModelProductList
import kk.example.jetpackcomposesturacturalapp.data.remote.webapi.AuthApiService
import retrofit2.Response
import javax.inject.Inject

class ProductListRepository @Inject constructor(private val authApiService: AuthApiService) {

    suspend fun getProductList(): Response<ModelProductList> {
        return authApiService.getProductList()
    }
}
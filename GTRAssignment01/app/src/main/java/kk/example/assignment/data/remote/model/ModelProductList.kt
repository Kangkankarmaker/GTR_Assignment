package kk.example.jetpackcomposesturacturalapp.data.remote.model


data class ModelProductList(
    val ProductList: List<ModelProducts>,
    val total: String,
    val skip: String,
    val limit: String,
)

data class ModelProducts(
    val Id : String,
    val Name : String,
    val Price : String,
    val CostPrice : String,
){
    fun doesMatchSearchQuery(query: String): Boolean {
        val matchingCombinations = listOf(
            "$Name$Price",
            "$Name $Price",
            "${Name.first()} ${Price.first()}",
        )

        return matchingCombinations.any {
            it.contains(query, ignoreCase = true)
        }
    }
}

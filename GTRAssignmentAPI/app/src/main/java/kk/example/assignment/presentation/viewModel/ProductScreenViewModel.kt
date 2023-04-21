package kk.example.jetpackcomposesturacturalapp.presentation.viewModel

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kk.example.jetpackcomposesturacturalapp.presentation.common_screens.SearchWidgetState
import kk.example.jetpackcomposesturacturalapp.data.remote.model.ModelProductList
import kk.example.jetpackcomposesturacturalapp.data.remote.model.ModelProducts
import kk.example.jetpackcomposesturacturalapp.data.remote.repository.ProductListRepository
import kk.example.jetpackcomposesturacturalapp.presentation.utils.Event
import kk.example.jetpackcomposesturacturalapp.presentation.utils.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

@HiltViewModel
class ProductScreenViewModel @Inject constructor(
    private val repository: ProductListRepository
) : ViewModel() {

    // live data for api response
    private val _productData = MutableLiveData<Event<Resource<ModelProductList>>>()
    val productData: LiveData<Event<Resource<ModelProductList>>> = _productData

    // data for product list response
    private val _productData2 = MutableStateFlow(emptyList<ModelProducts>())
    val state: StateFlow<List<ModelProducts>> get() =_productData2

    // to check search Widget State
    private val _searchWidgetState: MutableState<SearchWidgetState> = mutableStateOf(value = SearchWidgetState.CLOSED)
    val searchWidgetState: State<SearchWidgetState> = _searchWidgetState

    // to get text field text
    private val _searchTextState= MutableStateFlow( "")
    val searchTextState =_searchTextState.asStateFlow()

    // to get text field text loading state
    private val _isSearching = MutableStateFlow(false)
    val isSearching = _isSearching.asStateFlow()




     fun preformApiGetProduct(){
         viewModelScope.launch(Dispatchers.IO) {
             _productData.postValue(Event(Resource.Loading()))
             try {
                 val response = repository.getProductList()
                 if (response.isSuccessful) {
                     _productData.postValue(Event(Resource.Success(response.body()!!)))
                     _productData2.value = response.body()!!.ProductList
                 } else {
                     _productData.postValue(Event(Resource.Error(response.message())))
                 }
             } catch (e: HttpException) {
                 when (e) {
                     is IOException -> {
                         _productData.postValue(Event(Resource.Error(e.message!!)))
                     }
                 }
             } catch (t: Throwable) {
                 when (t) {
                     is IOException -> {
                         _productData.postValue(Event(Resource.Error(t.message!!)))
                     }
                 }
             }
         }
    }
    //////////  search widget ///////////
    fun updateSearchWidgetState(newValue: SearchWidgetState){
        _searchWidgetState. value = newValue

    }
    fun updateSearchTextState(newValue: String) {
        _searchTextState.value = newValue
    }

    val products = _searchTextState
        .debounce(1000L)
        .onEach { _isSearching.update { true } }
        .combine(_productData2) { text, persons ->
            if(text.isBlank()) {
                persons
            } else {
                delay(500)
                persons.filter {
                    it.doesMatchSearchQuery(text)
                }
            }
        }
        .onEach { _isSearching.update { false } }
        .stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5000),
            _productData2.value
        )
}
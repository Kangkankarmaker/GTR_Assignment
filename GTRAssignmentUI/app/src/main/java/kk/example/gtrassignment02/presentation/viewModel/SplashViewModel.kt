package kk.example.jetpackcomposesturacturalapp.presentation.viewModel

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kk.example.gtrassignment02.data.local.DataStoreRepository
import kk.example.jetpackcomposesturacturalapp.presentation.navigation.Screen
import kotlinx.coroutines.launch
import javax.inject.Inject


class SplashViewModel @Inject constructor(
    private val repository: DataStoreRepository
) : ViewModel() {

    private val _isLoading: MutableState<Boolean> = mutableStateOf(true)
    val isLoading: State<Boolean> = _isLoading

    private val _startDestination: MutableState<String> = mutableStateOf(Screen.Loading.route)
    val startDestination: State<String> = _startDestination

    init {
        viewModelScope.launch {
            repository.readOnBoardingState().collect { completed ->
                if (!completed) {
                    _startDestination.value = Screen.Welcome.route
                }else {
                    _startDestination.value = Screen.DashBoard.route
                }
            }
            _isLoading.value = false
        }
    }
}
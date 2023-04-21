package kk.example.jetpackcomposesturacturalapp.presentation.inputValidations


import androidx.compose.ui.focus.FocusDirection


sealed class ScreenEvent {
    class ShowToast(val messageId: Int) : ScreenEvent()
    class ShowMessageToast(val message: String) : ScreenEvent()
    class SuccessLogIn(val booleanMassage: Boolean): ScreenEvent()
    class UpdateKeyboard(val show: Boolean) : ScreenEvent()
    class RequestLogInFocus(val textFieldKey: LogInTextFieldKey) : ScreenEvent()
    class RequestRegFocus(val textFieldKey: RegTextFieldKey) : ScreenEvent()
    object ClearFocus : ScreenEvent()
    class MoveFocus(val direction: FocusDirection = FocusDirection.Down) : ScreenEvent()
}

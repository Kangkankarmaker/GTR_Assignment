package kk.example.jetpackcomposesturacturalapp.presentation.inputValidations


sealed class UserLogInInputEvent {
    class Email(val input: String) : UserLogInInputEvent()
    class Password(val input: String) : UserLogInInputEvent()
}

sealed class UserRegInputEvent {
    class Name(val input: String) : UserRegInputEvent()
    class Email(val input: String) : UserRegInputEvent()
    class Phone(val input: String) : UserRegInputEvent()
    class NID(val input: String) : UserRegInputEvent()
    class Password(val input: String) : UserRegInputEvent()
}
package kk.example.jetpackcomposesturacturalapp.presentation.inputValidations


import android.content.Context
import android.widget.Toast

fun Context.toast(messageId: Int) {
    Toast.makeText(this, getString(messageId), Toast.LENGTH_SHORT).show()
}

fun Context.messageToast(messageId: String) {
    Toast.makeText(this, messageId, Toast.LENGTH_SHORT).show()
}
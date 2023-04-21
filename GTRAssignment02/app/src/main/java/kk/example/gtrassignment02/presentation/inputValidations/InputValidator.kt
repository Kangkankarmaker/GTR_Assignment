package kk.example.jetpackcomposesturacturalapp.presentation.inputValidations

import kk.example.gtrassignment02.R


object InputValidator {

    fun getNameErrorIdOrNull(input: String): Int? {
        return when {
            input.length < 2 -> R.string.name_too_short
            else -> null
        }
    }

    fun getPhoneErrorIdOrNull(input: String): Int? {
        return when {
            input.length < 11 -> R.string.phone_too_short
            //etc..
            else -> null
        }
    }

    fun getPasswordErrorIdOrNull(input: String): Int? {
        /*return when {
            input.firstOrNull { it.isDigit() } == null  -> return R.string.invalid_password2
            input.filter { it.isLetter() }.firstOrNull { it.isUpperCase() } == null  -> return R.string.invalid_password2
            input.filter { it.isLetter() }.firstOrNull { it.isLowerCase() } == null  -> return R.string.invalid_password2
            input.firstOrNull { !it.isLetterOrDigit() } == null -> return R.string.invalid_password2
            input.length < 8 -> return R.string.invalid_password
            //etc..
            else -> null
        }*/
        return when {
            input.length < 2 -> R.string.invalid_password
            else -> null
        }
    }



    fun getEmailErrorIdOrNull(input: String): Int? {

        return when {
            input.length < 2 -> R.string.invalid_email
            else -> null
        }
       /* fun isValidEmail(input: String): Boolean {
            return  Patterns.EMAIL_ADDRESS.matcher(input).matches()
        }

        return when {
            !isValidEmail(input) -> R.string.invalid_email
            else -> null
        }*/

    }

}

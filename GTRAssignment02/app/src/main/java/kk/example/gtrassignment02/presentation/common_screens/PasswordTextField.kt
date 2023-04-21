package kk.example.jetpackcomposesturacturalapp.presentation.common_screens

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextRange
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.*
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kk.example.jetpackcomposesturacturalapp.presentation.inputValidations.InputWrapper

@Composable
fun PasswordTextField(
    modifier: Modifier,
    filedName:String,
    hint:String,
    fieldIcon: ImageVector,
    inputWrapper: InputWrapper,
    onValueChange: (value: String) -> Unit,
    onImeKeyAction: () -> Unit,
    @StringRes labelResId: Int,
    keyboardOptions: KeyboardOptions = remember {
        KeyboardOptions.Default
    }
) {

    val fieldValue = remember {
        mutableStateOf(TextFieldValue(inputWrapper.value, TextRange(inputWrapper.value.length)))
    }
    var passwordVisible by rememberSaveable { mutableStateOf(false) }

    OutlinedTextField(
        modifier = modifier,
        value = fieldValue.value,
        onValueChange = {
            fieldValue.value = it
            onValueChange(it.text)
        },
        isError = inputWrapper.errorId != null,
        label = {
            Text(
                stringResource(labelResId),
                style = TextStyle(
                    fontSize = 14.sp,
                    //color = MaterialTheme.colors.primary
                )
            )
        },
        placeholder = {
            Text(
                text = hint,
                style = TextStyle(
                    color = Color.Gray
                )
            )
        },
        colors = TextFieldDefaults.outlinedTextFieldColors(
            focusedBorderColor = MaterialTheme.colors.primary,
            unfocusedBorderColor = MaterialTheme.colors.primary
        ),
        shape = RoundedCornerShape(topEnd = 12.dp, bottomStart = 12.dp),
        singleLine = true,
        visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
        keyboardOptions = keyboardOptions,
        leadingIcon = {
            IconButton(onClick = { /*TODO*/ }) {
                Icon(
                    imageVector = fieldIcon,
                    contentDescription = filedName,
                    tint = MaterialTheme.colors.primary
                )
            }
        },
        keyboardActions = remember {
            KeyboardActions(onAny = { onImeKeyAction() })
        },
        trailingIcon = {
            val image = if (passwordVisible) Visibility else VisibilityOff
            val description = if (passwordVisible) "Hide password" else "Show password"

            IconButton(onClick = {passwordVisible = !passwordVisible}){
                Icon(imageVector  = image, description)
            }
        }
    )
    if (inputWrapper.errorId != null) {
        androidx.compose.material.Text(
            text = stringResource(inputWrapper.errorId),
            color = MaterialTheme.colors.error,
            style = MaterialTheme.typography.caption,
            modifier = Modifier.padding(start = 16.dp).fillMaxWidth(0.8f)
        )
    }
}
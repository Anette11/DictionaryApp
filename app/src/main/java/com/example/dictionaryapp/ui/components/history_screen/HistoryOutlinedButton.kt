package com.example.dictionaryapp.ui.components.history_screen

import androidx.compose.foundation.BorderStroke
import androidx.compose.material.OutlinedButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.dimensionResource
import com.example.dictionaryapp.R

@Composable
fun HistoryOutlinedButton(
    text: String,
    onClick: () -> Unit
) = OutlinedButton(
    onClick = onClick,
    border = BorderStroke(
        color = colorResource(id = R.color.violet),
        width = dimensionResource(id = R.dimen._1dp)
    )
) {
    Text(
        text = text,
        color = colorResource(id = R.color.violet)
    )
}

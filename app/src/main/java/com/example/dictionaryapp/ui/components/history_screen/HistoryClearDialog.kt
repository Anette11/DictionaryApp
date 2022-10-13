package com.example.dictionaryapp.ui.components.history_screen

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.window.Dialog
import com.example.dictionaryapp.R

@Composable
fun HistoryClearDialog(
    onDismissClick: () -> Unit,
    onConfirmClick: () -> Unit
) = Dialog(
    onDismissRequest = { }
) {
    Surface(
        shape = RoundedCornerShape(dimensionResource(id = R.dimen._4dp)),
        color = Color.White
    ) {
        Column(
            modifier = Modifier.padding(dimensionResource(id = R.dimen._16dp)),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = stringResource(id = R.string.history_clear_dialog_title),
                style = MaterialTheme.typography.subtitle1,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen._16dp)))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {
                HistoryOutlinedButton(
                    text = stringResource(id = R.string.history_clear_dialog_dismiss_button),
                    onClick = onDismissClick
                )
                Spacer(modifier = Modifier.width(dimensionResource(id = R.dimen._16dp)))
                HistoryOutlinedButton(
                    text = stringResource(id = R.string.history_clear_dialog_confirm_button),
                    onClick = onConfirmClick
                )
            }
        }
    }
}
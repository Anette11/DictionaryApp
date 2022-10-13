package com.example.dictionaryapp.ui.components.history_screen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import com.example.dictionaryapp.R

@Composable
fun HistoryItem(
    text: String,
    onClick: () -> Unit
) = Column(
    modifier = Modifier
        .fillMaxWidth()
        .clickable { onClick() }
) {
    Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen._8dp)))
    Text(
        text = text,
        style = MaterialTheme.typography.body1
    )
    Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen._8dp)))
}
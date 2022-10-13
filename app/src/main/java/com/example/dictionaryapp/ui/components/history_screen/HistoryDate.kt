package com.example.dictionaryapp.ui.components.history_screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import com.example.dictionaryapp.R
import com.example.dictionaryapp.ui.components.WordDivider

@Composable
fun HistoryDate(
    text: String
) = Column(
    modifier = Modifier.fillMaxWidth(),
    horizontalAlignment = Alignment.CenterHorizontally
) {
    Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen._8dp)))
    Text(
        text = text,
        style = MaterialTheme.typography.subtitle1,
        color = Color.Gray.copy(0.8f)
    )
    Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen._8dp)))
    WordDivider()
}
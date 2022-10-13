package com.example.dictionaryapp.ui.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Divider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import com.example.dictionaryapp.R

@Composable
fun WordDivider() =
    Divider(
        modifier = Modifier.fillMaxWidth(),
        color = Color.Gray.copy(0.2f),
        thickness = dimensionResource(id = R.dimen._1dp)
    )
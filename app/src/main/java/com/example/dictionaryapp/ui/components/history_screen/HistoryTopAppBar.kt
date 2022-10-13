package com.example.dictionaryapp.ui.components.history_screen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import com.example.dictionaryapp.R

@Composable
fun HistoryTopAppBar(
    onSearchClick: () -> Unit,
    onClearHistoryClick: () -> Unit
) = TopAppBar(
    modifier = Modifier.fillMaxWidth(),
    backgroundColor = colorResource(id = R.color.violet)
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = dimensionResource(id = R.dimen._8dp)),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Icon(
            modifier = Modifier.clickable {
                onSearchClick()
            },
            painter = painterResource(id = R.drawable.ic_search),
            contentDescription = stringResource(id = R.string.search),
            tint = Color.White
        )
        Spacer(modifier = Modifier.width(dimensionResource(id = R.dimen._4dp)))
        Text(
            text = stringResource(id = R.string.search_history),
            color = Color.White
        )
        Spacer(modifier = Modifier.width(dimensionResource(id = R.dimen._4dp)))
        Icon(
            modifier = Modifier.clickable {
                onClearHistoryClick()
            },
            painter = painterResource(id = R.drawable.ic_delete),
            contentDescription = stringResource(id = R.string.clear),
            tint = Color.White
        )
    }
}
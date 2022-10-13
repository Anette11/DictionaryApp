package com.example.dictionaryapp.ui.components.home_screen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusState
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import com.example.dictionaryapp.R

@Composable
fun HomeTopAppBar(
    value: String,
    isHintVisible: Boolean,
    onValueChange: (String) -> Unit,
    onFocusChanged: (FocusState) -> Unit,
    onSearchClick: () -> Unit,
    onClearClick: () -> Unit
) = TopAppBar(
    modifier = Modifier.fillMaxWidth(),
    backgroundColor = colorResource(id = R.color.violet)
) {
    Card(
        modifier = Modifier
            .padding(dimensionResource(id = R.dimen._8dp))
            .fillMaxSize(),
        shape = RoundedCornerShape(dimensionResource(id = R.dimen._4dp)),
        backgroundColor = Color.White.copy(0.2f),
        elevation = dimensionResource(id = R.dimen._0dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(
                    horizontal = dimensionResource(id = R.dimen._8dp),
                    vertical = dimensionResource(id = R.dimen._4dp)
                ),
            verticalAlignment = Alignment.CenterVertically
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
            Box(
                modifier = Modifier.weight(1f),
                contentAlignment = Alignment.CenterStart
            ) {
                BasicTextField(
                    modifier = Modifier
                        .onFocusChanged { focusState ->
                            onFocusChanged(focusState)
                        },
                    value = value,
                    onValueChange = { valueChanged ->
                        onValueChange(valueChanged)
                    },
                    singleLine = true,
                    textStyle = TextStyle(color = Color.White)
                )
                if (isHintVisible) {
                    Text(
                        text = stringResource(id = R.string.search_field_hint),
                        style = TextStyle(color = Color.White)
                    )
                }
            }
            Spacer(modifier = Modifier.width(dimensionResource(id = R.dimen._4dp)))
            Icon(
                modifier = Modifier.clickable { onClearClick() },
                painter = painterResource(id = R.drawable.ic_cancel),
                contentDescription = stringResource(id = R.string.cancel),
                tint = Color.White
            )
        }
    }
}
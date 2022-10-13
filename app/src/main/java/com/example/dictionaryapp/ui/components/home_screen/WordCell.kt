package com.example.dictionaryapp.ui.components.home_screen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import com.example.dictionaryapp.R
import com.example.dictionaryapp.data.local.entities.WordDbo

@Composable
fun WordCell(
    wordDbo: WordDbo,
    onPlayAudio: (String) -> Unit
) = Column(
    modifier = Modifier.fillMaxWidth()
) {
    Text(
        text = "${wordDbo.word}",
        style = MaterialTheme.typography.h5,
        fontWeight = FontWeight.Bold
    )
    wordDbo.phonetics.forEach { phoneticDbo ->
        phoneticDbo.text?.let {
            Row {
                Text(
                    text = phoneticDbo.text,
                    style = MaterialTheme.typography.subtitle1
                )
                Spacer(modifier = Modifier.width(dimensionResource(id = R.dimen._16dp)))
                phoneticDbo.audio?.let { audioUrl ->
                    Icon(
                        modifier = Modifier.clickable {
                            onPlayAudio(audioUrl)
                        },
                        painter = painterResource(id = R.drawable.ic_volume),
                        contentDescription = stringResource(id = R.string.audio)
                    )
                }
            }
        }
    }
    wordDbo.meanings.forEach { meaningDbo ->
        meaningDbo.partOfSpeech?.let {
            Text(
                text = meaningDbo.partOfSpeech,
                fontStyle = FontStyle.Italic,
                fontWeight = FontWeight.Bold
            )
            meaningDbo.definitions.filter { it.definition != null }
                .forEachIndexed { index, definitionDbo ->
                    Text(
                        text = "${index + 1}. ${definitionDbo.definition}",
                        style = MaterialTheme.typography.body1
                    )
                    definitionDbo.example?.let {
                        Text(
                            text = definitionDbo.example,
                            style = MaterialTheme.typography.body1,
                            fontStyle = FontStyle.Italic
                        )
                    }
                }
        }
    }
}
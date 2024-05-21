package com.compass.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.compass.ui.theme.CompassTheme

@Composable
fun CompassScreenRoot(
    viewModel: CompassViewModel = viewModel(),
) {
    CompassScreen(
        state = viewModel.state,
        onButtonClicked = { viewModel.makeRequests() }
    )
}

@Composable
private fun CompassScreen(
    state: CompassState,
    onButtonClicked: () -> Unit
) {
    Box(modifier = Modifier
        .fillMaxSize()
        .padding(8.dp),
        contentAlignment = Alignment.Center
    ) {
        if (state.isEveryTenthCharacterLoading || state.isWordCounterLoading) {
            CircularProgressIndicator()
        }
        Column(
            modifier = Modifier.fillMaxSize(),
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {
                Button(
                    enabled = !state.isEveryTenthCharacterLoading || !state.isWordCounterLoading,
                    onClick = onButtonClicked
                ) {
                    Text(text = "Make Request:")
                }
            }

            Spacer(modifier = Modifier.height(16.dp))
            Text(text = "Every 10th Characters:")
            Spacer(modifier = Modifier.height(8.dp))

            LazyRow {
                items(state.everyTenthCharacter.size) {
                    EveryTenthCharacter(state.everyTenthCharacter[it])
                }
            }

            Spacer(modifier = Modifier.height(16.dp))
            Text(text = "World Counter:")
            Spacer(modifier = Modifier.height(8.dp))

            LazyColumn(
                modifier = Modifier.fillMaxSize()
            ) {
                val list = state.wordCounter.entries.toList()
                items(list.size) {
                    WorldCounter(list[it])
                }
            }
        }
    }
}

@Composable
fun WorldCounter(item: Map.Entry<String, Int>) {
    Box(modifier = Modifier
        .fillMaxWidth()
        .padding(vertical = 4.dp)
        .background(
            color = MaterialTheme.colorScheme.primary,
            shape = RoundedCornerShape(4.dp)
        )
        .padding(8.dp)
    ) {
        Text(
            text = "\"${item.key}\" = ${item.value}",
            color = Color.White
        )
    }
}

@Composable
fun EveryTenthCharacter(textValue: String) {
    Box(modifier = Modifier
        .padding(horizontal = 4.dp)
        .background(
            color = MaterialTheme.colorScheme.primary,
            shape = RoundedCornerShape(4.dp)
        )
        .padding(horizontal = 8.dp, vertical = 4.dp)
    ) {
        Text(
            text = textValue,
            color = Color.White
        )
    }
}

@Preview
@Composable
private fun CompassScreenPreview() {
     CompassTheme{
        CompassScreen(
            state = CompassState(),
            onButtonClicked = {}
        )
    }
}
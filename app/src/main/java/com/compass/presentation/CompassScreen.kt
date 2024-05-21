package com.compass.presentation

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.compass.ui.theme.CompassTheme

@Composable
fun CompassScreenRoot(
    viewModel: CompassViewModel = viewModel(),
) {
    CompassScreen(
        /*state = viewModel.state,
        onAction = viewModel::onAction*/
    )
}

@Composable
private fun CompassScreen(
    /*state: ,
    onAction: () -> Unit*/
) {
    Text(text = "Compass")
}

@Preview
@Composable
private fun CompassScreenPreview() {
     CompassTheme{
        CompassScreen(
            /*state = (),
            onAction = {}*/
        )
    }
}
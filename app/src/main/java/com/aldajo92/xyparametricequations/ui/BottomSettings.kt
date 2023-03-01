package com.aldajo92.xyparametricequations.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.ContentAlpha
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Settings
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.unit.dp
import com.aldajo92.xyparametricequations.EquationUIState

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun InputEquationsRow(
    modifier: Modifier = Modifier,
    equationXUIState: EquationUIState = EquationUIState(),
    equationYUIState: EquationUIState = EquationUIState(),
    enableInputText: Boolean = true,
    onEquationExpressionXChange: (String) -> Unit = {},
    onEquationExpressionYChange: (String) -> Unit = {},
) {
    val keyboardController = LocalSoftwareKeyboardController.current
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        InputStringField(
            modifier = Modifier
                .weight(1f),
            textTitle = "x(t)=",
            textValue = equationXUIState.equationString,
            enableInputText = enableInputText,
            onValueChange = onEquationExpressionXChange,
            showError = equationXUIState.showError,
            errorMessage = equationXUIState.errorMessage,
            keyboardController = keyboardController
        )
        InputStringField(
            modifier = Modifier
                .weight(1f),
            textTitle = "y(t)=",
            textValue = equationYUIState.equationString,
            enableInputText = enableInputText,
            onValueChange = onEquationExpressionYChange,
            showError = equationYUIState.showError,
            errorMessage = equationYUIState.errorMessage,
            keyboardController = keyboardController
        )
    }
}

@Composable
fun SliderForTParameter(
    modifier: Modifier = Modifier,
    range: ClosedFloatingPointRange<Float> = 0f..100f,
    startValue: Float = (range.start + range.endInclusive) / 2f,
    tParameter: Float = startValue,
    enableSlider: Boolean = true,
    onSettingsClicked: () -> Unit = {},
    onValueChanged: (Float) -> Unit = {}
) {
    Row(modifier = modifier) {
        Text(
            modifier = Modifier
                .align(Alignment.CenterVertically)
                .padding(8.dp),
            text = "t="
        )
        SimpleContinuousSlider(
            modifier = Modifier.weight(1f),
            range = range,
            selection = tParameter,
            enableSlider = enableSlider,
            onValueChanged = onValueChanged
        )
        // TODO: Extract this out from this composable
        Icon(
            modifier = Modifier
                .padding(8.dp)
                .align(Alignment.CenterVertically)
                .alpha(if (enableSlider) 1f else ContentAlpha.disabled)
                .clickable { if (enableSlider) onSettingsClicked() },
            imageVector = Icons.Default.Settings,
            tint = MaterialTheme.colors.onBackground,
            contentDescription = "Settings"
        )
    }
}

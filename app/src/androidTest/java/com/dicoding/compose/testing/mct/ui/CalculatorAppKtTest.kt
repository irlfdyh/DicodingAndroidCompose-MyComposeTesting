package com.dicoding.compose.testing.mct.ui

import androidx.activity.ComponentActivity
import androidx.compose.ui.test.assertHasNoClickAction
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextInput
import com.dicoding.compose.testing.mct.ui.theme.MyComposeTestingTheme
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import com.dicoding.compose.testing.mct.R

class CalculatorAppKtTest {

    @get:Rule
    val composeAndroidRule = createAndroidComposeRule<ComponentActivity>()

    @Before
    fun setup() {
        composeAndroidRule.setContent {
            MyComposeTestingTheme {
                CalculatorApp()
            }
        }
    }

    @Test
    fun calculate_area_of_rectangle_correct() {
        composeAndroidRule.onNodeWithText(composeAndroidRule.activity.getString(R.string.enter_length)).performTextInput("3")
        composeAndroidRule.onNodeWithText(composeAndroidRule.activity.getString(R.string.enter_width)).performTextInput("4")
        composeAndroidRule.onNodeWithText(composeAndroidRule.activity.getString(R.string.count)).performClick()
        composeAndroidRule.onNodeWithText(composeAndroidRule.activity.getString(R.string.count), useUnmergedTree = true).assertHasNoClickAction()
        composeAndroidRule.onNodeWithText(composeAndroidRule.activity.getString(R.string.result, 12.0)).assertExists()
    }

    @Test
    fun wrong_input_not_calculated() {
        composeAndroidRule.onNodeWithText(composeAndroidRule.activity.getString(R.string.enter_length)).performTextInput("..3")
        composeAndroidRule.onNodeWithText(composeAndroidRule.activity.getString(R.string.enter_width)).performTextInput("4")
        composeAndroidRule.onNodeWithText(composeAndroidRule.activity.getString(R.string.count)).performClick()
        composeAndroidRule.onNodeWithText(composeAndroidRule.activity.getString(R.string.result, 0.0)).assertExists()
    }

}
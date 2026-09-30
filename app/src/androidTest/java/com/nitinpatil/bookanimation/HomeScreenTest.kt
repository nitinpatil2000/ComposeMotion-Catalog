package com.nitinpatil.bookanimation

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onAllNodesWithContentDescription
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import org.junit.Rule
import org.junit.Test

class HomeScreenTest {

    @get:Rule
    val composeTestRule = createAndroidComposeRule<MainActivity>()

    @Test
    fun testSequentialAnimationsAndUiSections(){
        composeTestRule.mainClock.autoAdvance = false

        composeTestRule.mainClock.advanceTimeBy(1500L)
        composeTestRule.waitForIdle()

        //header
        composeTestRule.onNodeWithText("Nitin Patil").assertIsDisplayed()

        //search
        composeTestRule.mainClock.advanceTimeBy(2000L)
        composeTestRule.waitForIdle()

        //popular product
        composeTestRule.mainClock.advanceTimeBy(5000L)
        composeTestRule.waitForIdle()

        composeTestRule.onNodeWithText("Popular Products").assertIsDisplayed()
    }

    @Test
    fun testCategoryChipSelection() {
        // Allow animations to run normally for this test
        composeTestRule.mainClock.autoAdvance = true

        // Wait until the "Sofa" category chip appears on screen
        composeTestRule.waitUntil(timeoutMillis = 6000L) {
            try {
                composeTestRule.onNodeWithText("Sofa").assertExists()
                true
            } catch (e: AssertionError) {
                false
            }
        }

        // Perform click on "Sofa" chip
        composeTestRule.onNodeWithText("Sofa").performClick()

        // Verify the chip remains displayed and interactive
        composeTestRule.onNodeWithText("Sofa").assertIsDisplayed()
    }

    @Test
    fun testCollectionCardDetailOverlayOpens() {
        composeTestRule.mainClock.autoAdvance = true

        // Wait until collection items appear on screen
        composeTestRule.waitUntil(timeoutMillis = 10000L) {
            try {
                composeTestRule.onNodeWithText("Super Collection").assertExists()
                true
            } catch (e: AssertionError) {
                false
            }
        }

        Thread.sleep(1500)
        composeTestRule.waitForIdle()

        // Click index 1 ("Super Collection")
        composeTestRule.onAllNodesWithContentDescription("Navigate")[1]
            .assertIsDisplayed()
            .performClick()

        // Wait for overlay title "Super Collection" to appear
        composeTestRule.waitUntil(timeoutMillis = 5000L) {
            try {
                composeTestRule.onNodeWithText("Super Collection").assertIsDisplayed()
                true
            } catch (e: AssertionError) {
                false
            }
        }
    }
}
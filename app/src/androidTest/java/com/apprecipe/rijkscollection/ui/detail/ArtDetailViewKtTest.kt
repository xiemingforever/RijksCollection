package com.apprecipe.rijkscollection.ui.detail

import androidx.compose.material.Surface
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithText
import androidx.test.platform.app.InstrumentationRegistry
import com.apprecipe.rijkscollection.MainActivity
import com.apprecipe.rijkscollection.R
import com.apprecipe.rijkscollection.data.ArtDetail
import com.apprecipe.rijkscollection.data.WebImage
import com.apprecipe.rijkscollection.ui.theme.RijksCollectionTheme
import org.junit.Rule
import org.junit.Test

class ArtDetailViewKtTest {

    @get:Rule
    val composeTestRule = createAndroidComposeRule<MainActivity>()

    @Test
    fun artDetailViewTest() {
        val artDetail = ArtDetail(
            objectNumber = "SK-C-5",
            title = "The Night Watch",
            longTitle = "The Night Watch, 1642",
            webImage = WebImage(
                "bbd1fae8-4023-4859-8ed1-d38616aec96c",
                "https://lh3.googleusercontent.com/SsEIJWka3_cYRXXSE8VD3XNOgtOxoZhqW1uB6UFj78eg8gq3G4jAqL4Z_5KwA12aD7Leqp27F653aBkYkRBkEQyeKxfaZPyDx0O8CzWg=s0"
            ),
            descriptionEN = "Rembrandt’s largest, most famous canvas was made for the Arquebusiers guild hall. This was one of several halls of Amsterdam’s civic guard, the city’s militia and police. \r\nRembrandt was the first to paint figures in a group portrait actually doing something. The captain, dressed in black, is telling his lieutenant to start the company marching. The guardsmen are getting into formation. Rembrandt used the light to focus on particular details, like the captain’s gesturing hand and the young girl in the foreground. She was the company mascot.\r\n",
            descriptionNL = "Rembrandt’s largest, most famous canvas was made for the Arquebusiers guild hall. This was one of several halls of Amsterdam’s civic guard, the city’s militia and police. \r\nRembrandt was the first to paint figures in a group portrait actually doing something. The captain, dressed in black, is telling his lieutenant to start the company marching. The guardsmen are getting into formation. Rembrandt used the light to focus on particular details, like the captain’s gesturing hand and the young girl in the foreground. She was the company mascot.\r\n",
            subTitle = "Rembrandt van Rijn",
        )
        composeTestRule.setContent {
            RijksCollectionTheme {
                Surface {
                    ArtDetailsView(
                        artDetail
                    ) { }
                }
            }
        }

        val context = InstrumentationRegistry.getInstrumentation().targetContext
        composeTestRule.onNodeWithContentDescription(context.getString(R.string.content_description_art_photo_art_detail))
            .assertIsDisplayed()
        composeTestRule.onNodeWithText(artDetail.longTitle).assertIsDisplayed()
        composeTestRule.onNodeWithText(artDetail.subTitle).assertIsDisplayed()
        composeTestRule.onNodeWithText(artDetail.descriptionEN!!).assertIsDisplayed()
    }
}

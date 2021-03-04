package com.pedroluis.fastshoptest.features.catalog.view.activity

import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.ActivityTestRule
import com.pedroluis.fastshoptest.robot.CatalogRobot
import com.pedroluis.fastshoptest.robot.command
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class CatalogActivityTest {

    @get:Rule
    val rule = ActivityTestRule<CatalogActivity>(CatalogActivity::class.java)
    private val robot = CatalogRobot()

    @Test
    fun testScrolled_and_click() {
        robot.command {
            scrollToRight()
            clickOnItem()
            takeScreenShot("Catalog_scrolled")
        }
    }
}
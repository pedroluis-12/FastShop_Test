package com.pedroluis.fastshoptest.robot

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import com.pedroluis.fastshoptest.R
import com.pedroluis.fastshoptest.features.catalog.view.adapter.ViewHolderCatalog
import com.pedroluis.fastshoptest.utils.ViewActionUtils
import com.pedroluis.fastshoptest.utils.ViewActionUtils.clickItemList
import com.pedroluis.fastshoptest.utils.ViewActionUtils.scrollToPosition
import com.pedroluis.fastshoptest.utils.retryer
import org.hamcrest.core.AllOf.allOf

fun CatalogRobot.command(func: CatalogRobot.()-> Unit) = this.apply { func() }

class CatalogRobot {
    fun scrollToRight() {
       retryer {
            onView(allOf(withId(R.id.catalog_movies_list), isDisplayed()))
                .perform(scrollToPosition(3))
       }
    }

    fun clickOnItem() {
        retryer {
            onView(withId(R.id.catalog_card_item)).perform(click())
            onView(withId(R.id.catalog_movies_list)).perform(
                RecyclerViewActions.actionOnItemAtPosition<ViewHolderCatalog>(3,
                    clickItemList(R.id.catalog_card_item)))
        }
    }

    fun takeScreenShot(fileName: String) {
        retryer {
            onView(withId(R.id.detail_container)).perform(ViewActionUtils.takeScreenShot(fileName))
        }
    }
}
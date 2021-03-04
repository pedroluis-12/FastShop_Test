package com.pedroluis.fastshoptest.utils

import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.LinearSmoothScroller
import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.UiController
import androidx.test.espresso.ViewAction
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.isAssignableFrom
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import com.facebook.testing.screenshot.Screenshot.snap
import org.hamcrest.Matcher
import org.hamcrest.core.AllOf.allOf

object ViewActionUtils {

    fun scrollToPosition(position: Int): ViewAction =
        object : ViewAction {
            override fun getDescription(): String =
                String.format("swipe para o lado posição %d", position)

            override fun getConstraints(): Matcher<View> =
                allOf(
                    isAssignableFrom(RecyclerView::class.java),
                    ViewMatchers.isDisplayed()
                )

            override fun perform(uiController: UiController, view: View) {
                val context = view.context
                val recyclerView = view as RecyclerView
                val layoutManager = recyclerView.layoutManager as LinearLayoutManager
                val smoothScroller = object : LinearSmoothScroller(context) {
                    override fun getVerticalSnapPreference(): Int {
                        return SNAP_TO_END
                    }
                }
                smoothScroller.targetPosition = position
                layoutManager.startSmoothScroll(smoothScroller)
            }
        }

    fun takeScreenShot(fileName: String): ViewAction =
        object : ViewAction {
            override fun getDescription(): String = "tira o screenshot da view : $fileName"

            override fun getConstraints(): Matcher<View> =
                allOf(isAssignableFrom(View::class.java), isDisplayed())

            override fun perform(uiController: UiController?, view: View?) {
                snap(view).setName(fileName).record()
            }

        }

    fun clickItemList(id: Int): ViewAction =
        object : ViewAction {
            override fun getConstraints(): Matcher<View>? = null

            override fun getDescription(): String = "Click on a child view with specified id."

            override fun perform(uiController: UiController, view: View) {
                val v = view.findViewById<View>(id)
                v.performClick()
            }
        }
}

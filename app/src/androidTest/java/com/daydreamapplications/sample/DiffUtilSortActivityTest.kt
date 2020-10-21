package com.daydreamapplications.sample

import android.view.View
import androidx.test.espresso.Espresso.openActionBarOverflowOrOptionsMenu
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry.getInstrumentation
import com.agoda.kakao.recycler.KRecyclerItem
import com.agoda.kakao.recycler.KRecyclerView
import com.agoda.kakao.screen.Screen
import com.agoda.kakao.screen.Screen.Companion.onScreen
import com.agoda.kakao.text.KTextView
import org.hamcrest.Matcher
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class DiffUtilSortActivityTest {

    @get:Rule
    val activityRule: ActivityScenarioRule<DiffUtilSortActivity> = ActivityScenarioRule(DiffUtilSortActivity::class.java)

    @Test
    fun itemsOfOneType_correctlyDisplayedInRecyclerView() {
        // Check initial state of list
        onScreen<SortingScreen> {
            recycler {
                childAt<SortingScreen.Item>(0) {
                    text.hasText("Hello")
                }
                childAt<SortingScreen.Item>(1) {
                    text.hasText("World")
                }
                childAt<SortingScreen.Item>(2) {
                    text.hasText("What")
                }
                childAt<SortingScreen.Item>(3) {
                    text.hasText("A")
                }
                childAt<SortingScreen.Item>(4) {
                    text.hasText("Nice")
                }
                childAt<SortingScreen.Item>(5) {
                    text.hasText("Day")
                }
            }

            // Select Alphabetical from menu
            openActionBarOverflowOrOptionsMenu(getInstrumentation().targetContext)
            alphabeticalMenuItem.click()

            // Verify new state of list
            recycler {
                childAt<SortingScreen.Item>(0) {
                    text.hasText("A")
                }
                childAt<SortingScreen.Item>(1) {
                    text.hasText("Day")
                }
                childAt<SortingScreen.Item>(2) {
                    text.hasText("Hello")
                }
                childAt<SortingScreen.Item>(3) {
                    text.hasText("Nice")
                }
                childAt<SortingScreen.Item>(4) {
                    text.hasText("What")
                }
                childAt<SortingScreen.Item>(5) {
                    text.hasText("World")
                }
            }
        }
    }
}

private class SortingScreen : Screen<SortingScreen>() {

    val recycler: KRecyclerView = KRecyclerView({
        withId(R.id.recycler_view)
    }, itemTypeBuilder = {
        itemType(::Item)
    })

    val alphabeticalMenuItem: KTextView = KTextView { withText("Alphabetical") }

    class Item(parent: Matcher<View>) : KRecyclerItem<Item>(parent) {
        val text: KTextView = KTextView(parent) { withId(R.id.textView) }
    }
}


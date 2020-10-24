package com.daydreamapplications.sample

import android.view.View
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
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
class DiffUtilAddRemoveActivityTest {

    @get:Rule
    val activityRule: ActivityScenarioRule<DiffUtilAddRemoveActivity> = ActivityScenarioRule(DiffUtilAddRemoveActivity::class.java)

    @Test
    fun itemsOfOneType_correctlyDisplayedInRecyclerView() {
        // Check initial state of list
        onScreen<DiffUtilAddRemoveScreen> {
            recycler {
                childAt<DiffUtilAddRemoveScreen.Item>(0) {
                    text.hasText("0")
                }
                childAt<DiffUtilAddRemoveScreen.Item>(1) {
                    text.hasText("1")
                }
                childAt<DiffUtilAddRemoveScreen.Item>(2) {
                    text.hasText("2")
                }
                childAt<DiffUtilAddRemoveScreen.Item>(3) {
                    text.hasText("3")
                }
            }
        }
    }
}

private class DiffUtilAddRemoveScreen : Screen<DiffUtilAddRemoveScreen>() {

    val recycler: KRecyclerView = KRecyclerView({
        withId(R.id.recycler_view)
    }, itemTypeBuilder = {
        itemType(::Item)
    })

    class Item(parent: Matcher<View>) : KRecyclerItem<Item>(parent) {
        val text: KTextView = KTextView(parent) { withId(R.id.textView) }
    }
}


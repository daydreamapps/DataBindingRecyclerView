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
class SingleItemTypeAsyncActivityTest {

    @get:Rule
    val activityRule: ActivityScenarioRule<SingleItemTypeAsyncActivity> = ActivityScenarioRule(SingleItemTypeAsyncActivity::class.java)

    @Test
    fun itemsOfOneType_correctlyDisplayedInRecyclerView() {
        Thread.sleep(1000)

        onScreen<SingleItemTypeAsyncScreen> {
            recycler {
                childAt<SingleItemTypeAsyncScreen.Item>(0) {
                    isVisible()
                    text.hasText("Loading...")
                }
                childAt<SingleItemTypeAsyncScreen.Item>(1) {
                    isVisible()
                    text.hasAnyText() //
                }
                childAt<SingleItemTypeAsyncScreen.Item>(2) {
                    isVisible()
                    text.hasAnyText()
                }
            }
        }

        Thread.sleep(2000)

        onScreen<SingleItemTypeAsyncScreen> {
            recycler {
                childAt<SingleItemTypeAsyncScreen.Item>(0) {
                    isVisible()
                    text.hasText("Loaded")
                }
            }
        }
    }
}

private class SingleItemTypeAsyncScreen : Screen<SingleItemTypeAsyncScreen>() {

    val recycler: KRecyclerView = KRecyclerView({
        withId(R.id.recycler_view)
    }, itemTypeBuilder = {
        itemType(::Item)
    })

    class Item(parent: Matcher<View>) : KRecyclerItem<Item>(parent) {
        val text: KTextView = KTextView(parent) { withId(R.id.textView) }
    }
}


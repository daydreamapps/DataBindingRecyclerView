package com.daydreamapplications.sample

import android.view.View
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.agoda.kakao.recycler.KRecyclerItem
import com.agoda.kakao.recycler.KRecyclerView
import com.agoda.kakao.screen.Screen
import com.agoda.kakao.screen.Screen.Companion.onScreen
import com.agoda.kakao.text.KButton
import com.agoda.kakao.text.KTextView
import org.hamcrest.Matcher
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import kotlin.random.Random

@RunWith(AndroidJUnit4::class)
class DiffUtilAddRemoveRandomActivityTest {

    @get:Rule
    val activityRule: ActivityScenarioRule<DiffUtilAddRemoveRandomActivity> = ActivityScenarioRule(DiffUtilAddRemoveRandomActivity::class.java)

    private val dummyRandom: Random = object : Random() {

        // Always return 2
        override fun nextInt(until: Int): Int = 2
        override fun nextBits(bitCount: Int): Int = TODO()
    }

    @Test
    fun addItem_addsRandomItemToList() {
        activityRule.scenario.onActivity {
            it.random = dummyRandom // Replace random with dummy which will return 2
        }

        // Check initial state of list
        onScreen<DiffUtilAddRemoveRandomScreen> {
            recycler {
                hasSize(4)

                childAt<DiffUtilAddRemoveRandomScreen.Item>(0) {
                    text.hasText("0")
                }
                childAt<DiffUtilAddRemoveRandomScreen.Item>(1) {
                    text.hasText("1")
                }
                childAt<DiffUtilAddRemoveRandomScreen.Item>(2) {
                    text.hasText("2")
                }
                childAt<DiffUtilAddRemoveRandomScreen.Item>(3) {
                    text.hasText("3")
                }
            }

            addItemButton.click()

            recycler {
                hasSize(5)

                childAt<DiffUtilAddRemoveRandomScreen.Item>(0) {
                    text.hasText("0")
                }
                childAt<DiffUtilAddRemoveRandomScreen.Item>(1) {
                    text.hasText("1")
                }
                childAt<DiffUtilAddRemoveRandomScreen.Item>(2) {
                    text.hasText("2")
                }
                childAt<DiffUtilAddRemoveRandomScreen.Item>(3) {
                    text.hasText("2")
                }
                childAt<DiffUtilAddRemoveRandomScreen.Item>(4) {
                    text.hasText("3")
                }
            }
        }
    }

    @Test
    fun removeItem_removesRandomItemFromList() {
        activityRule.scenario.onActivity {
            it.random = dummyRandom // Replace random with dummy which will return 2
        }

        // Check initial state of list
        onScreen<DiffUtilAddRemoveRandomScreen> {
            recycler {
                hasSize(4)

                childAt<DiffUtilAddRemoveRandomScreen.Item>(0) {
                    text.hasText("0")
                }
                childAt<DiffUtilAddRemoveRandomScreen.Item>(1) {
                    text.hasText("1")
                }
                childAt<DiffUtilAddRemoveRandomScreen.Item>(2) {
                    text.hasText("2")
                }
                childAt<DiffUtilAddRemoveRandomScreen.Item>(3) {
                    text.hasText("3")
                }
            }

            removeItemButton.click()

            recycler {
                hasSize(3)

                childAt<DiffUtilAddRemoveRandomScreen.Item>(0) {
                    text.hasText("0")
                }
                childAt<DiffUtilAddRemoveRandomScreen.Item>(1) {
                    text.hasText("1")
                }
                childAt<DiffUtilAddRemoveRandomScreen.Item>(2) {
                    text.hasText("3")
                }
            }
        }
    }
}

private class DiffUtilAddRemoveRandomScreen : Screen<DiffUtilAddRemoveRandomScreen>() {

    val addItemButton: KButton = KButton { withId(R.id.button_add_item) }
    val removeItemButton: KButton = KButton { withId(R.id.button_remove_item) }

    val recycler: KRecyclerView = KRecyclerView({
        withId(R.id.recycler_view)
    }, itemTypeBuilder = {
        itemType(::Item)
    })

    class Item(parent: Matcher<View>) : KRecyclerItem<Item>(parent) {
        val text: KTextView = KTextView(parent) { withId(R.id.textView) }
    }
}


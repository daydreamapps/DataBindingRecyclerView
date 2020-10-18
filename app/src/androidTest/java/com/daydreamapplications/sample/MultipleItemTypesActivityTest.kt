package com.daydreamapplications.sample

import android.view.View
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.agoda.kakao.image.KImageView
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
class MultipleItemTypesActivityTest {

    @get:Rule
    val activityRule: ActivityScenarioRule<MultipleItemTypesActivity> = ActivityScenarioRule(MultipleItemTypesActivity::class.java)

    @Test
    fun itemsOfOneType_correctlyDisplayedInRecyclerView() {
        Thread.sleep(1000)

        onScreen<MultipleItemTypesScreen> {
            recycler {
                childAt<MultipleItemTypesScreen.TitleWithIcon>(0) {
                    isVisible()
                    icon { hasDrawable(R.drawable.circle) }
                    title { hasText("Hello") }
                }
                childAt<MultipleItemTypesScreen.Title>(1) {
                    isVisible()
                    title { hasText("World") }
                }
            }
        }
    }
}

private class MultipleItemTypesScreen : Screen<MultipleItemTypesScreen>() {

    val recycler: KRecyclerView = KRecyclerView(
        builder = {
            withId(R.id.recycler_view)
        },
        itemTypeBuilder = {
            itemType(::Title)
            itemType(::TitleWithIcon)
        }
    )

    class Title(parent: Matcher<View>) : KRecyclerItem<Title>(parent) {
        val title: KTextView = KTextView(parent) { withId(R.id.title) }
    }

    class TitleWithIcon(parent: Matcher<View>) : KRecyclerItem<TitleWithIcon>(parent) {
        val icon: KImageView = KImageView(parent) { withId(R.id.icon) }
        val title: KTextView = KTextView(parent) { withId(R.id.title) }
    }
}


package com.daydreamapplications.sample

import android.widget.TextView
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.withParent
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.agoda.kakao.screen.Screen
import com.agoda.kakao.screen.Screen.Companion.onScreen
import com.agoda.kakao.text.KButton
import com.agoda.kakao.text.KTextView
import org.hamcrest.CoreMatchers.allOf
import org.hamcrest.CoreMatchers.instanceOf
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class MainActivityTest {

    @get:Rule
    val activityRule: ActivityScenarioRule<MainActivity> = ActivityScenarioRule(MainActivity::class.java)

    @Test
    fun titleText_isCorrect() {
        onScreen<MainActivityScene> {
            toolbar.hasText(R.string.app_name)
        }
    }

    @Test
    fun clickSingleItemTypeButton_openSingleItemTypeActivity_seeTitleAndRecycler() {
        onScreen<MainActivityScene> {
            singleItemTypeButton.click()
            toolbar.hasText(R.string.single_item_type)
        }
    }

    @Test
    fun clickSingleItemTypeAsyncButton_openSingleItemTypeAsyncActivity_seeTitleAndRecycler() {
        onScreen<MainActivityScene> {
            singleItemTypeAsync.click()
            toolbar.hasText(R.string.single_item_type_async)
        }
    }

    @Test
    fun clickMultipleItemTypesButton_openMultipleItemTypesActivity_seeTitleAndRecycler() {
        onScreen<MainActivityScene> {
            multipleItemTypesButton.click()
            toolbar.hasText(R.string.multiple_item_types)
        }
    }

    @Test
    fun clickDiffUtilSortingButton_openSortingActivity_seeTitleAndRecycler() {
        onScreen<MainActivityScene> {
            diffUtilSortingButton.click()
            toolbar.hasText(R.string.diff_util_sorting)
        }
    }

    @Test
    fun clickDiffUtilAddRemoveButton_openSortingActivity_seeTitleAndRecycler() {
        onScreen<MainActivityScene> {
            diffUtilAddRemoveButton.click()
            toolbar.hasText(R.string.diff_util_add_remove)
        }
    }
}

private class MainActivityScene : Screen<MainActivityScene>() {

    val singleItemTypeButton: KButton = KButton { withId(R.id.button_single_item_type) }
    val singleItemTypeAsync: KButton = KButton { withId(R.id.button_single_item_type_async) }
    val multipleItemTypesButton: KButton = KButton { withId(R.id.button_multiple_item_types) }
    val diffUtilSortingButton: KButton = KButton { withId(R.id.button_diff_util_sorting) }
    val diffUtilAddRemoveButton: KButton = KButton { withId(R.id.button_diff_util_add_remove) }

    val toolbar: KTextView = KTextView {
        withMatcher(
            allOf(
                instanceOf(TextView::class.java),
                withParent(ViewMatchers.withResourceName("action_bar"))
            )
        )
    }
}
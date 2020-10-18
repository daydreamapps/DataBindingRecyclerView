package com.daydreamapplications.sample

import android.widget.TextView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.hamcrest.CoreMatchers.allOf
import org.hamcrest.CoreMatchers.instanceOf
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
class SampleActivityTest {

    @get:Rule
    val activityRule: ActivityScenarioRule<SampleActivity> =
        ActivityScenarioRule(SampleActivity::class.java)

    @Before
    fun initValidString() {
    }

    @Test
    fun titleText_isCorrect() {
        onView(allOf(instanceOf(TextView::class.java), withParent(withResourceName("action_bar"))))
            .check(matches(withText(R.string.app_name)))
    }

    @Test
    fun clickSingleItemTypeButton_openSingleItemTypeActivity_seeTitleAndRecycler() {
        onView(withId(R.id.button_single_item_type))
            .perform(click())


        onView(allOf(instanceOf(TextView::class.java), withParent(withResourceName("action_bar"))))
            .check(matches(withText(R.string.single_item_type)))

        onView(withId(R.id.recycler_view)).check(matches(isDisplayed()));
    }

    @Test
    fun clickMultipleItemTypesButton_openMultipleItemTypesActivity_seeTitleAndRecycler() {
        onView(withId(R.id.button_multiple_item_types))
            .perform(click())


        onView(allOf(instanceOf(TextView::class.java), withParent(withResourceName("action_bar"))))
            .check(matches(withText(R.string.multiple_item_types)))

        onView(withId(R.id.recycler_view)).check(matches(isDisplayed()));
    }
}
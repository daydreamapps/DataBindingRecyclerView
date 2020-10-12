package com.daydreamapplications.sample

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class SampleActivityTest {

    @get:Rule
    val activityRule: ActivityScenarioRule<SampleActivity> = ActivityScenarioRule(SampleActivity::class.java)

    @Before
    fun initValidString() {
    }

    @Test
    fun titleText_isCorrect() {
        // Check that the text was changed.
        onView(withId(R.id.toolbar)).check(matches(isDisplayed()));
        onView(withText("BindingRecycler Sample")).check(matches(withParent(withId(R.id.toolbar))));
    }
}
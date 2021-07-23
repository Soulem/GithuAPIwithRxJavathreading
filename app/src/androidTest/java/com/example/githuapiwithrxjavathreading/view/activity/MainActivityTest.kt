package com.example.githuapiwithrxjavathreading.view.activity

import androidx.test.espresso.Espresso
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.rule.ActivityTestRule
import com.example.githuapiwithrxjavathreading.R
import org.junit.Rule
import org.junit.Test

class MainActivityTest {

    val input = "AlexisYA703"

    @JvmField
    @Rule
    val testRule = ActivityTestRule(MainActivity::class.java)

//    @Rule
//    val activityScenario: ActivityScenario<MainActivity> = ActivityScenario.launch(MainActivity::class.java)

    @Test
    fun testSearchUserInput() {
        Espresso.onView(withId(R.id.user_name_editText)).perform(ViewActions.typeText(input))
        Espresso.onView(withId(R.id.user_name_editText)).check(matches(withText(input)))
    }

    @Test
    fun testSearchUserButton() {
        Espresso.onView(withId(R.id.user_name_editText)).perform(ViewActions.typeText(input))
        Espresso.closeSoftKeyboard()
        Espresso.onView(withId(R.id.search_user_button)).perform(click())
    }

}
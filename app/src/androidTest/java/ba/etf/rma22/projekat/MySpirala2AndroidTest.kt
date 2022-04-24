package ba.etf.rma22.projekat

import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.hamcrest.CoreMatchers
import org.hamcrest.core.Is
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class MySpirala2AndroidTest {

    @get:Rule
    val intentsTestRule = ActivityScenarioRule<MainActivity>(MainActivity::class.java)

    @Test
    fun testUpisIstrazivanja(){
        Espresso.onView(withId(R.id.pager)).perform(ViewPager2Actions.scrollToPosition(1))
        Espresso.onView(withId(R.id.odabirGodina)).perform(ViewActions.click())

        Espresso.onData(
            CoreMatchers.allOf(
                Is(CoreMatchers.instanceOf(String::class.java)),
                CoreMatchers.`is`("1")
            )
        ).perform(ViewActions.click())
        Espresso.onView(withId(R.id.odabirIstrazivanja)).perform(ViewActions.click())
        Espresso.onData(
            CoreMatchers.allOf(
                Is(CoreMatchers.instanceOf(String::class.java)),
                CoreMatchers.`is`("Istrazivanje 3")
            )
        ).perform(ViewActions.click())
        Espresso.onView(withId(R.id.odabirGrupa)).perform(ViewActions.click())
        Espresso.onData(
            CoreMatchers.allOf(
                Is(CoreMatchers.instanceOf(String::class.java)),
                CoreMatchers.`is`("I3 G2")
            )
        ).perform(ViewActions.click())

        Espresso.onView(withId(R.id.dodajIstrazivanjeDugme)).perform(ViewActions.click())
        Espresso.onView(withSubstring("Uspješno ste upisani"))
            .check(ViewAssertions.matches(isDisplayed()))

        Espresso.onView(withId(R.id.pager)).perform(ViewPager2Actions.scrollToPosition(0))

        Espresso.onView(withId(R.id.listaAnketa)).perform(
            RecyclerViewActions.scrollTo<RecyclerView.ViewHolder>(
                CoreMatchers.`is`(
                    hasDescendant(withSubstring("I3 G2"))
                )
            )
        )
    }
    @Test
    fun testZaustaviAnketu(){

    }
}
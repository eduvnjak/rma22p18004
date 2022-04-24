package ba.etf.rma22.projekat

import android.view.View.VISIBLE
import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.onData
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import ba.etf.rma22.projekat.data.repositories.AnketaRepository
import org.hamcrest.CoreMatchers
import org.hamcrest.Matchers.allOf
import org.hamcrest.Matchers.anything
import org.hamcrest.core.Is
import org.junit.Assert
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class MySpirala2AndroidTest {

    @get:Rule
    val intentsTestRule = ActivityScenarioRule(MainActivity::class.java)

    @Test
    fun testUpisIstrazivanja(){
        onView(withId(R.id.pager)).perform(ViewPager2Actions.scrollToPosition(1))
        onView(withId(R.id.odabirGodina)).perform(click())

        onData(
            CoreMatchers.allOf(
                Is(CoreMatchers.instanceOf(String::class.java)),
                CoreMatchers.`is`("1")
            )
        ).perform(click())
        onView(withId(R.id.odabirIstrazivanja)).perform(click())
        onData(
            CoreMatchers.allOf(
                Is(CoreMatchers.instanceOf(String::class.java)),
                CoreMatchers.`is`("Istrazivanje 3")
            )
        ).perform(click())
        onView(withId(R.id.odabirGrupa)).perform(click())
        onData(
            CoreMatchers.allOf(
                Is(CoreMatchers.instanceOf(String::class.java)),
                CoreMatchers.`is`("I3 G2")
            )
        ).perform(click())

        onView(withId(R.id.dodajIstrazivanjeDugme)).perform(click())
        onView(withSubstring("Uspješno ste upisani"))
            .check(ViewAssertions.matches(isDisplayed()))

        onView(withId(R.id.pager)).perform(ViewPager2Actions.scrollToPosition(0))

        onView(withId(R.id.listaAnketa)).perform(
            RecyclerViewActions.scrollTo<RecyclerView.ViewHolder>(
                CoreMatchers.`is`(
                    hasDescendant(withSubstring("I3 G2"))
                )
            )
        )
    }
    @Test
    fun testZaustaviAnketu(){
        onView(withId(R.id.pager)).perform(ViewPager2Actions.scrollToFirst())

        var ankete = AnketaRepository.getMyAnkete()
        val pocetniProgres = ankete[0].progres
        val pocetniStatus = ankete[0].dajStatusAnkete()

        onView(withId(R.id.listaAnketa)).perform(RecyclerViewActions.actionOnItem<RecyclerView.ViewHolder>(CoreMatchers.allOf(hasDescendant(withText(ankete[0].naziv)),
            hasDescendant(withText(ankete[0].nazivIstrazivanja))), click()
        ))
        onView(withId(R.id.odgovoriLista)).perform(click())
        onData(
            CoreMatchers.allOf(
                CoreMatchers.`is`(CoreMatchers.instanceOf(String::class.java)),
                CoreMatchers.`is`("opcija 1"))
            ).perform(click())
        onView(withId(R.id.pager)).perform(ViewPager2Actions.scrollToPosition(1))

        onView(allOf(
            withId(R.id.odgovoriLista),
            isDisplayed()
        )).perform(click())
        onData(anything())
            .inAdapterView(allOf(withId(R.id.odgovoriLista), isCompletelyDisplayed()))
            .atPosition(0).perform(click())
        onView(withId(R.id.pager)).perform(ViewPager2Actions.scrollToPosition(2))

        onView(allOf(
            withId(R.id.odgovoriLista),
            isDisplayed())).perform(click())
        onData(anything())
            .inAdapterView(allOf(withId(R.id.odgovoriLista), isCompletelyDisplayed()))
            .atPosition(1).perform(click())
        onView(withId(R.id.pager)).perform(ViewPager2Actions.scrollToPosition(3))

        onView(allOf(
            withId(R.id.odgovoriLista),
            isDisplayed())).perform(click())
        onData(anything())
            .inAdapterView(allOf(withId(R.id.odgovoriLista), isCompletelyDisplayed()))
            .atPosition(4).perform(click())
        onView(withId(R.id.pager)).perform(ViewPager2Actions.scrollToPosition(4))

        onView(allOf(
            withId(R.id.odgovoriLista),
            isDisplayed())).perform(click())
        onData(anything())
            .inAdapterView(allOf(withId(R.id.odgovoriLista), isCompletelyDisplayed()))
            .atPosition(1).perform(click())
        onView(withId(R.id.pager)).perform(ViewPager2Actions.scrollToPosition(5))

        onView(allOf(
            withId(R.id.odgovoriLista),
            isDisplayed())).perform(click())
        onData(anything())
            .inAdapterView(allOf(withId(R.id.odgovoriLista), isCompletelyDisplayed()))
            .atPosition(1).perform(click())

        onView(withId(R.id.dugmeZaustavi)).perform(click())

        ankete = AnketaRepository.getMyAnkete()
        val noviProgres = ankete[0].progres
        val noviStatus = ankete[0].dajStatusAnkete()

        Assert.assertTrue(pocetniStatus == noviStatus)
        Assert.assertTrue(noviProgres != pocetniProgres)
        Assert.assertTrue(noviProgres == 1f)
    }
}
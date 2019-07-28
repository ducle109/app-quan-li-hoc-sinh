package jp.com.studentproject;



import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;


@RunWith(RobolectricTestRunner.class)

public class EspressoDemoTest {
    private EspressoDemo espressoDemo;
    @Rule


    @Before
    public void setUp() throws Exception {
        espressoDemo = Robolectric.buildActivity(EspressoDemo.class).create().get();
    }

    @Test
    public void customDialog() {
    }

}
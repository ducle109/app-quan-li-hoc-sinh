package jp.com.studentproject;

import android.widget.Button;
import android.widget.EditText;
import android.widget.TableLayout;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;

import static org.junit.Assert.*;
@RunWith(RobolectricTestRunner.class)
public class School_ScheduleTest {
    private School_Schedule schollActivity;
    private Button mBtnModify;
    private TableLayout tbLayoutStudent;
    private EditText edtText;

    @Before
    public void setUp() throws Exception {
        schollActivity = Robolectric.buildActivity(School_Schedule.class).create().get();

        mBtnModify = (Button) schollActivity.findViewById(R.id.btnModify);
        edtText = (EditText) schollActivity.findViewById(R.id.edtText);
        tbLayoutStudent      = (TableLayout) schollActivity.findViewById(R.id.tbLayoutStudent);
    }

    @Test
    public void onCreate() {
    }

    @Test
    public void onClick() {
    }

    @Test
    public void getShowData() {

    }

    @Test
    public void init() {
    }

    @Test
    public void showToast() {
    }
}
package jp.com.studentproject;

import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import java.util.List;
import java.util.Objects;

import static jp.com.studentproject.R.drawable;
import static jp.com.studentproject.R.id;
import static jp.com.studentproject.R.layout;

public class School_Schedule extends AppCompatActivity implements View.OnClickListener {

    private DatabaseSQLite databaseSQLite;
    private List<Schoole_Time> studentList;

    private Button btnModify;
    private Button btnCancel;
    private EditText edtText;
    private TableLayout tbLayoutStudent;
    private int idSchool;
    private int setIdButton;
    private int[][] data = new int[7][7];

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(layout.activity_school__schedule);
        init();

        databaseSQLite = new DatabaseSQLite(this);
        databaseSQLite.createDefaultNotesIfNeed();
        studentList =  databaseSQLite.getAllSchooleTime();
        getShowData();
        btnModify.setEnabled(false);
        btnModify.setOnClickListener(this);

    }

    // click edit time
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public void onClick(View view) {
        //hidenKeyBoard();
        String getText = edtText.getText().toString();
        Schoole_Time schooleTime = new Schoole_Time();
        schooleTime.setId((idSchool/10)+1);
        switch (idSchool%10) {
            case 0:
                schooleTime.setSession(getText);
                break;
            case 1:
                schooleTime.setTime(getText);
                break;
            case 2:
                schooleTime.setSubjects_monday(getText);
                break;
            case 3:
                schooleTime.setSubjects_tuesday(getText);
                break;
            case 4:
                schooleTime.setSubjects_wednesday(getText);
                break;
            case 5:
                schooleTime.setSubjects_thursday(getText);
                break;
            case 6:
                schooleTime.setSubjects_friday(getText);
                break;
        }
        int result = databaseSQLite.updateStudent(schooleTime, idSchool % 10);
        showToast("Updated! " + getText );
        Button button = (Button) findViewById(idSchool);
        button.setText(getText);
        button.setTextColor(Color.parseColor("#F3000000"));
        edtText.setText("");
        btnModify.setEnabled(false);
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    public void getShowData() {
        for(int i = 0; i < 7; i++) {
            TableRow tableRow = new TableRow(this);
            for(int j = 0; j < 7; j++) {
                Schoole_Time schooleTime = studentList.get(i);
                final Button button = new Button(this);
                data[i][j] = 10*i + j;
                //Log.d("checkMsg",(10*i + j) + "");
                // id 行
                button.setId(data[i][j]);
                switch (j) {
                    case 0:
                        button.setText(schooleTime.getSession());
                        break;
                    case 1:
                        button.setHeight(50);
                        button.setTextSize(11);
                        button.setTranslationY(-20);
                        button.setText(schooleTime.getTime());
                        break;
                    case 2:
                        button.setText(schooleTime.getSubjects_monday());
                        break;
                    case 3:
                        button.setText(schooleTime.getSubjects_tuesday());
                        break;
                    case 4:
                        button.setText(schooleTime.getSubjects_wednesday());
                        break;
                    case 5:
                        button.setText(schooleTime.getSubjects_thursday());
                        break;
                    case 6:
                        button.setText(schooleTime.getSubjects_friday());
                        break;
                }
                button.setBackground(getResources().getDrawable(drawable.custom_button));

                tableRow.addView(button);
                button.setOnLongClickListener(new View.OnLongClickListener() {
                    @Override
                    public boolean onLongClick(View view) {
                        btnModify.setEnabled(true);
                        Button bt = (Button) view;
                        String str = bt.getText().toString();
                        idSchool = bt.getId();
                        Button bt1 = (Button) findViewById(setIdButton);
                        bt1.setTextColor(Color.parseColor("#F3000000"));
                        bt.setTextColor(Color.parseColor("#ED0A57"));
                        edtText.setText(str);
                        setIdButton = idSchool;
                        return false;
                    }
                });

            }
            tbLayoutStudent.addView(tableRow);
        }
    }

    public void clickCancel(View view) {
        Button bt = (Button) findViewById(idSchool);
        //hidenKeyBoard();
        edtText.setText("");
        bt.setTextColor(Color.parseColor("#F3000000"));
        btnModify.setEnabled(false);
    }

    public void init() {
        btnModify             = (Button) findViewById(id.btnModify);
        btnCancel             = (Button) findViewById(id.btnCancel);
        edtText               = (EditText) findViewById(id.edtText);
        tbLayoutStudent      = (TableLayout) findViewById(id.tbLayoutStudent);
    }

    public void showKeyboard() {
        InputMethodManager inputMethod = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        inputMethod.showSoftInput(this.getCurrentFocus(), InputMethodManager.SHOW_IMPLICIT);
    }

    public void hidenKeyBoard() {
        InputMethodManager inputMethod = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        inputMethod.hideSoftInputFromWindow(Objects.requireNonNull(this.getCurrentFocus()).getWindowToken(), 0);
    }

    public void showToast(String msg) {
        Toast.makeText(School_Schedule.this, msg, Toast.LENGTH_SHORT).show();
    }
}
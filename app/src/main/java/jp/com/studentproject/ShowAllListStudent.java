package jp.com.studentproject;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.io.ByteArrayOutputStream;
import java.util.List;

public class ShowAllListStudent extends AppCompatActivity implements View.OnClickListener {
    TextView txtname;
    TextView txtSex;
    TextView txtAge;
    TextView txtYear;
    TextView txtPhone;
    TextView txtClass;
    TextView txtChairman;
    TextView txtHobby;
    TextView txtGrade;
    EditText edtYear;
    EditText edtPhone;
    EditText edtClass;
    EditText edtChairman;
    EditText edtHobby;
    EditText edtGrade;
    private ImageView imgAvatar;
    private Button btnDialogSave;
    private Button btnDialogCancel;
    private Dialog dialog1;
    private Dialog dialog2;
    private ListView lvShowStudent;
    public static DatabaseSQLite databaseSQLite;
    private CustomAdapter customAdapter;
    private List<Student> studentList;

    private int getID;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_all_list_student);
        init();
        databaseSQLite = new DatabaseSQLite(this);
        studentList = databaseSQLite.getAllStudent();
        setAdapter();

        lvShowStudent.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int position, long l) {
                Student student = studentList.get(position);
                showEditDialog(student);
                return true;
            }
        });

        lvShowStudent.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                Student student = studentList.get(position);
                showInformationDialog(student);
            }
        });
    }


    public byte[] ConverttoArrayByte(ImageView img) {
        BitmapDrawable bitmapDrawable = (BitmapDrawable) img.getDrawable();
        Bitmap bitmap = bitmapDrawable.getBitmap();

        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
        return stream.toByteArray();
    }

    public void showEditDialog(Student student) {
        dialog1 = new Dialog(ShowAllListStudent.this, R.style.Dialog);
        dialog1.setTitle("Notification");
        dialog1.setContentView(R.layout.custom_dialog);

        // show text information
        getShowEditInformation(student);

        imgAvatar.setOnClickListener(this);
        btnDialogSave.setOnClickListener(this);

        // cancel
        btnDialogCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog1.dismiss();
            }
        });

        dialog1.show();
    }

    public void showInformationDialog(Student student) {
        dialog2 = new Dialog(this, R.style.Dialog);
        dialog2.setTitle("Information");
        dialog2.setContentView(R.layout.dialog_show_information);

        getShowInformation(student);
        dialog2.show();
    }


    @Override
    public void onClick(View view) {
        Student student = new Student();
        student.setId(getID);
        student.setDate(edtYear.getText().toString());
        student.setPhoneNumber(edtPhone.getText().toString());
        student.setStClass(edtClass.getText().toString());
        student.setChairman(edtChairman.getText().toString());
        student.setHobby(edtHobby.getText().toString());
        student.setGrade(edtGrade.getText().toString());
        databaseSQLite.updateStudentInformation(student);
        studentList.clear();
        studentList.addAll(databaseSQLite.getAllStudent());
        customAdapter.notifyDataSetChanged();
        dialog1.dismiss();
        showToast("save");
    }    // create student information


    @SuppressLint("SetTextI18n")
    public void getShowEditInformation(Student student) {
        txtname   = (TextView) dialog1.findViewById(R.id.txt_show_name);
        txtSex    = (TextView) dialog1.findViewById(R.id.txt_show_sex);
        txtAge    = (TextView) dialog1.findViewById(R.id.txt_show_age);

        edtYear = (EditText) dialog1.findViewById(R.id.edt_year);
        edtPhone = (EditText) dialog1.findViewById(R.id.edt_phone);
        edtClass = (EditText) dialog1.findViewById(R.id.edt_class);
        edtChairman = (EditText) dialog1.findViewById(R.id.edt_chairman);
        edtHobby = (EditText) dialog1.findViewById(R.id.edt_hobby);
        edtGrade = (EditText) dialog1.findViewById(R.id.edt_grade);

        // edt text
        imgAvatar        = (ImageView) dialog1.findViewById(R.id.imgAvatar);
        btnDialogSave   = (Button) dialog1.findViewById(R.id.btn_dialog_save);
        btnDialogCancel = (Button) dialog1.findViewById(R.id.btn_dialog_cancel);

        txtname.setText(student.getName());
        txtSex.setText(student.getSex());
        txtAge.setText(student.getAge() + "");
        edtYear.setText(student.getDate());
        edtPhone.setText(student.getPhoneNumber());
        edtClass.setText(student.getStClass());
        edtChairman.setText(student.getChairman());
        edtHobby.setText(student.getHobby());
        edtGrade.setText(student.getGrade());

        getID = student.getId();
    }

    @SuppressLint("SetTextI18n")
    public void getShowInformation(Student student) {
        txtname = (TextView) dialog2.findViewById(R.id.txt_get_name);
        txtSex = (TextView) dialog2.findViewById(R.id.txt_get_sex);
        txtAge = (TextView) dialog2.findViewById(R.id.txt_get_age);
        txtYear = (TextView) dialog2.findViewById(R.id.txt_get_year);
        txtPhone = (TextView) dialog2.findViewById(R.id.txt_get_phone);
        txtClass = (TextView) dialog2.findViewById(R.id.txt_get_class);
        txtChairman = (TextView) dialog2.findViewById(R.id.txt_get_chairman);
        txtHobby = (TextView) dialog2.findViewById(R.id.txt_get_hobby);
        txtGrade = (TextView) dialog2.findViewById(R.id.txt_get_grade);

        txtname.setText(student.getName());
        txtSex.setText(student.getSex());
        txtAge.setText(student.getAge() + "");
        txtYear.setText(student.getDate());
        txtPhone.setText(student.getPhoneNumber());
        txtClass.setText(student.getStClass());
        txtChairman.setText(student.getChairman());
        txtHobby.setText(student.getHobby());
        txtGrade.setText(student.getGrade());

    }


    public void setAdapter() {
        if (customAdapter == null) {
            customAdapter = new CustomAdapter(this, R.layout.activity_show_all_list_student, studentList);
            lvShowStudent.setAdapter(customAdapter);
        } else {
            customAdapter.notifyDataSetChanged();
            lvShowStudent.setSelection(customAdapter.getCount() - 1);
        }
    }

    public void init() {

        lvShowStudent = (ListView) findViewById(R.id.lvShowStudent);
    }

    public void showToast(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
        //toast.setGravity(Gravity.TOP|Gravity.LEFT, 0, 0);
    }


}

/*
public Student createStudentInformation() {
        String date = "";
        String stClass = "";
        String chairman = "";
        String hobby = "";
        String grade = "";
        Student student = null;
        String ckDate = edtYear.getText().toString();
        String ckStClass = edtClass.getText().toString();
        String ckChairman = edtChairman.getText().toString();
        String ckHobby = edtHobby.getText().toString();
        String ckGrade = edtGrade.getText().toString();
        if (ckDate.isEmpty()) {
            showToast("Please enter the Date");
        } else {
            date = ckDate;
        }
        if (ckStClass.isEmpty()) {
            showToast("Please enter the Class");
        } else {
            stClass = ckStClass;
        }
        if (ckChairman.isEmpty()) {
            showToast("Please enter the Chairman");
        } else {
            chairman = ckChairman;
        }
        if (ckHobby.isEmpty()) {
            showToast("Please enter the Hobby");
        } else {
            hobby = ckHobby;
        }
        if (ckGrade.isEmpty()) {
            showToast("Please enter the Grade");
        } else {
            grade = ckGrade;
        }
        if (!date.isEmpty() && !stClass.isEmpty() && !chairman.isEmpty() && !hobby.isEmpty() && !grade.isEmpty()) {
            student = new Student(date, stClass, chairman, hobby, grade);
        } else {
            showToast("Please complete all information");
        }
        return student;
    }*/
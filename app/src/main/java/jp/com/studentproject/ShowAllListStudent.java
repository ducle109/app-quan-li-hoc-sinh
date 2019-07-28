package jp.com.studentproject;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.util.List;

public class ShowAllListStudent extends AppCompatActivity implements View.OnClickListener{
    private ImageView imgAvatar;
    private ImageView imgEditYear;
    private ImageView imgEditPhone;
    private ImageView imgEditClass;
    private ImageView imgEditChairman;
    private ImageView imgEditHobby;
    private ImageView imgEditGrade;

    EditText edtYear;
    EditText edtPhone;
    EditText edtClass;
    EditText edtChairman;
    EditText edtHobby;
    EditText edtGrade;

    private ListView lvShowStudent;
    private DatabaseSQLite databaseSQLite;
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
                showDialog(student);

                return false;
            }
        });
    }

    public byte[] ConverttoArrayByte(ImageView img) {
        BitmapDrawable bitmapDrawable = (BitmapDrawable) img.getDrawable();
        Bitmap bitmap=bitmapDrawable.getBitmap();

        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
        return stream.toByteArray();
    }

    public void showDialog(Student student) {
        Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.custom_dialog);

        edtYear            = (EditText) dialog.findViewById(R.id.edt_year);
        edtPhone           = (EditText) dialog.findViewById(R.id.edt_phone);
        edtClass           = (EditText) dialog.findViewById(R.id.edt_class);
        edtChairman        = (EditText) dialog.findViewById(R.id.edt_chairman);
        edtHobby           = (EditText) dialog.findViewById(R.id.edt_hobby);
        edtGrade           = (EditText) dialog.findViewById(R.id.edt_grade);

        // edt text
        imgAvatar                  = (ImageView) dialog.findViewById(R.id.imgAvatar);
        imgEditYear               = (ImageView) dialog.findViewById(R.id.img_edt_year);
        imgEditYear               = (ImageView) dialog.findViewById(R.id.img_edt_year);
        imgEditPhone              = (ImageView) dialog.findViewById(R.id.img_edt_phone);
        imgEditClass              = (ImageView) dialog.findViewById(R.id.img_edt_class);
        imgEditChairman           = (ImageView)dialog. findViewById(R.id.img_edt_chairman);
        imgEditHobby              = (ImageView) dialog.findViewById(R.id.img_edt_hobby);
        imgEditGrade              = (ImageView) dialog.findViewById(R.id.img_edt_grade);

        imgAvatar.setOnClickListener(this);
        imgEditYear.setOnClickListener(this);
        imgEditPhone.setOnClickListener(this);
        imgEditClass.setOnClickListener(this);
        imgEditChairman.setOnClickListener(this);
        imgEditHobby.setOnClickListener(this);
        imgEditGrade.setOnClickListener(this);

        edtPhone.setText(student.getPhoneNumber().toString());
        getID = student.getId();

        dialog.show();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.imgAvatar:
                //databaseSQLite.InsertInformation(ConverttoArrayByte(imgAvatar), edtYear.getText().toString(),edtClass.getText().toString(),edtChairman.getText().toString(),edtHobby.getText().toString(),edtGrade.getText().toString());
                imgAvatar.requestFocus();
                showToast("edit ok avatar");
                break;
            case R.id.img_edt_year:
                databaseSQLite.InsertInformation(ConverttoArrayByte(imgAvatar), edtYear.getText().toString(),edtClass.getText().toString(),edtChairman.getText().toString(),edtHobby.getText().toString(),edtGrade.getText().toString());
                edtYear.requestFocus();
                showToast("edit ok year");
                break;
             case R.id.img_edt_phone:
                 Student student = new Student();
                 student.setId(getID);
                 student.setPhoneNumber(edtPhone.getText().toString());
                 databaseSQLite.updateStudentPhone(student);
                 studentList.clear();
                 studentList.addAll(databaseSQLite.getAllStudent());
                 customAdapter.notifyDataSetChanged();

                 showToast("edit ok phone");
                 break;
             case R.id.img_edt_class:
                 databaseSQLite.InsertInformation(ConverttoArrayByte(imgAvatar), edtYear.getText().toString(),edtClass.getText().toString(),edtChairman.getText().toString(),edtHobby.getText().toString(),edtGrade.getText().toString());
                 edtClass.requestFocus();
                 showToast("edit ok class");
                 break;
             case R.id.img_edt_chairman:
                 databaseSQLite.InsertInformation(ConverttoArrayByte(imgAvatar), edtYear.getText().toString(),edtClass.getText().toString(),edtChairman.getText().toString(),edtHobby.getText().toString(),edtGrade.getText().toString());
                 edtChairman.requestFocus();
                 showToast("edit ok chairman");
                break;
             case R.id.img_edt_hobby:
                 databaseSQLite.InsertInformation(ConverttoArrayByte(imgAvatar), edtYear.getText().toString(),edtClass.getText().toString(),edtChairman.getText().toString(),edtHobby.getText().toString(),edtGrade.getText().toString());
                 edtHobby.requestFocus();
                showToast("edit ok hobby");
                break;
             case R.id.img_edt_grade:
                 databaseSQLite.InsertInformation(ConverttoArrayByte(imgAvatar), edtYear.getText().toString(),edtClass.getText().toString(),edtChairman.getText().toString(),edtHobby.getText().toString(),edtGrade.getText().toString());
                 edtGrade.requestFocus();
                 showToast("edit ok grade");
                break;
        }
    }

    public void setAdapter() {
        if(customAdapter == null) {
            customAdapter = new CustomAdapter(this, R.layout.activity_show_all_list_student, studentList);
            lvShowStudent.setAdapter(customAdapter);
        } else {
            customAdapter.notifyDataSetChanged();
            lvShowStudent.setSelection(customAdapter.getCount() -1);
        }
    }

    public void init() {
        lvShowStudent = (ListView) findViewById(R.id.lvShowStudent);
    }

    public void showToast(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }


}

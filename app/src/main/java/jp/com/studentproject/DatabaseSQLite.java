package jp.com.studentproject;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class DatabaseSQLite extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "System_Manager.db";
    private static final String TABLE_NAME      = "student";
    private static final String ID               = "id";
    private static final String NAME             = "name";
    private static final String AGE              = "age";
    private static final String SEX              = "sex";
    private static final String PHONENUMBER     = "phoneNumber";
    private static final String IMGAVATAR       = "imgAvatar";
    private static final String DATE            = "date";
    private static final String STUDENTCLASS   = "stClass";
    private static final String CHAIRMAN        = "chairman";
    private static final String HOBBY           = "hobby";
    private static final String GRADE          = "grade";



    private static final String SCHOOL_NAME = "school";
    private static final String ID_SCHOOL = "id";
    private static final String SESSION = "session";
    private static final String SUBJECTS_MONDAY = "subjects_monday";
    private static final String SUBJECTS_TUESDAY = "subjects_tuesday";
    private static final String SUBJECTS_WEDNESDAY = "subjects_wednesday";
    private static final String SUBJECTS_THURSDAY = "subjects_thursday";
    private static final String SUBJECTS_FRIDAY = "subjects_friday";
    private static final String TIME = "time";

    private Context context;
    // create database
    public DatabaseSQLite(Context context) {
        super(context, DATABASE_NAME,null, 1);
        this.context = context;
        //SQLiteDatabase db = this.getWritableDatabase();
        //String sql = "DROP TABLE " + TABLE_NAME;
        //db.execSQL(createStudent());

    }

    private String createStudent() {
        return "CREATE TABLE "+ TABLE_NAME + " (" +
                ID                   + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                NAME                 + " TEXT, " +
                AGE                  + " INTEGER, " +
                SEX                  + " TEXT, " +
                PHONENUMBER         + " INTEGER, " +
                DATE                 + " NUMERIC, " +
                STUDENTCLASS        + " TEXT, " +
                CHAIRMAN            + " TEXT, " +
                HOBBY               + " TEXT, " +
                GRADE               + " TEXT" +
                ")";
    }
    private String createSchool() {
        return "CREATE TABLE "+ SCHOOL_NAME + " (" +
                ID_SCHOOL                 +" INTEGER PRIMARY KEY ," +
                SESSION                   + " TEXT," +
                SUBJECTS_MONDAY         + " TEXT," +
                SUBJECTS_TUESDAY        + " TEXT," +
                SUBJECTS_WEDNESDAY      + " TEXT," +
                SUBJECTS_THURSDAY       + " TEXT," +
                SUBJECTS_FRIDAY         + " TEXT," +
                TIME                      + " TEXT )";
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(createSchool());
        db.execSQL(createStudent());
        Toast.makeText(context, "Create successfylly", Toast.LENGTH_SHORT).show();
    }
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + SCHOOL_NAME);
        onCreate(sqLiteDatabase);
        sqLiteDatabase.close();

    }


    /*
   Add new a student
     */
    public void addStudent(Student student) {
        // write data in database
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(NAME, student.getName());
        values.put(AGE, student.getAge());
        values.put(SEX, student.getSex());
        values.put(PHONENUMBER, student.getPhoneNumber());

        // insert data in database
        db.insert(TABLE_NAME, null, values);
        // close database
        db.close();
    }

    public void addAStudent(Student student) {
        // write data in database
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(NAME, student.getName());
        values.put(AGE, student.getAge());
        values.put(SEX, student.getSex());
        values.put(PHONENUMBER, student.getPhoneNumber());
        values.put(DATE, student.getDate());
        values.put(STUDENTCLASS, student.getStClass());
        values.put(CHAIRMAN, student.getChairman());
        values.put(HOBBY, student.getHobby());
        values.put(GRADE, student.getGrade());

        // insert data in database
        db.insert(TABLE_NAME, null, values);
        // close database
        db.close();
    }

    public void addStudentInformation(Student student) {
        // write data in database
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(DATE, student.getDate());
        values.put(STUDENTCLASS, student.getStClass());
        values.put(CHAIRMAN, student.getChairman());
        values.put(HOBBY, student.getHobby());
        values.put(GRADE, student.getGrade());

        // insert data in database
        db.insert(TABLE_NAME, null, values);
        // close database
        db.close();
    }

    void InsertInformation(byte[] hinh, String date, String stClass, String chairman, String hobby, String grade) {
        SQLiteDatabase db = getWritableDatabase();
        String sql="Insert into student values ( ?, ?, ?, ?, ?, ?)";
        SQLiteStatement statement=db.compileStatement(sql);
        statement.clearBindings();
        statement.bindBlob(5,hinh);
        statement.bindString(6,date);
        statement.bindString(7,stClass);
        statement.bindString(8,chairman);
        statement.bindString(9,hobby);
        statement.bindString(10,grade);
        statement.executeInsert();
    }


    /*
    Update name of student
     */
    public int updateStudent(Student student) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(NAME, student.getName());
        values.put(AGE, student.getAge());
        values.put(SEX, student.getSex());
        values.put(PHONENUMBER, student.getPhoneNumber());

        return db.update(TABLE_NAME, values, ID + " = ?",
                new String[] {String.valueOf(student.getId())});
    }

    /*
    Update Student Information of student
     */
    public int updateStudentInformation(Student student) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(DATE, student.getDate());
        values.put(PHONENUMBER, student.getPhoneNumber());
        values.put(STUDENTCLASS, student.getStClass());
        values.put(CHAIRMAN, student.getChairman());
        values.put(HOBBY, student.getHobby());
        values.put(GRADE, student.getGrade());
        return db.update(TABLE_NAME, values, ID + " = ?",
                new String[] {String.valueOf(student.getId())});
    }


    // IDを更新する。
    public void updateStudentIDD(Student student) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        String countQuery = "SELECT * FROM " + TABLE_NAME;
        Cursor cursor = db.rawQuery(countQuery, null);
        // idをとります
        int id = student.getId();
        // 消したIDのところから指します。
        if(cursor.move(id)) {
            do {
                // 今のIDはvaluesにいれる。入れてから＋１
                values.put(ID, id++);
                //  今のIDは次のIDに変更する
                db.update(TABLE_NAME, values, ID + " = ?",
                        new String[] {String.valueOf(id)});
            } while(cursor.moveToNext());
        }
    }

    /*
   Delete a student by ID
    */

    public int deleteStudent(int id){
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TABLE_NAME,ID+"=?", new String[] {String.valueOf(id)});
    }


    public int updateID() {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("seq", 0);

        return db.update("sqlite_sequence ", cv, "name = ?",
                new String[] {TABLE_NAME});
    }



    /*
   Search a student by ID
    */
    public List<Student> getStudent(String name) {
        List<Student> list = new ArrayList<Student>();
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.query(TABLE_NAME, new String[]{ID, NAME, AGE, SEX, PHONENUMBER}, NAME + " LIKE ?",
                new String[]{name + "%"}, null, null, null);
        while (cursor.moveToNext()) {
            Student student = new Student(cursor.getInt(0), cursor.getString(1), cursor.getInt(2),
                    cursor.getString(3),cursor.getString(4));
            list.add(student);
        }
        cursor.close();
        db.close();
        return list;
    }

    /*
     Getting All Student (in tat ca hs)
      */
    public List<Student> getAllStudent() {
        List<Student> list = new ArrayList<Student>();
        // SELECT ALL QUERY
        String selectQuery = "SELECT * FROM " + TABLE_NAME;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);


        // If there is still data, still take data
        if(cursor.moveToFirst()) {
            do {
                Student student = new Student();
                // offset 0, 1, 2, 3 == (cot 1, cot 2, cot 3, va no bat dau tu vi tri 0)
                student.setId(cursor.getInt(0));
                student.setName(cursor.getString(1));
                student.setAge(cursor.getInt(2));
                student.setSex(cursor.getString(3));
                student.setPhoneNumber(cursor.getString(4));
                student.setDate(cursor.getString(5));
                student.setStClass(cursor.getString(6));
                student.setChairman(cursor.getString(7));
                student.setHobby(cursor.getString(8));
                student.setGrade(cursor.getString(9));
                list.add(student);

            } while(cursor.moveToNext());
        }
        cursor.close();
        db.close();

        return list;
    }

    /*
  Get Count Student in Table Student (lay ra tong so dong trong TABLE)
   */
    public int getStudentsCount() {
        String countQuety = "SELECT * FROM " + TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuety, null);
        cursor.close();

        // return count
        return cursor.getCount();
    }




    /* ------------------------------------------------------------------------------------時間割------------------------------------------------------------------------------*/

    public void createDefaultNotesIfNeed()  {
        int count = this.getNotesCount();
        if(count == 0 ) {
            Schoole_Time school1 = new Schoole_Time(1,"",     "数学","体育","古典", "歴史", "化学","9:30\n~10:15");
            Schoole_Time school2 = new Schoole_Time(2,"午前","体育", "情報","情報", "化学", "数学","10:20\n~11:05");
            Schoole_Time school3 = new Schoole_Time(3,"",     "化学", "情報","体育", "数学","化学","11:10\n~12:00");
            Schoole_Time school4 = new Schoole_Time(4,"",     "地学","情報", "古典","情報","体育","13:00\n~13:45");
            Schoole_Time school5 = new Schoole_Time(5,"午後", "情報", "数学","古典","化学","情報","13:50\n~14:35");
            Schoole_Time school6 = new Schoole_Time(6,"",     "古典","地学","化学","情報","歴史", "14:40\n~15:25");
            Schoole_Time school7 = new Schoole_Time(7,"",     "情報", "歴史","数学","地学","体育","15:30\n~16:15");

            this.addNote(school1);
            this.addNote(school2);
            this.addNote(school3);
            this.addNote(school4);
            this.addNote(school5);
            this.addNote(school6);
            this.addNote(school7);
        }
    }

    private void addNote(Schoole_Time school) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(ID_SCHOOL, school.getId());
        values.put(SESSION, school.getSession());
        values.put(SUBJECTS_MONDAY, school.getSubjects_monday());
        values.put(SUBJECTS_TUESDAY, school.getSubjects_tuesday());
        values.put(SUBJECTS_WEDNESDAY, school.getSubjects_wednesday());
        values.put(SUBJECTS_THURSDAY, school.getSubjects_thursday());
        values.put(SUBJECTS_FRIDAY, school.getSubjects_friday());
        values.put(TIME, school.getTime());

        // insert data in database
        db.insert(SCHOOL_NAME, null, values);
        // close database
        db.close();

    }

    public int updateStudent(Schoole_Time school, int num) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        switch (num) {
            case 0:
                values.put(SESSION, school.getSession());
                break;
            case 1:
                values.put(TIME, school.getTime());
                break;
            case 2:
                values.put(SUBJECTS_MONDAY, school.getSubjects_monday());
                break;
            case 3:
                values.put(SUBJECTS_TUESDAY, school.getSubjects_tuesday());
                break;
            case 4:
                values.put(SUBJECTS_WEDNESDAY, school.getSubjects_wednesday());
                break;
            case 5:
                values.put(SUBJECTS_THURSDAY, school.getSubjects_thursday());
                break;
            case 6:
                values.put(SUBJECTS_FRIDAY, school.getSubjects_friday());
                break;
        }

        return db.update(SCHOOL_NAME, values, ID_SCHOOL + " = ?",
                new String[] {String.valueOf(school.getId())});
    }


    /* Getting All Student (in tat ca hs)*/


    public List<Schoole_Time> getAllSchooleTime() {
        List<Schoole_Time> list = new ArrayList<Schoole_Time>();
        // SELECT ALL QUERY
        String selectQuery = "SELECT * FROM " + SCHOOL_NAME;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        // If there is still data, still take data
        if(cursor.moveToFirst()) {
            do {
                Schoole_Time schoole_time = new Schoole_Time();
                // offset 0, 1, 2, 3 == (cot 1, cot 2, cot 3, va no bat dau tu vi tri 0)
                schoole_time.setId(cursor.getInt(0));
                schoole_time.setSession(cursor.getString(1));
                schoole_time.setSubjects_monday(cursor.getString(2));
                schoole_time.setSubjects_tuesday(cursor.getString(3));
                schoole_time.setSubjects_wednesday(cursor.getString(4));
                schoole_time.setSubjects_thursday(cursor.getString(5));
                schoole_time.setSubjects_friday(cursor.getString(6));
                schoole_time.setTime(cursor.getString(7));
                list.add(schoole_time);
                //Log.d("checkMsg", list+"");
            } while(cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return list;
    }

    private int getNotesCount() {
        String countQuery = "SELECT * FROM " + SCHOOL_NAME;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        int count = cursor.getCount();
        cursor.close();
        // return count
        return count;
    }
}
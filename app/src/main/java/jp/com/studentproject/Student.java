package jp.com.studentproject;

public class  Student {
    private int id;
    private String name;
    private int age;
    private String sex;
    private String phoneNumber;
    private byte[] imgAvatar;
    private String date;
    private String stClass;
    private String chairman;
    private String hobby;
    private String grade;

    public Student() {
    }
    public Student(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Student(String name, int age, String sex, String phoneNumber) {
        this.name = name;
        this.age = age;
        this.sex = sex;
        this.phoneNumber = phoneNumber;
    }


    public Student(int id, String name, int age, String sex, String phoneNumber) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.sex = sex;
        this.phoneNumber = phoneNumber;
    }

    public Student(byte[] imgAvatar , String phoneNumber, String date, String stClass, String chairman, String hobby, String grade) {
        this.imgAvatar = imgAvatar;
        this.phoneNumber = phoneNumber;
        this.date = date;
        this.stClass = stClass;
        this.chairman = chairman;
        this.hobby = hobby;
        this.grade = grade;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public byte[] getImgAvatar() {
        return imgAvatar;
    }

    public void setImgAvatar(byte[] imgAvatar) {
        this.imgAvatar = imgAvatar;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getStClass() {
        return stClass;
    }

    public void setStClass(String stClass) {
        this.stClass = stClass;
    }

    public String getChairman() {
        return chairman;
    }

    public void setChairman(String chairman) {
        this.chairman = chairman;
    }

    public String getHobby() {
        return hobby;
    }

    public void setHobby(String hobby) {
        this.hobby = hobby;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }
}
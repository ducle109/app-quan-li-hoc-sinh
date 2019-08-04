package jp.com.studentproject;

public class Schoole_Time {
    private int id;
    private String session;
    private String subjects_monday;
    private String subjects_tuesday;
    private String subjects_wednesday;
    private String subjects_thursday;
    private String subjects_friday;
    private String time;

    public Schoole_Time() {
    }
    public Schoole_Time(int id, String session, String subjects_monday, String subjects_tuesday, String subjects_wednesday, String subjects_thursday, String subjects_friday, String time) {
        this.id = id;
        this.session = session;
        this.subjects_monday = subjects_monday;
        this.subjects_tuesday = subjects_tuesday;
        this.subjects_wednesday = subjects_wednesday;
        this.subjects_thursday = subjects_thursday;
        this.subjects_friday = subjects_friday;
        this.time = time;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSession() {
        return session;
    }

    public void setSession(String session) {
        this.session = session;
    }

    public String getSubjects_monday() {
        return subjects_monday;
    }

    public void setSubjects_monday(String subjects_monday) {
        this.subjects_monday = subjects_monday;
    }

    public String getSubjects_tuesday() {
        return subjects_tuesday;
    }

    public void setSubjects_tuesday(String subjects_tuesday) {
        this.subjects_tuesday = subjects_tuesday;
    }

    public String getSubjects_wednesday() {
        return subjects_wednesday;
    }

    public void setSubjects_wednesday(String subjects_wednesday) {
        this.subjects_wednesday = subjects_wednesday;
    }

    public String getSubjects_thursday() {
        return subjects_thursday;
    }

    public void setSubjects_thursday(String subjects_thursday) {
        this.subjects_thursday = subjects_thursday;
    }

    public String getSubjects_friday() {
        return subjects_friday;
    }

    public void setSubjects_friday(String subjects_friday) {
        this.subjects_friday = subjects_friday;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
package org.courses;

import java.util.Random;

public class Courses {

    private static final String[] titles = new String[]{"Алгебра","Литература","Физика","Экономия"};
    private static final Random random = new Random();
    private int id;  // айди
    private String title;  // название
    private int duration;  // продолжительность

    public Courses(){

    }

    public Courses(String title, int duration){
        this.title = title;
        this.duration = duration;
    }

    public static Courses create() {
        return null;
    }


    /*
    метод по изменению продолжительности курса
     */
    public void updateDuration(){
        duration = random.nextInt(60,90);
    }

    /*
    метод по изменению названия курса
     */
    public void updateTitle(){
        title = titles[random.nextInt(titles.length)];
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    @Override
    public String toString() {
        return "Курс :" +
                "id=" + id +
                ", название='" + title + '\'' +
                ", продолжительность=" + duration +
                '}';
    }
}

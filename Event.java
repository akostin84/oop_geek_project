// класс для единичной записи с указанием времени и заголовка

import java.util.*;
import java.text.*;
import java.io.*;

public class Event implements Saveable{
    private Date EventTime; // время события
    private String Name; // сущность события
    private static final String PATH = "calendar.txt";
    
    public Event(String day, String time){
        SimpleDateFormat sf = new SimpleDateFormat("dd-MM-yyyy; HH:mm:ss");
        String DateString = String.format("%s; %s", day, time);
        try {
            EventTime = sf.parse(DateString);
            Name = "";
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    // проверка, что событие произошло в указанный день
    public boolean isUserDay(String user_day){
        String start_user_day = user_day + "; 00:00:00";
        String end_user_day = user_day + "; 23:59:00";
        SimpleDateFormat sfd = new SimpleDateFormat("dd-MM-yyyy; HH:mm:ss");
        Date start_day = new Date();
        Date end_day = new Date();
        try {
            start_day = sfd.parse(start_user_day);
            end_day = sfd.parse(end_user_day);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        
        if (end_day.after(this.getTime()) && start_day.before(this.getTime())) {
            return true;
        } else {
            return false;
        }
    }

    // при выводе на экран для пользователя добавляются поля Date: и Name:
    @Override
    public String toString(){
        SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy; HH:mm:ss");
        String output = String.format("Date: %s Name: %s\n", df.format(EventTime), Name);
        return output;            
    }
    // при записи в файл поля разделяются слешем /
    public String toFile(){
        SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy/HH:mm:ss");
        String output = String.format("%s/%s", df.format(EventTime), Name);
        return output;            
    }

    public void setName(String n){
        this.Name = n;
    }

    public Date getTime(){
        return this.EventTime;
    }

    public String getName(){
        return this.Name;
    }
// метод для сохранения нового события
    public void save(){
        try (FileWriter writer = new FileWriter(PATH, true)) {
            writer.append(String.format("%s\n", this.toFile()));
            writer.flush();
            writer.close();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
}


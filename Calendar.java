import java.util.*;
import java.io.*;

public class Calendar{
    public ArrayList<Event> events = new ArrayList<Event>();

    // создание календаря из содержимого файла
    public Calendar(String path){
        ArrayList<String> eventsStr = readContent(path);
        for (int i = 0; i < eventsStr.size(); i++){
            String[] splittedStr = eventsStr.get(i).split("/");
            String date_part = splittedStr[0];
            String time_part = splittedStr[1];
            String name_part = splittedStr[2];
            Event e = new Event(date_part, time_part);
            e.setName(name_part);
            this.events.add(e);
        }  
    };
    // построчный вывод для чтения содержимого календаря
    @Override
    public String toString(){
        String output = "";
        for (int i = 0; i < this.events.size(); i++){
            output += this.events.get(i) + "\n";
        }
        return output;            
    }
    // чтение файла календаря как список строк
    private ArrayList<String> readContent(String path){
        ArrayList<String> words = new ArrayList<String>();
        BufferedReader reader = null;
        try{
            reader = new BufferedReader(new FileReader(path));
            String line;
            while ((line = reader.readLine()) != null) {
                words.add(line);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
                try{
            reader.close();
            } catch (Exception exp) {}
        }
        return words;
    }
}

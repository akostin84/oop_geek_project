// создание нового проекта "Календарь": задача создать календарь в котором есть возможность делать записи на определенный день. 
// Работа через консоль. Должна быть реализована возможность просмотра календаря, просмотра записей на определенный день, 
// добавление записей (например "в 14:00 стоматолог"). Реализована возможность сохранения календаря в файл и восстановление из файла. 
// Этот проект должен быть выполнен также с учетом принципов SOLID’а

import java.util.*;

public class final_project {
    public static void main(String[] args) {
        String initialMsg = String.format("%s\n%s\n%s\n%s\n%s", "Final project: CALENDAR",
        "Select mode:", "1 - Load the whole calendar", "2 - Load the selected day", 
        "3 - Add new note"); 
        System.out.println(initialMsg);
        Scanner reader = new Scanner(System.in);
        int userMode = readIntFromTerminal("You select:", reader);
        //switch case
        switch (userMode) {
            case  (1):
                Calendar c = new Calendar("calendar.txt");
                System.out.println(c); 
                break;
            case (2):
                String userDay1 = readStrFromTerminal("Enter day [dd-MM-yyyy]: ", reader);
                Calendar c1 = new Calendar("calendar.txt");
                ArrayList<Event> e = c1.events;
                for (int i = 0; i < e.size(); i++)
                    if (e.get(i).isUserDay(userDay1)){
                        System.out.println(e.get(i));
                    }  
                break;
            case (3):
                String userDay = readStrFromTerminal("Enter day [dd-MM-yyyy]: ", reader);
                String userTime = readStrFromTerminal("Enter time [HH:mm:ss]: ", reader);
                String userName = readStrFromTerminal("Enter name: ", reader);
                Event newEvent = new Event(userDay, userTime);
                newEvent.setName(userName);
                newEvent.save();
                break;
            default:
                System.out.println("Wrong selection");
                break;
        }
        reader.close();
 
    }

    static int readIntFromTerminal(String message, Scanner reader) {
        System.out.print(message);
        String strNumber = reader.nextLine(); 
        int Number = Integer.parseInt(strNumber);
        return Number;
    }

    static String readStrFromTerminal(String message, Scanner reader) {
        System.out.print(message);
        String strUser = reader.nextLine();
        return strUser;
    }
}

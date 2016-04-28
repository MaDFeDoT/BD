package bank_database;

import java.io.*;

public class Bank_Database {    
    
    public static void main(String[] args) {
        DB database;
        System.out.println("WELCOME BANK DATABASE SYSTEM");
        try {
            database= new DB();
            while(true)
            {
                System.out.println("МЕНЮ:\n"
                        + "\ta: Посмотреть список клиентов"
                        + "\n\tb: Создать новый контракт"
                        + "\n\tс: Положить предмет в ячейку"
                        + "\n\td: Изъять предмет из ячейки"
                        + "\n\te: Осмотреть предмет в ячейке"
                        + "\n\tf: Забрать все предметы из ячейки"
                        + "\n\tg: Посмотреть вещи клиента по контракту"
                        + "\n\th: Посмотреть содержимое ячейки"
                        + "\n\ti: Выйти из приложения");
                byte[] answer=new byte[80];
                try{System.in.read(answer);}
                catch(IOException e){e.printStackTrace();}
                switch((char) answer[0]) {
                    case 'a':
                        database.ShowClients(); break;
                    case 'b':
                        database.CreateContract(); break;
                    case 'c':
                        database.PutObject(); break; 
                    case 'd':
                        database.TakeObject(); break;
                    case 'e':
                        database.SeeObject(); break;
                    case 'f':
                        database.TakeAll(); break;
                    case 'g':
                        database.ShowObjects(); break;
                    case 'h':
                        database.ShowCellOb(); break;
                    case 'i':
                        database.BDDisconnect(); return;
                    default:
                        System.out.println("Неверный пункт меню. Попробуйте еще раз."); break;
                }
            }
        } catch (ClassNotFoundException e) {
                e.printStackTrace();
        }
    }
    
}

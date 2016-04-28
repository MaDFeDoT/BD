package bank_database;

import java.io.*;
import java.sql.*;
import java.util.Properties;
import java.nio.charset.*;
import java.util.ArrayList;

public class DB {
    public String DB_URL = "jdbc:firebirdsql://localhost:3050/C:/DataBase/Test.FDB";
    public String DB_DEFAULT_USER = "SYSDBA";
    public String DB_DEFAULT_PASSWORD = "masterkey";
    public String DB_DEFAULT_ENCODING = "win1251";
    public Connection conn = null;
    Properties props = null;
    
    /**
     * Конструктор DB
     * заполняем параметры, вызываем подключение
     * @throws ClassNotFoundException - ошибка jdbc
     */
    public DB() throws ClassNotFoundException
    {
        Class.forName("org.firebirdsql.jdbc.FBDriver");
        props = new Properties();
        props.setProperty("user", DB_DEFAULT_USER);
        props.setProperty("password", DB_DEFAULT_PASSWORD);
        props.setProperty("encoding", DB_DEFAULT_ENCODING);
        DBConnect();
    }
    
    /**
     * Подключение к БД
     */
    public void DBConnect()
    {
        try {
            conn = DriverManager.getConnection(DB_URL, props);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    /**
     * Отключение от БД
     */
    public void BDDisconnect()
    {
        try {
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    /*Отображение списка пользователей*/
    public void ShowClients ()
    {
        String Query = "SELECT CLIENT_ID, NAME, BORN_DATE, ADRESS FROM CLIENTS";
        try {
            PreparedStatement statement = conn.prepareStatement(Query);
            ResultSet resultSet = statement.executeQuery();
            System.out.println("Список клиентов:");
            byte[] answer = new byte[20];
            resultSet.next();
            while(true)
            {
                for(int i=0; i<10; i++)
                {
                    System.out.println(resultSet.getString("CLIENT_ID")+"\tName: "+resultSet.getString("NAME")+"\n\t\tBorn date: "+resultSet.getString("BORN_DATE")+"\n\t\tAdress: "+resultSet.getString("ADRESS"));
                    if (!resultSet.next())
                        return;
                }
                System.out.println("Продолжить? y/n");
                try{System.in.read(answer);}
                catch(IOException e){e.printStackTrace();}
                if('n'==(char)answer[0])
                {
                    return;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    /*Создание нового контракта*/
    public void CreateContract()
    {
        String Start_date, End_date, Arend_Cost, Cell_number, Client_id, Group_id, Rule_acsess;
        try{
            byte[] answer = new byte[40];
            System.out.print("Создание нового контракта: \n\tВведите свой ID: ");
            System.in.read(answer);
            Client_id = ConvByteToString(answer);
            
            System.out.print("\tВведите ID группы: ");
            System.in.read(answer);
            Group_id = ConvByteToString(answer);
            
            System.out.print("\tВведите тип доступа: ");
            System.in.read(answer);
            Rule_acsess = ConvByteToString(answer);
            
            System.out.print("\tВведите номер ячейки: ");
            System.in.read(answer);
            Cell_number = ConvByteToString(answer);
            
            System.out.print("\tВведите стоимость аренды: ");
            System.in.read(answer);
            Arend_Cost = ConvByteToString(answer);
            
            System.out.print("\tВведите дату начала аренды: ");
            System.in.read(answer);
            Start_date = ConvByteToString(answer);
            
            System.out.print("\tВведите дату окончания аренды: ");
            System.in.read(answer);
            End_date = ConvByteToString(answer);
            
            try {
                String Query = "SELECT MAX(CONTRACT_ID) AS COLL FROM CONTRACTS";
                PreparedStatement statement = conn.prepareStatement(Query);
                ResultSet resultSet = statement.executeQuery();
                resultSet.next();
                int num=resultSet.getInt("COLL")+1;
                String Contract_id=String.valueOf(num);
                
                Query = "INSERT INTO CONTRACTS(CONTRACT_ID, START_DATE, END_DATE, AREND_COST, CELL_NUMBER, CLIENT_ID, GROUP_ID, RULE_ACSESS) "
                        + "VALUES ("+Contract_id+", \'"+Start_date+"\', \'"+End_date+"\', "+Arend_Cost+", "+Cell_number+", "+Client_id+", "+Group_id+", "+Rule_acsess+")";
                statement = conn.prepareStatement(Query);
                statement.execute();
                
            } catch (SQLException e) {
                e.printStackTrace();
            }    
            
            return;
        }catch(IOException e){e.printStackTrace();}
    }
    
    /*Преодразование Byte into String*/
    public String ConvByteToString(byte[] data)
    {
        String answer="";
        for(int i=0; i<data.length; i++)
        {
            if((char) data[i]=='\n')
                return answer;
            answer+=(char) data[i];
        }
        return answer;
    }
    
    /*Положить предемет в ячейку*/
    public void PutObject()
    {
        String Cell_number, Contract_id, Name, Cost, Date;
        try{
            byte[] answer = new byte[40];
            System.out.print("Внесение ценности на хранение: \n\tВведите ID контракта: ");
            System.in.read(answer);
            Contract_id = ConvByteToString(answer);
            
            System.out.print("\tВведите номер ячейки: ");
            System.in.read(answer);
            Cell_number = ConvByteToString(answer);
            
            System.out.print("\tВведите название ценности: ");
            System.in.read(answer);
            Name = ConvByteToString(answer);
            
            System.out.print("\tВведите стоимость предмета: ");
            System.in.read(answer);
            Cost = ConvByteToString(answer);
            
            System.out.print("\tВведите дату доступа: ");
            System.in.read(answer);
            Date = ConvByteToString(answer);
            
            try {
                String Query = "SELECT MAX(ID) AS COLL FROM HISTORY_CELL";
                PreparedStatement statement = conn.prepareStatement(Query);
                ResultSet resultSet = statement.executeQuery();
                resultSet.next();
                int num=resultSet.getInt("COLL")+1;
                String History_id=String.valueOf(num);
                
                Query = "SELECT MAX(ITEM_ID) AS COLL FROM ITEMS";
                statement = conn.prepareStatement(Query);
                resultSet = statement.executeQuery();
                resultSet.next();
                num=resultSet.getInt("COLL")+1;
                String Item_id=String.valueOf(num);
                
                Query = "INSERT INTO ITEMS(ITEM_ID, CONTRACT_ID, NAME, COST) "
                        + "VALUES ("+Item_id+", "+Contract_id+", \'"+Name+"\', "+Cost+")";
                statement = conn.prepareStatement(Query);
                statement.execute();
                
                Query = "INSERT INTO HISTORY_CELL(ID, CELL_NUMBER, ACSESS_TYPE, DATE_ACSESS, ITEM_ID) "
                        + "VALUES ("+History_id+", "+Cell_number+", 1, \'"+Date+"\', "+Item_id+")";
                statement = conn.prepareStatement(Query);
                statement.execute();
                
            } catch (SQLException e) {
                e.printStackTrace();
            }    
            
            return;
        }catch(IOException e){e.printStackTrace();}
    }
    
    /*Забрать предмет из ячейки*/
    public void TakeObject()
    {
        String Cell_number, Date, Item_id;
        try{
            byte[] answer = new byte[40];
            System.out.print("Изъятие ценности из хранилища: \n\tВведите ID ценности: ");
            System.in.read(answer);
            Item_id = ConvByteToString(answer);
            
            System.out.print("\tВведите номер ячейки: ");
            System.in.read(answer);
            Cell_number = ConvByteToString(answer);
            
            System.out.print("\tВведите дату доступа: ");
            System.in.read(answer);
            Date = ConvByteToString(answer);
            
            try {
                String Query = "SELECT MAX(ID) AS COLL FROM HISTORY_CELL";
                PreparedStatement statement = conn.prepareStatement(Query);
                ResultSet resultSet = statement.executeQuery();
                resultSet.next();
                int num=resultSet.getInt("COLL")+1;
                String History_id=String.valueOf(num);
                Query = "SELECT ITEM_ID AS NUM FROM HISTORY_CELL WHERE (ITEM_ID="+Item_id+")AND(CELL_NUMBER="+Cell_number+")AND(ACSESS_TYPE=2)AND(DATE_ACSESS>"
                        + "(SELECT FIRST 1 DATE_ACSESS FROM HISTORY_CELL WHERE ACSESS_TYPE<2 ORDER BY DATE_ACSESS ASC))";
                statement = conn.prepareStatement(Query);
                resultSet = statement.executeQuery();
                
                if(resultSet.next())
                {
                    System.out.print("\tПредмет уже изъят из ячейки\n");
                    return;
                }
                Query = "INSERT INTO HISTORY_CELL(ID, CELL_NUMBER, ACSESS_TYPE, DATE_ACSESS, ITEM_ID) "
                        + "VALUES ("+History_id+", "+Cell_number+", 2, \'"+Date+"\', "+Item_id+")";
                statement = conn.prepareStatement(Query);
                statement.execute();
                
            } catch (SQLException e) {
                e.printStackTrace();
            }    
            System.out.print("\tПредмет изъят из ячейки\n");
            return;
        }catch(IOException e){e.printStackTrace();}
    }
    
    /*Осмотреть предмет в ячейке*/
    public void SeeObject()
    {
        String Cell_number, Date, Item_id;
        try{
            byte[] answer = new byte[40];
            System.out.print("Осмотр ценности в хранилище: \n\tВведите ID ценности: ");
            System.in.read(answer);
            Item_id = ConvByteToString(answer);
            
            System.out.print("\tВведите номер ячейки: ");
            System.in.read(answer);
            Cell_number = ConvByteToString(answer);
            
            System.out.print("\tВведите дату доступа: ");
            System.in.read(answer);
            Date = ConvByteToString(answer);
            
            try {
                String Query = "SELECT MAX(ID) AS COLL FROM HISTORY_CELL";
                PreparedStatement statement = conn.prepareStatement(Query);
                ResultSet resultSet = statement.executeQuery();
                resultSet.next();
                int num=resultSet.getInt("COLL")+1;
                String History_id=String.valueOf(num);
                
                Query = "SELECT ITEM_ID AS NUM FROM HISTORY_CELL WHERE (ITEM_ID="+Item_id+")AND(CELL_NUMBER="+Cell_number+")AND(ACSESS_TYPE=2)AND(DATE_ACSESS>"
                        + "(SELECT FIRST 1 DATE_ACSESS FROM HISTORY_CELL WHERE ACSESS_TYPE<2 ORDER BY DATE_ACSESS ASC))";
                statement = conn.prepareStatement(Query);
                resultSet = statement.executeQuery();
                
                if(resultSet.next())
                {
                    System.out.print("\tПредмет изъят из ячейки. Осмотр не возможен\n");
                    return;
                }
                Query = "INSERT INTO HISTORY_CELL(ID, CELL_NUMBER, ACSESS_TYPE, DATE_ACSESS, ITEM_ID) "
                        + "VALUES ("+History_id+", "+Cell_number+", 0, \'"+Date+"\', "+Item_id+")";
                statement = conn.prepareStatement(Query);
                statement.execute();
                
            } catch (SQLException e) {
                e.printStackTrace();
            }    
            
            return;
        }catch(IOException e){e.printStackTrace();}
    }
    
    /*Забрать все предметы из ячейки*/
    public void TakeAll()
    {
        String Contract_id, Cell_number, Date;
        ArrayList Item_id = new ArrayList();
        try{
            byte[] answer = new byte[40];
            System.out.print("Забрать все предметы из ячейки: \n\tВведите ID контракта: ");
            System.in.read(answer);
            Contract_id = ConvByteToString(answer);
            
            System.out.print("\tВведите номер ячейки: ");
            System.in.read(answer);
            Cell_number = ConvByteToString(answer);
            
            System.out.print("\tВведите дату доступа: ");
            System.in.read(answer);
            Date = ConvByteToString(answer);
            try {
                String Query = "SELECT MAX(ID) AS COLL FROM HISTORY_CELL";
                PreparedStatement statement = conn.prepareStatement(Query);
                ResultSet resultSet = statement.executeQuery();
                resultSet.next();
                int num=resultSet.getInt("COLL")+1;
                String History_id=String.valueOf(num);
                
                Query = "SELECT ITEM_ID AS ITID FROM ITEMS WHERE CONTRACT_ID="+Contract_id;
                statement = conn.prepareStatement(Query);
                resultSet = statement.executeQuery();
                
                int i=0;
                while(resultSet.next())
                {
                    Item_id.add(resultSet.getString("ITID"));
                    i=i+1;
                }

                for(int j=0; j<i; j++)
                {
                    Query = "SELECT ITEM_ID AS NUM FROM HISTORY_CELL WHERE (ITEM_ID="+Item_id.get(j)+")AND(CELL_NUMBER="+Cell_number+")AND(ACSESS_TYPE=2)AND(DATE_ACSESS>"
                        + "(SELECT FIRST 1 DATE_ACSESS FROM HISTORY_CELL WHERE ACSESS_TYPE<2 ORDER BY DATE_ACSESS ASC))";
                    statement = conn.prepareStatement(Query);
                    ResultSet newres = statement.executeQuery();
                
                     if(!newres.next())
                    {
                        Query = "INSERT INTO HISTORY_CELL(ID, CELL_NUMBER, ACSESS_TYPE, DATE_ACSESS, ITEM_ID) "
                            + "VALUES ("+History_id+", "+Cell_number+", 2, \'"+Date+"\', "+Item_id.get(j)+")";
                        statement = conn.prepareStatement(Query);
                        statement.execute();
                        
                        num=num+1;
                        History_id=String.valueOf(num);
                    }
                }  
                Query = "UPDATE CONTRACTS SET END_DATE=\'"+Date+"\' WHERE CONTRACT_ID="+Contract_id;
                statement = conn.prepareStatement(Query);
                statement.execute();
                        
            } catch (SQLException e) {
                e.printStackTrace();
            }    
            
            return;
        }catch(IOException e){e.printStackTrace();}
    }
    
    /*Посмотреть вещи клиента*/
    public void ShowObjects()
    {
        String Cell_number, Client_id, Contract_id;
        try{
            byte[] answer = new byte[40];
            
            System.out.print("Посмотреть список вещей клиента по контракту: \n\t\tВведите ID контракта: ");
            System.in.read(answer);
            Contract_id = ConvByteToString(answer);
            
            try {
                String Query = "SELECT CELL_NUMBER, CLIENT_ID FROM CONTRACTS WHERE CONTRACT_ID="+Contract_id;
                PreparedStatement statement = conn.prepareStatement(Query);
                ResultSet resultSet = statement.executeQuery();
                if(!resultSet.next())
                        {
                            System.out.println("Не существует данного контракта.");
                            return;                              
                        }
                Cell_number=resultSet.getString("CELL_NUMBER");
                Client_id=resultSet.getString("CLIENT_ID");
                
                Query = "SELECT ITEM_ID, NAME, COST FROM ITEMS WHERE (CONTRACT_ID="+Contract_id+")AND(ITEM_ID NOT IN (SELECT ITEM_ID FROM HISTORY_CELL WHERE ACSESS_TYPE=2))";
                statement = conn.prepareStatement(Query);
                resultSet = statement.executeQuery();
                
                System.out.println("Список вещей клиента "+Client_id+" по контракту "+Contract_id+" для ячейки "+Cell_number+":");
                boolean flag=resultSet.next();
                if(!flag)
                    System.out.println("Нет вещей по данному контракту");
                while(flag)
                {
                    System.out.println("\tПредмет: "+resultSet.getString("ITEM_ID")+" Название: "+resultSet.getString("NAME")+"\n"
                            + "\tСтоимость: "+resultSet.getString("COST"));
                    flag=resultSet.next();
                }                        
            } catch (SQLException e) {
                e.printStackTrace();
            }    
            
            return;
        }catch(IOException e){e.printStackTrace();}
    }
    
     /*Посмотреть содержимое ячейки*/
    public void ShowCellOb()
    {
        String Cell_number;
        try{
            byte[] answer = new byte[40];
            
            System.out.print("Посмотреть список вещей в ячейке: \n\t\tВведите номер ячейки: ");
            System.in.read(answer);
            Cell_number = ConvByteToString(answer);
            
            try {
                String Query = "SELECT ITEMS.ITEM_ID, NAME, COST FROM ITEMS, HISTORY_CELL WHERE (HISTORY_CELL.ITEM_ID=ITEMS.ITEM_ID)"
                        + "AND(HISTORY_CELL.CELL_NUMBER="+Cell_number+")AND(HISTORY_CELL.ITEM_ID NOT IN (SELECT ITEM_ID FROM HISTORY_CELL WHERE ACSESS_TYPE=2)"
                        + "AND(HISTORY_CELL.ACSESS_TYPE=1))";
                PreparedStatement statement = conn.prepareStatement(Query);
                ResultSet resultSet = statement.executeQuery();
                
                System.out.println("Список вещей для ячейки "+Cell_number+":");
                boolean flag=resultSet.next();
                if(!flag)
                    System.out.println("Вещи отсутствуют в данной ячейке");
                while(flag)
                {
                    System.out.println("\tПредмет: "+resultSet.getString("ITEM_ID")+" Название: "+resultSet.getString("NAME")+"\n"
                            + "\tСтоимость: "+resultSet.getString("COST"));
                    flag=resultSet.next();
                }                        
            } catch (SQLException e) {
                e.printStackTrace();
            }    
            
            return;
        }catch(IOException e){e.printStackTrace();}
    }
    
}

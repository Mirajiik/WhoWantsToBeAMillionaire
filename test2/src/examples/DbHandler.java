/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package examples;

/**
 *
 * @author ravil
 */
import org.sqlite.JDBC;
 
import java.sql.*;
import java.util.*;
 
public class DbHandler {
 
// Константа, в которой хранится адрес подключения
    private static final String CON_STR = "jdbc:sqlite:C:\\User files\\Project\\SQLite\\SQLite.db";
 
    // Используем шаблон одиночка, чтобы не плодить множество
    // экземпляров класса DbHandler
    private static DbHandler instance = null;
 
    public static synchronized DbHandler getInstance() throws SQLException {
        if (instance == null)
            instance = new DbHandler();
        return instance;
    }
 
    // Объект, в котором будет храниться соединение с БД
    private Connection connection;
 
    private DbHandler() throws SQLException {
        // Регистрируем драйвер, с которым будем работать
        // в нашем случае Sqlite
        DriverManager.registerDriver(new JDBC());
        // Выполняем подключение к базе данных
        this.connection = DriverManager.getConnection(CON_STR);
    }
    
    public void CreateTable() {
        String sql = "CREATE TABLE question (id INTEGER PRIMARY KEY, text TEXT NOT NULL, level INTEGER NOT NULL, answer1 TEXT NOT NULL,"
                + " answer2 TEXT NOT NULL, answer3 TEXT NOT NULL, answer4 TEXT NOT NULL, num_answer INTEGER NOT NULL);";
        try (Statement statement = this.connection.createStatement()) {
            statement.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
    }
    
    public void InsertTable(ArrayList<Question> question) {
        String sqlQuery = "INSERT INTO question (id, text, level, answer1, answer2, answer3, answer4, num_answer) VALUES(?,?,?,?,?,?,?,?);";
        
        try {
            PreparedStatement preparedStatement;
            preparedStatement = connection.prepareStatement(sqlQuery);
            for (int i = 0; i < question.size(); i++) {
                preparedStatement.setString(2, question.get(i).Text);
                preparedStatement.setInt(3, question.get(i).Level);
                preparedStatement.setString(4, question.get(i).Answers[0]);
                preparedStatement.setString(5, question.get(i).Answers[1]);
                preparedStatement.setString(6, question.get(i).Answers[2]);
                preparedStatement.setString(7, question.get(i).Answers[3]);
                preparedStatement.setInt(8, (Integer.parseInt(question.get(i).RightAnswer)));
                preparedStatement.executeUpdate();
            }
            //preparedStatement.setInt(1,0);
            
            System.out.println("Data has been inserted!");
        } catch (SQLException ex) {
        }
    }
}
package es.ieslavereda.model.bd;


import com.mysql.cj.jdbc.MysqlDataSource;

import javax.sql.DataSource;

public class MyDataSource {
    public static DataSource getDataSource(){
        MysqlDataSource dataSource = new MysqlDataSource();
        dataSource.setURL("jdbc:mysql://localhost:3306/examen2425ord");
        dataSource.setUser("usuarioEx2425ord");
        dataSource.setPassword("1234");
        return dataSource;
    }
}

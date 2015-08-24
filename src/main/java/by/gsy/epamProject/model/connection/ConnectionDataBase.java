package by.gsy.epamProject.model.connection;


import by.gsy.epamProject.model.constans.Constants;

import java.sql.*;


public class ConnectionDataBase {
    private static Statement statement = null;

    static {
        try {
            Class.forName(Constants.Connection.DRIVER);
        } catch (ClassNotFoundException e) {
            System.err.println(Constants.ERRORS.DRIVER_ERROR);
        }
    }

    private ConnectionDataBase() {
        super();
    }

    public static Connection getConnection() {
        try {
            return DriverManager.getConnection(Constants.Connection.URL, Constants.Connection.NAME, Constants.Connection.PASSWORD);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void closeConnection(Connection connection) {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public static void closePreparedStatement(PreparedStatement preparedStatement) {
        if (preparedStatement != null) {
            try {
                preparedStatement.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public static void closeResultSet(ResultSet resultSet) {
        if (resultSet != null) {
            try {
                resultSet.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }
}

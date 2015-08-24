package by.gsy.epamProject.model.daoImpl;


import by.gsy.epamProject.model.beans.User;
import by.gsy.epamProject.model.connection.ConnectionDataBase;
import by.gsy.epamProject.model.constans.Constants;
import by.gsy.epamProject.model.dao.IUserDao;
import by.gsy.epamProject.model.exeption.DSException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDaoDataBase implements IUserDao {

    public boolean check(String email) {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Connection connection = ConnectionDataBase.getConnection();
        try {
            preparedStatement = connection.prepareStatement(Constants.Query.SELECT_FROM_USERS_WHERE_EMAIL);
            preparedStatement.setString(1, email);
            resultSet = preparedStatement.executeQuery();
            return resultSet.next();
        } catch (SQLException e) {
            return true;
        } finally {
            ConnectionDataBase.closePreparedStatement(preparedStatement);
            ConnectionDataBase.closeConnection(connection);
        }
    }

    public void createUser(User user) throws DSException {
        if (!check(user.getEmail())) {
            Connection connection = ConnectionDataBase.getConnection();
            PreparedStatement preparedStatement = null;
            try {
                preparedStatement = connection.prepareStatement(Constants.Query.INSERT_USER);
                preparedStatement.setString(1, user.getName());
                preparedStatement.setString(2, user.getEmail());
                preparedStatement.setString(3, user.getPassword());
                synchronized (preparedStatement) {
                    preparedStatement.executeUpdate();
                }
            } catch (SQLException e) {
                throw new DSException(Constants.ERRORS.DATA_BASE_ERROR, e);
            } finally {
                ConnectionDataBase.closePreparedStatement(preparedStatement);
                ConnectionDataBase.closeConnection(connection);
            }
        } else {
            throw new DSException(Constants.ERRORS.MESSAGE_USER);
        }
    }

    public User getUser(String email) throws DSException {
        PreparedStatement preparedStatement = null;
        java.sql.Connection connection = ConnectionDataBase.getConnection();
        ResultSet resultSet = null;
        try {
            preparedStatement = connection.prepareStatement(Constants.Query.SELECT_FROM_USERS_WHERE_EMAIL);
            preparedStatement.setString(1, email);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getInt(Constants.TablesDB.ID));
                user.setName(resultSet.getString(Constants.TablesDB.NAME));
                user.setEmail(resultSet.getString(Constants.TablesDB.EMAIL));
                user.setPassword(resultSet.getString(Constants.TablesDB.PASSWORD));
                return user;
            } else {
                throw new DSException(Constants.ERRORS.MESSAGE_NOT_USER);
            }
        } catch (SQLException e) {
            throw new DSException(Constants.ERRORS.DATA_BASE_ERROR, e);
        } finally {
            ConnectionDataBase.closePreparedStatement(preparedStatement);
            ConnectionDataBase.closeResultSet(resultSet);
            ConnectionDataBase.closeConnection(connection);
        }
    }

    public void deleteUser(User user) throws DSException {
        Connection connection = ConnectionDataBase.getConnection();
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(Constants.Query.DELETE_USER);
            preparedStatement.setInt(1, user.getId());
            synchronized (preparedStatement) {
                preparedStatement.executeUpdate();
            }
        } catch (SQLException e) {
            throw new DSException(Constants.ERRORS.RECORD_DELETE_ERROR);
        } finally {
            ConnectionDataBase.closePreparedStatement(preparedStatement);
            ConnectionDataBase.closeConnection(connection);
        }
    }

    public List<User> getAll() throws DSException {
        List<User> users = new ArrayList<User>();
        Connection connection = ConnectionDataBase.getConnection();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            preparedStatement = connection.prepareStatement(Constants.Query.SELECT_ALL_USERS);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getInt(Constants.TablesDB.ID));
                user.setEmail(resultSet.getString(Constants.TablesDB.EMAIL));
                user.setName(resultSet.getString(Constants.TablesDB.NAME));
                users.add(user);
            }
            resultSet.close();
            return users;
        } catch (SQLException e) {
            throw new DSException(Constants.ERRORS.DATA_BASE_ERROR, e);
        } finally {
            ConnectionDataBase.closeResultSet(resultSet);
            ConnectionDataBase.closePreparedStatement(preparedStatement);
            ConnectionDataBase.closeConnection(connection);
        }
    }
}

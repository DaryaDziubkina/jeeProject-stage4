package by.gsy.epamProject.model.dao;


import by.gsy.epamProject.model.beans.User;
import by.gsy.epamProject.model.exeption.DSException;

import java.util.List;

public interface IUserDao {

    boolean check(String email);

    void createUser(User user) throws DSException;

    User getUser(String email) throws DSException;

    void deleteUser(User user) throws DSException;


    List<User> getAll() throws DSException;

}

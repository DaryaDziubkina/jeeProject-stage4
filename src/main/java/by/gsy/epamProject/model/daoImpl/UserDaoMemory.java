package by.gsy.epamProject.model.daoImpl;

import by.gsy.epamProject.model.beans.User;
import by.gsy.epamProject.model.constans.Constants;
import by.gsy.epamProject.model.dao.IUserDao;
import by.gsy.epamProject.model.exeption.DSException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class UserDaoMemory implements IUserDao {

    private static Map<String, User> users;

    static {
        users = new HashMap<String, User>();
        users.put("kella@mail.ru", new User("Masha", "kella@mail.ru", "4545578"));
        users.put("kea@mail.ru", new User("Key", "kea@mail.ru", "5858"));
    }

    public boolean check(String email) {
        return users.containsKey(email);
    }

    public void createUser(User user) throws DSException {
        if (!check(user.getEmail())) {
            users.put(user.getEmail(), user);
        } else {
            throw new DSException(Constants.ERRORS.MESSAGE_USER);
        }
    }

    public User getUser(String email) throws DSException {
        if (users.containsKey(email)) {
            return users.get(email);
        } else {
            throw new DSException(Constants.ERRORS.MESSAGE_NOT_USER);
        }
    }

    public void deleteUser(User user) throws DSException {
        users.clear();
    }

    public List<User> getAll() throws DSException {
        return null;
    }
}

package by.gsy.epamProject.model.factories;

import by.gsy.epamProject.model.dao.INoteDao;
import by.gsy.epamProject.model.dao.IUserDao;
import by.gsy.epamProject.model.daoImpl.NoteDaoDataBase;
import by.gsy.epamProject.model.daoImpl.UserDaoDataBase;

public class DataBaseDaoFactory extends AbstractDaoFactory {
    private IUserDao userDao = null;
    private INoteDao noteDao = null;

    @Override
    public IUserDao getUserDao() {
        if (userDao == null) {
            userDao = new UserDaoDataBase();
        }
        return userDao;
    }

    @Override
    public INoteDao getNoteDao() {
        if (noteDao == null) {
            noteDao = new NoteDaoDataBase();
        }
        return noteDao;
    }
}

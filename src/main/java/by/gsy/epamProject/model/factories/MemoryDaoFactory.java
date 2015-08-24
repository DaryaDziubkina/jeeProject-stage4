package by.gsy.epamProject.model.factories;

import by.gsy.epamProject.model.dao.INoteDao;
import by.gsy.epamProject.model.dao.IUserDao;
import by.gsy.epamProject.model.daoImpl.UserDaoMemory;


public class MemoryDaoFactory extends AbstractDaoFactory {

    private IUserDao userDao = null;
    private INoteDao noteDao = null;


    @Override
    public IUserDao getUserDao() {
        if (userDao == null) {
            userDao = new UserDaoMemory();
        }
        return userDao;
    }

    @Override
    public INoteDao getNoteDao() {
//        if(noteDao == null){
//            noteDao = new NoteDaoMemory();
//        }
        return noteDao;
    }

}

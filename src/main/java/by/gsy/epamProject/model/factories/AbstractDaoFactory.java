package by.gsy.epamProject.model.factories;

import by.gsy.epamProject.model.dao.INoteDao;
import by.gsy.epamProject.model.dao.IUserDao;

import java.util.HashMap;
import java.util.Map;


public abstract class AbstractDaoFactory {

    private static final Map<Class<?>, AbstractDaoFactory> map = new HashMap<Class<?>, AbstractDaoFactory>();

    static {
        map.put(DataBaseDaoFactory.class, new DataBaseDaoFactory());
        map.put(MemoryDaoFactory.class, new MemoryDaoFactory());
    }

    public static <T> AbstractDaoFactory getFactory(Class<? extends AbstractDaoFactory> type) {
        if (map.containsKey(type)) {
            return map.get(type);
        } else {
            throw new IllegalArgumentException(type.getName());
        }
    }

    public abstract IUserDao getUserDao();

    public abstract INoteDao getNoteDao();
}


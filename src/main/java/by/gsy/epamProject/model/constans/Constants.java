package by.gsy.epamProject.model.constans;

public class Constants {

    public static class Date {
        public static final String DATE_PARSE_PATTERN_2 = "dd-MM";
        public static final String DATE_PARSE_PATTERN = "dd-MM-yyyy";
        public static final int DAYS_TO_NEXT_DAY = 1;
        public static final int DAYS_TO_NEXT_TWO_DAY = 2;
        public static final int DAYS = 100;
    }

    public static class Connection {
        public static final String URL = "jdbc:mysql://localhost/jee";
        public static final String NAME = "root";
        public static final String PASSWORD = "";
        public static final String DRIVER = "com.mysql.jdbc.Driver";
    }

    public static class Query {
        //const for Users
        public static final String INSERT_USER = "insert into users (name, email, password) values (?, ?, ?)";
        public static final String SELECT_FROM_USERS_WHERE_EMAIL = "Select * from users where email = ?";
        //        public static final String SELECT_FROM_USERS_WHERE_EMAIL = "Select * FROM users where email = ?";
        public static final String DELETE_USER = "delete from users where id = ?";
        public static final String SELECT_ALL_USERS = "select * from users";
        //const for Notes
        public static final String INSERT_INTO_NOTES = "insert into notes (id_author, topic, text, date_execution, fixed, deleted, filename) " +
                "values (?, ?, ?, ?, ?, ?, ?)";
        public static final String SELECT_FROM_NOTES = "select * from notes where id_author = ? and id = ?";
        public static final String UPDATE_NOTE = "update notes set topic = ?, text = ?, date_execution = ?, " +
                "fixed = ?, deleted = ?, filename = ? where id_author = ? and id = ?";
        public static final String DELETE_NOTE = "delete from notes where id_author = ? and id = ?";
        public static final String SELECT_ALL_NOTES = "select * from notes where id_author = ?";
        public static final String
                SELECT_NOTES_BY_TYPES_AND_DATA = "select * from notes where id_author = ? and fixed = ? and deleted = ? and date_execution <= ? and date_execution >= ?";
        public static final String SELECT_NOTES_BY_TYPES_AND_DATA_AFTER = "select * from notes where id_author = ? and fixed = ? and deleted = ? and date_execution > ?";
    }


    public static class TablesDB {
        //users
        public static final String ID = "id";
        public static final String NAME = "name";
        public static final String EMAIL = "email";
        public static final String PASSWORD = "password";
        //notes
        public static final String ID_AUTHOR = "id_author";
        public static final String TOPIC = "topic";
        public static final String TEXT = "text";
        public static final String DATE_EXECUTION = "date_execution";
        public static final String FIXED = "fixed";
        public static final String DELETED = "deleted";
        public static final String FILE_NAME = "filename";
    }


    public static class ERRORS {
        public static final String DRIVER_ERROR = "Driver loading error!";
        public static final String MESSAGE_USER = "User is in the database";
        public static final String DATA_BASE_ERROR = " Database error";
        public static final String MESSAGE_NOT_USER = "User isn't in the database!";
        public static final String RECORD_ALREADY_EXIST = "Record already exist";
        public static final String RECORD_NOT_EXIST = "Record doesn't exist";
        public static final String RECORD_CREATE_ERROR = "Record create error";
        public static final String RECORD_UPDATE_ERROR = "Record update error";
        public static final String RECORD_DELETE_ERROR = "The remote recording";
        public static final String RECORD_READING_ERROR = "Record reading error";
        public static final String NOTES_LIST_EMPTY = "Your notes list is empty";
        public static final String CLOSE_CONNECTION = "Ñan not close the connection";
        public static final String ERROR_USER_REQUEST = "Request user from data source error";
    }
}


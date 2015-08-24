package by.gsy.epamProject.controller;

import by.gsy.epamProject.model.factories.AbstractDaoFactory;
import by.gsy.epamProject.model.factories.DataBaseDaoFactory;

public class ConstantsController {

    public static final Class<? extends AbstractDaoFactory> FACTORY = DataBaseDaoFactory.class;

    public static class Fields {
        public static final String ACTION = "action";
        public static final String NAME = "name";
        public static final String EMAIL = "email";
        public static final String PASSWORD = "password";

        public static final String USER = "user";
        public static final String NOTE_ID = "noteId";
        public static final String USERNAME = "username";
        public static final String NOTES_LIST = "notesList";
        public static final String NOTE_TYPE = "noteTypes";
        //        public static final String NOTE_TOPIC = "noteTopic";
//        public static final String NOTE_DATE = "noteDate";
//        public static final String NOTE_TEXT = "noteText";
        public static final String SELECT = "select";
        public static final String ERROR_MESSAGE = "errorMessage";
        public static final String BACK_PAGE = "backPage";
    }

    public static class Pages {
        public static final String LOGIN = "/login.jsp";
        public static final String NOTE = "/note.jsp";
        public static final String ERROR = "/error.jsp";
        public static final String REGISTRATION = "/registration.jsp";
        public static final String ADD_NOTE = "/addNote.jsp";
    }

    public static class Controllers {
        public static final String NOTE = "/note";
        public static final String LOGIN = "/login";
        public static final String ERORR = "/error";
        public static final String ADD = "/addNote";
        public static final String ACTION_BUTTON = "/actionButton";


    }

    public static class MessagesError {
        public static final String NOTES_LIST_EMPTY = "Your list of notes is empty";
        public static final String REGISTRATION_SUCCESSFULLY_COMPLETED = "Registration successfully completed";
        public static final String EMPTY_FIELDS = "Empty fields";
        public static final String INVALID_LOGIN_OR_PASSWORD = "Invalid login or password";
        public static final String TASKS_REQUEST_ERROR = "Tasks request error";
        public static final String UNSUPPORTED = "Unsupported error. Don't panic and contact to developers";
    }

    public static class Actions {
        public static final String ACTION = "action";
        public static final String LOGIN = "login_button";
        public static final String REGISTER = "register_button";
        public static final String DOWNLOAD_FILE = "downloadFile";


        public static final String ADD = "add";
        public static final String REMOVE = "remove";
        public static final String REMOVE_FILE = "removeFile";
        public static final String REMOVE_ALL = "removeAll";
        public static final String FIX = "fixedNote";
        public static final String USER_DELETE = "userDelete";
    }

    public static class File {

        public static final String FILEPATH = "filepath";
        public static final String APPLICATION_OCTET_STREAM = "APPLICATION/OCTET-STREAM";
        public static final String CONTENT_DISPOSITION = "Content-Disposition";
        public static final String ATTACHMENT_FILENAME = "attachment; filename=\"";
    }
}

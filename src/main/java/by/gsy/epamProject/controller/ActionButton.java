package by.gsy.epamProject.controller;


import by.gsy.epamProject.model.beans.Note;
import by.gsy.epamProject.model.beans.NoteTypes;
import by.gsy.epamProject.model.beans.User;
import by.gsy.epamProject.model.dao.INoteDao;
import by.gsy.epamProject.model.dao.IUserDao;
import by.gsy.epamProject.model.factories.AbstractDaoFactory;
import by.gsy.epamProject.requestparser.FileManagement;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class ActionButton extends AbstractController {


    @Override
    protected void performTask(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String action = req.getParameter(ConstantsController.Actions.ACTION);
        User user = (User) req.getSession().getAttribute(ConstantsController.Fields.USER);
        INoteDao noteDao = AbstractDaoFactory.getFactory(ConstantsController.FACTORY).getNoteDao();
        IUserDao userDao = AbstractDaoFactory.getFactory(ConstantsController.FACTORY).getUserDao();

        if (ConstantsController.Actions.REMOVE_ALL.equals(action)) {
            List<Note> notes = noteDao.getAll(user.getId(), NoteTypes.RECYCLE_BIN);
            for (Note note : notes) {
                noteDao.delete(note);
            }
            //переделать
//        } else if (ConstantsController.Actions.USER_DELETE.equals(action)) {
//            List<Note> notes = noteDao.getAll(user.getId(), NoteTypes.ALL);
//            for (Note note : notes) {
//                noteDao.delete(note);
//            }
//            List<Note> notes1 = noteDao.getAll(user.getId(), NoteTypes.FIXED);
//            for (Note note : notes1) {
//                noteDao.delete(note);
//            }
//            List<Note> notes2 = noteDao.getAll(user.getId(), NoteTypes.RECYCLE_BIN);
//            for (Note note : notes2) {
//                noteDao.delete(note);
//            }
//            userDao.deleteUser(user);
//            jumpTo(ConstantsController.Pages.LOGIN, req, resp);
        } else if (ConstantsController.Actions.REMOVE_FILE.equals(action)) {
            int noteId = Integer.parseInt(req.getParameter(ConstantsController.Fields.NOTE_ID));
            Note note = noteDao.read(user.getId(), noteId);
            String path = FileManagement.concatPath(getServletContext().getInitParameter(ConstantsController.File.FILEPATH), user.getEmail());
            FileManagement.deleteFile(note.getFilename(), path);
            note.setFilename("");
            noteDao.update(note);

        } else if (ConstantsController.Actions.DOWNLOAD_FILE.equals(action)) {
            String noteId = req.getParameter(ConstantsController.Fields.NOTE_ID);
            Note note = noteDao.read(user.getId(), Integer.parseInt(noteId));
            downloadFile(user, note, resp);

        } else {
            NoteTypes noteTypes = (NoteTypes) req.getSession().getAttribute(ConstantsController.Fields.NOTE_TYPE);
            String[] notesId = req.getParameterValues(ConstantsController.Fields.SELECT);
            if (notesId != null) {
                for (String id : notesId) {
                    if (ConstantsController.Actions.REMOVE.equals(action)) {
                        Note note = noteDao.read(user.getId(), Integer.parseInt(id));
                        if (noteTypes != null && NoteTypes.RECYCLE_BIN.equals(noteTypes)) {
                            noteDao.delete(note);
                        } else {
                            note.setIsDeleted(true);
                            noteDao.update(note);
                        }
                    } else if (ConstantsController.Actions.FIX.equals(action)) {
                        Note note = noteDao.read(user.getId(), Integer.parseInt(id));
                        note.setIsFixed(true);
                        noteDao.update(note);
                    }
                }
            }
        }
        jumpTo(ConstantsController.Controllers.NOTE, req, resp);
    }
}


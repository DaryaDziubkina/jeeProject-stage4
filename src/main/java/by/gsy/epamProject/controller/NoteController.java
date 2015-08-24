package by.gsy.epamProject.controller;


import by.gsy.epamProject.model.beans.Note;
import by.gsy.epamProject.model.beans.NoteTypes;
import by.gsy.epamProject.model.beans.User;
import by.gsy.epamProject.model.dao.INoteDao;
import by.gsy.epamProject.model.exeption.DSException;
import by.gsy.epamProject.model.factories.AbstractDaoFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;


public class NoteController extends AbstractController {

    @Override
    protected void performTask(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String noteTypeStr = req.getParameter(ConstantsController.Actions.ACTION);
        NoteTypes noteTypes;

        if (noteTypeStr == null) {
            noteTypes = (NoteTypes) req.getSession().getAttribute(ConstantsController.Fields.NOTE_TYPE);
            if (noteTypes == null) {
                noteTypes = NoteTypes.TODAY;
            }
        } else {
            try {
                noteTypes = NoteTypes.valueOf(noteTypeStr);
            } catch (IllegalArgumentException e) {
                noteTypes = NoteTypes.TODAY;
            }
        }


        try {
            User user = (User) req.getSession().getAttribute(ConstantsController.Fields.USER);
//            req.setAttribute(ConstantsController.Fields.WITH_DATE, noteTypes.isDateShow());
//            req.setAttribute(ConstantsController.Fields.BUTTON_FIX, noteTypes.isFixedShow());
            req.setAttribute(ConstantsController.Fields.USERNAME, user.getName());
            req.getSession().setAttribute(ConstantsController.Fields.NOTE_TYPE, noteTypes);

            INoteDao noteDao = AbstractDaoFactory.getFactory(ConstantsController.FACTORY).getNoteDao();
            List<Note> list = noteDao.getAll(user.getId(), noteTypes);
            req.setAttribute(ConstantsController.Fields.NOTES_LIST, list);
//req.setAttribute(ConstantsController.Fields.NOTE_TYPE, noteTypes.getValue());
        } catch (DSException e) {
            jumpToError(ConstantsController.MessagesError.TASKS_REQUEST_ERROR, ConstantsController.Pages.NOTE, req, resp);
        }
        jumpTo(ConstantsController.Pages.NOTE, req, resp);

    }
}



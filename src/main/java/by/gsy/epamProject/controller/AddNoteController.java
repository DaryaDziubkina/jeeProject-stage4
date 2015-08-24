package by.gsy.epamProject.controller;


import by.gsy.epamProject.model.beans.Note;
import by.gsy.epamProject.model.beans.NoteTypes;
import by.gsy.epamProject.model.beans.User;
import by.gsy.epamProject.model.constans.Constants;
import by.gsy.epamProject.model.dao.INoteDao;
import by.gsy.epamProject.model.exeption.ValidationException;
import by.gsy.epamProject.model.factories.AbstractDaoFactory;
import by.gsy.epamProject.requestparser.FileManagement;
import by.gsy.epamProject.requestparser.RequestBody;
import by.gsy.epamProject.requestparser.RequestParser;
import by.gsy.epamProject.requestparser.UploadedFile;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;
import java.time.LocalDate;

public class AddNoteController extends AbstractController {

    @Override
    protected void performTask(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestBody request = RequestParser.parse(req);

        String action = request.getParameter(ConstantsController.Actions.ACTION);
        if (ConstantsController.Actions.ADD.equals(action)) {
            String topic = request.getParameter(Constants.TablesDB.TOPIC);
            String text = request.getParameter(Constants.TablesDB.TEXT);
            String dateExecution = request.getParameter(Constants.TablesDB.DATE_EXECUTION);
            UploadedFile file = request.getFile(Constants.TablesDB.FILE_NAME);


            User user = (User) req.getSession().getAttribute(ConstantsController.Fields.USER);
            NoteTypes noteTypes = (NoteTypes) req.getSession().getAttribute(ConstantsController.Fields.NOTE_TYPE);
            Note note = new Note();

            INoteDao noteDao = AbstractDaoFactory.getFactory(ConstantsController.FACTORY).getNoteDao();
            try {
                validateField(topic);
                validateField(text);
                if (NoteTypes.TODAY.equals(noteTypes)) {

                    note.setDateExecution(Date.valueOf(LocalDate.now()));
                } else if (NoteTypes.TOMORROW.equals(noteTypes)) {
                    note.setDateExecution(Date.valueOf(LocalDate.now().plusDays(Constants.Date.DAYS_TO_NEXT_DAY)));
                } else {
                    validateField(dateExecution);
                    note.setDateExecution(dateExecution);
                }

                note.setAuthor(user.getId());
                note.setTopic(topic);
                note.setText(text);

                if (!file.isEmpty()) {
                    String filepath = getServletContext().getInitParameter(ConstantsController.File.FILEPATH);
                    filepath = FileManagement.concatPath(filepath, user.getEmail());
                    if (FileManagement.saveFile(file, filepath)) {
                        note.setFilename(file.getFilename());
                    }
                }
                noteDao.create(note);
                req.setAttribute(ConstantsController.Fields.NAME, user.getName());
                redirectTo(ConstantsController.Controllers.NOTE, req, resp);
            } catch (ValidationException e) {
                jumpToError(ConstantsController.MessagesError.EMPTY_FIELDS, ConstantsController.Pages.ADD_NOTE, req, resp);
            }
        } else {
            jumpTo(ConstantsController.Pages.ADD_NOTE, req, resp);
        }
    }
}

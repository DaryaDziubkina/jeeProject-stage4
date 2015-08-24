package by.gsy.epamProject.controller;

import by.gsy.epamProject.model.beans.Note;
import by.gsy.epamProject.model.beans.User;
import by.gsy.epamProject.model.dao.INoteDao;
import by.gsy.epamProject.model.factories.AbstractDaoFactory;
import by.gsy.epamProject.requestparser.FileManagement;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;

public abstract class AbstractController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        performTask(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        performTask(req, resp);
    }

    protected abstract void performTask(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException;

    protected void jumpTo(String page, HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        getServletContext().getRequestDispatcher(page).forward(req, resp);
    }

    protected void redirectTo(String page, HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.sendRedirect(page);
    }

    protected void validateField(String field) {
        if (field == null || "".equals(field)) {
            throw new IllegalArgumentException(field);
        }
    }

    protected void jumpToError(String message,
                               HttpServletRequest req,
                               HttpServletResponse resp)
            throws ServletException, IOException {
        req.setAttribute(ConstantsController.Fields.ERROR_MESSAGE, message);
        jumpTo(ConstantsController.Pages.ERROR, req, resp);
    }

    protected void jumpToError(String message, String backPage,
                               HttpServletRequest req,
                               HttpServletResponse resp)
            throws ServletException, IOException {
        req.setAttribute(ConstantsController.Fields.BACK_PAGE, backPage);
        jumpToError(message, req, resp);
    }

    protected void downloadFile(User user, Note note, HttpServletResponse resp) throws ServletException, IOException {
        INoteDao noteDao = AbstractDaoFactory.getFactory(ConstantsController.FACTORY)
                .getNoteDao();

        String filename = note.getFilename();
        String filepath = getServletContext().getInitParameter(ConstantsController.File.FILEPATH);
        resp.setContentType(ConstantsController.File.APPLICATION_OCTET_STREAM);
        resp.setHeader(ConstantsController.File.CONTENT_DISPOSITION, ConstantsController.File.ATTACHMENT_FILENAME + filename + "\"");

        String path = FileManagement.concatPath(filepath, user.getEmail(), filename);

        FileInputStream fileInputStream = new FileInputStream(path);
        int i;
        PrintWriter out = resp.getWriter();
        while ((i = fileInputStream.read()) != -1) {
            out.write(i);
        }
        fileInputStream.close();
        out.close();
    }
}

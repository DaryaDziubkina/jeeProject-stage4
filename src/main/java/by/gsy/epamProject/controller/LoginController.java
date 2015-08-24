package by.gsy.epamProject.controller;


import by.gsy.epamProject.model.beans.User;
import by.gsy.epamProject.model.dao.IUserDao;
import by.gsy.epamProject.model.exeption.DSException;
import by.gsy.epamProject.model.exeption.ValidationException;
import by.gsy.epamProject.model.factories.AbstractDaoFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class LoginController extends AbstractController {

    @Override
    protected void performTask(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter(ConstantsController.Fields.ACTION);
        String email = req.getParameter(ConstantsController.Fields.EMAIL);
        String password = req.getParameter(ConstantsController.Fields.PASSWORD);

        IUserDao userDao = AbstractDaoFactory.getFactory(ConstantsController.FACTORY).getUserDao();

        if (ConstantsController.Actions.LOGIN.equals(action)) {
            try {
                validateField(email);
                validateField(password);
                User user = userDao.getUser(email);
                if ((user.getPassword().equals(password))) {
                    req.getSession(true).setAttribute(ConstantsController.Fields.USER, user);
                    req.setAttribute(ConstantsController.Fields.NAME, user.getName());
                    jumpTo(ConstantsController.Controllers.NOTE, req, resp);
                } else {
                    jumpToError(ConstantsController.MessagesError.INVALID_LOGIN_OR_PASSWORD, req, resp);
                }

            } catch (ValidationException e) {
                jumpToError(ConstantsController.MessagesError.EMPTY_FIELDS, req, resp);
            } catch (DSException e) {
                jumpToError(ConstantsController.MessagesError.INVALID_LOGIN_OR_PASSWORD, req, resp);

            }
        } else {
            redirectTo(ConstantsController.Pages.LOGIN, req, resp);
        }
    }
}

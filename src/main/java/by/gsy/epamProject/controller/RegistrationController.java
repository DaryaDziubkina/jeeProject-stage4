package by.gsy.epamProject.controller;

import by.gsy.epamProject.model.beans.User;
import by.gsy.epamProject.model.dao.IUserDao;
import by.gsy.epamProject.model.exeption.DSException;
import by.gsy.epamProject.model.factories.AbstractDaoFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


public class RegistrationController extends AbstractController {

    @Override
    protected void performTask(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter(ConstantsController.Fields.ACTION);

        if (ConstantsController.Actions.REGISTER.equals(action)) {
            String email = req.getParameter(ConstantsController.Fields.EMAIL);
            String password = req.getParameter(ConstantsController.Fields.PASSWORD);
            String name = req.getParameter(ConstantsController.Fields.NAME);

            IUserDao userDao = AbstractDaoFactory.getFactory(ConstantsController.FACTORY).getUserDao();
            User user = new User();
            try {
                validateField(email);
                validateField(password);
                validateField(name);
                user.setName(name);
                user.setEmail(email);
                user.setPassword(password);
                userDao.createUser(user);
                user = userDao.getUser(user.getEmail());

                req.getSession(true).setAttribute(ConstantsController.Fields.USER, user);
                req.setAttribute(ConstantsController.Fields.NAME, user.getName());
                redirectTo(ConstantsController.Controllers.NOTE, req, resp);
            } catch (DSException e) {
                jumpToError(ConstantsController.MessagesError.REGISTRATION_SUCCESSFULLY_COMPLETED, ConstantsController.Pages.REGISTRATION, req, resp);
            } catch (IllegalArgumentException e) {
                jumpToError(ConstantsController.MessagesError.EMPTY_FIELDS, ConstantsController.Pages.REGISTRATION, req, resp);
            }
        } else {
            jumpTo(ConstantsController.Pages.LOGIN, req, resp);
        }
    }
}

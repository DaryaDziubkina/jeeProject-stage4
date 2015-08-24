package by.gsy.epamProject.controller;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ActionController extends AbstractController {
    @Override
    protected void performTask(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter(ConstantsController.Actions.ACTION);
        if (action != null) {
            if (ConstantsController.Actions.REMOVE.equals(action) || ConstantsController.Actions.FIX.equals(action) ||
                    ConstantsController.Actions.USER_DELETE.equals(action) ||
                    ConstantsController.Actions.REMOVE_FILE.equals(action) ||
                    ConstantsController.Actions.DOWNLOAD_FILE.equals(action)) {
                jumpTo(ConstantsController.Controllers.ACTION_BUTTON, req, resp);
            } else if (ConstantsController.Actions.ADD.equals(action)) {
                jumpTo(ConstantsController.Pages.ADD_NOTE, req, resp);
            }
        }
        jumpTo(ConstantsController.Controllers.NOTE, req, resp);
    }
}


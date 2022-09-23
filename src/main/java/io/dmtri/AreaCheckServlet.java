package io.dmtri;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = "/check_point")
public class AreaCheckServlet extends HttpServlet {
    private static final long serialVersionUID = -7754328558138565095L;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        float x, y, r;

        try {
            x = Float.parseFloat(req.getParameter("x"));
            y = Float.parseFloat(req.getParameter("y"));
            r = Float.parseFloat(req.getParameter("r"));
        } catch (NullPointerException | NumberFormatException e) {
            resp.sendError(400, "Invalid coordinates\n" + e);
            return;
        }

        resp.getWriter().println("x = " + x);
        resp.getWriter().println("y = " + y);
        resp.getWriter().println("r = " + r);
    }
}

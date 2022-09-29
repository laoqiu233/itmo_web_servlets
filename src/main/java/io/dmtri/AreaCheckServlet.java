package io.dmtri;

import java.io.IOException;
import java.util.Date;

import io.dmtri.areas.Area;
import io.dmtri.attemptsmanagers.AttemptsManager;
import io.dmtri.models.Point;
import io.dmtri.models.PointAttempt;
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
        final long start = System.nanoTime();
        

        double x, y, r;

        try {
            x = Double.parseDouble(req.getParameter("x"));
            y = Double.parseDouble(req.getParameter("y"));
            r = Double.parseDouble(req.getParameter("r"));
        } catch (NullPointerException | NumberFormatException e) {
            resp.sendError(400, "Invalid coordinates\n" + e);
            return;
        }

        final Point point = new Point(x, y, r);

        final AttemptsManager am = (AttemptsManager) req.getAttribute("attemptsManager");
        final Area checker = (Area) req.getAttribute("checker");
        final boolean res = checker.checkPoint(point);

        final long end = System.nanoTime();

        final PointAttempt attempt = new PointAttempt(point, System.currentTimeMillis(), (end - start) / 1000d, res);
        am.addAttempt(attempt);
        
        resp.sendRedirect(getServletContext().getContextPath() + "/?show-attempt=" + (am.getAttemptsCount() - 1));
    }
}

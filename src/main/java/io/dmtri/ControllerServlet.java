package io.dmtri;

import java.io.IOException;
import java.util.Base64;

import io.dmtri.areas.AndArea;
import io.dmtri.areas.Area;
import io.dmtri.areas.CircleArea;
import io.dmtri.areas.NotArea;
import io.dmtri.areas.OrArea;
import io.dmtri.areas.QuadrantArea;
import io.dmtri.areas.RectangleArea;
import io.dmtri.areas.RotatedArea;
import io.dmtri.areas.TranslatedArea;
import io.dmtri.attemptsmanagers.AttemptsManager;
import io.dmtri.attemptsmanagers.SessionAttemptsManager;
import io.dmtri.formmanagers.FormManager;
import io.dmtri.formmanagers.InputElement;
import io.dmtri.formmanagers.RadioInputElement;
import io.dmtri.formmanagers.SelectInputElement;
import io.dmtri.formmanagers.TextInputElement;
import io.dmtri.models.Point;
import io.dmtri.models.PointAttempt;
import io.dmtri.utils.BitmapEncoder;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Controller class, proxies every other servlet.
 */
@WebServlet(urlPatterns = "/", loadOnStartup = 0)
public class ControllerServlet extends HttpServlet {
    private static final long serialVersionUID = 5808170032936139555L;
    private static final int BITMAP_RESOLUTION = 300;
    // Task graph
    // private static final Area checker = new OrArea(
    //     new TranslatedArea(0.5, 0.5, new RectangleArea(1, 1)),
    //     new QuadrantArea(QuadrantArea.LOWER_LEFT, new CircleArea(0.5)),
    //     new QuadrantArea(QuadrantArea.LOWER_RIGHT, new RotatedArea(Math.PI / 4, new RectangleArea(Math.sqrt(2), Math.sqrt(2))))
    // );
    // Roblox logo graph
    // private static final Area checker = new RotatedArea(Math.PI * -10 / 180, new AndArea(
    //     new RectangleArea(2, 2),
    //     new NotArea(new RectangleArea(0.5, 0.5)) 
    // ));
    // Male genital graph
    private static final Area checker = new OrArea(
        new QuadrantArea(QuadrantArea.LOWER_LEFT | QuadrantArea.UPPER_LEFT, new TranslatedArea(-0.3, -0.3, new CircleArea(0.5))),
        new QuadrantArea(QuadrantArea.LOWER_RIGHT | QuadrantArea.UPPER_RIGHT, new TranslatedArea(0.3, -0.3, new CircleArea(0.5))),
        new TranslatedArea(0, 0.5, new RectangleArea(0.5, 1)),
        new TranslatedArea(0, 1, new CircleArea(0.25))
    );
    private static final boolean[][] bitmap = checker.generateBitmap(BITMAP_RESOLUTION);
    private static final String bitmapB64 = BitmapEncoder.encode(bitmap, BITMAP_RESOLUTION);
    private static final FormManager fm = new FormManager(
        new TextInputElement(-3, 5),
        new RadioInputElement(-2, -1.5, -1, -0.5, 0, 0.5, 1, 1.5, 2),
        new SelectInputElement(1, 2, 3, 4, 5)
    );

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Dependency injection
        final AttemptsManager am = new SessionAttemptsManager(req.getSession());
        req.setAttribute("attemptsManager", am);
        req.setAttribute("checker", checker);
        req.setAttribute("formManager", fm);

        // Forward request if all parameters are present
        if (req.getParameter("x") != null && req.getParameter("y") != null && req.getParameter("r") != null) {
            getServletContext().getRequestDispatcher("/check_point").forward(req, resp);
            return;
        }

        if (req.getParameter("show-attempt") != null) {
            PointAttempt attemptToShow;
            try {
                attemptToShow = am.getAttempts().get(Integer.parseInt(req.getParameter("show-attempt")));
            } catch (NumberFormatException | IndexOutOfBoundsException e) {
                attemptToShow = null;
            }

            req.setAttribute("attemptToShow", attemptToShow);
            getServletContext().getRequestDispatcher("/result.jsp").forward(req, resp);
            return;
        }

        final String requestedPath = req.getRequestURI().substring(req.getContextPath().length());
        
        if (requestedPath.equals("/")) {
            getServletContext().getRequestDispatcher("/main.jsp").forward(req, resp);
        } else if (requestedPath.equals("/getBitmap")) {
            resp.getWriter().print(bitmapB64);
        } else {
            getServletContext().getNamedDispatcher("default").forward(req, resp);
        }
    }
}

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
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = "/", loadOnStartup = 0)
public class ControllerServlet extends HttpServlet {
    private static final long serialVersionUID = 5808170032936139555L;
    private static final int BITMAP_RESOLUTION = 300;
    //private static final Area checker = new TranslatedArea(0.5, 0, new CircleArea(0.5));
    //private static final Area checker = new RectangleArea(1, 1);
    // private static final Area checker = new OrArea(
    //     new TranslatedArea(0.5, 0.5, new RectangleArea(1, 1)),
    //     new QuadrantArea(QuadrantArea.LOWER_LEFT, new CircleArea(0.5)),
    //     new QuadrantArea(QuadrantArea.LOWER_RIGHT, new RotatedArea(Math.PI / 4, new RectangleArea(Math.sqrt(2), Math.sqrt(2))))
    // );
    private static final Area checker = new AndArea(
        new RotatedArea(Math.PI * -10 / 180, new RectangleArea(2, 2)),
        new NotArea(new RotatedArea(Math.PI * -10 / 180, new RectangleArea(0.3, 0.3)))
    );
    private static final boolean[][] bitmap = checker.generateBitmap(BITMAP_RESOLUTION);
    private String bitmapB64;

    @Override
    public void init() throws ServletException {
        // Encodes the bitmap in base64 for frontend use.

        byte[] bitmapBytes = new byte[(int) Math.ceil(BITMAP_RESOLUTION * BITMAP_RESOLUTION / 8.0)];

        getServletContext().log("Encoded bitmap as byte array. Size: " + bitmapBytes.length);

        for (int i = 0; i < BITMAP_RESOLUTION * BITMAP_RESOLUTION; i++) {
            final int byteCount = i / 8;
            bitmapBytes[byteCount] <<= 1;
            if (bitmap[i / BITMAP_RESOLUTION][i % BITMAP_RESOLUTION]) bitmapBytes[byteCount]++;
        }

        bitmapB64 = Base64.getEncoder().encodeToString(bitmapBytes);

        getServletContext().log("Encoded bitmap byte array in base 64. Size: " + bitmapB64.length());
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Forward request if all parameters are present
        if (req.getParameter("x") != null && req.getParameter("y") != null && req.getParameter("r") != null) {
            getServletContext().getRequestDispatcher("/check_point").forward(req, resp);
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

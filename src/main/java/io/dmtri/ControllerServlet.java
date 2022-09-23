package io.dmtri;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Base64;

import io.dmtri.areas.Area;
import io.dmtri.areas.RectangleArea;
import io.dmtri.areas.RotatedArea;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("")
public class ControllerServlet extends HttpServlet {
    private static final long serialVersionUID = 5808170032936139555L;
    private static final int BITMAP_RESOLUTION = 100;
    private static final Area checker = new RotatedArea(Math.PI * 45 / 180, new RectangleArea(1, 1));
    private static final boolean[][] bitmap = checker.generateBitmap(BITMAP_RESOLUTION);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Forward request if all parameters are present
        if (req.getParameter("x") != null && req.getParameter("y") != null && req.getParameter("r") != null) {
            getServletContext().getRequestDispatcher("/check_point").forward(req, resp);
        }

        //getServletContext().getRequestDispatcher("/WEB-INF/main.jsp").forward(req, resp);

        byte[] bitmapBytes = new byte[(int) Math.ceil(BITMAP_RESOLUTION * BITMAP_RESOLUTION / 8.0)];
        System.out.println(bitmapBytes.length);
        for (int i = 0; i < BITMAP_RESOLUTION * BITMAP_RESOLUTION; i++) {
            final int byteCount = i / 8;
            bitmapBytes[byteCount] <<= 1;
            if (bitmap[i / BITMAP_RESOLUTION][i % BITMAP_RESOLUTION]) bitmapBytes[byteCount]++;
        }

        for (byte b : Base64.getEncoder().encode(bitmapBytes)) {
            System.out.println(b);
        }

        resp.getWriter().print(Base64.getEncoder().encodeToString(bitmapBytes));
    }
}

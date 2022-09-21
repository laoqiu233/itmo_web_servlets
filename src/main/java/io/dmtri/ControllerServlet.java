package io.dmtri;

import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class ControllerServlet extends HttpServlet {
    private static final long serialVersionUID = 5808170032936139555L;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String param = req.getParameter("test");

        PrintWriter writer = resp.getWriter();
        writer.append("<html><body>");
        writer.append(param == null ? "null" : param);
        writer.append("</body></html>");
    } 
}

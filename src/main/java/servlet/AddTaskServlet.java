package servlet;

import manager.TaskManager;
import model.Task;
import model.TaskStatusType;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@WebServlet(urlPatterns = "/addTask")
public class AddTaskServlet extends HttpServlet {
    private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

    private TaskManager taskManager = new TaskManager();

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        String description = req.getParameter("description");
        String date = req.getParameter("date");
        TaskStatusType status = TaskStatusType.valueOf(req.getParameter("status"));
        long userId = Long.parseLong(req.getParameter("user_id"));
        try {
            taskManager.add(Task.builder()
                    .name(name)
                    .description(description)
                    .deadline(sdf.parse(date))
                    .taskStatusType(status)
                    .userId((int) userId)
                    .build());
            resp.sendRedirect("/managerHome");
        } catch (ParseException e) {
            e.printStackTrace();
        }


    }
}

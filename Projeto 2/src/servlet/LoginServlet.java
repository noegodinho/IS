package servlet;

import data.Administrator;
import data.Professor;
import data.User;
import ejb.ClientBean;
import ejb.ClientBeanRemote;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;


@WebServlet(name = "/LoginServlet")
public class LoginServlet extends HttpServlet{
    private static final long serialVersionUID = 1L;

    @EJB
    private ClientBeanRemote ejbremote;
    private Logger logger;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet(){
        super();
        this.logger = LoggerFactory.getLogger(ClientBean.class);
    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        HttpSession session = request.getSession();
        String instEmail = request.getParameter("instEmail");
        String password = request.getParameter("password");
        PrintWriter out = response.getWriter();
        ArrayList<String> menuOptions = new ArrayList<>();

        if(instEmail.isEmpty() || password.isEmpty()){
            out.println("<script type=\"text/javascript\">");
            out.println("if (confirm(\"Need to fill all the fields!\")) {}");
            out.println("window.location.replace(\"http://localhost:8080/Web/index.jsp\");");
            out.println("</script>");
            logger.error("User did not input email and/or password");
        }

        else{
            try{
                String hashedPassword = new Utils().createHash(password);

                User loggedUser = this.ejbremote.loginUser(instEmail, hashedPassword);

                if(loggedUser != null){
                    request.setAttribute("user", loggedUser);
                    session.setAttribute("user", loggedUser);
                    setOptions(menuOptions, loggedUser);
                    session.setAttribute("options", menuOptions);
                    request.getRequestDispatcher("menu.jsp").forward(request, response);
                }

                else{
                    out.println("<script type=\"text/javascript\">");
                    out.println("if (confirm(\"Wrong email and/or password!\")) {}");
                    out.println("window.location.replace(\"http://localhost:8080/Web/index.jsp\");");
                    out.println("</script>");
                    logger.error("Wrong email and/or password");
                }
            }catch(EJBException ejbe){
                logger.error("Magic on login");
            }
        }
    }

    private void setOptions(ArrayList<String> menuOptions, User user){
        if(user instanceof Administrator){ //admin
            menuOptions.add("Create New User");
            menuOptions.add("Edit User Information");
            menuOptions.add("Create New Course");
            menuOptions.add("Edit Course Information");
            menuOptions.add("Delete User");
            menuOptions.add("Delete Course");
            menuOptions.add("Upload Course Material");
            menuOptions.add("Delete Material");
            menuOptions.add("List Students");
            menuOptions.add("Search Student");
        }

        if(user instanceof Professor){ //prof
            menuOptions.add("Upload Course Material");
            menuOptions.add("Delete Material");
            menuOptions.add("List Students");
            menuOptions.add("Search Student");
        }

        menuOptions.add("List Course");
        menuOptions.add("List Materials");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        response.getWriter().append("Served at: ").append(request.getContextPath());
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        if(request.getParameter("action").equals("login")){
            processRequest(request, response);
        }
    }
}

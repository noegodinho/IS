package servlet;

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
public class LoginServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @EJB
    private ClientBeanRemote ejbremote;
    private Logger logger;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet(){
        super();
        logger = LoggerFactory.getLogger(ClientBean.class);
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String instEmail = request.getParameter("instEmail");
        String password = request.getParameter("password");
        PrintWriter out = response.getWriter();
        ArrayList<String> menuOptions = new ArrayList<>();

        if(instEmail.isEmpty() || password.isEmpty()){
            out.println("<script type=\"text/javascript\">");
            out.println("if (confirm(\"Need to fill all the fields!\")) {return true;}");
            out.println("window.location.replace(\"http://localhost:8080/Projeto_2_war_exploded/index.jsp\");");
            out.println("</script>");
            logger.error("User did not input email and/or password");
        }

        else{
            try{
                User loggedUser = ejbremote.loginUser(instEmail, password);
                if(loggedUser != null){
                    request.setAttribute("user", loggedUser);
                    session.setAttribute("user", loggedUser);
                    setOptions(menuOptions,loggedUser.getUserType());
                    session.setAttribute("options", menuOptions);
                    request.getRequestDispatcher("menu.jsp").forward(request, response);
                }
                else{
                    out.println("<script type=\"text/javascript\">");
                    out.println("if (confirm(\"Wrong password!\")) {return true;}");
                    out.println("window.location.replace(\"http://localhost:8080/Projeto_2_war_exploded/index.jsp\");");
                    out.println("</script>");
                    logger.error("User input wrong password");
                }

            }catch(EJBException ejbe){
                out.println("<script type=\"text/javascript\">");
                out.println("if (confirm(\"No account found related to this email!!\")) {return true;}");
                out.println("window.location.replace(\"http://localhost:8080/Projeto_2_war_exploded/index.jsp\");");
                out.println("</script>");
                logger.error("User does not exist");
            }
        }
    }

    //If userType remains int
    private void setOptions(ArrayList<String> menuOptions, int userType ){
        switch(userType){
            case 0: //admin
                menuOptions.add("Create New User");
                menuOptions.add("Edit User Information");
                menuOptions.add("Create New Course");
                menuOptions.add("Edit Course Information");
                menuOptions.add("Delete User");
                menuOptions.add("Delete Course");
            case 1: //prof
                menuOptions.add("Upload Course Material");
                menuOptions.add("Delete Material");
                menuOptions.add("List Students");
                menuOptions.add("Search Student");
            case 2: //student
                menuOptions.add("List Course");
                menuOptions.add("List Materials");
                break;
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.getWriter().append("Served at: ").append(request.getContextPath());
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        logger.error("NAO ENCONTRA POST");
        if(request.getParameter("action").equals("login"))
            processRequest(request, response);
    }
}

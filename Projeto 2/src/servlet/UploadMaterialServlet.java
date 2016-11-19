package servlet;

import ejbservices.ProfBeanRemote;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Paths;

@WebServlet(name = "/UploadMaterialServlet")
@MultipartConfig
public class UploadMaterialServlet extends HttpServlet {

    @EJB
    ProfBeanRemote ejbremote;

    public UploadMaterialServlet(){
        super();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        if(request.getParameter("action").equals("upload")) {
            Part filePart = request.getPart("file");
            String fileName = filePart.getSubmittedFileName();
            InputStream fileContent = filePart.getInputStream();

            //TRATAR DO FICHEIRITO AQUI
            //AQUI
            //AQUI CRL
            //CHUPMOS OH BOI
            //ORE WA OCHINCHIN GA DAISUKI NANDAYO (se estiver mal é porque nao fiz copy-paste e escrevi de cabeça)
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}

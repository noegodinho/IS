package servlet;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.security.NoSuchAlgorithmException;
import java.util.Calendar;
import java.util.Date;

public class UtilsServlet{
    public Date getDate(Integer day, Integer month, Integer year) {
        if (day!= null && month != null && year!=null) {
            Calendar cal = Calendar.getInstance();
            cal.set(Calendar.YEAR, year);
            cal.set(Calendar.MONTH, month - 1);
            cal.set(Calendar.DAY_OF_MONTH, day);
            return cal.getTime();
        }

        else{
            return null;
        }
    }

    public String createHash(String password){
        try{
            java.security.MessageDigest md = java.security.MessageDigest.getInstance("MD5");
            byte[] array = md.digest(password.getBytes());
            StringBuffer sb = new StringBuffer();

            for(int i = 0; i < array.length; ++i){
                sb.append(Integer.toHexString((array[i] & 0xFF) | 0x100).substring(1,3));
            }

            return sb.toString();
        }catch(NoSuchAlgorithmException e){
        }

        return null;
    }

    public void popupMessage(HttpServletResponse response, String message, String webpage) throws IOException {
        PrintWriter out = response.getWriter();

        out.println("<script type=\"text/javascript\">");
        out.println("if (confirm(\""+message+"\")) {}");
        out.println("window.location.replace(\"http://localhost:8080/Web/"+webpage+".jsp\");");
        out.println("</script>");
        out.close();
    }
}

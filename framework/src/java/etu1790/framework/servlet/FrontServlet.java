package etu1790.framework.servlet;

import etu1790.framework.Mapping;
import fonctions.Utilitaire;
import fonctions.Utils;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Vector;


public class FrontServlet extends HttpServlet {
    private final HashMap<String, Mapping> mappingUrls = new HashMap<>();
   @Override
    public void init()
    {
       try
        {
            Vector<String> paths = new Utilitaire().readPackage(getServletContext().getResource(".").toURI().getPath() + "WEB-INF/classes");
            for (String path : paths)
            {
                Vector<Utilitaire> utilitaires = new Utilitaire().readAnnotation(path);
                for (Utilitaire utilitaire : utilitaires)
                {
                    mappingUrls.put(utilitaire.getValue(), new Mapping(utilitaire.getClassName(), utilitaire.getMethod()));
                }
            }
        }
        catch (Exception ignored)
        {
              System.out.println(ignored.getMessage());
        } 
    }    
    public void processRequest(HttpServletRequest request, HttpServletResponse response) throws IOException
    {
        try{
            response.setContentType("text/html");
            PrintWriter out = response.getWriter();
            out.println("<html><body>");
            out.println(this.mappingUrls);
            out.println(this.mappingUrls.get("/Emp-All").getMethod());
            out.println("</body></html>");
        }catch(Exception e){
        }
        
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }

}

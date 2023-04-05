package etu1790.framework.servlet;

import etu1790.framework.Mapping;
import etu1790.framework.ModelView;
import fonctions.Utilitaire;
import fonctions.Utils;
import jakarta.servlet.RequestDispatcher;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;
import java.util.ArrayList;
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
            String a=request.getServletPath();
            System.out.println("le url="+a);
            if(this.mappingUrls.containsKey(a)){
                    Mapping map= this.mappingUrls.get(a);
                    Class myclass=Class.forName(map.getClassName());
                    Object o=myclass.getConstructor().newInstance();
                    Method m=o.getClass().getDeclaredMethod(map.getMethod());
                    ModelView mv=(ModelView) m.invoke(o);
                    
                    HashMap data=mv.getData();
                    ArrayList ob=(ArrayList)data.get("anarana");
                    System.out.println(ob.size()+"   size an le ob");
                    request.setAttribute("anarana", ob);
                    RequestDispatcher dispat = request.getRequestDispatcher(mv.getJspName());
                    dispat.forward(request,response);

            }
        }catch(Exception e){
            e.printStackTrace();
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

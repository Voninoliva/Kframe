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
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;
import etu1790.framework.ModelView;
import java.lang.reflect.Field;

public class FrontServlet extends HttpServlet {
    private final HashMap<String, Mapping> mappingUrls = new HashMap<>();
    private Object modelView;

    @Override
    public void init() {
        try {
            Vector<String> paths = new Utilitaire()
                    .readPackage(getServletContext().getResource(".").toURI().getPath() + "WEB-INF/classes");
            for (String path : paths) {
                Vector<Utilitaire> utilitaires = new Utilitaire().readAnnotation(path);
                for (Utilitaire utilitaire : utilitaires) {
                    mappingUrls.put(utilitaire.getValue(),
                            new Mapping(utilitaire.getClassName(), utilitaire.getMethod()));
                }
            }
        } catch (Exception ignored) {
            System.out.println(ignored.getMessage());
        }
    }

    public void processRequest(HttpServletRequest req, HttpServletResponse res) throws IOException {
        try {
            String key = req.getServletPath().substring(1);
            if (mappingUrls.containsKey(key)) {
                String nomDeLaClasse = mappingUrls.get(key).getClassName();
                String nomDeLaMethode = mappingUrls.get(key).getMethod();

                Class<?> classe = Class.forName(nomDeLaClasse);
                Object objet = classe.getDeclaredConstructor().newInstance();
                Method method = objet.getClass().getDeclaredMethod(nomDeLaMethode);
                Enumeration<String> parameterNames = req.getParameterNames();
                if (parameterNames == null) {
                    ModelView mv = (ModelView) method.invoke(objet);

                    HashMap data = mv.getData();
                    ArrayList ob = (ArrayList) data.get("anarana");
                    System.out.println(ob.size() + "   size an le ob");
                    req.setAttribute("anarana", ob);
                    RequestDispatcher dispat = req.getRequestDispatcher(mv.getJspName());
                    dispat.forward(req, res);
                } else {
                    while (parameterNames.hasMoreElements()) {
                        // <% response.sendRedirect("http://localhost:8080/test-framework/Emp-Save"); %>
                        String nomDeLAtrribut = parameterNames.nextElement();
                        for (int i = 0; i < objet.getClass().getDeclaredMethods().length; i++) {
                            Method m = objet.getClass().getDeclaredMethods()[i];
                            if (m.getName().equalsIgnoreCase("set" + nomDeLAtrribut)) {
                                System.out.println("m==" + m.getName());
                                Field[] ff = objet.getClass().getDeclaredFields();
                                for (int q = 0; q < ff.length; q++) {
                                    System.out.println();
                                    if (nomDeLAtrribut.equalsIgnoreCase(ff[q].getName())) {

                                        System.out.println(ff[q].getName());
                                        Object o = req.getParameter(nomDeLAtrribut);
                                        Class a = ff[q].getType();
                                        o = Utils.caster(String.valueOf(o), a);
                                        System.out.println("o.className()==" + o.getClass().getName());
                                        m.invoke(objet, o);
                                    }
                                }

                            }
                        }
                    }
                    if (method.invoke(objet).getClass() == ModelView.class) {
                        ModelView modelView = (ModelView) method.invoke(objet);
                        System.out.println("le .class");
                        HashMap<String, Object> data = modelView.getData();
                        for (Map.Entry<String, Object> entry : data.entrySet()) {
                            req.setAttribute(entry.getKey(), entry.getValue());
                        }
                        req.getRequestDispatcher(modelView.getJspName()).forward(req, res);
                    }

                }
            }

        } catch (Exception e) {
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
    // <%
    // Object o=request.getAttribute("anarana");
    // ArrayList<Emp> array=(ArrayList)o;
    // for(int i=0;i<array.size();i++){
    // Emp e=array.get(i);
    // out.println(e.getNom());
    // }
    // %>
}

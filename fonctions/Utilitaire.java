package fonctions;

import etu1790.framework.annotations.Urls;

import java.io.File;
import java.util.Objects;
import java.util.Vector;

public class Utilitaire
{
    private String value;
    private String className;
    private String method;
    public String getValue() {
        return value;
    }
    public void setValue(String value) {
        this.value = value;
    }
    public String getClassName() {
        return className;
    }
    public void setClassName(String className) {
        this.className = className;
    }
    public String getMethod() {
        return method;
    }
    public void setMethod(String method) {
        this.method = method;
    }
    public Utilitaire(){}
    public Utilitaire(String value, String className, String method)
    {
        setValue(value);
        setClassName(className);
        setMethod(method);
    }
    public Vector<String> readPackage(String path)
    {
        Vector<String> stringVector = new Vector<>();
        File file = new File(path);
        for (int i = 0; i < Objects.requireNonNull(file.listFiles()).length; i++)
        {
            stringVector.add(Objects.requireNonNull(file.listFiles())[i].toString());
            if (Objects.requireNonNull(file.listFiles())[i].isDirectory())
            {
                stringVector.addAll(readPackage(Objects.requireNonNull(file.listFiles())[i].getPath()));
            }
        }
        return stringVector;
    }
    public Vector<Utilitaire> readAnnotation(String path)
    {
        Vector<Utilitaire> utilitaires = new Vector<>();
        File file = new File(path);
        if (!file.isDirectory())
        {
            try
            {
                Class<?> c = Class.forName( file.getParentFile().getName() + "." + file.getName().split("\\.")[0]);
                for (int j = 0; j < c.getDeclaredMethods().length; j++)
                {
                    Urls urls = c.getDeclaredMethods()[j].getAnnotation(Urls.class);
                    if (urls != null)
                    {
                        utilitaires.add(new Utilitaire(urls.url(), c.getName(), c.getDeclaredMethods()[j].getName()));
                    }
                }
            }
            catch (Exception ignored)
            {

            }
        }
        return utilitaires;
    }
}

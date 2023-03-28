package etu1790.framework;
public class Mapping {
    private String className;
    private String Method;

    public Mapping(String className, String Method) {
        this.className = className;
        this.Method = Method;
    }

   
    public String getClassName() {
        return className;
    }

    
    public void setClassName(String className) {
        this.className = className;
    }

    
    public String getMethod() {
        return Method;
    }

    
    public void setMethod(String Method) {
        this.Method = Method;
    }
    
}

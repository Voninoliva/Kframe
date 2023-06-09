package etu1790.framework;

import java.util.HashMap;

public class ModelView {
    private String jspName;
    private HashMap<String, Object> data;

    public ModelView(String j) {
        jspName = j;
        this.data = new HashMap();
    }

    public ModelView() {
        this.data = new HashMap();
    }

    public String getJspName() {
        return this.jspName;
    }

    public void setJspName(String jspName) {
        this.jspName = jspName;
    }

    public HashMap<String, Object> getData() {
        return data;
    }

    public void setData(HashMap<String, Object> data) {
        this.data = data;
    }

    public void addData(String key, Object o) {
        this.data.put(key, o);
    }
}

package fonctions;

import java.beans.PropertyEditorManager;
import java.beans.PropertyEditorSupport;
import java.sql.Date;
import java.sql.Time;

public class Utils {
    public Utils(){}
    public static String getMyRequest(String a){
        String b="";
        for(int i=2;i<a.split("/").length;i++){
            b+=a.split("/")[i]+"";
        }
     return b;
    }
    public static <T> T caster(String val,Class<T> type){
        if(type==Date.class){
            Date eval = Date.valueOf(val);
            return (T) eval;
        }
        else if(type == Time.class){
            Time tt = Time.valueOf(val);
            return (T) tt;
        }
        PropertyEditorSupport editor = (PropertyEditorSupport) PropertyEditorManager.findEditor(type);
        editor.setAsText(val);
        return (T) editor.getValue();
    } 
}

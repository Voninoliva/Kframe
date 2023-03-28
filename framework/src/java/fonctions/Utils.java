
package fonctions;

public class Utils {
    public Utils(){}
    public static String getMyRequest(String a){
        String b="";
        for(int i=2;i<a.split("/").length;i++){
            b+=a.split("/")[i]+"";
        }
     return b;
    }
}

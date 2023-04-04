   
package model;

import etu1790.framework.annotations.Urls;
import etu1790.framework.*;
public class Emp {
    @Urls(url = "/Emp-All")
    public ModelView findAllAFind()
    {
        return new ModelView("a.jsp");
    }
}



package model;

import etu1790.framework.annotations.Urls;

public class Emp {
    @Urls(url = "/Emp-All")
    public void findAll()
    {
        System.out.println("findAll");
    }
}

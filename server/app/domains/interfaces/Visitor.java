package domains.interfaces;

import domains.Admin;
import domains.RegularUser;

public interface Visitor {


    public double visit(Admin admin, int id);
    public double visit(RegularUser user, int id);

}

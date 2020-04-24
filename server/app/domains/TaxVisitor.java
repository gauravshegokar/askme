package domains;

import domains.interfaces.Visitor;

public class TaxVisitor implements Visitor {

    static double regularUserTaxPercent = 0.18;
    static double adminUserTaxPercent = 0.20;

    public TaxVisitor(){

    }

    @Override
    public double visit(Admin admin, int id) {

        return admin.getUser(id).getMonthlySubscriptionPrice()*adminUserTaxPercent;

    }

    @Override
    public double visit(RegularUser user, int id) {
        return user.getUser(id).getMonthlySubscriptionPrice()*regularUserTaxPercent;
    }
}

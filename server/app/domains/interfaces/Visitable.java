package domains.interfaces;

public interface Visitable {

    public double accept(Visitor visitor, int id);
}

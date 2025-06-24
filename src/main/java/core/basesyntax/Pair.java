package core.basesyntax;

public class Pair implements Comparable {
    private final String _name;
    private int _ammount;

    public Pair(String name, int ammount) {
        this._name = name;
        this._ammount = ammount;
    }

    public boolean equalByName(String other) {
        return this._name.equals(other);
    }

    public void addAmmount(int ammount) {
        _ammount+= ammount;
    }

    public String get_name() {
        return _name;
    }

    public int get_ammount() {
        return _ammount;
    }

    @Override
    public String toString() {
        return new StringBuilder("Name: ").append(_name).append(" Ammount: ").append(_ammount).toString();
    }

    @Override
    public int compareTo(Object o) {
        if(this.getClass().equals(o.getClass())) {
            return ((Pair) o).get_ammount() - _ammount;
        }
        return 0;
    }
}

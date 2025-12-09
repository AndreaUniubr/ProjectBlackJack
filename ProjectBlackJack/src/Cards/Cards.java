package Cards;

public class Cards{
    private Suits s;
    private Ranks r;

    public Cards(Suits s, Ranks r)
    {
        this.setS(s);
        this.setR(r);
    }

    public Suits getS() {
        return s;
    }

    public void setS(Suits s) {
        this.s = s;
    }

    public Ranks getR() {
        return r;
    }

    public void setR(Ranks r) {
        this.r = r;
    }
}

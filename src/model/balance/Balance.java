
package model.balance;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class Balance {
    // Attributi
    private int saldo;

    protected final PropertyChangeSupport pcs2 = new PropertyChangeSupport(this);

    //Costruttore
    public Balance (int saldo)
    {
        this.saldo = saldo;
    }

    // Aumento saldo
    public void aggiungiSoldi (int importo)
    {
        this.setSaldo(this.saldo + importo);
    }

    // Prelevare soldi
    public boolean togliSoldi (int importo)
    {
        if (saldo >= importo)
        {
            this.setSaldo(this.saldo - importo);
            return true;
        }
        else return false;
    }

    public void addBalanceListener(PropertyChangeListener l)
    {
        pcs2.addPropertyChangeListener("balance", l);
    }

    // Getter saldo
    public int getSaldo()
    {
        return saldo;
    }

    // Resettiamo saldo
    public void setSaldo(int saldo)
    {
        int old = this.saldo;
        this.saldo = saldo;
        pcs2.firePropertyChange("balance", old, saldo);
    }
}


package model.balance;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class Balance {
    private int saldo;
    // Notifies listeners whenever the balance changes.
    private final PropertyChangeSupport pcs =
            new PropertyChangeSupport(this);

    public Balance (int saldo)
    {
        this.saldo = saldo;
    }

    // Add money
    public void aggiungiSoldi (int importo)
    {
        setSaldo(saldo + importo);
    }

    // Remove money
    public boolean togliSoldi (int importo)
    {
        if (saldo >= importo)
        {
            setSaldo(saldo - importo);
            return true;
        }
        else return false;
    }

    public void addBalanceListener(PropertyChangeListener l)
    {
        pcs.addPropertyChangeListener("balance", l);
    }

    public int getSaldo()
    {
        return saldo;
    }

    // Updates the balance and notifies listeners.
    public void setSaldo(int saldo)
    {
        int old = this.saldo;
        this.saldo = saldo;
        pcs.firePropertyChange("balance", old, saldo);
    }
}

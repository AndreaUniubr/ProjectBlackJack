
package model.balance;

public class Balance {
    // Attributi
    private int saldo;

    //Costruttore
    public Balance (int saldo)
    {
        this.saldo = saldo;
    }

    // Aumento saldo
    public void aggiungiSoldi (int importo)
    {
        this.saldo += importo;
    }

    // Prelevare soldi
    public boolean togliSoldi (int importo)
    {
        if (saldo >= importo)
        {
            saldo -= importo;
            return true;
        }
        else return false;
    }

    // Getter saldo
    public int getSaldo()
    {
        return saldo;
    }

    // Resettiamo saldo
    public void setSaldo(int saldo)
    {
        this.saldo = saldo;
    }
}

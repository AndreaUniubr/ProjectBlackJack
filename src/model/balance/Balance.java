
package model.balance;

public class Balance {
    // Attributi
    private int saldo;
    private int punatatCorrente;

    //Costruttore
    public Balance (int saldo){
        this.saldo = saldo;
        this.punatatCorrente = 0;
    }

    // Aumento saldo
    public void aggiungiSoldi (int importo){
        saldo+=importo;
    }

    // Prelevare soldi
    public boolean togliSoldi (int importo) {
        if (saldo >= importo) {
            saldo -= importo;
            punatatCorrente = importo;
            return true;
        } else {
            System.out.println("Saldo insufficiente");
            return false;
        }
    }

    // Scommessa
    public boolean scommetti(int puntata) {
        return togliSoldi(puntata);
    }

    // vincita
    public void vinci(int importo) {
        aggiungiSoldi(importo);
    }

    //perdita
    public void perdi(){
        punatatCorrente = 0;
    }

    // Getter saldo
    public double getSaldo() {
        return saldo;
    }

    //Puntata corrente
    public double getPunatatCorrente(){
        return punatatCorrente;
    }

    // Resettiamo saldo
    public void setSaldo(int saldo) {
        this.saldo = saldo;
    }
}

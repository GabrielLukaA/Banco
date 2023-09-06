public class Corrente extends Conta {

    private double limite;

    public Corrente(int numero, String senha, double saldo, int qtdTransacoes, Pessoa titular){
        super(numero,senha,saldo,qtdTransacoes,titular);
        this.limite = 1;
    }
    public void saque(){

    }

    public void transferencia() {

    }
}

public class Credito extends Conta {

    private int diaFatura;
    private double limite;

    public Credito(int numero, String senha, double saldo, int qtdTransacoes, Pessoa titular) {
        super(numero, senha, saldo, qtdTransacoes, titular);
        this.diaFatura = 10;
        this.limite = 1;
    }

}

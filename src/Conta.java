public class Conta {

    private int numero;
    private String senha;
    private double saldo;
    private int qtdTransacoes;
    private Pessoa titular;


    public Conta(int numero, String senha, double saldo, int qtdTransacoes, Pessoa titular) {
        this.numero = numero;
        this.senha = senha;
        this.saldo = saldo;
        this.qtdTransacoes = qtdTransacoes;
        this.titular = titular;
    }

    public void pagamento() {

    }

    public void credito() {

    }

    public double saldo() {
        return this.saldo;
    }

    //region getters and setters

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    public int getQtdTransacoes() {
        return qtdTransacoes;
    }

    public void setQtdTransacoes(int qtdTransacoes) {
        this.qtdTransacoes = qtdTransacoes;
    }

    public Pessoa getTitular() {
        return titular;
    }

    public void setTitular(Pessoa titular) {
        this.titular = titular;
    }


    //endregion
}

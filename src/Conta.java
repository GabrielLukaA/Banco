public class Conta {

    private int numero;
    private String senha;
    private double saldo;
    private int qtdTransacoes;
    private int qtdMaxTransacoes = 5;
    private Pessoa titular;


    public Conta(int numero, String senha, double saldo, Pessoa titular) {
        this.numero = numero;
        this.senha = senha;
        this.saldo = saldo;
        this.qtdTransacoes = 0;
        this.titular = titular;
    }

    public String menu() {
        return """
                O que você deseja realizar?
                0 - Logout
                1 - Pagamento
                2 - Depósito
                """;
    }


    public boolean pagamento(double valor) {
        return false;
    }

    public boolean credito(double valor) {
        this.saldo += valor;
        return true;
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

    public void adicionarJuros(double juros) {

    }

    public void adicionarJuros(double juros, int dia) {
    }

    public int getQtdMaxTransacoes() {
        return qtdMaxTransacoes;
    }


//endregion
}

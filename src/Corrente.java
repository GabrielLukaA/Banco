public class Corrente extends Conta {

    private double limite;

    public Corrente(int numero, String senha, Pessoa titular) {
        super(numero, senha, 1000, titular);
        this.limite = 50;
    }

    public boolean saque(double valor,double taxaServico) {
        if (valor < this.getSaldo()) {
            setQtdTransacoes(getQtdTransacoes()+1);
            if (getQtdTransacoes()> getQtdMaxTransacoes()){
                if (this.getSaldo()>valor+taxaServico){
                    this.setSaldo(this.getSaldo() - valor);
                    return true;
                } else {
                return false;
                }
            }
            this.setSaldo(this.getSaldo() - valor);
            return true;
        }
        return false;
    }

    @Override
    public String menu() {
        return super.menu() + """
                3 - Ver Saldo
                4 - Sacar
                5 - Transferir
                """;
    }

    @Override
    public void adicionarJuros(double juros) {
        if (getSaldo() < 0) {
            setSaldo(getSaldo() - (getSaldo() / 100 * juros));
        }
    }

    @Override
    public boolean pagamento(double valor) {
        if (this.getSaldo() > valor) {
            this.setSaldo(this.getSaldo() - valor);
            return true;
        } else if (this.getSaldo() - valor > -this.limite) {
            this.setSaldo(this.getSaldo() - valor);
            this.limite -= valor;
            return true;
        }
        return false;
    }

    public void transferencia() {

    }
}

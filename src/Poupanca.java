public class Poupanca extends Conta {
    public Poupanca(int numero, String senha, Pessoa titular) {
        super(numero, senha, 1000, titular);
    }

    @Override
    public String menu() {
        return super.menu() + """
                3 - Ver Saldo
                4 - Sacar
                """;
    }

    @Override
    public void adicionarJuros(double juros) {
        setSaldo(getSaldo() + getSaldo() / 100 * juros);
    }

    public boolean saque(double valor, double taxaServico) {
        if (valor < this.getSaldo()) {
            setQtdTransacoes(getQtdTransacoes() + 1);
            if (getQtdTransacoes() > getQtdMaxTransacoes()) {
                if (this.getSaldo() > valor + taxaServico) {
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
    public boolean pagamento(double valor) {
        if (this.getSaldo() > valor) {
            this.setSaldo(this.getSaldo() - valor);
            return true;
        }
        return false;

    }
}

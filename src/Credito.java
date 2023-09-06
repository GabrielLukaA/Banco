public class Credito extends Conta {

    private int diaFatura;
    private double limite;
    private boolean faturaPaga = true;

    public Credito(int numero, String senha, Pessoa titular) {
        super(numero, senha, 0, titular);
        this.diaFatura = 10;
        this.limite = 1000;
    }

    @Override
    public String menu() {
        return super.menu() + """
                3 - Ver Fatura Atual
                4 - Ver dia de fechamento da Fatura
                5 - Pagar Fatura
                6 - Ver limite
                """;
    }

    @Override
    public boolean pagamento(double valor) {
        if (this.getSaldo() + valor < this.limite) {
            this.setSaldo(this.getSaldo() + valor);
            if (getSaldo()==0){
                this.faturaPaga = true;
            }
            return true;
        }
        return false;


    }

    @Override
    public void adicionarJuros(double juros, int dia) {
        if (dia == this.diaFatura){
            this.faturaPaga = false;
        }
    }

    @Override
    public boolean credito(double valor) {
        if (valor > 0) {
            if (valor <= this.getSaldo()) {
                this.setSaldo(this.getSaldo() - valor);
                return true;
            }
        }
        return false;
    }

    public int getDiaFatura() {
        return diaFatura;
    }

    public double getLimite() {
        return limite;
    }

    public void setLimite(double limite) {
        this.limite = limite;
    }
    public boolean aumentarLimite(double novoLimite) {
        if (this.limite < novoLimite){
            this.limite = novoLimite;
            return true;
        }
        return false;
    }
}

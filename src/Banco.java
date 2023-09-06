import java.util.ArrayList;

public class Banco {

    private String nome;
    private String endereco;
    private int agencia;
    private ArrayList<Conta> contas;
    private double juros;
    private double taxaDeServico;

    public Banco(String nome, String endereco, int agencia, double juros, double taxaDeServico) {
        this.nome = nome;
        this.endereco = endereco;
        this.agencia = agencia;
        this.contas = new ArrayList<>();
        this.juros = juros;
        this.taxaDeServico = taxaDeServico;
    }

    public void cadastrarConta() {

    }

    public Conta procurarConta(int numeroConta) {
        Conta contaRetornada = null;
        for (Conta conta : this.contas) {
            if (conta.getNumero() == numeroConta) {
                contaRetornada = conta;
                break;
            }
        }
        return contaRetornada;
    }


    //region getters and setters

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public int getAgencia() {
        return agencia;
    }

    public void setAgencia(int agencia) {
        this.agencia = agencia;
    }

    public ArrayList<Conta> getContas() {
        return contas;
    }

    public void setContas(ArrayList<Conta> contas) {
        this.contas = contas;
    }

    public double getJuros() {
        return juros;
    }

    public void setJuros(double juros) {
        this.juros = juros;
    }

    public double getTaxaDeServico() {
        return taxaDeServico;
    }

    public void setTaxaDeServico(double taxaDeServico) {
        this.taxaDeServico = taxaDeServico;
    }


    //endregion
}

import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    static Banco banco = new Banco("Santo André", "Pindamoiangaba", 4762, 0.33, 10);
    static Scanner sc = new Scanner(System.in);
    static Pessoa pessoa = new Pessoa("R. Wagner Luiz Zapello");
    static Conta conta1 = new Credito(1, "1", pessoa);
    static Conta conta2 = new Corrente(2, "2", pessoa);
    static Conta conta3 = new Poupanca(3, "3", pessoa);
    static ArrayList<Conta> contas = new ArrayList<>();
    static int dia;

    public static void main(String[] args) {
        contas.add(conta1);
        contas.add(conta2);
        contas.add(conta3);
        banco.setContas(contas);
        do {
            telaInicial();
        } while (true);

    }

    private static void telaInicial() {
        System.out.println("Seja bem vindo ao banco " + banco.getNome() + ", o que você deseja realizar?");
        System.out.println("""
                1 - Cadastrar Conta
                2 - Acessar Minha Conta
                3 - Passar dias
                """);
        int opcao = sc.nextInt();
        switch (opcao) {
            //   case 1 -> cadastrarConta();
            case 2 -> menu();
            case 3 -> passarDias();
            default -> System.out.println("Selecione um número válido");
        }
    }

    private static void passarDias() {
        System.out.println("Quantos dias você deseja passar?");
        int dias = sc.nextInt();
        if (dias > 0) {
            for (int i = 0; i < dias; i++) {
                if (dia == 30) {
                    dia = 1;
                } else {
                    dia++;
                }
                for (Conta conta : banco.getContas()) {
                    if (conta instanceof Credito) {
                        conta.setQtdTransacoes(0);
                        conta.adicionarJuros(banco.getJuros(), dia);
                    }
                    conta.adicionarJuros(banco.getJuros());
                }
            }
        } else {
            System.out.println("Impossível voltar no tempo.");
        }
    }

    private static void menu() {
        System.out.println("informe o número da conta:");
        int numeroConta = sc.nextInt();
        System.out.println("informe a senha da conta:");
        String senhaConta = sc.next();
        Conta conta = banco.procurarConta(numeroConta);
        if (conta != null) {
            if (conta.getSenha().equals(senhaConta)) {
                do {
                    System.out.println(conta.menu());
                    int opcao = sc.nextInt();

                    switch (opcao) {
                        case 0 -> conta = null;
                        case 1 -> pagamento(conta);
                        case 2 -> deposito(conta);
                        case 3 -> verSaldo(conta);
                        case 4 -> {
                            if (!(conta instanceof Credito)) {
                                sacar(conta);
                            } else {
                                System.out.println("O fechamento de sua fatura é dia " + ((Credito) conta).getDiaFatura());
                            }
                        }
                        case 5 -> {
                            if (conta instanceof Credito) {
                                pagarFatura(conta);
                            } else if (conta instanceof Corrente) {
                                transferir(conta);
                            } else {
                                System.out.println("Valor inválido inserido, tente novamente.");
                            }
                        }

                        case 6 -> {
                            if (conta instanceof Credito) {
                                limite(conta);
                            } else {
                                System.out.println("Valor inválido inserido, tente novamente.");
                            }
                        }

                        default -> System.out.println("Valor inválido inserido, tente novamente.");
                    }


                } while (conta != null);
            } else {
                System.out.println("A senha informada é inválida.");
            }
        } else {
            System.out.println("Você informou um número de conta inexistente.");
        }
    }

    private static void pagarFatura(Conta conta) {
        System.out.println("Quanto você deseja pagar da fatura: ");
        double valor = sc.nextDouble();
        if (valor > 0) {
            if (conta.credito(valor)) {
                System.out.println("Fatura paga com sucesso.");
            } else {
                System.out.println("Você inseriu um valor maior que sua fatura.");
            }
        } else {
            System.out.println("Você inseriu um valor inferior ou igual a 0.");
        }
    }

    private static void transferir(Conta conta) {
        System.out.println("Informe o número da conta que deseja transferir");
        Conta contaRecebedora = banco.procurarConta(sc.nextInt());
        if (contaRecebedora != null) {
            if (!(contaRecebedora instanceof Credito)) {
                System.out.println("Quanto deseja transferir para essa conta?");
                double valor = sc.nextDouble();
                if (valor > 0) {
                    if (conta.pagamento(valor)) {
                        contaRecebedora.credito(valor);
                        conta.setQtdTransacoes(conta.getQtdTransacoes() + 1);
                    } else {
                        System.out.println("Você não tem saldo para essa transferencia.");
                    }
                } else {
                    System.out.println("Você inseriu um valor negativo.");
                }
            } else {
                System.out.println("Você inseriu uma conta de crédito.");
            }
        } else {
            System.out.println("Você inseriu um número de conta inválido.");
        }
    }

    private static void limite(Conta conta) {
        System.out.println("Seu limite é " + ((Credito) conta).getLimite());
        System.out.println("Tecle 1 para Realizar modificação ou tecle qualquer outra para continuar.");
        if (sc.nextInt() == 1) {
            System.out.println("Para quanto você deseja aumentar o seu limite?");
            double novoLimite = sc.nextDouble();
            if (((Credito) conta).aumentarLimite(novoLimite)) {
                System.out.println("Limite alterado com sucesso.");
            } else {
                System.out.println("Esse valor não é permitido");
            }
        }
    }

    private static void sacar(Conta conta) {
        System.out.println("Informe quanto você deseja sacar");
        double valor = sc.nextDouble();
        if (conta instanceof Corrente) {
            if (((Corrente) conta).saque(valor, banco.getTaxaDeServico())) {
                System.out.println("Saque realizado com sucesso.");
            } else {
                System.out.println("Saldo insuficiente para saque");
            }
        } else {
            if (((Poupanca) conta).saque(valor, banco.getTaxaDeServico())) {
                System.out.println("Saque realizado com sucesso.");
            } else {
                System.out.println("Saldo insuficiente para saque");
            }
        }
    }

    private static void verSaldo(Conta conta) {
        if (!(conta instanceof Credito)) {
            System.out.println("Seu saldo é " + conta.getSaldo());
        } else {
            System.out.println("Sua Fatura Atual é " + conta.getSaldo());
        }

    }

    private static void deposito(Conta conta) {
        System.out.println("Quanto você irá creditar a sua conta?");
        double valor = sc.nextDouble();
        if (conta.credito(valor)) {
            System.out.println("Depósito realizado com sucesso.");
        } else {
            System.out.println("Valor inválido de depósito");
            if (conta instanceof Credito) {
                System.out.println("Você não pode pagar um valor maior que sua fatura.");
            }
        }
    }

    private static void pagamento(Conta conta) {
        System.out.println("Quanto você está pagando?");
        double valor = sc.nextDouble();
        if (valor > 0) {
            if (conta.pagamento(valor)) {
                System.out.println("Pagamento efetuado com sucesso.");
            } else {
                System.out.println("Saldo ou limite insuficiente.");
            }
        } else {
            System.out.println("Valor inserido é inferior ou igual a 0.");
        }

    }
}

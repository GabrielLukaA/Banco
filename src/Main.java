import java.util.Scanner;

public class Main {
    static Banco banco = new Banco("Santo André", "Pindamoiangaba", 4762, 0.33, 10);
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {

        do {
            menu();
        } while (true);

    }

    private static void menu() {
        System.out.println("Seja bem vindo ao banco " + banco.getNome() + ", o que você deseja realizar?");
        System.out.println("""
                1 - Cadastrar Conta
                2 - Acessar Minha Conta
                """);
        int opcao = sc.nextInt();
        switch (opcao) {
            case 1 -> cadastrarConta();
            case 2 -> menuDaConta();
            default -> System.out.println("Selecione um número válido");
        }
    }

    private static void menuDaConta() {
        System.out.println("informe o número da conta:");
        int numeroConta = sc.nextInt();
        System.out.println("informe a senha da conta:");
        String senhaConta = sc.next();
        Conta conta = banco.procurarConta(numeroConta);
        if (conta != null) {
            if (conta.getSenha().equals(senhaConta)) {
                do {
                    telaInicialDaConta();
                } while (conta != null);
            } else {
                System.out.println("A senha informada é inválida.");
            }
        } else {
            System.out.println("Você informou um número de conta inexistente.");
        }
    }
}

package SolucaoLinear;

public class PrintaAcaoUtil {

    private final static String EM_BRANCO = "|____________|";

    public static void printaCabecalho() {
        System.out.println("|| FILOSOFO 0 || FILOSOFO 1 || FILOSOFO 2 || FILOSOFO 3 || FILOSOFO 4 ||");
        System.out.println("||____________||____________||____________||____________||____________||");
    }

    public static void printaAcao(int idFilosofo, boolean pensando, int qtdFilosofos) {

        String acao = "";

        for (int i = 0; i < qtdFilosofos; i++) {
            if (i == idFilosofo)
                acao += acao(pensando);
            else
                acao += EM_BRANCO;
        }

        System.out.println("..____________..____________..____________..____________..____________..");
        System.out.println("|| FILOSOFO 0 || FILOSOFO 1 || FILOSOFO 2 || FILOSOFO 3 || FILOSOFO 4 ||");
        System.out.println("|" + acao + "|");
    }

    private static String acao(boolean pensando) {
        if (pensando)
            return "|__PENSANDO__|";

        return "|__JANTANDO__|";
    }

}
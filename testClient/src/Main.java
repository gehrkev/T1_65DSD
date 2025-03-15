import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        String IP = "127.0.0.1";
        int PORT = 1234;
        Scanner scanner = new Scanner(System.in);

        System.out.println("Cliente iniciado");

        while (true) {
            System.out.print("Digite um comando (ou 'exit' para sair):");
            String cmd = scanner.nextLine();

            if (cmd.equals("exit")) {
                break;
            }

            try {
                Socket con = new Socket(IP, PORT);
                InputStream in = con.getInputStream();
                OutputStream out = con.getOutputStream();

                out.write(cmd.getBytes());
                out.flush();

                byte[] dadosBrutos = new byte[1024];
                int qtdBytesLidos = in.read(dadosBrutos);
                if (qtdBytesLidos > 0) {
                    String dadosStr = new String(dadosBrutos, 0, qtdBytesLidos);
                    System.out.println(dadosStr);
                }

                con.close();

            } catch (UnknownHostException e) {
                System.out.println("Host não encontrado");
                e.printStackTrace();
            } catch (IOException e) {
                System.out.println("Erro de comunicação: " + e.getMessage());
            }
        }
        scanner.close();
        System.out.println("Cliente encerrado");
    }
}
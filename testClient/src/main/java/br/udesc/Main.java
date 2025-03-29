package br.udesc;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        String IP = new String();
        int PORT = 1234;
        Scanner scanner = new Scanner(System.in);
        System.out.println("Cliente iniciado");
        System.out.print("Digite o IP do servidor: ");
        IP = scanner.nextLine();

        while (true) {
            System.out.print("Digite um comando (ou 'exit' para sair):");
            String cmd = scanner.nextLine();

            if (cmd.equalsIgnoreCase("exit")) {
                break;
            }

            try {
                System.out.println("Conectando com IP: " + IP);
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
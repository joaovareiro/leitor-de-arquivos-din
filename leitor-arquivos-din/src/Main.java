import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        int bytes = 0;
        ArrayList<String> enderecos = new ArrayList<>();
        ArrayList<String> enderecosRepetidos = new ArrayList<>();
        while (true) {


            System.out.println("Digite o nome do arquivo (por favor, diferencie letras maiúsculas e minúsculas)");
            String nomeArquivo = sc.nextLine();
            if (!nomeArquivo.contains(".din")) {
                nomeArquivo = nomeArquivo + ".din";
            }
            try (FileInputStream fis = new FileInputStream(nomeArquivo)) {
                InputStreamReader isr = new InputStreamReader(fis);
                BufferedReader br = new BufferedReader(isr);
                String linha = br.readLine();
                while (linha != null) {
/*
                    System.out.println(linha);
*/
                    String[] arrayLinha;
                    arrayLinha = new String[2];
                    arrayLinha = linha.split(" ");

                    if (arrayLinha[0].equals("0") || arrayLinha[0].equals("1")) {
                        bytes = bytes + 4;
                    }
                    enderecos.add(arrayLinha[1]);
                    linha = br.readLine();
                }
            } catch (FileNotFoundException e) {
                System.out.println("""
                        O arquivo não foi encontrado.\s
                        Deseja encerrar o programa?
                        1 - Sim
                        2 - Não""");
                int op = sc.nextInt();
                nomeArquivo = null;
                if (op == 1) {
                    System.exit(0);
                }
            } catch ( ArrayIndexOutOfBoundsException e){
                System.out.println("""
                        O arquivo está formatado incorretamente.\s
                        Deseja encerrar o programa?
                        1 - Sim
                        2 - Não""");
                int op = sc.nextInt();
                nomeArquivo = null;
                if (op == 1) {
                    System.exit(0);
                }
            }
            break;
        }

        System.out.println("Número de bytes acessados na memória: " + bytes);
        enderecosRepetidos = (ArrayList<String>) enderecos.stream().filter(e -> Collections.frequency(enderecos,e)>1).distinct().collect(Collectors.toList());
        if(enderecosRepetidos.isEmpty()){
            System.out.println("Não existem acessos redundantes no mesmo arquivo.");
        }else{
            System.out.println("Acessos redundantes:");
            enderecosRepetidos.forEach(System.out::println);
        }
        }
    }


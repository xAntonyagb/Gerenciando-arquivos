package br.unipar.gerenciamento.arquivos;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import javax.swing.JOptionPane;

public class GerenciamentoArquivos {

    public static void main(String[] args) {
        File arquivo = new File("temp\\arquivo.txt");

        try {
            
            if(arquivo.exists()) {
                JOptionPane.showMessageDialog(null, "Esse arquivo já existe, seu conteúdo será exibido no console!", "Erro ao criar", JOptionPane.ERROR_MESSAGE);
            }
            else {
                //Criar arquivo
                new File("temp").mkdirs();
                arquivo.createNewFile();
                System.out.println("Arquivo criado");
                
                //Escrever tabuada no arquivo
                try(FileWriter escritor = new FileWriter(arquivo)) {
                    try(BufferedWriter buffer = new BufferedWriter(escritor)){ 
                        for(int i = 1; i < 10; i++) {
                            buffer.write("9 * " + i + " = "+ 9*i);
                            buffer.newLine();
                        }
                    }
                }
            }
            
            //Ler arquivo
            try(FileReader leitor = new FileReader(arquivo)) {
                try(BufferedReader buffer = new BufferedReader(leitor)) {
                    String linha;
                    while((linha = buffer.readLine()) != null) {
                        System.out.println(linha);
                    }
                }
            }
        
        }
        catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao criar o arquivo:\n"+ ex.getMessage(), "Erro ao criar", JOptionPane.ERROR_MESSAGE);
        }
        catch(Exception ex) {
            JOptionPane.showMessageDialog(null, "Um erro inesperado aconteceu:\n"+ ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
        finally {
            if(arquivo.exists()) {
                arquivo.delete();
            }
        }
        
    }
}

import javax.crypto.*;
import javax.crypto.spec.*;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;
import javax.swing.JFileChooser;
import java.security.spec.*;

public class Hashing {

    private static final String INIT_VECTOR = "initialVector123";
    private static final String EXTENSION_PART = ".encrypted";
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("1-CRIPTOGRAFAR ARQUIVO");
        System.out.println("2-DESCRIPTOGRAFAR ARQUIVO");
        int choice = scanner.nextInt();
        scanner.nextLine();

        switch (choice) {
            case 1:
                encryptFile();
                break;
            case 2:
                decryptFile();
                break;
            default:
                System.out.println("NÃO EXISTE ESSA OPÇÃO");
        }
        scanner.close();
    }
    //ADICIONAR FUNCIONALIDADE DE REMOVER A EXTENSÃO PRIMORDIAL DO ARQUIVO
    /*public static String removeExtension(String file){
        int pos = file.lastIndexOf('.');
        if(pos>1){
            return file.substring(0, pos);
        }
        else{
            return file;
        }
    }
    */

    public static void encryptFile() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("SELECIONE O AQRQUIVO PARA CRIPTOGRAFAR");
        int result = fileChooser.showOpenDialog(null);

        if (result == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            String filePath = selectedFile.getAbsolutePath();

            Scanner scanner = new Scanner(System.in);
            System.out.println("DIGITE A SENHA DE CRIPTOGRAFIA: ");
            String password = scanner.nextLine();


            try {
                byte[] fileBytes = Files.readAllBytes(Paths.get(filePath));

                // AES GENERATE
                SecretKey secretKey = generateAESKeyFromPassword(password);

                Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
                AlgorithmParameterSpec iv = new IvParameterSpec(INIT_VECTOR.getBytes());

                cipher.init(Cipher.ENCRYPT_MODE, secretKey, iv);
                byte[] encryptedBytes = cipher.doFinal(fileBytes);

                // EXTENSÃO
                String encryptedFilePath = filePath + EXTENSION_PART;

                Files.write(Paths.get(encryptedFilePath), encryptedBytes);
                System.out.println("ARQUIVO CRIPTOGRAFADO: " + encryptedFilePath);
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                scanner.close();
            }
        } else {
            System.out.println("NENHUM ARQUIVO FOI SELECIONADO. ENCERRANDO O PROGRAMA");
        }
    }

    public static void decryptFile() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("SELECIONE O AQRQUIVO PARA DESCRIPTOGRAFAR");
        int result = fileChooser.showOpenDialog(null);

        if (result == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            String filePath = selectedFile.getAbsolutePath();

            Scanner scanner = new Scanner(System.in);
            System.out.println("DIGITE A SENHA DO ARQUIVO: ");
            String password = scanner.nextLine();

            try {
                byte[] encryptedFileBytes = Files.readAllBytes(Paths.get(filePath));
                Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");

                // Gerar chave AES a partir da senha
                SecretKey secretKey = generateAESKeyFromPassword(password);

                AlgorithmParameterSpec iv = new IvParameterSpec(INIT_VECTOR.getBytes());
                cipher.init(Cipher.DECRYPT_MODE, secretKey, iv);
                byte[] decryptedBytes = cipher.doFinal(encryptedFileBytes);

                // Alterar extensão do arquivo para indicar que está descriptografado
                String decryptedFilePath = filePath.substring(0, filePath.lastIndexOf('.'));

                Files.write(Paths.get(decryptedFilePath), decryptedBytes);
                System.out.println("ARQUIVO DESCRIPTOGRAFADO: " + decryptedFilePath);
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                scanner.close();
            }
        } else {
            System.out.println("NENHUM ARQUIVO FOI SELECIONADO. ENCERRANDO O PROGRAMA");
        }
    }

    public static SecretKey generateAESKeyFromPassword(String password) {
        try {
            // PARÂMETROS
            byte[] salt = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10}; // SALT
            int iterations = 65536; // ITERAÇÕES
            int keyLength = 256; // TAMANHO BITS

            SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");
            KeySpec keySpec = new PBEKeySpec(password.toCharArray(), salt, iterations, keyLength);

            // GERAR UMA NOVA CHAVE COM ALGORITMO AES
            return new SecretKeySpec(factory.generateSecret(keySpec).getEncoded(), "AES");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}

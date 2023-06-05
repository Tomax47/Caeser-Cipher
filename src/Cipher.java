import java.util.*;

public class Cipher {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        System.out.print("Enter the text : ");
        String plainText = scan.nextLine();

        String text = encrypt(plainText,2);
        System.out.println("Encrypted text : "+text);

        System.out.println("Decrypted text : "+decrypt(text,2));

        scan.close();
    }

    private static String encrypt(String plainText, int encryptionKey){

        String encryptedText = "";

        String[] alphabet = {"A","B","C","D","E","F","G","H","I","J","K","L","M","N","O","P","Q","R","S","T","U","V","W","X","Y","Z"};

        List<String> text = new ArrayList<>();

        for (int i = 0; i < plainText.length(); i++) {
            if (plainText.charAt(i) == ' ') {
                text.add("\s");
            } else {
                text.add(String.valueOf(plainText.charAt(i)));
            }
        }

        int cipherLetterIndex = 0;
        for (int i = 0; i < text.size(); i++) {
            if (text.get(i) == "\s") {
                continue;
            } else {
                cipherLetterIndex = (indexOfLetter(text.get(i),alphabet) + encryptionKey) % alphabet.length;
                text.set(i,alphabet[cipherLetterIndex]);
            }
        }

        for (String letter : text) {
            if (letter == "\s") {
                encryptedText += spaceSymbol();
            } else {
                encryptedText += letter;
            }
        }
        return encryptedText;
    }

    private static String decrypt(String encryptedText, int encryptionKey) {
        String decryptedText = "";

        String[] alphabet = {"A","B","C","D","E","F","G","H","I","J","K","L","M","N","O","P","Q","R","S","T","U","V","W","X","Y","Z"};

        List<String> text = new ArrayList<>();

        for (int i = 0; i < encryptedText.length(); i++) {
            text.add(String.valueOf(encryptedText.charAt(i)));
        }

        int cipherLetterIndex = 0;
        for (int i = 0; i < text.size(); i++) {
            if (isASpaceSymbol(text.get(i))) {
                text.set(i,"\s");
            } else {
                cipherLetterIndex = (indexOfLetter(text.get(i),alphabet) - encryptionKey) % alphabet.length;
                if (cipherLetterIndex < 0) {
                    cipherLetterIndex += alphabet.length;
                }
                text.set(i,alphabet[cipherLetterIndex]);
            }
        }

        for (String letter : text) {
            decryptedText += letter;
        }
        return decryptedText;
    }

    private static int indexOfLetter(String letter, String[] array) {
        int index = 0;
        for (int i = 0; i < array.length; i++) {
            if (array[i].equals(letter.toUpperCase())) {
                index = i;
                break;
            }
        }
        return index;
    }

    private static String spaceSymbol(){
        Random rnd = new Random();

        String[] symbols = {"$","@","*","%","&"};
        int index = rnd.nextInt(0,4);

        return symbols[index];
    }

    public static boolean isASpaceSymbol(String character) {

        int num = 0;
        String[] symbols = {"$","@","*","%","&"};

        for (String symbol : symbols) {
            if (character == symbol) {
                num = 1;
                break;
            }
        }
        if (num == 1) {
            return true;
        } else {
            return false;
        }
    }
}
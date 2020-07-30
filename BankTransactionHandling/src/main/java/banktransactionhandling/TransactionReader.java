package banktransactionhandling;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class TransactionReader {
    private final String INPUT_FILE_PATH = "..\\";
    private final String INPUT_FILE_NAME = "bankTransactions.txt";
    private Scanner scanner;

    public TransactionReader() {
        try {
            scanner = new Scanner(new File(INPUT_FILE_PATH + INPUT_FILE_NAME));
        } catch (FileNotFoundException e) {
            System.out.println("File not found next to the program library with name: " + INPUT_FILE_NAME);
            System.exit(-1);
        }
    }

    public boolean hasNext() {
        return scanner.hasNextLine();
    }

    public Transaction readTransaction() {
        String line = scanner.nextLine();
        String[] transactionData = line.split(",");

        if (transactionData.length < 3) {
            System.out.println("Not enough data for creating a transaction in line: " + line);
            System.out.println("(Data is expected in \"account number, currency, amount, exchange rate\" format, without the quotation marks.)");
            return null;
        }

        String accountNumber = transactionData[0].trim();
        Currency currency;
        double amount;
        Double exchangeRate;

        try {
            currency = Currency.valueOf(transactionData[1].trim());
            amount = Double.parseDouble(transactionData[2].trim());
            exchangeRate = transactionData.length == 3 ? null : Double.valueOf(transactionData[3].trim());
        } catch (NumberFormatException e) {
            System.out.println("Wrong number format in line: " + line);
            return null;
        } catch (IllegalArgumentException e) {
            System.out.println("Unknown currency: " + transactionData[1].trim());
            return null;
        }

        return new Transaction(accountNumber, currency, amount, exchangeRate);
    }
}

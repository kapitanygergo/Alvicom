package banktransactionhandling;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TransactionHandler {
    private AccountDataBase accountDataBase;
    private Map<String, List<Transaction>> transactions;
    private int numberOfTransactions;

    public TransactionHandler() {
        accountDataBase = new AccountDataBase();
        transactions = new HashMap<>();
        numberOfTransactions = 0;
    }

    public void handleTransaction(Transaction transaction) {
        Account account = accountDataBase.getAccount(transaction.getAccountNumber());
        if (account == null) {
            System.out.println("Account not found with account number: " + transaction.getAccountNumber());
            return;
        }

        calculateAccountBalance(account, transaction);
        saveTransaction(transaction);

        if (numberOfTransactions % 10 == 0) {
            writeReport();
        }
    }

    private void calculateAccountBalance(Account account, Transaction transaction) {
        double amount = transaction.getAmount();
        if (!account.getCurrency().equals(transaction.getCurrency())) {
            if (transaction.getExchangeRate() != null) {
                amount *= transaction.getExchangeRate();
            } else {
                System.out.println("Exchange rate is missing for transaction. " + transaction);
            }
        }
        account.setBalance(account.getBalance() + amount);
    }

    private void saveTransaction(Transaction transaction) {
        transactions.computeIfAbsent(transaction.getAccountNumber(), key -> new ArrayList<>());
        transactions.get(transaction.getAccountNumber()).add(transaction);
        numberOfTransactions++;
    }

    private void writeReport() {
        System.out.println("\n\n\nNew report for " + numberOfTransactions + " number of transactions.");
        for (String accountNumber : transactions.keySet()) {
            System.out.println("Transactions on account with account number: " + accountNumber);
            for (Transaction transaction : transactions.get(accountNumber)) {
                System.out.println(transaction);
            }
        }
    }
}

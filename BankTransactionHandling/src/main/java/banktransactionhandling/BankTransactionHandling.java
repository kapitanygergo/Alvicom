package banktransactionhandling;

public class BankTransactionHandling {

    public static void main(String[] args) {
        TransactionReader transactionReader = new TransactionReader();
        TransactionHandler transactionHandler = new TransactionHandler();

        while (transactionReader.hasNext()) {
            Transaction transaction = transactionReader.readTransaction();
            if (transaction != null) {
                transactionHandler.handleTransaction(transaction);
            }
        }
    }
}

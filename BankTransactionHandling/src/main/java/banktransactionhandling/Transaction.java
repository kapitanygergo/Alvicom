package banktransactionhandling;

public class Transaction {
    private String accountNumber;
    private Currency currency;
    private double amount;
    private Double exchangeRate;

    public Transaction(String accountNumber, Currency currency, double amount, Double exchangeRate) {
        this.accountNumber = accountNumber;
        this.currency = currency;
        this.amount = amount;
        this.exchangeRate = exchangeRate;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public Currency getCurrency() {
        return currency;
    }

    public double getAmount() {
        return amount;
    }

    public Double getExchangeRate() {
        return exchangeRate;
    }

    @Override
    public String toString() {
        return "Transaction details: account number: " + accountNumber + ", currency: " + currency + ", amount: " + amount + ", exchange rate: " + exchangeRate;
    }
}

package banktransactionhandling;

import java.util.HashMap;
import java.util.Map;

import static banktransactionhandling.Currency.HUF;
import static banktransactionhandling.Currency.USD;

public class AccountDataBase {
    private Map<String, Account> accounts;

    public AccountDataBase() {
        accounts = new HashMap<>();
        addAccount(new Account("11111111-22222222", HUF, 150000));
        addAccount(new Account("22222222-33333333", USD, 1230));
    }

    private void addAccount(Account account) {
        accounts.put(account.getAccountNumber(), account);
    }

    public Account getAccount(String accountNumber) {
        return accounts.get(accountNumber);
    }
}

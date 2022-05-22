/**
*@author Aysenur YELEK
*/

import java.util.ArrayList;
import java.util.Locale;

public class Assignment02_20170808060 {


    public static void main(String[] args) throws Exception {

        Bank b = new Bank("My Bank", "My Bank's Address");
        b.addCompany(1, "Company 1");
        b.getCompany(1).openAccount("1234", 0.05);
        b.addAccount(b.getCompany(1).getAccount("1234"));
        b.getAccount("1234").deposit(500000);
        b.getCompany(1).getAccount("1234").deposit(500000);
        b.getCompany(1).openAccount("1235", 0.03);
        b.addAccount(b.getCompany(1).getAccount("1235"));
        b.getCompany(1).getAccount("1235").deposit(25000);
        b.addCompany(2, "Company 2");
        b.getCompany(2).openAccount("2345", 0.03);
        b.addAccount(b.getCompany(2).getAccount("2345"));
        b.getCompany(2).getAccount("2345").deposit(350);
        b.addCustomer(1, "Customer", "1");
        b.addCustomer(2, "Customer", "2");
        Customer c = b.getCustomer(1);
        c.openAccount("3456");
        c.openAccount("3457");
        c.getAccount("3456").deposit(150);
        c.getAccount("3457").deposit(250);
        c = b.getCustomer(2);
        c.openAccount("4567");
        c.getAccount("4567").deposit(1000);
        b.addAccount(c.getAccount("4567"));
        c = b.getCustomer(1);
        b.addAccount(c.getAccount("3456"));
        b.addAccount(c.getAccount("3457"));
        System.out.println(b.toString());

        
    }
}


class Bank {

    private String name;
    private String address;

    private ArrayList<Customer> customers = new ArrayList<>();
    private ArrayList<Company> companies = new ArrayList<>();
    private ArrayList<Account> accounts = new ArrayList<>();

    
    public Bank() {}

    public Bank(String name, String address) {
        this.name = name;
        this.address = address;
    }


    public void transferFunds(String accountFrom, String accountTo, double amount) {
        Account from = getAccount(accountFrom);
        Account to = getAccount(accountTo);
        
        from.withdrawal(amount);
        to.deposit(amount);
    }
    
    public void closeAccount(String accountNum) {
        for (Account account : accounts) {
            if (account.getAcctNum().equals(accountNum)) {
                if (account.getBalance() > 0) {
                    throw new BalanceRemainingException(account.getBalance());
                }
                else {
                    accounts.remove(account);
                    return;
                }
            }
        }
        throw new AccountNotFoundException(accountNum); 
    }
    
    public void addCustomer(int id, String name, String surname) throws Exception {
        this.customers.add(new Customer(id, name, surname));
    }

    public void addCompany(int id, String name) throws Exception{
        this.companies.add(new Company(id, name));
    }

    public void addAccount(Account account) {
        this.accounts.add(account);
    }

    public Customer getCustomer(int id) {
        for (Customer customer : customers) {
            if (customer.getId() == id)
                return customer;
        }
        throw new CustomerNotFoundException(id);
    }

    public Customer getCustomer(String name, String surname) {
        for (Customer customer : customers) {
            if (customer.getName().equals(name) && customer.getSurname().equals(surname))
                return customer;
        }
        throw new CustomerNotFoundException(name, surname);
    }

    public Company getCompany(int id){
        for (Company company : companies) {
            if (company.getId() == id)
                return company;
        }
        throw new CompanyNotFoundException(id);
    }

    public Company getCompany(String name) {
        for (Company company : companies) {
            if (company.getName() == name)
                return company;
        }
        throw new CompanyNotFoundException(name);    
    }
    
    public Account getAccount(String accountNum) {
        for (Account account : accounts) {
            if (account.getAcctNum().equals(accountNum))
                return account;
            }
            throw new AccountNotFoundException(accountNum);    
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public String getAddress() {
        return address;
    }
    
    public void setAddress(String address) {
        this.address = address;
    }

    public String toString() {
        String ret = name + "\t" + address;
        
        for (Company company : companies) {
            ret += "\n\t" + company.toString();
            for (BusinessAccount account : company.businessAccounts) {
                ret += "\n\t\t" + account.getAcctNum() + "\t" + account.getRate() + "\t" + account.getBalance();
            }
        }
        for (Customer customer : customers) {
            ret += "\n\t" + customer.toString();
            for (PersonalAccount account : customer.personalAccounts) {
                ret += "\n\t\t" + account.getAcctNum() + "\t" + account.getBalance();
            }
        }

        return ret;
    }
}

class Account {

    private String accountNumber;
    private double balance;
    

    Account() {}

    Account (String accountNumber) {
        this.accountNumber = accountNumber;
        this.balance = 0;
    }

    Account (String accountNumber, double balance) {
        this.accountNumber = accountNumber;

        if (balance > 0)
            this.balance = balance;
        else
            this.balance = 0;
    }


    public void deposit(double amount) throws InvalidAmountException {
        if (amount >= 0)
            this.balance += amount;
        else 
            throw new InvalidAmountException(amount);
    }

    public void withdrawal(double amount) throws InvalidAmountException {
        if (amount < 0 || this.balance - amount < 0)
            throw new InvalidAmountException(amount);
        else
            this.balance -= amount;
    }
    
    public String getAcctNum() {
        return this.accountNumber;
    }
    
    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }
    
    public double getBalance() {
        return this.balance;
    }
    
    public void setBalance(double balance) {
        if (balance > 0)
            this.balance = balance;
        else
            this.balance = 0;
        }

    public String toString() {
        return "Account " + accountNumber + " has " + balance;
    }
}

class PersonalAccount extends Account {

    private String name;
    private String surname;
    private String PIN;


    PersonalAccount(String accountNumber, String name, String surname){
        super(accountNumber);
        this.name = name;
        this.surname = surname;

        int pin = (int) (Math.random()*10000);
        this.PIN = Integer.toString(pin);
    }

    PersonalAccount(String accountNumber, String name, String surname, double balance) {
        super(accountNumber, balance);
        this.name = name;
        this.surname = surname;

        int pin = (int) (Math.random()*10000);
        this.PIN = Integer.toString(pin);
    }

    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getPIN() {
        return PIN;
    }

    public void setPIN(String PIN) {
        this.PIN = PIN;
    }

    public String toString() {
        return "Account " + this.getAcctNum() + " belonging to " + this.name + " " + this.surname.toUpperCase(Locale.ENGLISH) + " has " + this.getBalance();
    }
}

class BusinessAccount extends Account {
    
    private double rate;


    BusinessAccount(String accountNumber, double rate) throws Exception {
        super(accountNumber);
        if(rate > 0)
            this.rate = rate;
        else 
            this.rate = 0;
    }

    BusinessAccount(String accountNumber, double balance, double rate) throws Exception {
        super(accountNumber, balance);
        if (rate > 0)
            this.rate = rate;
        else
            this.rate = 0;
    }

    
    public double getRate() {
        return rate;
    }

    public void setRate(double rate) throws Exception {
        if (rate > 0)
            this.rate = rate;
        else
            this.rate = 0;
    }

    public double calculateInterest(){
        return this.getBalance()*rate;
    }
}

class Customer {

    private int id;
    private String name;
    private String surname;
    ArrayList<PersonalAccount> personalAccounts;


    Customer (int id, String name, String surname) throws Exception {
        if (id <= 0)
            throw new Exception("id must be positive!");

        this.id = id;
        this.name = name;
        this.surname = surname;
        this.personalAccounts = new ArrayList<PersonalAccount>();
    }


    public void openAccount(String acctNum) throws Exception {
        PersonalAccount personalAccount = new PersonalAccount(acctNum, name, surname);
        personalAccount.setBalance(0);
        personalAccounts.add(personalAccount);
    }
    
    public PersonalAccount getAccount(String accountNum) {
        for (PersonalAccount personalAccount : personalAccounts) {
            if (personalAccount.getAcctNum().equals(accountNum))
                return personalAccount;
        }
        throw new AccountNotFoundException(accountNum);
    }

    public void closeAccount(String accountNum) {
        for (PersonalAccount personalAccount : personalAccounts) {
            if (personalAccount.getAcctNum().equals(accountNum)) {
                if (personalAccount.getBalance() > 0) {
                    throw new BalanceRemainingException(personalAccount.getBalance());
                }
                else {
                    personalAccounts.remove(personalAccount);
                    return;
                }
            }
        }
        throw new AccountNotFoundException(accountNum);
    }
    
    public int getId() {
        return id;
    }
    
    public void setId(int id) throws Exception {
        if (id > 0)
            this.id = id;
            else
            throw new Exception("id must be positive");
        }
        
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public String getSurname() {
        return surname;
    }
    
    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String toString(){
        return name + " " + surname.toUpperCase(Locale.ENGLISH);
    }
}

class Company {

    private String name;
    private int id;
    ArrayList<BusinessAccount> businessAccounts;


    Company (int id, String name) throws Exception {
        if (id <= 0)
            throw new Exception("id must be positive!");
        
        businessAccounts = new ArrayList<>();
        this.name = name;
        this.id = id;
    }

    
    public void openAccount(String acctNum, double rate) throws Exception {
        BusinessAccount businessAccount = new BusinessAccount(acctNum, rate);
        businessAccount.setBalance(0);
        businessAccounts.add(businessAccount);        
    }
    
    public BusinessAccount getAccount(String acctNum) {
        for (BusinessAccount businessAccount : businessAccounts) {
            if (businessAccount.getAcctNum().equals(acctNum))
                return businessAccount;
        }
        throw new AccountNotFoundException(acctNum);
    }

    public void closeAccount(String accountNum) {
        for (BusinessAccount businessAccount : businessAccounts) {
            if (businessAccount.getAcctNum().equals(accountNum)) {
                if (businessAccount.getBalance() > 0)
                    throw new BalanceRemainingException(businessAccount.getBalance());
                else {
                    businessAccounts.remove(businessAccount);
                    return;
                }
            }
        }
        throw new AccountNotFoundException(accountNum);
    }
    
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public int getId() {
        return id;
    }
    
    public void setId(int id) {
        this.id = id;
    }

    public String toString() {
        return name;
    }
}




class CustomerNotFoundException extends RuntimeException {
    private int id;
    private String name, surname;

    public CustomerNotFoundException(int id) {
        this.id = id;
    }

    public CustomerNotFoundException(String name, String surname) {
        this.name = name;
        this.surname = surname;
    }

    public String toString() {
        if (name != null && surname != null)
            return "CustomerNotFoundException: name - " + name + " " + surname;
        else
            return "CustomerNotFoundException: id - " + id;
    }
}

class CompanyNotFoundException extends RuntimeException {
    private int id;
    private String name;

    public CompanyNotFoundException(int id) {
        this.id = id;
    }

    public CompanyNotFoundException(String name) {
        this.name = name;
    }

    public String toString() {
        if (name != null)
            return "CompanyNotFoundException: name - " + name;
        else
            return "CompanyNotFoundException: id - " + id;
    }
}

class AccountNotFoundException extends RuntimeException {
    private String acctNum;

    public AccountNotFoundException(String acctNum) {
        this.acctNum = acctNum;
    }

    public String toString() {
        return "AccountNotFoundException: " + acctNum;
    }
}

class InvalidAmountException extends RuntimeException {
    private double amount;

    public InvalidAmountException(double amount) {
        this.amount = amount;
    }

    public String toString() {
        return "InvalidAmountException: " + amount;
    }
}

class BalanceRemainingException extends RuntimeException {
    private double balance;


    public BalanceRemainingException(double balance) {
        this.balance = balance;
    }
    

    public double getBalance() {
        return balance;
    }

    public String toString() {
        return "BalanceRemainingException: " + balance;
    }
}


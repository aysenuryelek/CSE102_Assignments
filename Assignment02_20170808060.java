import java.nio.charset.Charset;
import java.util.Random;

/**
 * 
 * @author AYÞENUR YELEK
 * @date 03.24.2022
 *
 */

public class Assignment02_20170808060 {

}

class Bank {
	
	private String name;
	private String address;
	
	private String customers;
	private String companies;
	private int accounts;
	
	Customer customerArray[] = new Customer[100] ;
	Company companyArray[] = new Company[100] ;
	
	
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
	
	
	public void addCustomer(int id, String name) {
		
		 // Customer customerArray[] = new Customer[100] ; BURAYI HOCAYA SOR. KENDÝMÝZ ÝSTEDÝÐÝMÝZ BÝR SAYI KOYABÝLÝR MÝYÝZ? ben 100 yazdým mesela????
		for(int i = 0; i < 101; i++) {
			customerArray[i] = new Customer(id, name);
		}
	}
	
	
	
	public void addCompany(int id, String name) {
		
		// Company companyArray[] = new Company[100] ; // BURAYI HOCAYA SORDUM. KENDÝMÝZ ÝSTEDÝÐÝMÝZ BÝR SAYI KOYABÝLÝR MÝYÝZ? ben 100 yazdým mesela????
		for(int i=0; i<101; i++) {
			companyArray[i] = new Company(id, name);
		}
	}
	
	
	
	public void addAccount(Account account) {
		Account accountArray[] = new Account[100] ; // BURAYI HOCAYA SORDUM. KENDÝMÝZ ÝSTEDÝÐÝMÝZ BÝR SAYI KOYABÝLÝR MÝYÝZ? ben 100 yazdým mesela????
		for(int i=0; i<101; i++) {
			accountArray[i] = account;
		}
	}
	
	
	public Customer getCustomer(int id) { // burada id dediði hesap numarasý di mi?
		
		try {
			for(int i=0; i<101; i++) {
				boolean answer;
				answer = (customerArray[i].getId() == id);
				if(answer == true) {
					return customerArray[i];
					
				}
			}
		}
		catch(CustomerNotFoundException e) {
			e.toString();
		}
	}
	
	
	
	public Customer getCustomer(String name) {
		
	}
	
	public Company getCompany(int id) {
		
	}
	
	public Company getCompany(String name) {
		
	}
	
	public Account getAccount(String accountNum) {
		
	}
	
	public Transfers transferFunds (String accountFrom, String accountTo, double amount) {
		
	}
	
	public void closeAccount(String accountNum) {
		
	}
	
	// string metod ekle
}


class Account {
	
	private String accountNumber;
	private double balance;
	
	public Account(String accountNumber) {
		this.accountNumber = accountNumber;
		this.balance = 0;
	}

	public Account(String accountNumber, double balance) {
		this.accountNumber = accountNumber;
		if(balance<0) this.balance = 0;
		else this.balance = balance;
	}

	public String getAcctNum() {
		return accountNumber;
	}

	public double getBalance() {
		return balance;
	}

	public void deposit(double amount) {
		if(amount>=0) this.balance += amount;
		// deðiþiklik yapýlacak
	}

	public void withdrawal(double amount) {
		if(amount>=0) this.balance -= amount;
		// deðiþiklik yapýlacak
	}
	

	@Override
	public String toString() {
		return "Account " + getAcctNum() + " has " + getBalance();
	}

	
}



class PersonalAccount extends Account{

	private String name;
	private String surname;
	private String pin;
	
	public PersonalAccount(String accountNumber, String name, String surname) {
		super(accountNumber);
		this.name = name;
		this.surname = surname;
		byte[] array = new byte[4]; 
	    new Random().nextBytes(array);
	    this.pin = new String(array, Charset.forName("UTF-8"));
	}
	
	public PersonalAccount(String accountNumber, String name, String surname, double balance) {
		super(accountNumber, balance);
		this.name = name;
		this.surname = surname;
		byte[] array = new byte[4]; 
	    new Random().nextBytes(array);
	    this.pin = new String(array, Charset.forName("UTF-8"));
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

	public String getPin() {
		return pin;
	}

	public void setPin(String pin) {
		this.pin = pin;
	}

	@Override
	public String toString() {
		return "Account " + getAcctNum() + " belonging to " + getName() + 
				" " + getSurname().toUpperCase() + " has " + getBalance();
	}
}



class BusinessAccount extends Account{
	
	private double interestRate;

	public BusinessAccount(String accountNumber, double interestRate) {
		super(accountNumber);
		this.interestRate = interestRate;
	}

	public BusinessAccount(String accountNumber, double balance, double interestRate) {
		super(accountNumber, balance);
		this.interestRate = interestRate;
	}

	public double getInterestRate() {
		return interestRate;
	}

	public void setInterestRate(double interestRate) {
		this.interestRate = interestRate;
	}
	
	public double calculateInterest() {
		double amountOfInterest;
		amountOfInterest = (getInterestRate() * getBalance());
		return amountOfInterest;
	}
	
	
}

class Customer{
	
	private int id;
	private String name;
	private String surname;
	private int personalAccounts;
	
	public Customer(String name, String surname) {
		this.name = name;
		this.surname = surname;
	}
	
	public Customer(int id, String name) {
		this.id = id;
		this.name = name;
	}

	

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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
	
	public void openAccount(String acctNum) {
		newPersonalAccount = new PersonalAccount(acctNum, getName(), getSurname(), 0);
		// deðiþiklik yapýlacak
	}
	
	public PersonalAccount getAccount() {
		return newPersonalAccount;
		// deðiþiklik yapýlacak
	}

	public void closeAccount(String accountNum) {
		
	}
	
	
	@Override
	public String toString() {
		return getName() + " " + getSurname().toUpperCase();
	}
}

class Company{
	
	private int id;
	private String name;
	private int businessAccounts;
	
	public Company(String name) {
		this.name = name;
	}
	
	public Company(int id, String name) {
		this.id = id;
		this.name = name;
	}

	public int getId() {
		return id;
	}



	public void setId(int id) {
		this.id = id;
	}



	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public void openAccount(String acctNum, double rate) {
		 newBusinessAccount = new BusinessAccount(acctNum, 0, rate);
		 // deðiþiklik yapýlacak
	}
	
	public BusinessAccount getAccount(String acctNum) {
		return newBusinessAccount ;
		// deðiþiklik yapýlacak
	}
	
	public void closeAccount(String accountNum) {
		
	}

	@Override
	public String toString() {
		return getName();
	}
}



class AccountNotFoundException extends RuntimeException{
	private String acctNum;

	@Override
	public String toString() {
		return "AccountNotFoundException: " + acctNum;
	}
}


	
class BalanceRemainingException extends RuntimeException{
	private double balance;

	@Override
	public String toString() {
		return "BalanceRemainingException: " + balance;
	}
	
	public double getBalance() {
		// içi deðiþecek
	}
	
}


class CompanyNotFoundException extends RuntimeException{
	private int id;
	private String name;
	
	@Override
	public String toString() {
		
	}
	
	
}

class CustomerNotFoundException extends RuntimeException {
	private int id;
	private String name;
	private String surname;
	
	public String toString() {
		if(id == 0) 
			return "CompanyNotFoundException: name - " + name + " " + surname;
		else if(name == null && surname == null) 
			return "CompanyNotFoundException: id - " + Integer.toString(id);
		else
			return " ";
		
	}
	
}

class InvalidAmountException extends RuntimeException{
	private double amount;

	@Override
	public String toString() {
		return "InvalidAmountException: " + amount;
	}
	
	
}



































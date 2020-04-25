package pgdp;

public class BankAccount{
	
private int bankaccount;
private String firstname, surname;
private Money balance;


public BankAccount(int accountnumber, String fname, String surname){
	this.bankaccount = accountnumber;
	this.firstname = fname; 
	this.surname = surname;
	this.balance = new Money();
}

public int getAccountnumber(){
	return bankaccount;
}

public String getFirstname(){
	return firstname;
}

public String getSurname(){
	return surname;
}

public Money getBalance(){
	return balance;
}

public void addMoney(Money m){
	this.balance = this.balance.addMoney(m);
}

public void removeMoney(Money m){
	this.balance = this.balance.removeMoney(m);
}

public String toString(){
	return "Firstname: " + getFirstname() + "\nSurname:   " + getSurname() + "\nBalance:   " + getBalance().toString() + "\nAccountnumber: " + getAccountnumber() + "\n";
}

public static void main(String[] args){
		
}
}


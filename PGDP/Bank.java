package pgdp;

public class Bank {
	private BankAccountList accounts;

	public Bank() {
	}

	public boolean isEmpty() {
		return accounts == null;
	}

	public int getUniqueNumber() {

		int tmp;

		if (accounts == null) {
			tmp = 0;
			return tmp;
		}

		BankAccountList e = accounts;
		while (e.next != null) {
			e = e.next;
		}

		tmp = e.info.getAccountnumber();

		return ++tmp;

	}

	public int newAccount(String firstname, String lastname) {
		BankAccount account = new BankAccount(getUniqueNumber(), firstname, lastname);

		BankAccountList neu = new BankAccountList(account);

		if (accounts == null) {
			accounts = neu;
		} else {
			BankAccountList e = accounts;
			while (e.next != null) {
				e = e.next;
			}
			e.next = neu;
		}
		return account.getAccountnumber();
	}

	public void removeAccount(int accountnumber) {

		BankAccountList e = accounts;
		if (e == null) {return;
		} else if (accounts.info.getAccountnumber() == accountnumber) {
			 accounts = accounts.next;
			 }
		else {
			while (e.next != null) {
				if (e.next.info.getAccountnumber() == accountnumber) { //gibt irgendwanne einen Fehler
					e.next = e.next.next;
					return;
				}
				e = e.next;
			}
		}
	}



	public Money getBalance(int accountnumber) {
		BankAccountList e = accounts;
		
		if (e == null){
			return null;
		}
		
		while (e.next != null) {
			if (accounts.info.getAccountnumber() == accountnumber) {
				 return e.info.getBalance();
				 }
			else if (e.next.info.getAccountnumber() == accountnumber) {
				return e.next.info.getBalance();
			}
			e = e.next;
		}
		return null;
	}

	public boolean depositOrWithdraw(int accountnumber, Money m) {

		BankAccountList e = accounts;
		
		if (e == null){
			return false;
		}

		while (e.next != null) {
			if (accounts.info.getAccountnumber() == accountnumber) {
			 e.info.addMoney(m);
			 return true;
			 }
		else if (e.next.info.getAccountnumber() == accountnumber) {
				e.next.info.addMoney(m);
				return true;
			}
			e = e.next;
		}
		return false;
	}

	public boolean transfer(int from, int to, Money m) {
		boolean tmp1 = false;
		boolean tmp2 = false;

	
		BankAccountList e = accounts;
		
		if (e == null){
			return false;
		}
		
		while (e.next != null) {
			if (e.info.getAccountnumber() == from) {
				tmp1 = true;
			}
			if (e.info.getAccountnumber() == to) {
				tmp2 = true;
			}
			e = e.next;
		}
		if (e.info.getAccountnumber() == from) {
			tmp1 = true;
		}
		if (e.info.getAccountnumber() == to) {
			tmp2 = true;
		}

		if (tmp1 == true && tmp2 == true) {
			System.out.println("true");
			BankAccountList e1 = accounts;
			while (e1.next != null) {
				if (e1.info.getAccountnumber() == from) {
					e1.info.removeMoney(m);
				}
				if (e1.info.getAccountnumber() == to) {
					e1.info.addMoney(m);
				}
				e1 = e1.next;
			}
			if (e1.info.getAccountnumber() == from) {
				e1.info.removeMoney(m);
			}
			if (e1.info.getAccountnumber() == to) {
				e1.info.addMoney(m);
			}
			return true;
		}

		return false;
	}

	public void printBankReport() {
		BankAccountList e = accounts;
		if (e == null){
			return;
		}
		while (e.next != null) {
			System.out.println(e.info.toString());
			e = e.next;
		}
		System.out.println(e.info.toString());
	}

	private class BankAccountList {

		public BankAccount info;
		public BankAccountList next;

		public BankAccountList(BankAccount info) {
			this.info = info;
		}
	}

	public static void main(String[] args) {
//		Bank sparkasse = new Bank();
//		sparkasse.newAccount("Jürgen", "Schott");
//		sparkasse.newAccount("Annika", "Schott");
//		sparkasse.newAccount("Michael", "Schott");
//		sparkasse.newAccount("Karola", "Schott");
//		sparkasse.printBankReport();
//		sparkasse.removeAccount(0);
//		System.out.println(sparkasse.getBalance(0));
//		System.out.println("--------------------------------------");
//		sparkasse.printBankReport();
//		System.out.println("--------------------------------------");
//		sparkasse.depositOrWithdraw(1, new Money(50000));
//		sparkasse.depositOrWithdraw(2, new Money(20000));
//		System.out.println(sparkasse.getBalance(1));
//		sparkasse.printBankReport();
//		System.out.println("--------------------------------------");
//		sparkasse.transfer(2, 3, new Money(12363));
//		sparkasse.printBankReport();
//		System.out.println("--------------------------------------");
//		sparkasse.newAccount("Markus", "Schott");
	}
}

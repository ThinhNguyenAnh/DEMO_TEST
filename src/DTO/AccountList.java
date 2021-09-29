/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DTO;

import DTO.Account;
import DTO.PwdProcessor;
import MyLib.MyValidation;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.StringTokenizer;

/**
 *
 * @author ASUS
 */
public class AccountList extends ArrayList<Account> {

    public AccountList() {
        super();
    }
    public static Scanner sc = new Scanner(System.in);

    public boolean loadFromFile(String fileName) {
        FileReader fr = null;
        BufferedReader br = null;
        String line = "";
        StringTokenizer stk = null;
        try {
            fr = new FileReader(fileName);
            br = new BufferedReader(fr);
            while ((line = br.readLine()) != null) {
                line = line.trim();
                if (line.length() > 0 && !line.startsWith("//")) {
                    stk = new StringTokenizer(line, ";");
                    String id = stk.nextToken().trim();
                    String name = stk.nextToken().trim();
                    String pwd = stk.nextToken().trim();
                    //                 pwd = PwdProcessor.decode(pwd);
                    double amount = Double.parseDouble(stk.nextToken().trim());
                    Account x = new Account(id, name, pwd, amount);
                    this.add(x);
                }

            }
        } catch (Exception e) {
            System.err.println(e);
            System.exit(0);
        } finally {
            try {
                if (fr != null) {
                    fr.close();
                }

                if (br != null) {
                    br.close();
                }
            } catch (Exception e) {
                System.err.println(e);
            }
        }
        return true;
    }

    public boolean writeToFile(String fileName) {
        FileWriter fw = null;
        PrintWriter pw = null;
        try {
            fw = new FileWriter(fileName);
            pw = new PrintWriter(fw);
            for (int i = 0; i < this.size(); i++) {
                pw.println(this.get(i).strToFile());
            }
            pw.flush();
            //System.out.println("Writing file : DONE.  ");
            pw.close();
            fw.close();
        } catch (Exception e) {
            System.err.println(e);
        }
        return true;
    }

    public int search(String id) {
        for (Account x : this) {
            if (x.getId().equals(id)) {
                return -1;
            }
        }
        return 1;
    }

    public Account searchAccount(String id) {
        for (Account x : this) {
            if (x.getId().equalsIgnoreCase(id)) {
                return x;
            }
        }
        return null;
    }

    public void add() {
        String id;
        String name;
        String pwd;
        String confirmPWD;
        int duplicated;
        double amount;
        do {
            //id = MyValidation.inputString("Input id");
            id = MyValidation.inputID("Input id");
            duplicated = search(id);
            if (duplicated < 0) {
                System.err.println("Id: " + id + " has existed");
                System.out.println("Please enter another id");
            }
        } while (duplicated < 0);
        //name = MyValidation.inputString("Input name");
        name = MyValidation.inputName("Input name");
        do {
            pwd = MyValidation.inputPassword("Input password");
            confirmPWD = MyValidation.inputPassword("Confirm password");
            if (!pwd.equalsIgnoreCase(confirmPWD)) {
                System.err.println("Invalid . The password is not match");
            }
        } while (!pwd.equalsIgnoreCase(confirmPWD));
        pwd = PwdProcessor.coded(pwd);
        //pwd = PwdProcessor.decode(pwd);
        System.out.println("Input amount");
        amount = sc.nextDouble();
        Account x = new Account(id, name, pwd, amount);
        if (x != null) {
            this.add(x);
            System.out.println("Add operation succesfull");
        }

    }

    public Account login() {
        String name;
        String password;
        boolean check = false;
        do {
            System.out.println("------------------Login--------------------");
            name = MyValidation.inputString("Account name:");
            password = MyValidation.inputString("Password:");
            if (this.isEmpty()) {
                System.err.println("No account were found in database!");
                return null;
            } else {
                password = PwdProcessor.coded(password);
                for (int i = 0; i < this.size(); i++) {
                    if (this.get(i).name.compareTo(name) == 0 && this.get(i).pwd.compareTo(password) == 0) {
                        System.out.println("Login success");
                        check = true;
                        return this.get(i);

                    }
                }
                System.out.println("Not found account with that infomations");
                return null;
            }
        } while (check != false);
    }

    public void withdraw(Account x) {
        x.withdraw();
    }

    public void deposit(Account x) {
        double amount = 0;
        boolean choice;
        //if (x != null) {
        System.out.println("Enter money you want to deposit");
        amount = MyValidation.inputDouble();
        choice = MyValidation.getBoolean("Confirm " + amount + " you want to deposit (Y/N)");
        if (choice == true) {
            x.deposit(amount);
            System.out.println("Deposit successful");
        } else {
            System.out.println("Exit!");
        }
    }

    public boolean transferMoney(Account x) {
        // Account x = null;
        Account search = null;
        double amount = 0;
        String accountName;
        boolean choice;
        boolean nhaptiep = false;
        boolean check = false;
//        if (x != null) {
        do {
            accountName = MyValidation.inputString("Enter the recipient account");
            for (Account a : this) {
                if (a.name.compareToIgnoreCase(accountName) == 0) {
                    search = a;
                }
            }
            if (search == null) {
                System.err.println("The recipient account does not exist");
                check = true;

            } else {
                System.out.println("Has found the receipent " + search.name);
                check = false;
            }
        } while (check == true);
        do {
            System.out.println("Enter money you want to transfer");
            amount = MyValidation.inputDouble();
            if (x.amount < amount) {
                System.out.println("Your account is not sufficient to make this transaction");
                System.out.println("Please enter again");
                nhaptiep = true;
            } else {
                nhaptiep = false;
            }
        } while (nhaptiep != false);
        choice = MyValidation.getBoolean("Do you want to transfer " + amount + " $ to " + search.name + " (Y/N)");
        if (choice == true) {
            x.transfer(search, amount);
        } else {
            System.out.println("Exit!");
            return false;
        }
        return true;
    }

    public void remove(Account x) {
        //Account x = null;
        ArrayList<Account> delete = new ArrayList<>();
        delete.add(x);
        boolean choice = MyValidation.getBoolean("Do you want to delete " + x.name + " account(Y/N)");
        if (choice == true) {
            System.out.println("Delete " + x.name + " successful");
            this.removeAll(delete);
        } else {
            System.out.println("Exit!");
        }

    }

    public void printAll() {
        for (Account x : this) {
            System.out.println(x.toString());
        }
    }

    public void changePwd() {
        Account change;
        String oldPwd;
        String newPwd;
        String conPwd;
        boolean check = false;
        //System.out.println("Old password:");
        oldPwd = MyValidation.inputString("Old password");
        oldPwd = PwdProcessor.coded(oldPwd);
        for (Account x : this) {
            if (x.pwd.equalsIgnoreCase(oldPwd)) {
                check = true;
                do {
                    newPwd = MyValidation.inputPassword("Enter your new password");
                    conPwd = MyValidation.inputPassword("Confirm password");
                    if (!newPwd.equalsIgnoreCase(conPwd)) {
                        System.err.println("Invalid . The password is not match");
                    }
                } while (!newPwd.equalsIgnoreCase(conPwd));
                newPwd=PwdProcessor.coded(newPwd);
                x.setPwd(newPwd);
                System.out.println("Change password success");
            }
            
        }
        if(check ==false)
        {
            System.out.println("Invalid");
        }
           
        
        
    }

}

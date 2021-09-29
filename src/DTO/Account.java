/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DTO;

import MyLib.MyValidation;
import static MyLib.MyValidation.sc;

/**
 *
 * @author ASUS
 */
public class Account {

    String id;
    String name;
    String pwd;
    Double amount;

    public Account() {

    }

    public Account(String id, String name, String pwd, Double amount) {
        this.id = id;
        this.name = name;
        this.pwd = pwd;
        this.amount = amount;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "Account{" + "id=" + id + ", name=" + name + ", pwd=" + pwd + ", amount=" + amount + '}';
    }

    public void deposit(double deposit) {
        //deposit = sc.nextDouble();
        this.amount += deposit;
        
    }

    public void withdraw() {
        boolean check = false;
        double withDraw;
        do {
            System.out.println("Enter money you want to widthdraw");
            withDraw = MyValidation.inputDouble();
            if (withDraw > this.getAmount()) {
                System.out.println("Your account is not sufficient to make this transaction");
                System.out.println("Please enter again");
                check = false;
            } else {
                this.amount -= withDraw;
                System.out.println("Withdraw successful");
                check = true;
            }
        } while (check != true);
    }

    public boolean withdrawTransfer(double amount) {
        if (amount > this.getAmount()) {
            System.err.println("Your account is not sufficient to make this transaction");
            return false;

        } else {
            this.amount -= amount;
            return true;
        }
    }

    public void transfer(Account a, double transfer) {
        boolean check = false;
        if (transfer > this.getAmount()) {
            System.err.println("Invalid amount");
        } else {       
            withdrawTransfer(transfer);
            a.deposit(transfer);
            System.out.println("Transfer succesful . You have transfer " + transfer + " $ to "+a.name);
        }

    }

    public String strToFile() {
        return id + ";" + name + ";" + pwd + ";" + amount;
    }
}

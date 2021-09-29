/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import DTO.Account;
import DTO.AccountList;
import DTO.Menu;

/**
 *
 * @author ASUS
 */
public class BankMng {

    public static void main(String[] args) {
        String fileAcc = "BankAccount.dat";
        int mainChoice;
        Account x = null;
        Menu mainMenu = new Menu("----------------------------Bank Management-------------------------------- ");
        mainMenu.add("Create new account");
        mainMenu.add("Login function");
        mainMenu.add("Withdrawal function");
        mainMenu.add("Deposit function");
        mainMenu.add("Transfer money");
        mainMenu.add("Remove account");
        

        AccountList list = new AccountList();
        list.loadFromFile(fileAcc);
        for (Account account : list) {
            System.out.println(account.toString());
        }
        do {

            mainChoice = mainMenu.getUserChoice();
            switch (mainChoice) {
                case 1:
                    list.add();
                    break;
                case 2:
                    x = list.login();
                    break;
                case 3:
                    if (x != null) {
                        list.withdraw(x);
                    } else {
                        System.out.println("You need to login to use this function");
                    }
                    //System.out.println(list.toString());
                    break;
                case 4:
                    if (x != null) {
                        list.deposit(x);
                    } else {
                        System.out.println("You need to login to use this function");
                    }
                    //System.out.println(list.toString());
                    break;
                case 5:
                    if (x != null) {

                        list.transferMoney(x);
                    } else {
                        System.out.println("You need to login to use this function");
                    }
                    //System.out.println(list.toString());
                    break;
                case 6:
                    if (x != null) {
                        list.remove(x);
                    } else {
                        System.out.println("You need to login to use this function");
                    }
                    //System.out.println(list.toString());
                    break;
                default:
                    list.writeToFile(fileAcc);
                    System.out.println("GoodBye");
                    break;

            }
        } while (mainChoice > 0 && mainChoice <= mainMenu.size());
        

    }

}

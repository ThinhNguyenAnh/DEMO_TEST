/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MyLib;

import DTO.Account;
import java.util.Scanner;

/**
 *
 * @author ASUS
 */
public class MyValidation {

    public static final Scanner sc = new Scanner(System.in);
    public static final String PASSWORD_VALID = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{6,}$";
    public static final String ID_VALID = "^^[a-zA-Z0-9]+( [a-zA-Z0-9]+)*$";
    public static final String NAME_VALID = "^[a-zA-Z]+( [a-zA-Z]+)*$";

    public static String inputString(String msg) {
        while (true) {
            System.out.println(msg);
            String s = sc.nextLine().trim();
            if (s.isEmpty()) {
                System.out.println("Enter again");
                System.err.println("Empty found!");
            } else {
                return s;
            }
        }
    }
///asdasdasd/a/sd/asd
//    public static double inputAmount(String msg, double withdrawlAmount, double yourAmount) {
//        while (true) {
//            System.out.println(msg);
//            try {
//                withdrawlAmount = Double.parseDouble(sc.nextLine());
//                if (withdrawlAmount > yourAmount) {
//                    throw new NumberFormatException();
//                }
//                return yourAmount -=withdrawlAmount;
//            } catch (NumberFormatException e) {
//                System.err.println("Invalid withdrawl amount");
//                System.out.println("Enter again");
//            }
//
//        }
//    }
    public static String inputPassword(String msg) {
        while (true) {
            String result = inputString(msg);
            if (result.matches(PASSWORD_VALID)) {
                return result;
            } else {
                System.err.println("Invalid password");
                System.out.println("Please enter again");
            }
        }
    }
    
    public static String inputID(String msg)
    {
        while(true)
        {
            String result = inputString(msg);
            if(result.matches(ID_VALID))
            {
                return result;
            }
            else
            {
                System.err.println("Invalid id (Don't allow special character)");
                System.out.println("Please enter again");
            }
        }
    }
    
    public static String inputName(String msg)
    {
        while(true)
        {
            String result = inputString(msg);
            if(result.matches(NAME_VALID))
            {
                return result;
            }
            else
            {
                System.err.println("Invalid name (Don't allow numbers) ");
                System.out.println("Please enter again");
            }
        }
    }

    public static boolean getBoolean(String msg) {
        while (true) {
            String result = inputString(msg);
            if (result.equalsIgnoreCase("Y")) {
                return true;
            } else if (result.equalsIgnoreCase("N")) {
                return false;
            }
            System.err.println("Please input y/Y or n/N");
            System.out.println("Enter again!");
        }
    }

	 public static String inputPassword1(String msg) {
        while (true) {
            String result = inputString(msg);
            if (result.matches(PASSWORD_VALID)) {
                return result;
            } else {
                System.err.println("Invalid password");
                System.out.println("Please enter again");
            }
        }
    }


    public static Double inputDouble() {
        double value;
        while(true)
        {
            try {
                value = Double.parseDouble(sc.nextLine());
                return value;
            } catch (NumberFormatException e) {
                System.err.println("Invalid amount");
                System.out.println("Enter again");
            }
            }
        }
    }

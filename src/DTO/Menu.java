/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DTO;

import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author ASUS
 */
public class Menu extends ArrayList<String> {

    String msg = "";
	String new = "Hello";
String new = "Hello";
String new = "Hello";

    public Menu(String msg) {
        super();
        this.msg = msg;
    }

    public int getUserChoice() {
        while (true) {
            try {

                Scanner sc = new Scanner(System.in);
                System.out.println(msg);
                for (int i = 0; i < this.size(); i++) {
                    System.out.println((i + 1) + "-" + this.get(i));
                }
                System.out.println("Other:Quit");
                System.out.print("Choose: ");
                return Integer.parseInt(sc.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Please input valid choice (Number only)");
            }
        }
    }
}

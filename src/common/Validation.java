/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package common;

import java.util.Scanner;

/**
 *
 * @author GoldCandy
 */
public class Validation {

    public String inputString(String title) {
        String s = "";
        while (s.isBlank() || s.isEmpty()) {
            System.out.print(title + ": ");
            Scanner sc = new Scanner(System.in);
            s = sc.nextLine();
        }
        return s;
    }
}

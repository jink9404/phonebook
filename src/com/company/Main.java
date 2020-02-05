package com.company;

import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {
	// write your code here

        Contact contact = new Contact();
        contact.setName(null);
        System.out.println("name : " + contact.getName());
        contact.setNumber("010-92810413","0322888397");
        System.out.println("전화번호 : " + contact.getArrayListNumber());

    }
}

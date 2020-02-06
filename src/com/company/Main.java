package com.company;

import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {
	// write your code here
        ArrayList<Contact> contactlist = new ArrayList<Contact>();
        Contact contact = new Contact();
        ContactHandler ch = new ContactHandler();
        contactlist = ch.readCSV("Contacts.csv");

        contact.setName("기모찌");
        contact.setNumber("01022222222");
        ch.contactlistPrint(contactlist);
        ch.contactAppend(contactlist,contact);
        System.out.println();
        ch.contactlistPrint(contactlist);

        //ch.contactDelete(contactlist,ch.contactSelect(contactlist,"기모찌"));

        System.out.println();
        ch.contactlistPrint(contactlist);
        ch.writeCSV(contactlist);
    }
}

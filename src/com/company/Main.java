package com.company;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws IOException, InterruptedException {
	// write your code here
        final String os = System.getProperty("os.name");
        if(os.contains("windows"))
            new ProcessBuilder("cmd","/c","cls").inheritIO().start().waitFor();
        ArrayList<Contact> phonebook = null ;
        ContactHandler ch = new ContactHandler();
        try {
            try {
                phonebook = ch.readCSV("Contacts.csv");
            }catch (NoSuchElementException e)
            {
                phonebook = new ArrayList<Contact>();
            }
        }catch(FileNotFoundException e)
        {
            phonebook = new ArrayList<Contact>();
        }


        System.out.println("    ####    #    #   ####    ##    #  ######        ####      ####      ####    #    #");
        System.out.println("    #   #   #    #  #    #   # #   #  #             #    #   #    #    #    #   #  #  ");
        System.out.println("    ####    ###### #      #  #  #  #  ######        #####   #      #  #      #  ## #  ");
        System.out.println("    #       #    #  #    #   #   # #  #             #    #   #    #    #    #   #   # ");
        System.out.println("    #       #    #   ####    #    ##  ######        ####      ####      ####    #    #");

        end:
        while(true)
        {
            Scanner sc = new Scanner((System.in));
            System.out.println("1. 입력   2. 출력   3.수정");
            System.out.print("4. 삭제   5. 검색   6.종료    (1~6) : ");

            switch (sc.nextInt())
            {
                case 1 :
                    System.out.println();
                    ch.contactAppend(phonebook,ch.contactCreate());
                    break;
                case 2 :
                    System.out.println();
                    ch.contactlistPrint(phonebook);
                    break;
                case 3 :
                    Scanner sc1 = new Scanner((System.in));
                    System.out.println();
                    System.out.print("수정할 연락처 인덱스 : ");
                    int index = sc1.nextInt();
                    try{
                        ch.contactModify(ch.contactSelect(phonebook, index));
                    }catch (NullPointerException e){

                    }
                    break;

                case 4 :
                    Scanner sc2 = new Scanner((System.in));
                    System.out.println();
                    System.out.print("삭제할 연락처 인덱스 : ");
                    int index1 = sc2.nextInt();
                    try{
                      ch.contactDelete(phonebook,ch.contactSelect(phonebook,index1));
                    }catch (NullPointerException e)
                    {}
                    break;

                case 5 :
                    Scanner sc3 = new Scanner(System.in);
                    System.out.println();
                    System.out.print("검색 이름 : ");
                    String str = sc3.nextLine();
                    ch.contactlistPrint(ch.contactSearch(phonebook, str));

                    break;

                case 6 :
                    break end;
            }


        }
        try
        {
            ch.writeCSV(phonebook);
        }
        catch (FileNotFoundException e)
        {
            e.printStackTrace();
            System.out.println("비정삭적인 종료로 저장하지 못했음.");
        }
    }
}

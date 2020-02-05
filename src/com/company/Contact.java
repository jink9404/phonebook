package com.company;

import java.awt.image.AreaAveragingScaleFilter;
import java.util.ArrayList;
import java.util.regex.*;

public class Contact
{
    private String name;
    private ArrayList<String> number;
    private String address;
    private String email;

    public Contact()
    {
        this.name = new String();
        this.number = new ArrayList<String>();
        this.address = new String();
        this.email = new String();
    }
    public void setName(String name1) {this.name = name1;}
    public String getName() {return this.name;}
    public void setNumber(String... number1)
    {
        for(String a:number1)
        {
            if(Pattern.matches("^\\d{2,3}\\d{3,4}\\d{4}$",a.trim()) ||
               Pattern.matches("^01(?:0|1|[6-9])(?:\\d{3}|\\d{4})\\d{4}$",a.trim()) ||
               a == null)
            {
                this.number.add(a);
            }else
            {
                System.out.println("(" + a + ") 잘못된 전화번호 형식 입니다. (숫자만 입력)");
            }
        }
    }
    public ArrayList<String> getArrayListNumber(){return this.number;}
    public void setAddress(String address1){this.address = address1;}
    public String getAddress(){return this.address;}
    public void setEmail(String email1)
    {
        if(Pattern.matches("^[a-zA-Z0-9._%+-]+@[a-zA-Z]{2,6}$",email1.trim()) || email1 == null)
            this.email = email1;
        else
            System.out.println(email1 + "비정상적 이메일 주소입니다.");
    }
    public String getEmail(String email1){return null;}
}
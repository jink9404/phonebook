package com.company;


import java.awt.image.AreaAveragingScaleFilter;
import java.io.*;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.regex.Pattern;

public class ContactHandler {
    //private static final String fileName = "Contacts.csv";
    //private static final String filePath = "./";


    public ContactHandler()
    {

    }

    public ArrayList<Contact> readCSV(String path) throws FileNotFoundException, NoSuchElementException{
        ArrayList<Contact> contactList = new ArrayList<Contact>();
        ArrayList<String> tmplist = null;
        FileReader reader = null;
        BufferedReader bufferedReader = null;
        File file = new File(path);

        try
        {
            reader = new FileReader(file, Charset.forName("UTF8"));
            bufferedReader = new BufferedReader(reader);
            String line = "";
            String tmp = "";
            while((line = bufferedReader.readLine())!= null)
            {
                tmplist = new ArrayList<String>();
                StringTokenizer st = new StringTokenizer(line,",");
                Contact contact = new Contact();
                contact.setName(st.nextToken());

                tmp = st.nextToken();
                /*if(tmp.equals("null"))
                    contact.setEmail(null);
                else*/
                    contact.setEmail(tmp);

                tmp = st.nextToken();
                /*if(tmp.equals("null"))
                    contact.setAddress(null);
                else*/
                    contact.setAddress(tmp);
                while(st.hasMoreTokens())
                {
                    tmplist.add(st.nextToken(",tel)"));
                }
                contact.setNumber(tmplist);

                contactList.add(contact);
            }
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        finally
        {
            try
            {
                if(reader != null) reader.close();
                if(bufferedReader != null) bufferedReader.close();
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }


        }
        return contactList;
    }

    public int writeCSV(ArrayList<Contact> contactsArrayList) throws FileNotFoundException
    {
        File file = new File("Contacts.csv");
        FileWriter writer = null;
        BufferedWriter bufferedWriter = null;
        try
        {
            writer = new FileWriter(file, Charset.forName("UTF8"),false);
            bufferedWriter = new BufferedWriter(writer);

            bufferedWriter.write(65279); // byte order mark
            //code write.
            for(int i = 0; i < contactsArrayList.size(); i++)
            {
               bufferedWriter.write(contactsArrayList.get(i).getName());
               bufferedWriter.write(",");
               if (null == contactsArrayList.get(i).getEmail())
               {
                   bufferedWriter.write("null");
               }
               else
                   bufferedWriter.write(contactsArrayList.get(i).getEmail());
               bufferedWriter.write(",");
               if(null == contactsArrayList.get(i).getAddress())
               {
                   bufferedWriter.write("null");
               }
               else
                   bufferedWriter.write(contactsArrayList.get(i).getAddress());
               for (int j = 0; j < contactsArrayList.get(i).getArrayListNumber().size(); j++)
               {
                   if(null == contactsArrayList.get(i).getArrayListNumber().get(j))
                   {
                       bufferedWriter.write("null");
                   }
                   else
                   {
                       bufferedWriter.write(",tel)");
                       bufferedWriter.write(contactsArrayList.get(i).getArrayListNumber().get(j));
                   }
               }
                bufferedWriter.newLine();
            }
            bufferedWriter.flush();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        finally
        {
            try
            {
                if(bufferedWriter != null) bufferedWriter.close();
                if(writer != null) writer.close();
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
        }

        return 0;
    }

    public void contactAppend(ArrayList<Contact> contactArrayList, Contact contact)
    {
        if(contact != null)
            if(contact.getName() != null)
                contactArrayList.add(contact);
            else
        contact.allclear();
        this.contactSort(contactArrayList);
    }
    public void contactDelete(ArrayList<Contact> contactArrayList, Contact contact)
    {
        try
        {
            contactArrayList.remove(contact);
        }
        catch (NullPointerException e)
        {
            System.out.println("삭제할 연락처가 없습니다.");
        }
    }
    private void contactSort(ArrayList<Contact> contactArrayList)
    {
        Collections.sort(contactArrayList);
    }

    public ArrayList<Contact> contactSearch(ArrayList<Contact> contactArrayList, String name)
    {
        ArrayList<Contact> tmpList = new ArrayList<Contact>();
        if(contactArrayList.isEmpty())
            System.out.println("검색 할 연락처가 없습니다.");
        for(int i = 0; i < contactArrayList.size(); i++)
        {
            if(name.equals(contactArrayList.get(i).getName()))
            {
                tmpList.add(contactArrayList.get(i));
            }
            else if(name.length() == 1)
            {
                switch (name.charAt(0))
                {
                    case 'ㄱ': case '가':
                        if(contactArrayList.get(i).getName().charAt(0)>='\uABFF' && contactArrayList.get(i).getName().charAt(0)<='\uB097') //가~낗
                        {tmpList.add(contactArrayList.get(i));continue;} break;
                    case 'ㄴ': case '나':
                        if(contactArrayList.get(i).getName().charAt(0)>='\uB098' && contactArrayList.get(i).getName().charAt(0)<='\uB2E3') //나~닣
                        {tmpList.add(contactArrayList.get(i));continue;} break;
                    case 'ㄷ': case '다':
                        if(contactArrayList.get(i).getName().charAt(0)>='\uB2E4' && contactArrayList.get(i).getName().charAt(0)<='\uB77B') //다~띻
                        {tmpList.add(contactArrayList.get(i));continue;} break;
                    case 'ㄹ': case '라':
                        if(contactArrayList.get(i).getName().charAt(0)>='\uB77C' && contactArrayList.get(i).getName().charAt(0)<='\uB9C7') //라~맇
                        {tmpList.add(contactArrayList.get(i));continue;} break;
                    case 'ㅁ': case '마':
                        if(contactArrayList.get(i).getName().charAt(0)>='\uB9C8' && contactArrayList.get(i).getName().charAt(0)<='\uBC13') //마~밓
                        {tmpList.add(contactArrayList.get(i));continue;} break;
                    case 'ㅂ': case '바':
                        if(contactArrayList.get(i).getName().charAt(0)>='\uBC14' && contactArrayList.get(i).getName().charAt(0)<='\uC0AB') //바~삫
                        {tmpList.add(contactArrayList.get(i));continue;} break;
                    case 'ㅅ': case '사':
                        if(contactArrayList.get(i).getName().charAt(0)>='\uC0AC' && contactArrayList.get(i).getName().charAt(0)<='\uC543') //사~앃
                        {tmpList.add(contactArrayList.get(i));continue;} break;
                    case 'ㅇ': case '아':
                        if(contactArrayList.get(i).getName().charAt(0)>='\uC544' && contactArrayList.get(i).getName().charAt(0)<='\uC78F') //아~잏
                        {tmpList.add(contactArrayList.get(i));continue;} break;
                    case 'ㅈ': case '자':
                        if(contactArrayList.get(i).getName().charAt(0)>='\uC790' && contactArrayList.get(i).getName().charAt(0)<='\uCC27') //자~찧
                        {tmpList.add(contactArrayList.get(i));continue;} break;
                    case 'ㅊ': case '차':
                        if(contactArrayList.get(i).getName().charAt(0)>='\uCC28' && contactArrayList.get(i).getName().charAt(0)<='\uCE73') //차~칳
                        {tmpList.add(contactArrayList.get(i));continue;} break;
                    case 'ㅋ': case '카':
                        if(contactArrayList.get(i).getName().charAt(0)>='\uCE74' && contactArrayList.get(i).getName().charAt(0)<='\uD0BF') //카~킿
                        {tmpList.add(contactArrayList.get(i));continue;} break;
                    case 'ㅌ': case '타':
                        if(contactArrayList.get(i).getName().charAt(0)>='\uD0C0' && contactArrayList.get(i).getName().charAt(0)<='\uD30B') //타~팋
                        {tmpList.add(contactArrayList.get(i));continue;} break;
                    case 'ㅍ': case '파':
                        if(contactArrayList.get(i).getName().charAt(0)>='\uD30C' && contactArrayList.get(i).getName().charAt(0)<='\uD557') //파~핗
                        {tmpList.add(contactArrayList.get(i));continue;} break;
                    case 'ㅎ': case '하':
                        if(contactArrayList.get(i).getName().charAt(0)>='\uD558' && contactArrayList.get(i).getName().charAt(0)<='\uD7A3') //하~힣
                        {tmpList.add(contactArrayList.get(i));continue;} break;
                }
            }
        }
        return tmpList;
    }

    public Contact contactSelect(ArrayList<Contact> contactArrayList, int index)
    {

        if (index < contactArrayList.size() && index >= 0)
        {
            return contactArrayList.get(index);
        }

        return null;
    }
    public void contactlistPrint(ArrayList<Contact> contactArrayList)
    {

        for(int i=0;i<contactArrayList.size();i++)
        {
            System.out.print(i + ". {");
            System.out.print(contactArrayList.get(i).getName());
            System.out.print(", ");
            if(contactArrayList.get(i).getEmail() == null)
                System.out.print("null");
            else
                System.out.print(contactArrayList.get(i).getEmail());
            System.out.print(", ");
            if(contactArrayList.get(i).getAddress() == null)
                System.out.print("null");
            else
                System.out.print(contactArrayList.get(i).getAddress());
            System.out.print(", ");
            System.out.print(contactArrayList.get(i).getArrayListNumber());
            System.out.println("}");
        }
    }

    public Contact contactCreate()
    {
        Contact contact = new Contact();
        Scanner sc = new Scanner(System.in);
        System.out.print("이름 : ");
        contact.setName(sc.nextLine());
        System.out.print("E-Mail : ");
        contact.setEmail(sc.nextLine());
        System.out.print("주소 : ");
        contact.setAddress(sc.nextLine());
        System.out.print("전화 번호 : ");
        String str = sc.nextLine();
        if(Pattern.matches("^\\d{2,3}\\d{3,4}\\d{4}$",str.trim()) ||
           Pattern.matches("^01(?:0|1|[6-9])(?:\\d{3}|\\d{4})\\d{4}$",str.trim()))
            contact.getArrayListNumber().add(str);

        end:
        while(true)
        {
            String str1;
            String str2;
            System.out.println("\'a\' 전화번호 추가 , \'q\' : 종료");
            str1 = sc.nextLine();
            switch (str1)
            {
                case "a": System.out.print("추가 : ");
                    str2 = sc.nextLine();
                    if(Pattern.matches("^\\d{2,3}\\d{3,4}\\d{4}$",str2.trim()) || Pattern.matches("^01(?:0|1|[6-9])(?:\\d{3}|\\d{4})\\d{4}$",str2.trim())){
                        contact.getArrayListNumber().add(str2); }break;
                case "q": break end;
            }
        }
        return contact;
    }
    public void contactModify(Contact contact) throws NullPointerException
    {
        Scanner sc = new Scanner(System.in);
        ArrayList<String> tmp;
        System.out.print("이름 : " + contact.getName() + " ==> ");
        contact.setName(sc.nextLine());
        System.out.print("e-mail : " + contact.getEmail() + " ==> ");
        contact.setEmail(sc.nextLine());
        System.out.print("주소 : " + contact.getAddress() + " ==> ");
        contact.setAddress(sc.nextLine());
        System.out.print("전화번호 : ");
        for(int i = contact.getArrayListNumber().size() - 1; i >= 0 ; i--)
        {
            String str;
            System.out.print(contact.getArrayListNumber().get(i) + "==>");
            str = sc.nextLine();
            if(Pattern.matches("^\\d{2,3}\\d{3,4}\\d{4}$",str.trim()) ||
                    Pattern.matches("^01(?:0|1|[6-9])(?:\\d{3}|\\d{4})\\d{4}$",str.trim()))
                contact.getArrayListNumber().set(i,str);
            else if(str.trim().equals("null"))
                contact.getArrayListNumber().remove(i);
        }
        end:
        while(true)
        {
            String str1;
            String str2;
            System.out.println("\'a\' 전화번호 추가 , \'q\' : 종료");
            str1 = sc.nextLine();
            switch (str1)
            {
                case "a": System.out.print("추가 : ");
                    str2 = sc.nextLine();
                    if(Pattern.matches("^\\d{2,3}\\d{3,4}\\d{4}$",str2.trim()) || Pattern.matches("^01(?:0|1|[6-9])(?:\\d{3}|\\d{4})\\d{4}$",str2.trim())){
                        contact.getArrayListNumber().add(str2); }break;
                case "q": break end;
            }
        }
    }
}
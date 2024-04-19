package com.shark.example.service.email;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EmailFormatVaildExample {

    public static void main(String[] argv) {
        String regex = "^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$";
        List<String> emailList = new ArrayList();
        //valid
        emailList.add("simple@example.com");
        emailList.add("very.common@example.com");
        emailList.add("disposable.style.email.with+symbol@example.com");
        emailList.add("other.email-with-hyphen@example.com");
        emailList.add("fully-qualified-domain@example.com");
        emailList.add("user.name+tag+sorting@example.com");
        emailList.add("x@example.com");
        emailList.add("example-indeed@strange-example.com");
        emailList.add("admin@mailserver1");
        emailList.add("example@s.example");
        emailList.add("\" \"@example.org");
        emailList.add("john..doe\"@example.org");
        emailList.add("mailhost!username@example.org");
        emailList.add("user%example.com@example.org");
        //invalid
        emailList.add("x@example.com");
        emailList.add("Abc.example.com");
        emailList.add("A@b@c@example.com");
        emailList.add("a\"b(c)d,e:f;g<h>i[j\\k]l@example.com");
        emailList.add("just\"not\"right@example.com");
        emailList.add("this is\"not\\allowed@example.com");
        emailList.add("this\\ still\\\"not\\\\allowed@example.com");
        emailList.add("1234567890123456789012345678901234567890123456789012345678901234+x@example.com");
        //steven
        emailList.add("jeff.chen@sis-ai.com");

        Pattern pattern = Pattern.compile(regex);
        for(String email: emailList) {
            Matcher matcher = pattern.matcher(email);
            System.out.println("Email " + email + " is " + (matcher.matches() ? "valid" : "invalid"));
        }
    }
}

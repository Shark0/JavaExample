package com.shark.example.shoalter.chat;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ChatroomExample {

    private Set<String> parserMetaDataUserName(String metaData) {
        Set<String> userNameSet = new HashSet<>();
        int nameStartIndex = -1;
        for(int i = 1; i < metaData.length(); i++) {
            if(metaData.charAt(i) == '@' && metaData.charAt(i - 1) == '[') {
                nameStartIndex = i;
            }
            if(metaData.charAt(i) == ']' && nameStartIndex != -1) {
                String name = metaData.substring(nameStartIndex, i);
                userNameSet.add(name);
            }
        }
        return userNameSet;
    }

    private List<String> parserSubText(String text, Set<String> userNameSet) {
        List<String> textList = new ArrayList<>();
        int charIndex = 0;
        int textStartIndex = 0;
        int startNameIndex = -1;
        int endNameIndex = -1;
        while (charIndex < text.length()) {
            if(text.charAt(charIndex) == '@') {
                startNameIndex = charIndex;
            }
            if(text.charAt(charIndex) == ' ') {
                endNameIndex = charIndex;
            }
            if(startNameIndex != -1 && endNameIndex != -1 && startNameIndex < endNameIndex) {
                String name = text.substring(startNameIndex, endNameIndex);
                if(userNameSet.contains(name)) {
                    if(textStartIndex < startNameIndex) {
                        String subText = text.substring(textStartIndex, startNameIndex);
                        textList.add(subText);
                    }
                    textStartIndex = endNameIndex;
                    startNameIndex = -1;
                    endNameIndex = -1;
                }
            }
            charIndex ++;
        }

        if(textStartIndex < text.length()) {
            String subText = text.substring(textStartIndex);
            textList.add(subText);
        }

        return textList;
    }

    public String replaceMetaDataText(String metaData, List<String> textList) {
        String result = metaData;
        for(String text: textList) {
            result = result.replaceFirst("\\$text", text);
        }
        return result;
    }

    public static void main(String[] args) {
        ChatroomExample example = new ChatroomExample();
        String metaData = "$text[@Shark1]{user1=MMS1}$text[@Shark2]{user2=MMS2}$text";
        Set<String> userNameSet = example.parserMetaDataUserName(metaData);
        String text = "@SharkFakeName1 @Shark1 message1 @Shark2 message2 @SharkFakeName2 @SharkFakeName3";
        List<String> subTextList = example.parserSubText(text, userNameSet);
        System.out.println("subTextList: " + new Gson().toJson(subTextList));
        String result = example.replaceMetaDataText(metaData, subTextList);
        System.out.println(result);
    }
}

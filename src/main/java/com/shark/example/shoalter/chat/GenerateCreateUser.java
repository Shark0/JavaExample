package com.shark.example.shoalter.chat;

import com.opencsv.CSVReader;
import com.shark.example.data.string.StringUtil;
import org.mozilla.universalchardet.UniversalDetector;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;

public class GenerateCreateUser {

    public String generateSql(String fileName) {
        File file = new File(fileName);
        String encode = detectFileEncode(file);
        StringBuilder createUserStringBuilder = new StringBuilder();
        StringBuilder addParticipantStringBuilder = new StringBuilder();
        try (FileReader fileReader = new FileReader(fileName, Charset.forName(encode));
             CSVReader csvReader = new CSVReader(fileReader)) {
            String[] nextRecord;
            int i = 0;
            while ((nextRecord = csvReader.readNext()) != null) {
                if (i != 0) {
                    String createUser = nextRecord[5].replace("CREATE_USER", "TEMP_CREATE_USER");
                    createUserStringBuilder.append(createUser).append("\n");
                    String joinChatroom = nextRecord[6].replace("ADD_PARTICIPANT_TO_CHATROOM", "TEMP_ADD_PARTICIPANT_TO_CHATROOM_BY_CHATROOM_ID");
                    addParticipantStringBuilder.append(joinChatroom).append("\n");
                }

                i++;
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        createUserStringBuilder.append(addParticipantStringBuilder);
        return createUserStringBuilder.toString();
    }

    private String detectFileEncode(File file) {
        byte[] buff = new byte[4096];
        String encode = null;
        try (FileInputStream fileInputStream = new FileInputStream(file)) {
            UniversalDetector detector = new UniversalDetector(null);
            int read;
            while ((read = fileInputStream.read(buff)) > 0 && !detector.isDone()) {
                detector.handleData(buff, 0, read);
            }
            detector.dataEnd();
            encode = detector.getDetectedCharset();
            if (StringUtil.isEmpty(encode)) {
                encode = "UTF-8";
            }
            detector.reset();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("encode: " + encode);
        return encode;
    }

    public void saveText(String fileName, String text) {
        try {
            Files.write(Paths.get(fileName), text.getBytes());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {
        String fileName = "mmsFile/chat/user.csv";
        GenerateCreateUser generateCreateUser = new GenerateCreateUser();
        String sql = generateCreateUser.generateSql(fileName);
        generateCreateUser.saveText("mmsFile/chat/user.txt", sql);
    }
}

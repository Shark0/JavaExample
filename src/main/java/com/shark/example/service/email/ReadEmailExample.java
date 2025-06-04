package com.shark.example.service.email;

import javax.mail.*;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMultipart;
import java.io.File;
import java.io.IOException;
import java.util.Properties;

public class ReadEmailExample {

    public static void main(String[] args) {
        String host = "zmail.shoalter.com.tw";
        String username = "your_account";
        String password = "your_password";
        String saveDirectory = "file/email";

        try {
            // 設置 IMAP 屬性
            Properties properties = new Properties();
            properties.put("mail.store.protocol", "imaps");
            properties.put("mail.imap.host", host);
            properties.put("mail.imap.port", "993");
            properties.put("mail.imaps.ssl.enable", "true");
            properties.put("mail.imaps.ssl.protocols", "TLSv1.3");
            Session session = Session.getDefaultInstance(properties, null);
            Store store = session.getStore("imaps");
            store.connect(host, username, password);
            System.out.println("store connected");

            // 打開收件箱
            Folder inbox = store.getFolder("INBOX");
            inbox.open(Folder.READ_WRITE);

            // 獲取郵件列表
            Message[] messages = inbox.getMessages();
            System.out.println("Total messages: " + messages.length);

            // 創建附件保存目錄
            File downloadDir = new File(saveDirectory);
            if (!downloadDir.exists()) {
                downloadDir.mkdirs();
            }


            // 遍歷郵件
            for (Message message : messages) {
                System.out.println("Subject: " + message.getSubject());
                System.out.println("From: " + message.getFrom()[0]);
                System.out.println("Sent Date: " + message.getSentDate());

                Object content = message.getContent();
                if (content instanceof String) {
                    // 純文本內容
                    System.out.println("Content: " + content);
                } else if (content instanceof Multipart multipart) {
                    // 處理多部分內容（包括附件）
                    processMultipart(multipart, saveDirectory);
                }
            }
            Folder targetFolder = store.getFolder("Processed");
            if (!targetFolder.exists()) {
                targetFolder.create(Folder.HOLDS_MESSAGES);
            }
            targetFolder.open(Folder.READ_WRITE);
            inbox.copyMessages(messages, targetFolder);

            for (Message msg : messages) {
                msg.setFlag(Flags.Flag.DELETED, true);
            }
            // 關閉連接
            inbox.close(false);
            store.close();
        } catch (MessagingException | IOException e) {
            e.printStackTrace();
        }
    }

    private static void processMultipart(Multipart multipart, String saveDirectory)
            throws MessagingException, IOException {
        for (int i = 0; i < multipart.getCount(); i++) {
            BodyPart bodyPart = multipart.getBodyPart(i);
            if (bodyPart.isMimeType("text/plain")) {
                // 提取純文本內容
                System.out.println("Text Content: " + bodyPart.getContent());
            } else if (bodyPart.isMimeType("text/html")) {
                // 提取 HTML 內容
                System.out.println("HTML Content: " + bodyPart.getContent());
            } else if (Part.ATTACHMENT.equalsIgnoreCase(bodyPart.getDisposition())) {
                // 處理附件
                String fileName = bodyPart.getFileName();
                if (fileName != null) {
                    System.out.println("Found attachment: " + fileName);
                    MimeBodyPart mimeBodyPart = (MimeBodyPart) bodyPart;
                    mimeBodyPart.saveFile(new File(saveDirectory + File.separator + fileName));
                    System.out.println("Attachment saved to: " + saveDirectory + File.separator + fileName);
                }
            } else if (bodyPart.getContent() instanceof MimeMultipart) {
                processMultipart((Multipart) bodyPart.getContent(), saveDirectory);
            }
        }
    }
}

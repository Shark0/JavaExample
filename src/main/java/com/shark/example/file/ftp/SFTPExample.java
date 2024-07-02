package com.shark.example.file.ftp;

import com.google.gson.Gson;
import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.Session;
import com.jcraft.jsch.SftpException;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.Vector;

public class SFTPExample {


    public static void main(String[] args)  {
        String sftpHost = "aftp.linksynergy.com";
        String sftpUser = "rkp_4180383";
        String sftpPassword = "oK4Hh6MokFOiMMN1POQJnLB9";
        int sftpPort = 22;
        SFTPExample sftpExample = new SFTPExample();

        Session session = null;
        ChannelSftp channelSftp = null;
        try {
            session = sftpExample.connect(sftpHost, sftpPort, sftpUser, sftpPassword);
            channelSftp = sftpExample.connect(session);
            FtpPathDto ftpPathDto = sftpExample.listFile(channelSftp, "/");
            System.out.println(new Gson().toJson(ftpPathDto));
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if(session != null) {
                session.disconnect();
            }
            if(channelSftp != null) {
                channelSftp.disconnect();
            }
        }
    }

    public Session connect(String host, int port, String user, String password) throws Exception {
        JSch jsch = new JSch();
        Session session = jsch.getSession(user, host, port);
        session.setPassword(password);

        // Avoid asking for key confirmation
        Properties prop = new Properties();
        prop.put("StrictHostKeyChecking", "no");
        session.setConfig(prop);
        session.connect();
        return session;
    }

    public ChannelSftp connect(Session session) throws Exception {
        ChannelSftp channelSftp = (ChannelSftp) session.openChannel("sftp");
        channelSftp.connect();
        return channelSftp;
    }

    public FtpPathDto listFile(ChannelSftp channelSftp, String path) {
        System.out.println("path: " + path);
        Vector<ChannelSftp.LsEntry> fileList;
        FtpPathDto ftpPathDto = new FtpPathDto();
        ftpPathDto.setName(path);
        List<FtpPathDto> childList = new ArrayList<>();
        ftpPathDto.setChildList(childList);
        try {
            channelSftp.cd(path);
            fileList = channelSftp.ls(path);
        } catch (SftpException e) {
            e.printStackTrace();
            return ftpPathDto;
        }
        for (ChannelSftp.LsEntry lsEntry : fileList) {
            System.out.println(lsEntry.toString());
            String fileName = lsEntry.getFilename();
            if(lsEntry.getAttrs().isDir() && !".".equalsIgnoreCase(fileName)) {
                String childPath;
                if("/".equalsIgnoreCase(path)) {
                    childPath = path + fileName;
                } else {
                    childPath = path + "/" + fileName;
                }

                FtpPathDto childFilePath = listFile(channelSftp, childPath);
                childList.add(childFilePath);
            }
        }
        return ftpPathDto;
    }
}

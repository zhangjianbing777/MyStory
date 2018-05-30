package com.nmys.story.scheduled;

import java.io.*;
import java.util.Date;

public class DataBaseDump {

    public static String comman = "C:/Program Files/MySQL/MySQL Server 5.1/bin/mysqldump -uroot -proot story";

    public static void back(String mySqlBackupName, String mysqlBackupPath, String command) {

        String fPath = mysqlBackupPath + "/" + new Date().getTime() + ".sql";

        Runtime rt = Runtime.getRuntime();
        try {
            Process child = rt.exec(command);
            InputStream in = child.getInputStream();
            InputStreamReader input = new InputStreamReader(in, "utf8");

            String inStr;
            StringBuffer sb = new StringBuffer("");
            String outStr;

            BufferedReader br = new BufferedReader(input);
            while ((inStr = br.readLine()) != null) {
                sb.append(inStr + "\r\n");
            }
            outStr = sb.toString();

            FileOutputStream fout = new FileOutputStream(fPath);
            OutputStreamWriter writer = new OutputStreamWriter(fout, "utf8");
            writer.write(outStr);
            writer.flush();

            in.close();
            input.close();
            br.close();
            writer.close();
            fout.close();

            System.out.println("MYSQL备份成功");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args){
        back("stroy","E:/",comman);
    }

}

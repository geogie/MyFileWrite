package com.george.myfilewrite;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Created by George.ren on 2018/10/29.
 * Email:1063658094@qq.com
 * Describe:
 */
public class Utils {
    private static final String TAG = "Utils";

    /**
     * 测试：java环境
     *
     * @param args
     */
    public static void main(String[] args) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                readFile("app/src/main/java/com/george/myfilewrite/file/test1.scp");
                write2File("app/src/main/java/com/george/myfilewrite/file/test1.scp");
            }
        }).start();

    }

    /**
     * 对 myresult.scp 文件如下内容：
     * name-*-age
     * <p>
     * 按照行读取，以-*-来分组
     * <p>
     * 注意：读写权限
     */
    public static void readFile(String path) {
//        File path = new File(Environment.getExternalStorageDirectory().getAbsolutePath() + "/myfile/myresult.scp");
        try {
            InputStream instream = new FileInputStream(path);
            InputStreamReader inputreader = new InputStreamReader(instream);
            BufferedReader buffreader = new BufferedReader(inputreader);
            String line;
            while ((line = buffreader.readLine()) != null) {
                String[] lineArr = line.split("-\\*-");
                System.out.println(lineArr[0] + "  " + lineArr[1]);
            }
            instream.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 分行写入文件
     * <p>
     * 注意：读写权限
     */
    public static void write2File(String path) {
        try {
//            FileWriter finalResult = new FileWriter(Environment.getExternalStorageDirectory().getAbsolutePath() + "/myfile/final_result.txt", false);// false：覆盖写入。true：追加写入
            FileWriter finalResult = new FileWriter(path, true);// false：覆盖写入。true：追加写入
            finalResult.write("content" + "\n");
            finalResult.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}

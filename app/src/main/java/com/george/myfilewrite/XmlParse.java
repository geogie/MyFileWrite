package com.george.myfilewrite;

import android.os.Environment;
import android.util.Log;
import android.util.Xml;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlSerializer;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

/**
 * Created by George.ren on 2018/10/29.
 * Email:1063658094@qq.com
 * Describe:
 */
public class XmlParse {
    private static final String TAG = "XmlParse";

    public static void writeXml() {
        try {
            File file = new File(Environment.getExternalStorageDirectory().getAbsolutePath() + "/myfile/resultScore.xml");
            FileOutputStream fos = new FileOutputStream(file);
            // 获得一个序列化工具
            XmlSerializer serializer = Xml.newSerializer();
            serializer.setOutput(fos, "utf-8");
            // 设置文件头
            serializer.startDocument("utf-8", false);// false-->不允许重复表情。true-->允许重复标签
            serializer.startTag(null, "array1");
            for (int i = 0; i < 10; i++) {
                serializer.startTag(null, "array2");
                serializer.attribute(null, "id", String.valueOf(i));
                // 写姓名
                serializer.startTag(null, "content");
                serializer.text("张三" + i);
                serializer.endTag(null, "content");
                // 写性别
                serializer.startTag(null, "url");
                serializer.text("男" + i);
                serializer.endTag(null, "url");
                // 写年龄
                serializer.startTag(null, "score1");
                serializer.text("1" + i);
                serializer.endTag(null, "score1");

                // 写年龄
                serializer.startTag(null, "score2");
                serializer.text("1" + i);
                serializer.endTag(null, "score2");

                serializer.endTag(null, "array2");
            }
            serializer.endTag(null, "array1");
            serializer.endDocument();
            fos.close();
            Log.d(TAG, "写入成功");
        } catch (Exception e) {
            e.printStackTrace();
            Log.d(TAG, "写入失败");
        }
    }

    public static void readXml() {
        try {
            final File path = new File(Environment.getExternalStorageDirectory().getAbsolutePath() + "/myfile/mytest.xml");
            FileInputStream fis = new FileInputStream(path);
            // 获得pull解析器对象
            XmlPullParser parser = Xml.newPullParser();
            // 指定解析的文件和编码格式
            parser.setInput(fis, "utf-8");
            int eventType = parser.getEventType(); // 获得事件类型
            int stringIndex = 0;
            StringBuilder contentBuild = new StringBuilder();
            StringBuilder urlNameBuild = new StringBuilder();
            StringBuilder urlOldBuild = new StringBuilder();
            StringBuilder tagNameBuild = new StringBuilder();
            while (eventType != XmlPullParser.END_DOCUMENT) {
                tagNameBuild.delete(0, tagNameBuild.length());
                tagNameBuild.append(parser.getName()); // 获得当前节点的名称
                switch (eventType) {
                    case XmlPullParser.START_TAG: // 当前等于开始节点
                        if ("array".equals(tagNameBuild.toString())) {
                            stringIndex = 0;
                        } else if ("string".equals(tagNameBuild.toString())) {
                            if (stringIndex == 0) {
                                contentBuild.delete(0, contentBuild.length());
                                contentBuild.append(parser.nextText());
                            } else if (stringIndex == 1) {
                                urlOldBuild.delete(0, urlOldBuild.length());
                                urlOldBuild.append(parser.nextText());
                                urlNameBuild.delete(0, urlNameBuild.length());
                                urlNameBuild.append(urlOldBuild.substring(urlOldBuild.lastIndexOf("/") + 1));
                            }
                            ++stringIndex;
                        }
                        break;
                    case XmlPullParser.END_TAG: // </persons>  3
                        break;
                    case XmlPullParser.TEXT: // 4
                        break;
                    default:
                        break;
                }
                eventType = parser.next(); // 获得下一个事件类型
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {

        }
    }
}

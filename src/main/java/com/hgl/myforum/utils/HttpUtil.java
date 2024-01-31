package com.hgl.myforum.utils;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.*;

/**
 * @author HGL
 * @Date 2020/9/13
 */
public class HttpUtil {

    public static String get(String urlStr) {
        String ret = null;
        try {

            URL url = new URL(urlStr);

            HttpURLConnection connection = (HttpURLConnection) url
                    .openConnection();

            // 返回结果-字节输入流转换成字符输入流，控制台输出字符
            BufferedReader br = new BufferedReader(new InputStreamReader(
                    connection.getInputStream()));
            StringBuilder sb = new StringBuilder();
            String line;
            while ((line = br.readLine()) != null) {
                sb.append(line);
            }
            ret = sb.toString();

        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return ret;
    }

    public static void main(String[] args) throws UnsupportedEncodingException {
        //http://1.202.136.28:6084/voice/tts?client_id=&text=我觉得你真的好萌啊&speed=&pitch=&voice_name=xiaoxue&sample_rate=&bit=&tag_mode=&eng_mode=&format=wav&language=&dld=
//        String response = get("http://1.202.136.28:6084/voice/tts?" +
//                "text=我觉得你真的好萌啊&" +
//                "voice_name=xiaoxue&" +
//                "format=wav");
        String resp = get("http://1.202.136.28:6084/voice/tts?text=" + URLEncoder.encode("我觉得你真的好萌啊") + "&voice_name=xiaoxue&format=wav");
        System.out.println(resp);
    }
}

package PaChong;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;
import java.io.*;
import java.net.URL;
import java.net.URLConnection;
/**
 * @Description TODO
 * @Author gyhdx
 * @Date 2020/4/29 22:23
 */
public class URLConnGBKTest {
    private static BASE64Decoder decoder = new BASE64Decoder();
    private static BASE64Encoder encoder = new BASE64Encoder();
    public static void main(String[] args) throws Exception {
        String path = "http://www.baidu.com";
        url(path);
        System.out.println("解码：");
        decode("E://GBK.txt");
    }
    public static void url(String path) throws Exception {
        URL url = new URL(path);
        URLConnection urlConnection = url.openConnection();
        InputStream inputStream = urlConnection.getInputStream();
        BufferedReader reader = null;
        BufferedWriter writer = null;
        BufferedWriter writer2 = null;
        try {
            reader = new BufferedReader(new InputStreamReader(inputStream));
            writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("E://baidu.txt")));
            writer2 = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("E://GBK.txt")));
            String read;
            System.out.println("加密");
            while ((read = reader.readLine()) != null){
                writer.write(read);
                read = new String(read.getBytes("UTF-8"),"GBK");
                writer2.write(read);
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            inputStream.close();
            reader.close();
            writer.close();
            writer2.close();
        }
        System.out.println("加密结束");
    }
    public static String encode(String str){
        if (str == null || str.length() < 1) {
            return "";
        }
        return encoder.encode(str.getBytes());
    }
    public static void decode(String path){
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(path)));
            String read;
            while ((read = reader.readLine()) != null){
                read = new String(read.getBytes("GBK"),"UTF-8");
                System.out.println(read);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

package util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class IOTest {

    public static void main(String[] args) throws Exception{
        String path = System.getProperty("user.dir");
        System.out.println(path);
        FileOutputStream fileInputStream = new FileOutputStream(new File("./src/main/java/util/1.txt"));
        FileChannel fileChannel = fileInputStream.getChannel();
        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
        byteBuffer.clear();
        byteBuffer.put(new String("张萌").getBytes());
        byteBuffer.flip();
        while (byteBuffer.hasRemaining()){
            fileChannel.write(byteBuffer);
        }
//        int j = fileChannel.read(byteBuffer);
//        fileChannel.close();
        int i = 0;
    }

}

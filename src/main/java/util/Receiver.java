package util;

import java.io.IOException;
import java.io.PipedInputStream;

public class Receiver extends Thread {

    private PipedInputStream in = new PipedInputStream();

    public PipedInputStream getIn(){
        return in;
    }

    public void run(){
//        readMessageOnce();
        readMessageContinued();
    }

    public void readMessageOnce(){
        // 虽然buf的大小是2048个字节，但最多只会从“管道输入流”中读取1024个字节。
        // 因为，“管道输入流”的缓冲区大小默认只有1024个字节。
        byte[] bytes = new byte[2048];
        try {
            int len = in.read(bytes);
            System.out.println(new String(bytes,0,len));
            in.close();
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public void readMessageContinued(){
        int total = 0;
        while (true){
            byte[] bytes = new byte[1024];
            try {
                int len = in.read(bytes);
                total += len;
                System.out.println(new String(bytes,0,len));
                if(total > 1024)
                    break;
            }catch (IOException e){
                e.printStackTrace();
            }
        }
        try {
            in.close();
        }catch (IOException e){
            e.printStackTrace();
        }
    }

}

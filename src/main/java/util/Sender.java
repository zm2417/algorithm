package util;

import java.io.IOException;
import java.io.PipedOutputStream;

public class Sender extends Thread {
    private PipedOutputStream out = new PipedOutputStream();

    public PipedOutputStream getPipedOutputStream(){
        return out;
    }

    public void run(){
//        writeShortMessage();
            writeLongMessage();
    }

    private void writeShortMessage(){
        String strInfo = "this is a short message";
        try {
            out.write(strInfo.getBytes());
            out.close();
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    private void writeLongMessage(){
        StringBuilder sb = new StringBuilder();
        for(int i = 0;i<102;i++){
            sb.append("0123456789");
        }
        sb.append("abcdefghijklmnopqrstuvwxyz");
        String s = sb.toString();
        try {
            out.write(s.getBytes());
            out.close();
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}

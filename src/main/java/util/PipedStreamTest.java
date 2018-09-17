package util;

import java.io.IOException;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;

public class PipedStreamTest {

    public static void main(String[] args) throws Exception{
        Sender t1 = new Sender();
        Receiver t2 = new Receiver();

        PipedInputStream in = t2.getIn();
        PipedOutputStream out = t1.getPipedOutputStream();

        try {
            in.connect(out);
            t1.start();
            t2.start();
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}

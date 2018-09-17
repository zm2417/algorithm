package util;

import java.io.*;

public class SerializeTest implements Serializable {
    private static final long serialVersionUID = 1L;

    public int i = 1;
    public String a = "asd3";

    public static void main(String[] args){
        try {
//            ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("./src/main/java/util/test.obj"));
//            out.writeObject(new SerializeTest());
//            out.close();

            ObjectInputStream in = new ObjectInputStream(new FileInputStream("./src/main/java/util/test.obj"));
            SerializeTest serializeTest = (SerializeTest) in.readObject();
            System.out.println(serializeTest.a);
            in.close();
        }catch (FileNotFoundException e){
            e.printStackTrace();
        }catch (IOException e){
            e.printStackTrace();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

}

package main.program;

import java.io.FileOutputStream;
import java.nio.charset.StandardCharsets;

public class Log implements Settings {


    /*


     현재 사용하지 않습니다.

    A[0.1.0]

     */

    public void Log(String data) {
        try {


            FileOutputStream outputStream = new FileOutputStream(logPath);

            byte[] buf = data.getBytes(StandardCharsets.UTF_8);
            outputStream.write(buf);

            outputStream.flush();
            outputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }


    }
}

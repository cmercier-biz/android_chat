package com.example.ebiz.myapplication.utils;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;

/**
 * Created by ebiz on 07/07/2017.
 */
public final class HttpRequestUtils {

    public static final int OK_STATUS = 200;

    /**
     * @param inputStream
     * @return the stream as a string
     */
    public static String streamToString(InputStream inputStream) {
        StringBuilder result = new StringBuilder();

        String line;
        try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));) {
            do {
                line = bufferedReader.readLine();
                if (line != null) {
                    result.append(line);
                }
            } while (line != null);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return result.toString();
    }

    public static void writeToStream(OutputStream outputStream, String content) throws IOException {
        DataOutputStream dataOutputStream = new DataOutputStream(outputStream);
        dataOutputStream.writeBytes(content);
        dataOutputStream.flush();
        dataOutputStream.close();
    }
}

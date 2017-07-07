package com.example.ebiz.myapplication.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Created by ebiz on 07/07/2017.
 */

public final class HttpRequestUtils {

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
}

package com.todo.reader;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

@Component
public class ToDoFileReader implements ToDoReader {
    @Value("casestudy.todo.txt")
    public String fileName;

    @Override
    public List<String> readToDos() {
        List<String> result = new ArrayList<>();
        try {
            InputStream inputStream = getClass().getClassLoader().getResourceAsStream(fileName);
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, StandardCharsets.UTF_8);
            BufferedReader reader =
                    new BufferedReader(inputStreamReader);
            String line = "";
            while ((line = reader.readLine()) != null)
                result.add(line);
            reader.close();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return result;
    }
}

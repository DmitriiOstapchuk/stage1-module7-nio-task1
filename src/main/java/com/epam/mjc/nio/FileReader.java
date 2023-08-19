package com.epam.mjc.nio;

import java.io.File;
import java.nio.file.*;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;


public class FileReader {

    public Profile getDataFromFile(File file) {
        String name;
        Integer age;
        String email;
        Long phone;
        Path path = Paths.get(file.getPath());
        try (Stream<String> lines = Files.lines(path)){
            Map<String, String> profileData = lines.map(s -> s.split(": "))
                    .collect(Collectors.toMap(e -> e[0], e -> e[1]));
            name = profileData.get("Name");
            age = Integer.parseInt (profileData.get("Age"));
            email = profileData.get("Email");
            phone = Long.parseLong (profileData.get("Phone"));
            return new Profile(name, age, email, phone);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new Profile();
    }
}

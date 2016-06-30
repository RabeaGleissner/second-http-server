package de.rabea.request;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Directory {

    private String givenDirectory;

    public Directory(String givenDirectory) {
        this.givenDirectory = givenDirectory;
    }

    public List<String> allFilePaths() {
        List<String> files = new ArrayList<>();
        try {
            files = allExistingFiles(files);
        } catch (IOException e) {
            throw new FileException();
        }
        return files;
    }

    private List<String> allExistingFiles(List<String> files) throws IOException {
        Files.walk(Paths.get(givenDirectory)).forEach(filePath -> {
            if (Files.isRegularFile(filePath)) {
                files.add(String.valueOf(filePath));
            }
        });
        return files;
    }

    public List<String> allFileNames() {
        List<String> fileNames = new ArrayList<>();
        for (String filePath : allFilePaths()) {
            String[] folders = filePath.split("/");
            fileNames.add("/" + folders[folders.length - 1]);
        }
        return fileNames;
    }

    public class FileException extends RuntimeException {
        public FileException() {
            super("Cannot read files in given directory. Please make sure the directory is correct.");
        }
    }
}

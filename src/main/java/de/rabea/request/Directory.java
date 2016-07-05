package de.rabea.request;

import de.rabea.communication.FileReader;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Directory {

    private final String givenDirectory;

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

    public List<String> allFileNames() {
        List<String> fileNames = new ArrayList<>();
        for (String filePath : allFilePaths()) {
            String[] folders = filePath.split("/");
            fileNames.add("/" + folders[folders.length - 1]);
        }
        return fileNames;
    }

    public byte[] fileContent(String fileName) {
        String filePath = new File(givenDirectory).getAbsolutePath();
        return FileReader.read(filePath + fileName);
    }

    public Map<String, String> filesWithRelativePaths() {
        Map<String, String> filesWithPaths = new HashMap<>();
        for (String fileName : allFileNames()) {
            filesWithPaths.put(fileName, fileName);
        }
        return filesWithPaths;
    }

    public void updateFile(String fileName, String newContent) {
        String filePath = new File(givenDirectory).getAbsolutePath();
        File file = new File(filePath + fileName);
        try {
            FileWriter fileWriter = new FileWriter(file, false);
            fileWriter.write(newContent);
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public byte[] partialFileContent(String fileName, String range) {
        RangeParser rangeParser = new RangeParser(fileContent(fileName), range);
        return rangeParser.partialContent();
    }

    public class FileException extends RuntimeException {
        public FileException() {
            super("Cannot read files in given directory. Please make sure the directory is correct.");
        }
    }

    private List<String> allExistingFiles(List<String> files) throws IOException {
        Files.walk(Paths.get(givenDirectory)).forEach(filePath -> {
            if (Files.isRegularFile(filePath)) {
                files.add(String.valueOf(filePath));
            }
        });
        return files;
    }
}

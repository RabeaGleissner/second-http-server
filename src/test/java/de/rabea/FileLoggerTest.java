package de.rabea;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static org.junit.Assert.*;

public class FileLoggerTest {

    private String pathToEmptyFile;
    private String pathToFileWithContent;
    private File file;

    @Rule
    public TemporaryFolder folder = new TemporaryFolder();

    @Before
    public void setup() throws IOException {
        file = folder.newFile("log.txt");
        pathToEmptyFile = file.getAbsolutePath();
        File file2 = folder.newFile("content.txt");
        writeContentTo(file2, "Some content");
        pathToFileWithContent = file2.getAbsolutePath();
    }

    @Test
    public void writesMessageToFile() throws IOException {
        FileLogger logger = new FileLogger(pathToEmptyFile);
        logger.log("message");
        assertEquals("message", new String(Files.readAllBytes(Paths.get(pathToEmptyFile))));
    }

    @Test
    public void readsMessageFromFile() {
        FileLogger logger = new FileLogger(pathToFileWithContent);
        assertArrayEquals("Some content".getBytes(), logger.getLogs());
    }

    private void writeContentTo(File file, String content) throws IOException {
        FileWriter writer = new FileWriter(file.getAbsoluteFile());
        BufferedWriter bufferedWriter = new BufferedWriter(writer);
        bufferedWriter.write(content);
        bufferedWriter.close();
    }

}
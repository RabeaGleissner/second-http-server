package de.rabea.communication;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import static org.junit.Assert.*;

public class FileReaderTest {

    private String pathToFile;

    @Rule
    public TemporaryFolder testFolder = new TemporaryFolder();

    @Before
    public void setup() throws IOException {
        File file1 = testFolder.newFile("file1");
        writeContentTo(file1, "Some content");
        pathToFile = file1.getAbsolutePath();
    }

    @Test
    public void returnsFileContent() {
        assertArrayEquals("Some content".getBytes(), FileReader.read(pathToFile));
    }

    @Test(expected = FileReader.FileReaderException.class)
    public void throwsExceptionWhenFilePathIsIncorrect() {
        FileReader.read("wrong path");
    }

    private void writeContentTo(File file, String content) throws IOException {
        FileWriter writer = new FileWriter(file.getAbsoluteFile());
        BufferedWriter bufferedWriter = new BufferedWriter(writer);
        bufferedWriter.write(content);
        bufferedWriter.close();
    }
}
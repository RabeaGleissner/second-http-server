package de.rabea.request;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class DirectoryTest {

    private String pathToFolder;

    @Rule
    public TemporaryFolder testFolder = new TemporaryFolder();

    @Before
    public void setup() throws IOException {
        File file1 = testFolder.newFile("file1");
        writeContentTo(file1, "Some content");
        testFolder.newFile("file2");
        testFolder.newFile("file3");
        pathToFolder = testFolder.getRoot().getAbsolutePath();
    }

    @Test
    public void returnsAllFilePaths() {
        Directory directory = new Directory(pathToFolder);
        List<String> result = directory.allFilePaths();
        assertTrue(result.get(0).contains("/var/folders/gp/r"));
        assertEquals(3, result.size());
    }

    @Test
    public void returnsAllFileNames() {
        Directory directory = new Directory(pathToFolder);
        List<String> result = directory.allFileNames();
        assertEquals("/file1", result.get(0));
        assertEquals("/file2", result.get(1));
        assertEquals("/file3", result.get(2));
    }

    @Test(expected = Directory.FileException.class)
    public void throwsExceptionIfItCannotReadAnyFilesInFolder() {
        String nonExistentFolder = "DIR";
        Directory directory = new Directory(nonExistentFolder);
        directory.allFileNames();
    }

    @Test
    public void returnsContentOfGivenFile() {
        Directory directory = new Directory(pathToFolder);
        assertArrayEquals("Some content".getBytes(), directory.contentOfFile("/file1"));
    }

    @Test
    public void returnsMapOfFileNamesAndCorrespondingPaths() {
        Directory directory = new Directory(pathToFolder);
        Map<String, String> filesAndPaths = directory.filesWithRelativePaths();
        String path = filesAndPaths.get("/file1");
        assertEquals("/file1", path);
    }

    private void writeContentTo(File file, String content) throws IOException {
        FileWriter writer = new FileWriter(file.getAbsoluteFile());
        BufferedWriter bufferedWriter = new BufferedWriter(writer);
        bufferedWriter.write(content);
        bufferedWriter.close();
    }
}
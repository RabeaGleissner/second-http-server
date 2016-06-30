package de.rabea.request;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import java.io.IOException;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class DirectoryTest {

    private String pathToFolder;

    @Rule
    public TemporaryFolder testFolder = new TemporaryFolder();

    @Before
    public void setup() throws IOException {
        testFolder.newFile("file1");
        testFolder.newFile("file2");
        testFolder.newFile("file3");
        pathToFolder = testFolder.getRoot().getAbsolutePath();
    }

    @Test
    public void returnsAllFilePaths() {
        Directory directory = new Directory(pathToFolder);
        List<String> result = directory.allFilePaths();
//        assertEquals("", result.get(0));
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
        String nonExistantFolder = "DIR";
        Directory directory = new Directory(nonExistantFolder);
        directory.allFileNames();
    }
}
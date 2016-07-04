package de.rabea;

import de.rabea.controller.AssetController;
import de.rabea.controller.MethodOptions2Controller;
import de.rabea.controller.NotFoundController;
import de.rabea.request.Directory;
import de.rabea.request.HttpRequest;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import java.io.File;
import java.io.IOException;

import static de.rabea.request.HttpVerb.GET;
import static org.junit.Assert.assertTrue;

public class RouterTest {

    private String pathToFolder;

    @Rule
    public TemporaryFolder testFolder = new TemporaryFolder();

    @Before
    public void setup() throws IOException {
        createFile();
        pathToFolder = testFolder.getRoot().getAbsolutePath();
    }

    private File createFile() throws IOException {
        return testFolder.newFile("file1");
    }

    @Test
    public void returnsControllerForGivenRoute() {
        Router router = new Router();
        router.configure("/method_options2", new MethodOptions2Controller());
        Controller controller = router.getController(new HttpRequest(GET, "/method_options2"));
        assertTrue(controller instanceof MethodOptions2Controller);
    }

    @Test
    public void returnsControllerForNonExistingRoute() {
        Router router = new Router();
        Controller controller = router.getController(new HttpRequest(GET, "/some-route"));
        assertTrue(controller instanceof NotFoundController);
    }

    @Test
    public void returnsAssetControllerForFolderPath() {
        Router router = new Router();
        router.configure(new Directory(pathToFolder), new AssetController(new Directory(pathToFolder)));
        Controller controller = router.getController(new HttpRequest(GET, "/file1"));
        assertTrue(controller instanceof AssetController);
    }
}
package de.rabea;

import de.rabea.controller.AssetController;
import de.rabea.controller.NotFoundController;
import de.rabea.controller.RootController;
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
        Router router = new Router("PUBLIC_DIR");
        router.configure("/", new RootController());
        Controller2 controller = router.getController(new HttpRequest(GET, "/"));
        assertTrue(controller instanceof RootController);
    }

    @Test
    public void returnsControllerForNonExistingRoute() {
        Router router = new Router("PUBLIC_DIR");
        Controller2 controller = router.getController(new HttpRequest(GET, "/some-route"));
        assertTrue(controller instanceof NotFoundController);
    }

    @Test
    public void returnsAssetControllerForFolderPath() {
        Router router = new Router(pathToFolder);
        router.configure(pathToFolder, new AssetController(pathToFolder, new ContentStorage()));
        Controller2 controller = router.getController(new HttpRequest(GET, "/file1"));
        assertTrue(controller instanceof AssetController);
    }

    @Test(expected = Router.FileException.class)
    public void throwsExceptionIfItCannotReadAnyFilesInFolder() {
        String nonExistantFolder = "DIR";
        Router router = new Router(nonExistantFolder);
        router.configure(nonExistantFolder, new AssetController(pathToFolder, new ContentStorage()));
        Controller2 controller = router.getController(new HttpRequest(GET, "/file1"));
        assertTrue(controller instanceof AssetController);
    }
}
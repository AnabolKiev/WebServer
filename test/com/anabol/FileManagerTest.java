package com.anabol;

import org.junit.*;

import java.io.File;
import java.io.IOException;

import static org.junit.Assert.*;

public class FileManagerTest {

    @BeforeClass
    public static void createFiles() {
        try {
            new File("C:/DevTools/test").mkdir();
            new File("C:/DevTools/test/file.tst").createNewFile();
            new File("C:/DevTools/test/XXX").mkdir();
            new File("C:/DevTools/test/XXX/file2.tst").createNewFile();

            new File("C:/test2").mkdir();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void countFilesTest() {
        assertEquals(2, FileManager.countFiles("C:/DevTools/test"));
    }

    @Test
    public void countDirsTest() {
        assertEquals(1, FileManager.countDirs("C:/DevTools/test"));
    }

    @Test
    public void copyTest() {
        try {
            FileManager.copy("C:/DevTools/test", "C:/");
            assertTrue(new File("C:/test/XXX/file2.tst").exists());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void moveTest() {
        try {
            FileManager.move("C:/test", "C:/test2");
            assertTrue(new File("C:/test2/test/XXX/file2.tst").exists());
            assertFalse(new File("C:/test/XXX/file2.tst").exists());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @AfterClass
    public static void deleteFiles() {
        new File("C:/DevTools/test/XXX/file2.tst").delete();
        new File("C:/DevTools/test/file.tst").delete();
        new File("C:/DevTools/test/XXX").delete();
        new File("C:/DevTools/test").delete();

        new File("C:/test2/test/XXX/file2.tst").delete();
        new File("C:/test2/test/file.tst").delete();
        new File("C:/test2/test/XXX").delete();
        new File("C:/test2/test").delete();
        new File("C:/test2").delete();
    }
}

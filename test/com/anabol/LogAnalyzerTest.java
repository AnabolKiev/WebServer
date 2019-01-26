package com.anabol;

import static org.junit.Assert.*;

import org.junit.*;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

public class LogAnalyzerTest {

    @BeforeClass
    public static void beforeTest() throws IOException {
        FileWriter fileWriter = new FileWriter("test.log");
        fileWriter.write("64.242.88.10 - - [07/Mar/2004:16:05:49 -0800] \"GET /twiki/bin/edit/Main/Double_bounce_sender?topicparent=Main.ConfigurationVariables HTTP/1.1\" 401 12846\n");
        fileWriter.write("64.242.88.10 - - [07/Mar/2012:16:11:58 -0800] \"GET /twiki/bin/view/TWiki/WikiSyntax HTTP/1.1\" 200 7352\n");
        fileWriter.write("64.242.88.10 - - [07/Mar/2019:17:53:45 -0800] \"GET /twiki/bin/search/Main/SearchResult?scope=text®ex=on&search=Office%20*Locations[^A-Za-z] HTTP/1.1\" 200 7771");
        fileWriter.close();
    }

    @Test
    public void filterTestAll() throws IOException {
        List<LogToken> list = LogAnalyzer.filter("test.log", LocalDateTime.of(2000, 3, 7, 17, 00), LocalDateTime.of(2019, 3, 7, 18, 00));
        assertEquals(3, list.size());
    }

    @Test
    public void filterTestAllBefore() throws IOException {
        List<LogToken> list = LogAnalyzer.filter("test.log", LocalDateTime.of(2000, 3, 7, 17, 00), LocalDateTime.of(2004, 3, 7, 16, 00));
        assertEquals(0, list.size());
    }

    @Test
    public void filterTestAllInside() throws IOException {
        List<LogToken> list = LogAnalyzer.filter("test.log", LocalDateTime.of(2004, 3, 7, 17, 00), LocalDateTime.of(2019, 3, 7, 17, 00));
        assertEquals(1, list.size());
    }

    @Test
    public void filterTestAfter() throws IOException {
        List<LogToken> list = LogAnalyzer.filter("test.log", LocalDateTime.of(2019, 3, 7, 18, 00), LocalDateTime.of(2020, 3, 7, 17, 00));
        assertEquals(0, list.size());
    }

    @AfterClass
    public static void afterTest() {
        File file = new File("test.log");
        file.delete();
    }

}

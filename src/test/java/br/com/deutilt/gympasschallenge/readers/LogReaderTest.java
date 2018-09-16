package br.com.deutilt.gympasschallenge.readers;

import org.junit.Test;
import org.springframework.mock.web.MockMultipartFile;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class LogReaderTest {

    private MockMultipartFile file;

    private LogReader logReader;

    private List<String> fileContent;

    private final String firstLine = "23:49:10.858    033 – R.BARRICHELLO    1    1:04.352    43,243";
    private final String secondLine =  "23:49:10.858    033 – R.BARRICHELLO    1    1:04.352    43,243";
    private final String thirdLine = "23:49:11.075    002 – K.RAIKKONEN    1    1:04.108    43,408";
    private final String fourthLine = "23:49:12.667    023 – M.WEBBER    1    1:04.414    43,202";
    private final String fifthLine = "23:49:30.976    015 – F.ALONSO    1    1:18.456    35,47";

    private final String multipleLineFileContent = firstLine.concat("\n")
                                                    .concat(secondLine).concat("\n")
                                                    .concat(thirdLine).concat("\n")
                                                    .concat(fourthLine).concat("\n")
                                                    .concat(fifthLine);

    @Test
    public void shouldReadAFileWithOneLine(){
        givenAFileWithOneLine();
        givenALogReader();
        whenReadingAFile();
        shouldReadTheEntireLine();
    }

    @Test
    public void shouldReadAFileWithMultipleLines(){
        givenAFileWithMultipleLines();
        givenALogReader();
        whenReadingAFile();
        shouldReadTheEntireFile();
    }

    @Test
    public void shouldReadAnEmptyFile(){
        givenAnEmptyFile();
        givenALogReader();
        whenReadingAFile();
        shouldReadTheEmptyFile();
    }

    private void givenAFileWithOneLine(){
        this.file = new MockMultipartFile("files", "testFile.txt", "text/plain", firstLine.getBytes());
    }

    private void givenAFileWithMultipleLines(){
        this.file = new MockMultipartFile("files", "testFile.txt", "text/plain", multipleLineFileContent.getBytes());
    }

    private void givenAnEmptyFile(){
        this.file = new MockMultipartFile("files", "testFile.txt", "text/plain", "".getBytes());
    }

    private void givenALogReader(){
        this.logReader = new LogReader();
    }

    private void whenReadingAFile(){
        this.fileContent = this.logReader.readFile(file);
    }

    private void shouldReadTheEntireLine(){
        assertEquals(firstLine, fileContent.get(0));
    }

    private void shouldReadTheEntireFile(){
        assertEquals(firstLine, fileContent.get(0));
        assertEquals(secondLine, fileContent.get(1));
        assertEquals(thirdLine, fileContent.get(2));
        assertEquals(fourthLine, fileContent.get(3));
        assertEquals(fifthLine, fileContent.get(4));
    }

    private void shouldReadTheEmptyFile(){
        assertEquals(new ArrayList<>(), fileContent);
    }

}
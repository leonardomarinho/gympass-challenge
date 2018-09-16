package br.com.deutilt.gympasschallenge.services;

import br.com.deutilt.gympasschallenge.converters.StringToLapRecordConverter;
import br.com.deutilt.gympasschallenge.interfaces.IFileReader;
import br.com.deutilt.gympasschallenge.interfaces.IFileService;
import br.com.deutilt.gympasschallenge.models.Driver;
import br.com.deutilt.gympasschallenge.models.LapRecord;
import br.com.deutilt.gympasschallenge.readers.LogReader;
import br.com.deutilt.gympasschallenge.utils.DurationUtils;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.stubbing.Answer;
import org.springframework.mock.web.MockMultipartFile;

import java.io.File;
import java.math.BigDecimal;
import java.time.Duration;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.anyObject;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class FileServiceProviderTest {

    private MockMultipartFile emptyFile;
    private MockMultipartFile oneLineFile;
    private MockMultipartFile multiLineFile;

    @Mock
    private LogReader logReader;

    @InjectMocks
    private FileServiceProvider fileService;

    @Mock
    private StringToLapRecordConverter stringToLapRecordConverter;

    private List<LapRecord> fileContent;
    private final String firstLine = "Hora    Piloto  Nº Volta    Tempo Volta Velocidade média da volta";
    private final String secondLine = "23:49:10.858    033 – R.BARRICHELLO    1    1:04.352    43,243";
    private final String thirdLine =  "23:49:08.277    038 – F.MASSA    1    1:02.852    44,275";

    private List<String> mockedResult = new ArrayList<>();

    private final String multipleLineFileContent = firstLine.concat("\n")
            .concat(secondLine).concat("\n")
            .concat(thirdLine);

    private LapRecord firstLapRecord = new LapRecord.Builder()
                                                    .withAverageLapSpeed(new BigDecimal(43.243))
                                                    .withLapDuration(DurationUtils.getDurationFrom("1:04.352"))
                                                    .withLapNumber(1)
                                                    .withHour(LocalTime.of(23, 49, 10).withNano(858))
                                                    .withDriver(new Driver("033", "R.BARRICHELLO")).build();

    private LapRecord secondLapRecord = new LapRecord.Builder()
                                                    .withAverageLapSpeed(new BigDecimal(44.275))
                                                    .withLapDuration(DurationUtils.getDurationFrom("1:02.852"))
                                                    .withLapNumber(1)
                                                    .withHour(LocalTime.of(23, 49, 8).withNano(277))
                                                    .withDriver(new Driver("038", "F.MASSA")).build();
    @Before
    public void init(){
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void shouldReadAFileWithOneLine(){
        givenAFileWithOneLine();
        givenAFileServiceProvider();
        whenReadingAOneLineFile();
        shouldProduceALapRecordWithTheSameValuesAsTheLine();
    }

    @Test
    public void shouldReadAFileWithMultipleLines(){
        givenAFileWithMultipleLines();
        givenAFileServiceProvider();
        whenReadingAMultiLineFile();
        shouldReadTheEntireFile();
    }

    @Test
    public void shouldReadAnEmptyFile(){
        givenAnEmptyFile();
        givenAFileServiceProvider();
        whenReadingAnEmptyLineFile();
        shouldReadTheEmptyFile();
    }

    private void givenAFileWithOneLine(){
        this.oneLineFile = new MockMultipartFile("files", "testFile.txt", "text/plain", firstLine.concat("\n").concat(secondLine).getBytes());
    }

    private void givenAFileWithMultipleLines(){
        this.multiLineFile = new MockMultipartFile("files", "testFile.txt", "text/plain", multipleLineFileContent.getBytes());
    }

    private void givenAnEmptyFile(){
        this.emptyFile = new MockMultipartFile("files", "testFile.txt", "text/plain", "".getBytes());
    }

    private void givenAFileServiceProvider(){
        mockedResult = Arrays.asList(firstLine, secondLine, thirdLine);
        when(logReader.readFile(multiLineFile)).thenReturn(mockedResult);
        when(logReader.readFile(oneLineFile)).thenReturn(Arrays.asList(firstLine, secondLine));
        when(logReader.readFile(multiLineFile)).thenReturn(mockedResult);

        when(stringToLapRecordConverter.convert(secondLine)).thenReturn(firstLapRecord);
        when(stringToLapRecordConverter.convert(thirdLine)).thenReturn(secondLapRecord);
    }

    private void whenReadingAOneLineFile(){
        this.fileContent = this.fileService.getFromFile(oneLineFile);
    }

    private void whenReadingAMultiLineFile(){
        this.fileContent = this.fileService.getFromFile(multiLineFile);
    }

    private void whenReadingAnEmptyLineFile(){
        this.fileContent = this.fileService.getFromFile(emptyFile);
    }

    private void shouldProduceALapRecordWithTheSameValuesAsTheLine(){
        assertEquals(firstLapRecord.getAverageLapSpeed(), fileContent.get(0).getAverageLapSpeed());
        assertEquals(firstLapRecord.getHour(), fileContent.get(0).getHour());
        assertEquals(firstLapRecord.getLapDuration(), fileContent.get(0).getLapDuration());
        assertEquals(firstLapRecord.getLapNumber(), fileContent.get(0).getLapNumber());
        assertEquals(firstLapRecord.getDriver(), fileContent.get(0).getDriver());
    }

    private void shouldReadTheEntireFile(){
        assertEquals(firstLapRecord.getAverageLapSpeed(), fileContent.get(0).getAverageLapSpeed());
        assertEquals(firstLapRecord.getHour(), fileContent.get(0).getHour());
        assertEquals(firstLapRecord.getLapDuration(), fileContent.get(0).getLapDuration());
        assertEquals(firstLapRecord.getLapNumber(), fileContent.get(0).getLapNumber());
        assertEquals(firstLapRecord.getDriver(), fileContent.get(0).getDriver());

        assertEquals(secondLapRecord.getAverageLapSpeed(), fileContent.get(1).getAverageLapSpeed());
        assertEquals(secondLapRecord.getHour(), fileContent.get(1).getHour());
        assertEquals(secondLapRecord.getLapDuration(), fileContent.get(1).getLapDuration());
        assertEquals(secondLapRecord.getLapNumber(), fileContent.get(1).getLapNumber());
        assertEquals(secondLapRecord.getDriver(), fileContent.get(1).getDriver());
    }

    private void shouldReadTheEmptyFile(){
        assertEquals(new ArrayList<LapRecord>(), fileContent);
    }
}
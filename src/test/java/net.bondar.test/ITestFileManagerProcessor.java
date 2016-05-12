package net.bondar.test;

import net.bondar.manager.Command;
import net.bondar.manager.ConfigHolder;
import net.bondar.manager.FileManagerProcessor;
import net.bondar.manager.exceptions.ProcessingException;
import net.bondar.manager.executors.ExecutorFactory;
import net.bondar.manager.interfaces.IExecutorFactory;
import net.bondar.manager.interfaces.IProcessor;
import org.apache.commons.io.FileUtils;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.easymock.EasyMock;
import org.testng.annotations.*;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Arrays;

import static junit.framework.Assert.assertTrue;
import static org.easymock.EasyMock.expect;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.fail;
import static org.testng.AssertJUnit.assertFalse;

/**
 * Test for <code>{@link FileManagerProcessor}</code>.
 */
public class ITestFileManagerProcessor {

    /**
     * Logger.
     */
    private static final Logger log = LogManager.getLogger(ITestFileManagerProcessor.class);

    /**
     * Size of buffer.
     */
    private static Integer bufferSize;

    /**
     * Specified file.
     */
    private static File specifiedFile;

    /**
     * Executor factory.
     */
    private static IExecutorFactory executorFactory;

    /**
     * Processor.
     */
    private static IProcessor processor;

    /**
     * Initializes parser instance.
     */
    @BeforeTest
    public static void setUp() {
        ConfigHolder configHolder = EasyMock.createMock(ConfigHolder.class);
        expect(configHolder.getValue("bufferSize")).andReturn("1048576").anyTimes();
        EasyMock.replay(configHolder);
        bufferSize = Integer.parseInt(configHolder.getValue("bufferSize"));
        executorFactory = new ExecutorFactory();
    }

    /**
     * Creates testing files.
     */
    @BeforeMethod
    public void createTestingData() {
        if (new File(System.getProperty("java.io.tmpdir") + "/test").mkdir())
            specifiedFile = createFile(System.getProperty("java.io.tmpdir") + "/test.txt", 3);
    }

    /**
     * Initializes incorrect copy command arguments.
     */
    @DataProvider(name = "testCopyIncorrect")
    public Object[][] createIncorrectCopyData() {
        return new Object[][]{
                {"abcd1234, /home/test/test.txt"},
                {specifiedFile.getAbsolutePath() + ", "},
                {" , /home/test/test.txt"},
                {specifiedFile.getAbsolutePath() + ", abcd1234"},
        };
    }

    /**
     * Initializes correct copy testing arguments.
     */
    @DataProvider(name = "testCopyCorrect")
    public Object[][] createCorrectCopyData() {
        return new Object[][]{
                {specifiedFile.getAbsolutePath() + ", /home/111/"},
                {specifiedFile.getAbsolutePath() + ", /home/test/newTest.txt"},
        };
    }

    /**
     * Initializes incorrect copy command arguments.
     */
    @DataProvider(name = "testReplaceIncorrect")
    public Object[][] createIncorrectReplaceData() {
        return new Object[][]{
                {"abcd1234, /home/test/test.txt"},
                {specifiedFile.getAbsolutePath() + ", abcd1234"},
        };
    }

    /**
     * Initializes correct replace testing arguments.
     */
    @DataProvider(name = "testReplaceCorrect")
    public Object[][] createCorrectReplaceData() {
        return new Object[][]{
                {specifiedFile.getAbsolutePath() + ", /home/test/newtest.txt"},
                {specifiedFile.getAbsolutePath() + ", /home/111/"},
        };
    }

    /**
     * Initializes incorrect remove command arguments.
     */
    @DataProvider(name = "testRemoveIncorrect")
    public Object[][] createIncorrectRemoveData() {
        return new Object[][]{
                {"abcd"},
                {"1234"},
        };
    }

    /**
     * Initializes correct remove testing arguments.
     */
    @DataProvider(name = "testRemoveCorrect")
    public Object[][] createCorrectRemoveData() {
        return new Object[][]{
                {specifiedFile.getAbsolutePath()},
                {specifiedFile.getParentFile()},
        };
    }

    /**
     * Tests processor with correct copy command.
     */
    @Test(dataProvider = "testCopyCorrect")
    public void testProcessCopyCorrect(final String arg) {
        try {
            Command currentCommand = Command.COPY;
            currentCommand.setParameters(Arrays.asList(arg.split(", ")));
            String path = new File(currentCommand.getParameters().get(1)).isDirectory()
                    ? currentCommand.getParameters().get(1) + "/" + new File(currentCommand.getParameters().get(0)).getName()
                    : currentCommand.getParameters().get(1);
            assertTrue(new FileManagerProcessor(currentCommand, executorFactory).process());
            assertTrue(FileUtils.contentEquals(new File(currentCommand.getParameters().get(0)), new File(path)));
        } catch (IOException e) {
            log.error("Error while checking parts content equals. Message: " + e.getMessage());
        }
    }

    /**
     * Tests processor with incorrect copy arguments.
     */
    @Test(dataProvider = "testCopyIncorrect")
    public void testProcessCopyIncorrect(final String arg) {
        try {
            Command currentCommand = Command.COPY;
            currentCommand.setParameters(Arrays.asList(arg.split(", ")));
            new FileManagerProcessor(currentCommand, executorFactory).process();
            fail("Expected incorrect arguments. Arguments: " + arg);
        } catch (ProcessingException e) {
            assertEquals(ProcessingException.class, e.getClass());
        }
    }

    /**
     * Tests processor with correct replace command.
     */
    @Test(dataProvider = "testReplaceCorrect")
    public void testProcessReplaceCorrect(final String arg) {
        try {
            Command currentCommand = Command.REPLACE;
            currentCommand.setParameters(Arrays.asList(arg.split(", ")));
            String path = new File(currentCommand.getParameters().get(1)).isDirectory()
                    ? currentCommand.getParameters().get(1) + "/" + new File(currentCommand.getParameters().get(0)).getName()
                    : currentCommand.getParameters().get(1);
            assertTrue(new FileManagerProcessor(currentCommand, executorFactory).process());
            assertTrue(FileUtils.contentEquals(new File(currentCommand.getParameters().get(0)), new File(path)));
        } catch (IOException e) {
            log.error("Error while checking parts content equals. Message: " + e.getMessage());
        }
    }

    /**
     * Tests processor with incorrect replace arguments.
     */
    @Test(dataProvider = "testReplaceIncorrect")
    public void testProcessReplaceIncorrect(final String arg) {
        try {
            Command currentCommand = Command.REPLACE;
            currentCommand.setParameters(Arrays.asList(arg.split(", ")));
            new FileManagerProcessor(currentCommand, executorFactory).process();
            fail("Expected incorrect arguments. Arguments: " + arg);
        } catch (ProcessingException e) {
            assertEquals(ProcessingException.class, e.getClass());
        }
    }

    /**
     * Tests processor with correct remove command.
     */
    @Test(dataProvider = "testRemoveCorrect")
    public void testProcessRemoveCorrect(final String arg) {
        Command currentCommand = Command.REMOVE;
        currentCommand.setParameters(Arrays.asList(arg.split(", ")));
        assertTrue(new FileManagerProcessor(currentCommand, executorFactory).process());
        assertFalse(new File(currentCommand.getParameters().get(0)).exists());
    }

    /**
     * Tests processor with incorrect remove arguments.
     */
    @Test(dataProvider = "testRemoveIncorrect")
    public void testProcessRemoveIncorrect(final String arg) {
        try {
            Command currentCommand = Command.REMOVE;
            currentCommand.setParameters(Arrays.asList(arg.split(", ")));
            new FileManagerProcessor(currentCommand, executorFactory).process();
            fail("Expected incorrect arguments. Arguments: " + arg);
        } catch (ProcessingException e) {
            assertEquals(ProcessingException.class, e.getClass());
        }
    }

//    /**
//     * Deletes temporary files.
//     */
//    @AfterMethod
//    public static void destroy() {
//        if(specifiedFile.exists()){
//            specifiedFile.deleteOnExit();
//        }
//    }

    /**
     * Creates file for testing.
     *
     * @param dest file destination
     * @param size file size
     * @return complete file
     */
    private static File createFile(final String dest, final int size) {
        File file = new File(dest);
        try (RandomAccessFile output = new RandomAccessFile(file, "rw")) {
            byte[] buffer = new byte[bufferSize];
            while (file.length() < (long) size * bufferSize) {
                output.write(buffer);
            }
        } catch (IOException e) {
            log.error("Error while creating testing file. Message: " + e.getMessage());
        }
        return file;
    }

}

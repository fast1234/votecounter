import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class FileDirectoryCollectionTest {
    private  FileManager fileManager;

    @Before
    public void setUp(){
        this.fileManager = new FileManager();
    }
    @Test
    public void getProcessedFileSizeTest(){
        fileManager.addFile("file1",100,"c1");
        fileManager.addFile("file2",100,"c1");
        fileManager.addFile("file3",100,"c1");
        fileManager.addFile("file3",100,"c2");
        fileManager.addFile("file4",100,"c2");
        Assert.assertEquals(500,fileManager.getProcessedFileSize());
    }

    @Test
    public void getTopKCollectionsTest(){
        fileManager.addFile("file1",100,"c1");
        fileManager.addFile("file2",100,"c1");
        fileManager.addFile("file3",100,"c1");
        fileManager.addFile("file3",100,"c2");
        fileManager.addFile("file4",100,"c2");
        fileManager.addFile("file4",100,"");
        Directory[] directories = fileManager.getTopKCollections(3);
        Directory[] expectedDirectories = new Directory[1];
        expectedDirectories[0] = new Directory("c1");
        expectedDirectories[0].addFile(new File("file1",100));
        expectedDirectories[0].addFile(new File("file2",100));
        expectedDirectories[0].addFile(new File("file3",100));
        Assert.assertEquals(expectedDirectories.length,directories.length);
    }
}

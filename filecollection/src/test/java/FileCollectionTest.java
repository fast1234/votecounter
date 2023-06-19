import org.example.Directory;
import org.example.File;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FileCollectionTest {
    private FileCollection fc;

    @Before
    public void setUp(){
        fc = new FileCollection();
    }

    @Test
    public void topKCollectionTest(){
        fc.addFile("file1",800,"");
        fc.addFile("file2",400,"Collection1");
        fc.addFile("file2",400,"Collection2");
        fc.addFile("file3",100,"Collection1");
        Directory[] directories = fc.getTopKCollection(1);
        Directory[] expectedDirs = new Directory[1];

        List<File> files = new ArrayList<>(Arrays.asList(new File("file2",400)
                ,new File("file3",100)));
        expectedDirs[0] = new Directory("Collection1",files,500);
        //expectedDirs[1] = new Directory("Collection2");

        //Assert.assertArrayEquals(expectedDirs,directories);
        Assert.assertEquals(expectedDirs[0].getName(),directories[0].getName());
    }

    @Test
    public void totalSizeTest(){
        fc.addFile("file1",200,"");
        fc.addFile("file2",300,"Collection1");
        fc.addFile("file2",400,"Collection2");
        fc.addFile("file3",100,"Collection1");
        Assert.assertEquals(1000,fc.totalSize());
    }
}

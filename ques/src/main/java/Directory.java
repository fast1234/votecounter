import java.util.ArrayList;
import java.util.List;

public class Directory {
    List<File> files;

    String dirName;

    int size;

    public Directory(String name){
        this.dirName = name;
        this.files = new ArrayList<>();
        this.size = 0;
    }

    public void addFile(File file){
        this.files.add(file);
        this.size+=file.getSize();
    }

    public int getSize(){
        return this.size;
    }
}

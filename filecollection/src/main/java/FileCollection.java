import org.example.Directory;
import org.example.File;

import java.util.*;

public class FileCollection {
    PriorityQueue<Directory> topDirectory;
    Map<String, Directory> directoryMap;
    int totalSize;
    public FileCollection(){
        this.topDirectory = new PriorityQueue<>((a,b)->{
            return b.getSize()-a.getSize();
        });
        this.directoryMap = new HashMap<>();
        this.totalSize = 0;
    }

    public int totalSize() {
        return this.totalSize;
    }

    public Directory[] getTopKCollection(int k) {
        Directory[] topDirectories = new Directory[k];
        List<Directory> directoryList = new ArrayList<>();
        int index=0;
        while(k>0){
            Directory directory = topDirectory.poll();
            topDirectories[index++]=directory;
            directoryList.add(directory);
            k--;
        }
        topDirectory.addAll(directoryList);
        return topDirectories;
    }

    public void addFile(String fileName, int size, String collectionName) {
        Directory directory;
        if(directoryMap.containsKey(collectionName)) {
            directory = directoryMap.get(collectionName);
        }
        else{
            directory = new Directory(collectionName);
        }
        directory.addFile(new File(fileName,size));
        this.totalSize+=size;
        directoryMap.put(collectionName, directory);
        if(!collectionName.isEmpty())
            topDirectory.add(directory);
    }
}

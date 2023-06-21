import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

public class FileManager {
    private AtomicInteger currentSize;

    Map<String,Directory> directoryMap;

    PriorityQueue<Directory> orderedDirectories;
    public FileManager(){
        this.currentSize = new AtomicInteger();
        this.directoryMap = new HashMap<>();
        this.orderedDirectories = new PriorityQueue<>((a,b)->{
            return b.getSize()-a.getSize();
        });
    }

    public void addFile(String fileName, int size, String collection) {
        File file = new File(fileName,size);
        if(directoryMap.containsKey(collection)){
            directoryMap.get(collection).addFile(file);
        }
        else {
            Directory directory = new Directory(collection);
            directory.addFile(file);
            directoryMap.put(collection,directory);
            if(!collection.isEmpty()){
                this.orderedDirectories.add(directory);
            }
        }
        this.currentSize.addAndGet(size);
    }

    public int getProcessedFileSize(){
        return this.currentSize.get();
    }

    public Directory[] getTopKCollections(int rank){
        List<Directory> directoryList = new ArrayList<>();
        Directory[] topDirectory = new Directory[rank];
        for(int i=0;i<rank && !orderedDirectories.isEmpty();i++){
            topDirectory[i]=orderedDirectories.peek();
            System.out.println(orderedDirectories.peek().dirName);
            directoryList.add(orderedDirectories.poll());
        }
        orderedDirectories.addAll(directoryList);
        //System.out.println(topDirectory[2].dirName);
        return topDirectory;
    }
}

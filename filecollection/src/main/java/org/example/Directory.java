package org.example;

import java.util.ArrayList;
import java.util.List;

public class Directory {
    List<File> files;

    String name;
    int size;
    public Directory(String dirName){
        this.files = new ArrayList<>();
        this.name = dirName;
        this.size=0;
    }

    public Directory(String dirName, List<File> files, int size){
        this.files = files;
        this.name = dirName;
        this.size = size;
    }

    public int getSize(){
        return this.size;
    }

    public void addFile(File file){
        this.size+= file.size;
    }

    public String getName(){
        return this.name;
    }
}

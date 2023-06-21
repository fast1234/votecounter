public class File {
    private String name;
    private  int size;
    public File(String fileName,int size){
        this.name = fileName;
        this.size = size;
    }

    public int getSize(){
        return size;
    }
}

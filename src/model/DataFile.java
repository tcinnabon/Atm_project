package model;

public class DataFile {

    private String data[];

    public DataFile(String[] data) {
        this.data = data;
    }

    public String returnBalanceAtm(){
        return data[0];
    }

    public int sizeData(){
        return data.length;
    }

    public String returnElement(int index){
        return data[index];
    }
}

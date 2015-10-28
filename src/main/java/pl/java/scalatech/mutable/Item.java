package pl.java.scalatech.mutable;

import lombok.Data;

@Data
public class Item {

    private String name;
    private int value;
    
    public void restartValue(){
        value=0;
    }
    
}

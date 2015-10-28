package pl.java.scalatech.mutableTest;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.junit.Test;

import lombok.extern.slf4j.Slf4j;
import pl.java.scalatech.immutable.ImmutableItem;
import pl.java.scalatech.mutable.Item;
@Slf4j
public class MutableItemTest {
    
    final ExecutorService executorService = Executors.newFixedThreadPool(20);
    private final static int VALUE = 4;
    private final static int CONSTANT = 1;
    private Item item = new Item();
    
    private ImmutableItem immutableItem = new ImmutableItem(VALUE);
    
    void immutableEvaluateItem(ImmutableItem item){
        item = new ImmutableItem(VALUE);
        try {
            Thread.sleep(3);
        } catch (InterruptedException e) {
        }
        int x  = item.getValue();
        item = new ImmutableItem(x+CONSTANT);
        log.info("last value  immutable:  {} => thread {} ",item.getValue(), Thread.currentThread().getName());
        item = new ImmutableItem(0);
    }
    
    void evaluateItem(Item item){
        item.setValue(VALUE);
        try {
            Thread.sleep(3);
        } catch (InterruptedException e) {
        }
        int x  = item.getValue();
        item.setValue(x+CONSTANT);
       // Assert.assertEquals(5, item.getValue());
        log.info("last value :  {} => thread {} ",item.getValue(), Thread.currentThread().getName());
        item.restartValue();
    }
    
    @Test
    public void manipulateData(){
        for(int i =0;i<20;i++){
       log.info("for : ");
        executorService.execute(()->{ evaluateItem(item);   });
        }
    }
    
    @Test
    public void immutableManipulateData(){
        for(int i =0;i<20;i++){
        log.info("for immutable : ");
        executorService.execute(()->{ immutableEvaluateItem(immutableItem);   });
        }
    }
    
}



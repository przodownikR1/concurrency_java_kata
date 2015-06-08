package pl.java.scalatech.concurrency;

import lombok.extern.slf4j.Slf4j;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.task.TaskExecutor;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import pl.java.scalatech.config.AppConfig;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = AppConfig.class)
public class TaskExecutorTest {

    @Autowired
    private TaskExecutor te;

    @Test
    public void shouldTaskExecutorWork() {
        for (int i = 0; i < 25; i++) {
            String message = "Execution " + i;
            te.execute(new Messager());
        }

    }

    public void readWriteTest() {
        try {
            /*
             * ReadWriteResource resource = (ReadWriteResource) ApplicationContextManager
             * .getBean("readWriteResoure");
             * TaskExecutor taskExecutor = (TaskExecutor) ApplicationContextManager
             * .getBean("defaultTaskExecutor");
             * taskExecutor.execute(new ReadTask(resource));
             * taskExecutor.execute(new WriteTask(resource));
             * taskExecutor.execute(new ReadTask(resource));
             */
        } catch (BeansException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}

@Slf4j
class Messager implements Runnable {

    @Override
    public void run() {
        log.info(" +++   {}", "messager !!!");

    }

}

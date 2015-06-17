package pl.java.scalatech.bean;

import java.nio.charset.Charset;
import java.util.Random;

import lombok.extern.slf4j.Slf4j;

import com.google.common.hash.Hashing;

@Slf4j
public class HelperService {
    Random r = new Random();

    public void useResult(String result) {
        log.info("+++ result :  {}", result);
    }

    public void doSomethingElse() {
        log.info("+++ start doSomethingElse ");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            log.error("{}", e);
        }
        log.info("+++ end doSomethingElse");
    }

    public String computeResult() {
        log.info("+++ start computeResult ");
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            log.error("{}", e);
        }
        return Hashing.md5().hashString("" + r.nextInt(10), Charset.defaultCharset()).toString();

    }

}

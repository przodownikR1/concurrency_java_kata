package pl.java.scalatech.notification;

import lombok.extern.slf4j.Slf4j;

import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class ResponseHandlerTwo implements ApplicationListener<MessageEvent> {

    @Override
    public void onApplicationEvent(MessageEvent event) {
        log.info("+++ Two   {}, {}  ", event.getSource(), event.getMsg());
    }
}

package pl.java.scalatech.notification;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;
import org.springframework.stereotype.Component;

@Component
public class SampleSpringNotification implements ApplicationContextAware, ApplicationEventPublisherAware {

    private ApplicationContext applicationContext;
    private ApplicationEventPublisher applicationEvenPublisher;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;

    }

    public void sendEvent() {
        this.applicationEvenPublisher.publishEvent(new MessageEvent(this, "hello world"));
    }

    @Override
    public void setApplicationEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
        this.applicationEvenPublisher = applicationEventPublisher;

    }

}

package pl.java.scalatech.notification;

import lombok.Getter;
import lombok.ToString;

import org.springframework.context.ApplicationEvent;

@ToString
public class MessageEvent extends ApplicationEvent {

    private static final long serialVersionUID = 212813971454653731L;
    @Getter
    private String msg;

    public MessageEvent(Object source, String msg) {
        super(source);
        this.msg = msg;
    }

}

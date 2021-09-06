package io.schultz.dustin.todoapp.customization;

import org.springframework.boot.actuate.endpoint.annotation.DeleteOperation;
import org.springframework.boot.actuate.endpoint.annotation.Endpoint;
import org.springframework.boot.actuate.endpoint.annotation.ReadOperation;
import org.springframework.boot.actuate.endpoint.annotation.WriteOperation;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
@Endpoint(id="mycustomactuator")
public class MyCustomActuator {

    class Container {
        private String status;
        public Container(String status) {
            this.status = status;
        }
        public String getStatus() {return status;}
        public void setStatus(String status) {this.status = status;}
    }

    String status;

    public MyCustomActuator() {
        System.out.println("-MyCustomActuator--starting");
        status = "APP_STARTING";
    }

    @ReadOperation
    public Container getStatus() {
        System.out.println("-MyCustomActuator--read:"+status);

        return new Container(status);
    }

    @EventListener(ApplicationReadyEvent.class)
    public void onReady() {
        System.out.println("-MyCustomActuator--ready");
        this.status = "APP_READY";
    }

    @WriteOperation
    public Container setStatus(String status) {
        System.out.println("-MyCustomActuator--changed to:"+status);
        this.status = status;
        return new Container(this.status);
    }

    @DeleteOperation
    public void removeStatus() {
        System.out.println("-MyCustomActuator--delete:");
        this.status = "UNKNOWN";
    }

}

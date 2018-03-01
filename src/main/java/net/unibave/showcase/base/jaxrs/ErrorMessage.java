package net.unibave.showcase.base.jaxrs;

import java.util.*;

public class ErrorMessage {

    private String message;

    private Map<String, String> detailMessage = new HashMap<>();

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Map<String, String> getDetailMessage() {
        return Collections.unmodifiableMap(detailMessage);
    }

    public Map<String, String> addDetailMessage(String attribute, String message) {
        detailMessage.put(attribute, message);
        return detailMessage;
    }

    public Map<String, String> addDetailMessage(Map<String, String> messages) {
        detailMessage.putAll(messages);
        return detailMessage;
    }

    public Map<String, String> removeDetailMessage(String message) {
        detailMessage.remove(message);
        return detailMessage;
    }

    public void clearDetailMessage() {
        detailMessage.clear();
    }

    @Override
    public String toString() {
        return "ErrorMessage{" +
                "message='" + message + '\'' +
                ", detailMessage=" + detailMessage +
                '}';
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {

        private ErrorMessage errorMessage =  new ErrorMessage();

        public Builder message(String message) {
            errorMessage.setMessage(message);
            return this;
        }

        public Builder addDetailMessage(String attribute, String message) {
            errorMessage.addDetailMessage(attribute, message);
            return this;
        }

        public ErrorMessage build() {
            return errorMessage;
        }
    }

}

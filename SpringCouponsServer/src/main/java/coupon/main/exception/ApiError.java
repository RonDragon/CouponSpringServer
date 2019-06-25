package coupon.main.exception;

import java.util.Arrays;
import java.util.List;

import org.springframework.http.HttpStatus;

public class ApiError {
	 private HttpStatus status;
    private List<String> messages;
     
     
    public ApiError(HttpStatus status, String... messages) {
        super();
        this.status = status;
        this.messages = Arrays.asList(messages);
    }
     
    public void addMessage(String message) {
        messages.add(message);
    }
 
    public HttpStatus getStatus() {
        return status;
    }
     
    public void setStatus(HttpStatus status) {
        this.status = status;
    }
     
    public List<String> getMessages() {
        return messages;
    }
     
    public void setMessages(List<String> messages) {
        this.messages = messages;
    }
     
}


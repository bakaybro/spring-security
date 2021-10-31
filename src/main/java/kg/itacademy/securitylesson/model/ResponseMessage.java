package kg.itacademy.securitylesson.model;

import lombok.*;
import org.springframework.http.HttpStatus;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ResponseMessage<T> {
    private T value;
    private String status;
    private String details;

    public ResponseMessage<T> prepareSuccessMessage(String status, String details) {
        ResponseMessage<T> successMessage = new ResponseMessage<>();
        successMessage.setValue(value);
        successMessage.setStatus(HttpStatus.OK.getReasonPhrase());
        successMessage.setDetails("");
        return successMessage;
    }

    public ResponseMessage<T> prepareFailMessage(String status, String details) {
        ResponseMessage<T> successMessage = new ResponseMessage<>();
        successMessage.setValue(value);
        successMessage.setDetails("");
        return successMessage;
    }

//    public ResponseMessage<T> prepareSuccessMessage(String status, String details) {
//        ResponseMessage<T> failMessage = new ResponseMessage<>();
//        failMessage.setValue(value);
//        failMessage.setStatus(HttpStatus.OK.getReasonPhrase());
//        failMessage.setDetails("");
//        return failMessage;
//    }

}

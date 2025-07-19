package kg.kazbekov.megatesttask.dto;

import lombok.Data;
import org.springframework.http.ResponseEntity;

@Data
public class RestResponse<T> {
    private String message;
    private T data;

    public static <T> ResponseEntity<RestResponse<T>> ok(T response){
        RestResponse<T> restResponse = new RestResponse<>();
        restResponse.setData(response);
        return ResponseEntity.ok().body(restResponse);
    }

    public static RestResponseBuilder message(String message){
        RestResponseBuilder builder = new RestResponseBuilder();
        builder.message = message;
        return builder;
    }

    public static class RestResponseBuilder {
        private String message = "";

        public <T> ResponseEntity<RestResponse<T>> ok(T response){
            RestResponse<T> restResponse = new RestResponse<>();
            restResponse.setData(response);
            restResponse.setMessage(this.message);
            return ResponseEntity.ok().body(restResponse);
        }

        public <T> ResponseEntity<RestResponse<T>> build(){
            RestResponse<T> restResponse = new RestResponse<>();
            restResponse.setMessage(this.message);
            return ResponseEntity.ok().body(restResponse);
        }
    }
}

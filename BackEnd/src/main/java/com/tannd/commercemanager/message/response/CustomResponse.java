package com.tannd.commercemanager.message.response;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Data
public class CustomResponse {
    private int status;
    private String message;
    private Object data;
}

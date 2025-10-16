package it.mdotm.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class BaseException {

    private int status;
    private String message;
    private long timestamp;
    private String path;
}

package com.fusion.center.bff.interfaces.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

@Data
public class UserInfoDTO implements Serializable {

    @NotBlank(message = "searchKey is not null")
    private String searchKey;

}

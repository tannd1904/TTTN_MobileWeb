package com.tannd.commercemanager.dto;

import com.mysql.cj.exceptions.DataReadException;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
public class AbstractDTO {

    protected Date createdDate;
    protected Date updatedDate;

    public AbstractDTO( Date createdDate, Date updatedDate) {
        this.createdDate = createdDate;
        this.updatedDate = updatedDate;
    }
}

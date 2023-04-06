package com.doitwell.group.springTesting.RDV;

import org.springframework.stereotype.Component;

@Component
public class RDVMapper {

    public RDVDto toDto(RDV rdv){
        return new RDVDto(rdv.getId(),rdv.getUser());
    }
}

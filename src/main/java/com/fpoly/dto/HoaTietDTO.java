package com.fpoly.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter
public class HoaTietDTO extends BaseDTO<BaseDTO>{
    private String tenHoaTiet;

    private Boolean daXoa;
}

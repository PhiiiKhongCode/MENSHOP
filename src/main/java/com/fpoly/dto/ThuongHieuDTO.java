package com.fpoly.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class ThuongHieuDTO extends BaseDTO<BaseDTO>{
    private int soSanPhamCungThuongHieu;

    private String tenThuongHieu;

    private String tenThuongHieuSearch;

    private Boolean daXoa;
}

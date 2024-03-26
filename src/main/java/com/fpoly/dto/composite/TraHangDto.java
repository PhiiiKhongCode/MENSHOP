package com.fpoly.dto.composite;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TraHangDto {
    private String traHangId;
    private List<MultipartFile> hinhAnh;
    private String lyDo;
    private String sanPhamChiTietId;
    private String  hoaDonChiTietId;

}

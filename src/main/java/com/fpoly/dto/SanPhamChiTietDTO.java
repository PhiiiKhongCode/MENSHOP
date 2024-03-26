package com.fpoly.dto;

import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SanPhamChiTietDTO extends BaseDTO<BaseDTO> {

	private Long sanPhamId;
	
	private Long kichCoId;

	private Long mauSacId;

	private Long vanAoId;

	private Long cucAoId;

	private Long coAoId;

	private Long tayAoId;

	private Long tuiAoId;

	private Long hoaTietId;
	
	@NotNull(message = "Số lượng không được để trống")
	@Min(value = 0, message = "Số lượng không được nhỏ hơn 0")
	private Integer soLuong;
	
	private Boolean coHienThi;
	
	private Boolean daXoa;
	
	private String tenKichCo ;
	
	private String tenMauSac ;

	private String tenVanAo;

	private String tenCucAo;

	private String tenCoAo;

	private String tenTayAo;

	private String tenTuiAo;

	private String tenHoaTiet;
	
	private SanPhamDTO sanPhamDTO ;

}

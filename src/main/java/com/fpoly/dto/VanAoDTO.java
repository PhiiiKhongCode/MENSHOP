package com.fpoly.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VanAoDTO extends BaseDTO<BaseDTO> {
	private String tenVanAo;
	
	private Boolean daXoa;
}

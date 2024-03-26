package com.fpoly.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class CoAoDTO extends BaseDTO<BaseDTO> {
	
	private String tenCoAo;
	
	private Boolean daXoa;
}

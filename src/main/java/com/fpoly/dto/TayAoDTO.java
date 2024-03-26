package com.fpoly.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class TayAoDTO extends BaseDTO<BaseDTO> {
	
	private String tenTayAo;
	
	private Boolean daXoa;
}

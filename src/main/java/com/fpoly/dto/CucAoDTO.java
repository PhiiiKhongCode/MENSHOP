package com.fpoly.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class CucAoDTO extends BaseDTO<BaseDTO> {
	
	private String tenCucAo;
	
	private Boolean daXoa;
}

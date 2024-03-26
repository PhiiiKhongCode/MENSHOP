package com.fpoly.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class TuiAoDTO extends BaseDTO<BaseDTO> {
	
	private String tenTuiAo;
	
	private Boolean daXoa;
}

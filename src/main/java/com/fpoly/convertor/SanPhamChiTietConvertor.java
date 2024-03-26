package com.fpoly.convertor;

import org.springframework.stereotype.Component;

import com.fpoly.dto.SanPhamChiTietDTO;
import com.fpoly.entity.SanPhamChiTiet;

@Component
public class SanPhamChiTietConvertor {

	public SanPhamChiTietDTO toDTO(SanPhamChiTiet entity) {
		SanPhamChiTietDTO dto = new SanPhamChiTietDTO();
		dto.setDaXoa(entity.getDaXoa());
		dto.setId(entity.getId());
		dto.setTenKichCo(entity.getKichCo().getTenKichCo());
		dto.setTenMauSac(entity.getMauSac().getTenMauSac());
		dto.setTenVanAo(entity.getVanAo().getTenVanAo());
		dto.setTenCucAo(entity.getCucAo().getTenCucAo());
		dto.setTenCoAo(entity.getCoAo().getTenCoAo());
		dto.setTenTayAo(entity.getTayAo().getTenTayAo());
		dto.setTenTuiAo(entity.getTuiAo().getTenTuiAo());
		dto.setTenHoaTiet(entity.getHoaTiet().getTenHoaTiet());
		dto.setSoLuong(entity.getSoLuong());
		return dto ;
	}
}

package com.fpoly.api.customer;

import com.fpoly.service.GioHangService;
import com.fpoly.service.TraHangService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController(value = "hoaDonAPI")
public class HoaDonAPI {
	
	@Autowired
	private TraHangService traHangService ;
	
	@DeleteMapping("/customer/api/hoa-don/tinh-tien")
	public int tinhTienGioHang(@RequestBody  long[] idHoaDonChiTiet) {
			return traHangService.tinhTienHoaDonTheoMaHoaDonChiTiet(idHoaDonChiTiet);
	}
}

package com.fpoly.restController.HoaDon.Admin;

import com.fpoly.service.HoaDonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ChoTraHangAdminRestController {

    @Autowired
    HoaDonService hoaDonService;

    //UPDATE TRẠNG THÁI CỦA ĐƠN HÀNG LÀ TRẢ HÀNG THÀNH CÔNG
    @RequestMapping("/updateTraHangShipThanhCong/{id}")
    public ResponseEntity<String> updateTraHang(@PathVariable("id") Long hoaDonId){
        System.out.println("updateTraHang UPDATE TRẠNG THÁI CỦA ĐƠN HÀNG LÀ TRẢ HÀNG THÀNH CÔNG ");
        return hoaDonService.updateTraHangThanhCongChoTraHang(hoaDonId);
    }

    // UPDATE TRẠNG THÁI CỦA ĐƠN HÀNG LÀ TRẢ HÀNG THẤT BẠI
    @RequestMapping("/updateTraHangThatBai/{id}")
    public ResponseEntity<String> updateTraHangTB(@PathVariable("id") Long hoaDonId){
        System.out.println("UPDATE TRẠNG THÁI CỦA ĐƠN HÀNG LÀ TRẢ HÀNG THẤT BẠI");
        return hoaDonService.updateTraHangThatBaiChoTraHang(hoaDonId);
    }
}

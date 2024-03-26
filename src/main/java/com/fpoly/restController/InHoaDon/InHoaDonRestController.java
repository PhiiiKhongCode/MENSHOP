package com.fpoly.restController.InHoaDon;

import com.fpoly.service.InHoaDonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class InHoaDonRestController {
    @Autowired
    InHoaDonService inHoaDonService;

    @RequestMapping("/in-hoa-don/{hoaDonId}")
    public ResponseEntity<byte[]> generatePdf(@PathVariable Long hoaDonId) {
        System.out.println("generatePdf /in-hoa-don/{hoaDonId}");
        return inHoaDonService.generatePdf(hoaDonId);
    }

    @RequestMapping("/in-hoa-don-don-dat-hang/{hoaDonId}")
    public ResponseEntity<byte[]> generatePdfDonDatHang(@PathVariable Long hoaDonId) {
        System.out.println("generatePdfDonDatHang /in-hoa-don-don-dat-hang/{hoaDonId}");
        return inHoaDonService.generatePdfDonDatHang(hoaDonId);
    }

    @RequestMapping("/in-hoa-don-don-tai-quay/{hoaDonId}")
    public ResponseEntity<byte[]> generatePdfDonTaiQuay(@PathVariable Long hoaDonId) {
        System.out.println("generatePdfDonTaiQuay /in-hoa-don-don-tai-quay{hoaDonId}");
        return inHoaDonService.generatePdfDonTaiQuay(hoaDonId);
    }
}

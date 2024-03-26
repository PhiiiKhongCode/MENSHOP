package com.fpoly.restController.HoaDon.Admin;

import com.fpoly.service.HoaDonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
public class ChoGiaoHangAdminRestController {
    @Autowired
    HoaDonService hoaDonService;

    //UPDATE TRẠNG THÁI CỦA ĐƠN HÀNG LÀ ĐANG GIAO HÀNG
    @RequestMapping("/updateGiaoHang/{id}")
    public ResponseEntity<String> updateGiaoHang(@PathVariable("id") Long hoaDonId) {
        System.out.println("UPDATE TRẠNG THÁI CỦA ĐƠN HÀNG LÀ ĐANG GIAO HÀNG");
        return hoaDonService.updateGiaoHangChoGiaoHang(hoaDonId);
    }

    @RequestMapping("/updateGiaoHangHoan/{id}")
    public ResponseEntity<String> updateGiaoHangHoan(@PathVariable("id") Long hoaDonId, @RequestParam("lyDo") String lyDo){
        System.out.println("UPDATE TRẠNG THÁI CỦA ĐƠN HÀNG LÀ ĐANG GIAO ĐƠN HOÀN CHO SHOP");

        return hoaDonService.updateGiaoHangChoTraHang(hoaDonId,lyDo);
    }

    //GIAO TẤT CẢ
    @RequestMapping("/updateGiaoHangAll")
    public ResponseEntity<String> updateGiaoHangAll() {
        System.out.println("updateGiaoHangAll /updateGiaoHangAll");
        return hoaDonService.updateGiaoHangAllChoGiaoHang();
    }

    // thêm ảnh sản phẩm lỗi
//    @PostMapping("/uploadAnhSanPhamLoi")
//    public void uploadImage(Model model,
//                            @RequestParam("image") List<MultipartFile> file,
//                            @PathVariable("idHoaDon") Long id
//
//    ) throws IOException {
//        System.out.println("thêm ảnh lỗi id hoa don: "+id);
//        System.out.println("size "+file.size());
//        for (MultipartFile image : file) {
//            StringBuilder fileNames = new StringBuilder();
//            Path fileNameAndPath = Paths.get("C:\\Users\\NamTV\\Downloads\\upload", image.getOriginalFilename());
//            fileNames.append(image.getOriginalFilename());
//            Files.write(fileNameAndPath, image.getBytes());
//        }
//       //model.addAttribute("msg", "Uploaded images: " + image.toString());
//        System.out.println("ok");
//    }

//    @PostMapping("/files/upload")
//    public String uploadFiles(Model model,@RequestParam("files") MultipartFile[] files) {
//        System.out.println("uploadFiles trả hàng");
//        List<String> messages = new ArrayList<>();
//
//        Arrays.asList(files).stream().forEach(file -> {
//            try {
//                System.out.println(file.getOriginalFilename());
//                messages.add(file.getOriginalFilename() + " [Successful]");
//            } catch (Exception e) {
//                messages.add(file.getOriginalFilename() + " <Failed> - " + e.getMessage());
//            }
//        });
//        model.addAttribute("messages", messages);
//        return "redirect:/customer/HoaDon/ChiTietHoaDon/CTChoTraHangCustomer";
//        //return "upload_form";
//    }
}

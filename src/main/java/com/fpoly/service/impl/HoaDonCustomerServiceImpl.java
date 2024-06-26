package com.fpoly.service.impl;

import com.fpoly.entity.*;
import com.fpoly.repository.*;
import com.fpoly.service.EmailService;
import com.fpoly.service.HoaDonCustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import javax.mail.MessagingException;
import java.util.*;

@Service
public class HoaDonCustomerServiceImpl implements HoaDonCustomerService {
    @Autowired
    HoaDonRepoditory2 hoaDonRepoditory2;

    @Autowired
    HoaDonRepository hoaDonRepository;

    @Autowired
    GiaoDichRepository giaoDichRepository;

    @Autowired
    HoaDonChiTietRepository2 hoaDonChiTietRepository2;

    @Autowired
    HinhAnhRepository hinhAnhRepository;

    @Autowired
    LichSuHoaDonRepository lichSuHoaDonRepository;

    @Autowired
    SanPhamChiTietRepository sanPhamChiTietRepository;

    @Autowired
    HoaDonChiTietRepository hoaDonChiTietRepository;

    @Autowired
    NguoiDungRepository nguoiDungRepository;

    @Autowired
    EmailService emailService;

    @Override
    public void danhSachChoGiaoHangCustomer(int page, int size, Model model) {
        PageRequest pageable = PageRequest.of(page - 1, size);
        Page<HoaDon> ChoGiaoHang = hoaDonRepoditory2.findHoaDonbyId(2, pageable);
        model.addAttribute("customerChoGiaoHang", ChoGiaoHang.getContent());
        model.addAttribute("pageChoGiaoHang", ChoGiaoHang.getTotalPages());
        model.addAttribute("page", page);
    }

    @Override
    public void chiTietChoGiaoHangCustomer(Long id, Model model) {
        HoaDon hoaDon = hoaDonRepository.findById(id).get();
        List<GiaoDich> timeLineChoXacNhan = giaoDichRepository.findByTrangThaiIdAndHoaDonId(1, id);
        List<GiaoDich> timeLineChoGiaoHang = giaoDichRepository.findByTrangThaiIdAndHoaDonId(2, id);
        // Lấy danh sách ảnh chính của tất cả sản phẩm và lưu vào List
        List<HoaDonChiTiet> hoaDonChiTiet = hoaDonChiTietRepository2.findHDCT(id);
        List<String> tenAnhChinhList = new ArrayList<>();
        for (HoaDonChiTiet hoadonCT : hoaDonChiTiet) {
            MauSac mauSac = hoadonCT.getSanPhamChiTiet().getMauSac();
            Long sanPhamId = hoadonCT.getSanPhamChiTiet().getSanPham().getId();

            String tenAnhChinh = hinhAnhRepository.findTenAnhChinhByMauSacIdAndSanPhamId(mauSac.getId(), sanPhamId);
            tenAnhChinhList.add(tenAnhChinh);
        }
        List<lichSuHoaDon> lichSuHD = lichSuHoaDonRepository.findLichSuByHDID(id);
        model.addAttribute("lichSuHD", lichSuHD);

        model.addAttribute("tenAnhChinhList", tenAnhChinhList);
        model.addAttribute("timeLineChoXacNhan", timeLineChoXacNhan);
        model.addAttribute("timeLineChoGiaoHang", timeLineChoGiaoHang);
        model.addAttribute("hoaDon", hoaDon);
    }

    @Override
    public void danhSachChoXacNhanCustomer(int page, int size, Model model) {
        PageRequest pageable = PageRequest.of(page - 1, size);
        Page<HoaDon> choXacNhan = hoaDonRepoditory2.findHoaDonbyId(1, pageable);
        model.addAttribute("choXacNhanCustomer", choXacNhan.getContent());
        model.addAttribute("pageChoXacNhan", choXacNhan.getTotalPages());
        model.addAttribute("page", page);
    }

    @Override
    public void chiTietChoXacNhanCustomer(Long id, Model model) {
        HoaDon hoaDon = hoaDonRepository.findById(id).get();
        List<GiaoDich> timeLineChoXacNhan = giaoDichRepository.findByTrangThaiIdAndHoaDonId(1, id);
        // Lấy danh sách ảnh chính của tất cả sản phẩm và lưu vào List
        List<HoaDonChiTiet> hoaDonChiTiet = hoaDonChiTietRepository2.findHDCT(id);
        List<String> tenAnhChinhList = new ArrayList<>();
        for (HoaDonChiTiet hoadonCT : hoaDonChiTiet) {
            MauSac mauSac = hoadonCT.getSanPhamChiTiet().getMauSac();
            Long sanPhamId = hoadonCT.getSanPhamChiTiet().getSanPham().getId();

            String tenAnhChinh = hinhAnhRepository.findTenAnhChinhByMauSacIdAndSanPhamId(mauSac.getId(), sanPhamId);
            tenAnhChinhList.add(tenAnhChinh);
        }
        List<lichSuHoaDon> lichSuHD = lichSuHoaDonRepository.findLichSuByHDID(id);
        model.addAttribute("lichSuHD", lichSuHD);

        model.addAttribute("tenAnhChinhList", tenAnhChinhList);
        model.addAttribute("timeLineChoXacNhan", timeLineChoXacNhan);
        model.addAttribute("hoaDon", hoaDon);
    }

    @Override
    public ResponseEntity<String> updateHuyDonHangCustomer(Long hoaDonId) {
        Optional<HoaDon> optionalHoaDon = hoaDonRepository.findById(hoaDonId);
        if (optionalHoaDon.isPresent()) {
            HoaDon hoaDon = optionalHoaDon.get();
            TrangThai tt = new TrangThai();
            TrangThai hienTai = hoaDon.getTrangThai();
            if (hienTai != null && hienTai.getId().equals(Long.valueOf(1))) {
                System.out.println("trang thái đơn xác nhận từ 1 --> 5 Hủy đơn thành công");
                tt.setId(5L);

                List<HoaDonChiTiet> hoaDonChiTiets = hoaDonChiTietRepository.findAllByHoaDonId(hoaDon.getId());
                System.out.println("Thêm sản phẩm vào kho");
                if(!hoaDonChiTiets.isEmpty()){
                    List<SanPhamChiTiet> sanPhamChiTiets = new ArrayList<>();
                    for (HoaDonChiTiet donChiTiet : hoaDonChiTiets) {
                        SanPhamChiTiet sanPhamChiTiet = donChiTiet.getSanPhamChiTiet();
                        Optional<SanPhamChiTiet> optional = sanPhamChiTietRepository.findById(sanPhamChiTiet.getId());
                        if(optional.isPresent()){
                            SanPhamChiTiet phamChiTietTrongKho = optional.get();
                            int soLuongTraongKho = phamChiTietTrongKho.getSoLuong();
                            int soLuongHoanTra = donChiTiet.getSoLuong();
                            System.out.println("Số lượng hiện tại: "+soLuongTraongKho);
                            System.out.println("Số Hoàn trả: "+soLuongHoanTra);
                            phamChiTietTrongKho.setSoLuong(soLuongTraongKho + soLuongHoanTra);
                            sanPhamChiTiets.add(phamChiTietTrongKho);
                        }
                    }
                    System.out.println("lưu kho ...");
                    sanPhamChiTietRepository.saveAll(sanPhamChiTiets);
                }
            } else {
                System.out.println("trang thái đơn chuyển từ " + hienTai.getId() + " --> " + 2);
                tt.setId(2L);
            }
            hoaDon.setTrangThai(tt);
            hoaDon.setNgayCapNhat(new Date());
            hoaDon.setNguoiCapNhat("namtv");
            hoaDonRepository.save(hoaDon);
            GiaoDich gd = new GiaoDich();
            gd.setHoaDon(hoaDon);
            gd.setNgayCapNhat(new Date());
            gd.setNgayTao(new Date());
            gd.setNguoiCapNhat("ABC");
            gd.setNguoiTao("ABC");
            gd.setTrangThai(tt);
            giaoDichRepository.save(gd);

            String email = SecurityContextHolder.getContext().getAuthentication().getName();
            Optional<NguoiDung> OptNguoiDung = nguoiDungRepository.findByEmail2(email);
            if (OptNguoiDung.isPresent()) {
                NguoiDung nguoiDung = OptNguoiDung.get();
                gd.setNguoiDung(nguoiDung);
                giaoDichRepository.save(gd);
            }

            String emailNguoiNhan = hoaDon.getEmailNguoiNhan();

            try {
                emailService.sendMailDangGiaoHang(emailNguoiNhan, hoaDon);
            } catch (MessagingException e) {
                e.printStackTrace();
            }
            String message = "Hủy đơn thành công";
            return ResponseEntity.ok(message);

        } else {
            String errorMessage = "Không tìm thấy hóa đơn";
            return ResponseEntity.notFound().build();
        }
    }

    @Override
    public void danhSachDaGiaoCustomer(int page, int size, Model model) {
        PageRequest pageable = PageRequest.of(page - 1, size);
        Page<HoaDon> DaGiao = hoaDonRepoditory2.findHoaDonbyId(4, pageable);
        model.addAttribute("DaGiaoCustomer", DaGiao.getContent());
        model.addAttribute("pageDaGiao", DaGiao.getTotalPages());
        model.addAttribute("page", page);
    }

    @Override
    public void chiTietDaGiaoCustomer(Long id, Model model) {
        HoaDon hoaDon = hoaDonRepository.findById(id).get();
        List<GiaoDich> timeLineChoXacNhan = giaoDichRepository.findByTrangThaiIdAndHoaDonId(1, id);
        List<GiaoDich> timeLineChoGiaoHang = giaoDichRepository.findByTrangThaiIdAndHoaDonId(2, id);
        List<GiaoDich> timeLineDangGiaoHang = giaoDichRepository.findByTrangThaiIdAndHoaDonId(3, id);
        List<GiaoDich> timeLineDaGiaoHang = giaoDichRepository.findByTrangThaiIdAndHoaDonId(4, id);
        // Lấy danh sách ảnh chính của tất cả sản phẩm và lưu vào List
        List<HoaDonChiTiet> hoaDonChiTiet = hoaDonChiTietRepository2.findHDCT(id);
        List<String> tenAnhChinhList = new ArrayList<>();
        for (HoaDonChiTiet hoadonCT : hoaDonChiTiet) {
            MauSac mauSac = hoadonCT.getSanPhamChiTiet().getMauSac();
            Long sanPhamId = hoadonCT.getSanPhamChiTiet().getSanPham().getId();

            String tenAnhChinh = hinhAnhRepository.findTenAnhChinhByMauSacIdAndSanPhamId(mauSac.getId(), sanPhamId);
            tenAnhChinhList.add(tenAnhChinh);
        }
        List<lichSuHoaDon> lichSuHD = lichSuHoaDonRepository.findLichSuByHDID(id);
        model.addAttribute("lichSuHD", lichSuHD);
        model.addAttribute("tenAnhChinhList", tenAnhChinhList);
        model.addAttribute("timeLineChoXacNhan", timeLineChoXacNhan);
        model.addAttribute("timeLineChoGiaoHang", timeLineChoGiaoHang);
        model.addAttribute("timeLineDangGiaoHang", timeLineDangGiaoHang);
        model.addAttribute("timeLineDaGiaoHang", timeLineDaGiaoHang);
        model.addAttribute("hoaDon", hoaDon);
    }

    @Override
    public void danhSachDaHuyCustomer(int page, int size, Model model) {
        PageRequest pageable = PageRequest.of(page - 1, size);
        Page<HoaDon> DaHuy = hoaDonRepoditory2.findHoaDonbyId(5, pageable);
        model.addAttribute("DaHuyCustomer", DaHuy.getContent());
        model.addAttribute("pageDaHuy", DaHuy.getTotalPages());
        model.addAttribute("page", page);
    }

    @Override
    public void chiTietDaHuyCustomer(Long id, Model model) {
        HoaDon hoaDon = hoaDonRepository.findById(id).get();
        List<GiaoDich> timeLineChoXacNhan = giaoDichRepository.findByTrangThaiIdAndHoaDonId(1, id);
        List<GiaoDich> timeLineHuyDonHang = giaoDichRepository.findByTrangThaiIdAndHoaDonId(5, id);
        // Lấy danh sách ảnh chính của tất cả sản phẩm và lưu vào List
        List<HoaDonChiTiet> hoaDonChiTiet = hoaDonChiTietRepository2.findHDCT(id);
        List<String> tenAnhChinhList = new ArrayList<>();
        for (HoaDonChiTiet hoadonCT : hoaDonChiTiet) {
            MauSac mauSac = hoadonCT.getSanPhamChiTiet().getMauSac();
            Long sanPhamId = hoadonCT.getSanPhamChiTiet().getSanPham().getId();

            String tenAnhChinh = hinhAnhRepository.findTenAnhChinhByMauSacIdAndSanPhamId(mauSac.getId(), sanPhamId);
            tenAnhChinhList.add(tenAnhChinh);
        }
        List<lichSuHoaDon> lichSuHD = lichSuHoaDonRepository.findLichSuByHDID(id);
        model.addAttribute("lichSuHD", lichSuHD);
        model.addAttribute("tenAnhChinhList", tenAnhChinhList);
        model.addAttribute("timeLineChoXacNhan", timeLineChoXacNhan);
        model.addAttribute("timeLineHuyDonHang", timeLineHuyDonHang);
        model.addAttribute("hoaDon", hoaDon);
    }

    @Override
    public void danhSachDangGiaoCustomer(int page, int size, Model model) {
        PageRequest pageable = PageRequest.of(page - 1, size);
        List<Integer> trangThai = Arrays.asList(3,14);
        Page<HoaDon> dangtGiao = hoaDonRepoditory2.findHoaDonbyListId(trangThai, pageable);
        model.addAttribute("dangtGiaoCustomer", dangtGiao.getContent());
        model.addAttribute("pageDangGiao", dangtGiao.getTotalPages());
        model.addAttribute("page", page);
    }

    @Override
    public void chiTietDangGiaoCustomer(Long id, Model model) {
        HoaDon hoaDon = hoaDonRepository.findById(id).get();
        List<GiaoDich> timeLineChoXacNhan = giaoDichRepository.findByTrangThaiIdAndHoaDonId(1, id);
        List<GiaoDich> timeLineChoGiaoHang = giaoDichRepository.findByTrangThaiIdAndHoaDonId(2, id);
        List<GiaoDich> timeLineDangGiaoHang = giaoDichRepository.findByTrangThaiIdAndHoaDonId(3, id);
        List<GiaoDich> timeLineDangGiaoHoanTra = giaoDichRepository.findByTrangThaiIdAndHoaDonId(14,id);
        // Lấy danh sách ảnh chính của tất cả sản phẩm và lưu vào List
        List<HoaDonChiTiet> hoaDonChiTiet = hoaDonChiTietRepository2.findHDCT(id);
        List<String> tenAnhChinhList = new ArrayList<>();
        for (HoaDonChiTiet hoadonCT : hoaDonChiTiet) {
            MauSac mauSac = hoadonCT.getSanPhamChiTiet().getMauSac();
            Long sanPhamId = hoadonCT.getSanPhamChiTiet().getSanPham().getId();

            String tenAnhChinh = hinhAnhRepository.findTenAnhChinhByMauSacIdAndSanPhamId(mauSac.getId(), sanPhamId);
            tenAnhChinhList.add(tenAnhChinh);
        }
        List<lichSuHoaDon> lichSuHD = lichSuHoaDonRepository.findLichSuByHDID(id);
        model.addAttribute("lichSuHD", lichSuHD);
        model.addAttribute("tenAnhChinhList", tenAnhChinhList);
        model.addAttribute("timeLineChoXacNhan", timeLineChoXacNhan);
        model.addAttribute("timeLineChoGiaoHang", timeLineChoGiaoHang);
        model.addAttribute("timeLineDangGiaoHang", timeLineDangGiaoHang);
        model.addAttribute("timeLineDangGiaoHoanTra", timeLineDangGiaoHoanTra);
        model.addAttribute("hoaDon", hoaDon);
    }

    @Override
    public ResponseEntity<String> updateGiaoHangThanhCongCustomer(Long hoaDonId) {
        Optional<HoaDon> optionalHoaDon = hoaDonRepository.findById(hoaDonId);
        if (optionalHoaDon.isPresent()) {
            HoaDon hoaDon = optionalHoaDon.get();
            TrangThai tt = new TrangThai();
            tt.setId(4L);
            hoaDon.setTrangThai(tt);
            hoaDon.setNgayCapNhat(new Date());
            hoaDon.setNguoiCapNhat("namtv");
            hoaDonRepository.save(hoaDon);
            GiaoDich gd = new GiaoDich();
            gd.setHoaDon(hoaDon);
            gd.setNgayCapNhat(new Date());
            gd.setNgayTao(new Date());
            gd.setNguoiCapNhat("ABC");
            gd.setNguoiTao("ABC");
            gd.setTrangThai(tt);
            giaoDichRepository.save(gd);

            String email = SecurityContextHolder.getContext().getAuthentication().getName();
            Optional<NguoiDung> OptNguoiDung = nguoiDungRepository.findByEmail2(email);
            if (OptNguoiDung.isPresent()) {
                NguoiDung nguoiDung = OptNguoiDung.get();
                gd.setNguoiDung(nguoiDung);
                giaoDichRepository.save(gd);
            }
            String emailNguoiNhan = hoaDon.getEmailNguoiNhan();

            try {
                emailService.sendMailDangGiaoHang(emailNguoiNhan, hoaDon);
            } catch (MessagingException e) {
                e.printStackTrace();
            }
            String message = "Đã xác nhận đơn hàng là đang giao";
            return ResponseEntity.ok(message);

        } else {
            String errorMessage = "Không tìm thấy hóa đơn";
            return ResponseEntity.notFound().build();
        }
    }

    @Override
    public ResponseEntity<String> updateThanhCongAllCustomer() {
        List<HoaDon> hoaDonList = hoaDonRepository.findByTrangThaiHoaDonListTrangThai(3);
        if (!hoaDonList.isEmpty()) {
            for (HoaDon hoaDon : hoaDonList) {
                TrangThai tt = new TrangThai();
                tt.setId(4L);
                hoaDon.setTrangThai(tt);
                hoaDon.setNgayCapNhat(new Date());
                hoaDon.setNguoiCapNhat("namtv");
                hoaDonRepository.save(hoaDon);
                GiaoDich gd = new GiaoDich();
                gd.setHoaDon(hoaDon);
                gd.setNgayCapNhat(new Date());
                gd.setNgayTao(new Date());
                gd.setNguoiCapNhat("ABC");
                gd.setNguoiTao("ABC");
                gd.setTrangThai(tt);
                giaoDichRepository.save(gd);

                String email = SecurityContextHolder.getContext().getAuthentication().getName();
                Optional<NguoiDung> OptNguoiDung = nguoiDungRepository.findByEmail2(email);
                if (OptNguoiDung.isPresent()) {
                    NguoiDung nguoiDung = OptNguoiDung.get();
                    gd.setNguoiDung(nguoiDung);
                    giaoDichRepository.save(gd);
                }
                String emailNguoiNhan = hoaDon.getEmailNguoiNhan();

                try {
                    emailService.sendMailDangGiaoHang(emailNguoiNhan, hoaDon);
                } catch (MessagingException e) {
                    e.printStackTrace();
                }
            }
            
            String message = "Xác nhận tất cả thành công";
            return ResponseEntity.ok(message);
        } else {
            String errorMessage = "Không tìm thấy hóa đơn chưa xác nhận";
            return ResponseEntity.notFound().build();
        }
    }
}

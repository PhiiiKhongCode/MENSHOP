package com.fpoly.service;

import com.fpoly.dto.HoaDonChiTietDTO;
import com.fpoly.dto.HoaDonDTO;
import com.fpoly.entity.TraHang;
import org.springframework.ui.Model;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public interface TraHangService {
    <S extends TraHang> S save(S entity);

    void danhSachTraHangCustomer(int page, int size, Model model);

    void chiTietDaHoanCustomer(Long id, Model model);

    void chiTietChoTraHangCustomer(Long id, Model model);

    void capNhatSoLuongHoaDonChiTiet(Long[] ids, Integer[] soLuongs,Integer[] donGias);

    void capNhatTongTien(Long id);

    int tinhTienHoaDonTheoMaHoaDonChiTiet(long[] idHoaDonChiTiet);

    HoaDonDTO findByKhachHangId(Long id);

    BigDecimal getTongTienByKhachHangID(Long id);

    Long  taoTraDonBanHangOnline(List<Long> selectedCartItemIds, RedirectAttributes redirectAttributes);

    HoaDonChiTietDTO findById(Long id);

    Optional<TraHang> findByTHId(Long id);
}

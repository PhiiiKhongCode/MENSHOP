package com.fpoly.repository;

import com.fpoly.entity.GioHangChiTiet;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.fpoly.entity.HoaDonChiTiet;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Repository
public interface HoaDonChiTietRepository extends JpaRepository<HoaDonChiTiet,Long> {
    
    @Query(value = "SELECT count(*) FROM hoa_don_chi_tiet WHERE hoa_don_id = :id AND da_xoa=false", nativeQuery = true)
	Integer countByHoaDonId(@Param("id") Long id);
    
    @Query(value = "SELECT * FROM hoa_don_chi_tiet WHERE hoa_don_id = :id AND da_xoa=false", nativeQuery = true)
	Page<HoaDonChiTiet> findAllByHoaDonId(@Param("id")Long id, Pageable pageable);

    @Query(value = "update hoa_don_chi_tiet set da_xoa = true where id = ? and hoa_don_id = ?", nativeQuery = true)
    void xoaHoaDonCT(long HoaDonChiTietID, long hoaDonId);

    @Query(value = "select * from hoa_don_chi_tiet where san_pham_chi_tiet_id = ? and hoa_don_id = ? and da_xoa = false", nativeQuery = true)
    Optional<HoaDonChiTiet> findBySanPhamChiTietAndHoaDon(long sanPhamCTId, long hoaDonId);

    @Query(value = "select * from hoa_don_chi_tiet where hoa_don_id = ? and da_xoa = false", nativeQuery = true)
    List<HoaDonChiTiet> findByHoaDonIdAndDaXoa(long id);

    @Query("SELECT SUM(hdct.tongTien) FROM HoaDonChiTiet hdct WHERE hdct.hoaDon.id =:id")
    public Integer tongTien(@Param("id") Long id);

    @Query("SELECT hdct FROM HoaDonChiTiet hdct WHERE hdct.hoaDon.id =:id")
    public List<HoaDonChiTiet> findAllByHoaDonId(@Param("id") Long id);

    @Query("SELECT hdct FROM HoaDonChiTiet hdct WHERE hdct.hoaDon.id =:id")
    public Optional<HoaDonChiTiet> findByHoaDonId(@Param("id") Long id);
}

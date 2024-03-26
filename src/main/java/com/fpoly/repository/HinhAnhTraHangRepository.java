package com.fpoly.repository;

import com.fpoly.entity.HinhAnhTraHang;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HinhAnhTraHangRepository extends JpaRepository<HinhAnhTraHang, Long> {
    @Query(value = "select * from hinh_anh_tra_hang where hoa_don_id = ?1", nativeQuery = true)
    List<HinhAnhTraHang> findByHoaDonId(long hoaDonId);
}

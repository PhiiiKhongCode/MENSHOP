package com.fpoly.repository;

import com.fpoly.entity.TraHang;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface TraHangRepository extends JpaRepository<TraHang,Long> {
    @Query(value = "SELECT * FROM tra_hang WHERE da_xoa = false and status = ?1  ORDER BY ngay_tao DESC",
            countQuery = "SELECT COUNT(*) FROM hoa_don WHERE da_xoa = false and status = ?1",
            nativeQuery = true)
    Page<TraHang> findTraHangByStatus(int status, PageRequest pageable);

    @Query(value = "SELECT * FROM tra_hang WHERE da_xoa = 1 ORDER BY ngay_tao DESC",
//            countQuery = "SELECT COUNT(*) FROM hoa_don WHERE da_xoa = 1",
            nativeQuery = true)
    Page<TraHang> findAllTraHang(PageRequest pageable);
}

package com.fpoly.repository;

import com.fpoly.entity.HoaTiet;
import com.fpoly.entity.ThuongHieu;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HoaTietRepository extends JpaRepository<HoaTiet, Long> {
    @Query(value = "SELECT * FROM hoa_tiet t WHERE t.da_xoa = FALSE ORDER BY t.id DESC", nativeQuery = true)
    Page<HoaTiet> findAllHoaTietExist(Pageable pageable);

    @Query(value = "SELECT * FROM hoa_tiet t WHERE t.da_xoa = FALSE ORDER BY t.id DESC", nativeQuery = true)
    List<HoaTiet> findAllHoaTietExist();

    @Query(value = "SELECT * FROM hoa_tiet t WHERE t.da_xoa = FALSE AND t.ten_hoa_tiet LIKE %:tenHoaTiet% ORDER BY t.id DESC", nativeQuery = true)
    Page<HoaTiet> getHoaTietExistByName(@Param("tenHoaTiet") String tenHoaTiet, Pageable pageable);
}

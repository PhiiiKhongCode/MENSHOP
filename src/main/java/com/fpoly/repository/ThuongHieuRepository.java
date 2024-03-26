package com.fpoly.repository;

import com.fpoly.entity.ThuongHieu;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ThuongHieuRepository extends JpaRepository<ThuongHieu, Long> {
    @Query(value = "SELECT * FROM thuong_hieu t WHERE t.da_xoa = FALSE ORDER BY t.id DESC", nativeQuery = true)
    Page<ThuongHieu> findAllThuongHieuExist(Pageable pageable);

    @Query(value = "SELECT * FROM thuong_hieu t WHERE t.da_xoa = FALSE ORDER BY t.id DESC", nativeQuery = true)
    List<ThuongHieu> findAllThuongHieuExist();

    @Query(value = "SELECT * FROM thuong_hieu t WHERE t.da_xoa = FALSE AND t.ten_thuong_hieu LIKE %:tenThuongHieu% ORDER BY t.id DESC", nativeQuery = true)
    Page<ThuongHieu> getThuongHieuExistByName(@Param("tenThuongHieu") String tenThuongHieu, Pageable pageable);
}

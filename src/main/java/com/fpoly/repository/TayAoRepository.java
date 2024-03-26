package com.fpoly.repository;

import com.fpoly.entity.TayAo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TayAoRepository extends JpaRepository<TayAo,Long> {
	@Query(value = "SELECT * FROM tay_ao c WHERE c.da_xoa = false ORDER BY c.id DESC", nativeQuery = true)
	List<TayAo> selectAllTayAoExist();
	
	@Query(value = "SELECT * FROM tay_ao c WHERE c.da_xoa = false ORDER BY c.id DESC", nativeQuery = true)
	Page<TayAo> selectAllTayAoExist(Pageable pageable);
	
	@Query(value = "SELECT * FROM tay_ao c WHERE c.da_xoa = false AND c.ten_tay_ao like %:tenTayAo% ORDER BY c.id DESC", nativeQuery = true)
	Page<TayAo> getTayAoExistByName(@Param("tenTayAo") String tenTayAo, Pageable pageable);

	@Query(value = "SELECT  c.* FROM tay_ao c join san_pham_chi_tiet s1 on s1.tay_ao_id = c.id WHERE c.da_xoa = false and s1.san_pham_id = :sanPhamId group by c.id", nativeQuery = true)
	List<TayAo> selectAllTayAoBySanPhamId(@Param("sanPhamId") Long sanPhamId);
	
}

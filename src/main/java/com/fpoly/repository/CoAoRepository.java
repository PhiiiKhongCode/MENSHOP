package com.fpoly.repository;

import com.fpoly.entity.CoAo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CoAoRepository extends JpaRepository<CoAo,Long> {
	@Query(value = "SELECT * FROM co_ao c WHERE c.da_xoa = false ORDER BY c.id DESC", nativeQuery = true)
	List<CoAo> selectAllCoAoExist();
	
	@Query(value = "SELECT * FROM co_ao c WHERE c.da_xoa = false ORDER BY c.id DESC", nativeQuery = true)
	Page<CoAo> selectAllCoAoExist(Pageable pageable);
	
	@Query(value = "SELECT * FROM co_ao c WHERE c.da_xoa = false AND c.ten_co_ao like %:tenCoAo% ORDER BY c.id DESC", nativeQuery = true)
	Page<CoAo> getCoAoExistByName(@Param("tenCoAo") String tenCoAo, Pageable pageable);

	@Query(value = "SELECT  c.* FROM co_ao c join san_pham_chi_tiet s1 on s1.co_ao_id = c.id WHERE c.da_xoa = false and s1.san_pham_id = :sanPhamId group by c.id", nativeQuery = true)
	List<CoAo> selectAllCoAoBySanPhamId(@Param("sanPhamId") Long sanPhamId);
	
}

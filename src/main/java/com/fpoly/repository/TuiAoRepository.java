package com.fpoly.repository;

import com.fpoly.entity.TuiAo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TuiAoRepository extends JpaRepository<TuiAo,Long> {
	@Query(value = "SELECT * FROM tui_ao c WHERE c.da_xoa = false ORDER BY c.id DESC", nativeQuery = true)
	List<TuiAo> selectAllTuiAoExist();
	
	@Query(value = "SELECT * FROM tui_ao c WHERE c.da_xoa = false ORDER BY c.id DESC", nativeQuery = true)
	Page<TuiAo> selectAllTuiAoExist(Pageable pageable);
	
	@Query(value = "SELECT * FROM tui_ao c WHERE c.da_xoa = false AND c.ten_tui_ao like %:tenTuiAo% ORDER BY c.id DESC", nativeQuery = true)
	Page<TuiAo> getTuiAoExistByName(@Param("tenTuiAo") String tenTuiAo, Pageable pageable);

	@Query(value = "SELECT  c.* FROM tui_ao c join san_pham_chi_tiet s1 on s1.tui_ao_id = c.id WHERE c.da_xoa = false and s1.san_pham_id = :sanPhamId group by c.id", nativeQuery = true)
	List<TuiAo> selectAllTuiAoBySanPhamId(@Param("sanPhamId") Long sanPhamId);
	
}

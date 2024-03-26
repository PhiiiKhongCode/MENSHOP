package com.fpoly.repository;

import com.fpoly.entity.CucAo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CucAoRepository extends JpaRepository<CucAo,Long> {
	@Query(value = "SELECT * FROM cuc_ao c WHERE c.da_xoa = false ORDER BY c.id DESC", nativeQuery = true)
	List<CucAo> selectAllCucAoExist();
	
	@Query(value = "SELECT * FROM cuc_ao c WHERE c.da_xoa = false ORDER BY c.id DESC", nativeQuery = true)
	Page<CucAo> selectAllCucAoExist(Pageable pageable);
	
	@Query(value = "SELECT * FROM cuc_ao c WHERE c.da_xoa = false AND c.ten_cuc_ao like %:tenCucAo% ORDER BY c.id DESC", nativeQuery = true)
	Page<CucAo> getCucAoExistByName(@Param("tenCucAo") String tenCucAo, Pageable pageable);

	@Query(value = "SELECT  c.* FROM cuc_ao c join san_pham_chi_tiet s1 on s1.cuc_ao_id = c.id WHERE c.da_xoa = false and s1.san_pham_id = :sanPhamId group by c.id", nativeQuery = true)
	List<CucAo> selectAllCucAoBySanPhamId(@Param("sanPhamId") Long sanPhamId);
	
}

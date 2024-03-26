package com.fpoly.repository;

import com.fpoly.entity.VanAo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VanAoRepository extends JpaRepository<VanAo,Long> {
	@Query(value = "SELECT * FROM van_ao v WHERE v.da_xoa = false ORDER BY v.id DESC", nativeQuery = true)
	List<VanAo> selectAllVanAoExist();
	
	@Query(value = "SELECT * FROM van_ao v WHERE v.da_xoa = false ORDER BY v.id DESC", nativeQuery = true)
	Page<VanAo> selectAllVanAoExist(Pageable pageable);
	
	@Query(value = "SELECT * FROM van_ao v WHERE v.da_xoa = false AND v.ten_van_ao like %:tenVanAo% ORDER BY v.id DESC", nativeQuery = true)
	Page<VanAo> getVanAoExistByName(@Param("tenVanAo") String tenVanAo, Pageable pageable);

	@Query(value = "SELECT  v.* FROM van_ao v join san_pham_chi_tiet s1 on s1.van_ao_id = v.id WHERE v.da_xoa = false and s1.san_pham_id = :sanPhamId group by v.id", nativeQuery = true)
	List<VanAo> selectAllVanAoBySanPhamId(@Param("sanPhamId") Long sanPhamId);
	
}

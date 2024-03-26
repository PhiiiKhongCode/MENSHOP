package com.fpoly.service;

import com.fpoly.entity.VanAo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;


public interface VanAoService {

	List<VanAo> selectAllVanAoExist();

	Optional<VanAo> findById(Long id);

	<S extends VanAo> S save(S entity);

	Page<VanAo> selectAllVanAoExist(Pageable pageable);

	Page<VanAo> getVanAoExistByName(String tenVanAo, Pageable pageable);

	void delete(VanAo entity);

}

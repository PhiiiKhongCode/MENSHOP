package com.fpoly.service;

import com.fpoly.entity.CoAo;
import com.fpoly.entity.CucAo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;


public interface CoAoService {

	Page<CoAo> selectAllCoAoExist(Pageable pageable);

	Optional<CoAo> findById(Long id);

	<S extends CoAo> S save(S entity);

	List<CoAo> selectAllCoAoExist();

	Page<CoAo> getCoAoExistByName(String tenCoAo, Pageable pageable);

	void delete(CoAo entity);

}

package com.fpoly.service;

import com.fpoly.entity.CucAo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;


public interface CucAoService {

	Page<CucAo> selectAllCucAoExist(Pageable pageable);

	Optional<CucAo> findById(Long id);

	<S extends CucAo> S save(S entity);

	List<CucAo> selectAllCucAoExist();

	Page<CucAo> getCucAoExistByName(String tenCucAo, Pageable pageable);

	void delete(CucAo entity);

}

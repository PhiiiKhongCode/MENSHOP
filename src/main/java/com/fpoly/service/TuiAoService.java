package com.fpoly.service;

import com.fpoly.entity.TuiAo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;


public interface TuiAoService {

	Page<TuiAo> selectAllTuiAoExist(Pageable pageable);

	Optional<TuiAo> findById(Long id);

	<S extends TuiAo> S save(S entity);

	List<TuiAo> selectAllTuiAoExist();

	Page<TuiAo> getTuiAoExistByName(String tenTuiAo, Pageable pageable);

	void delete(TuiAo entity);

}

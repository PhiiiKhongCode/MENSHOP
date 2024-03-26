package com.fpoly.service;

import com.fpoly.entity.TayAo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;


public interface TayAoService {

	Page<TayAo> selectAllTayAoExist(Pageable pageable);

	Optional<TayAo> findById(Long id);

	<S extends TayAo> S save(S entity);

	List<TayAo> selectAllTayAoExist();

	Page<TayAo> getTayAoExistByName(String tenTayAo, Pageable pageable);

	void delete(TayAo entity);

}

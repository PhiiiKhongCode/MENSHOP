package com.fpoly.service;

import com.fpoly.entity.HinhAnhTraHang;

import java.util.List;

public interface HinhAnhTraHangService {
    <S extends HinhAnhTraHang> S save(S entity);
    List<HinhAnhTraHang> findAll();

    List<HinhAnhTraHang> findAllByHoaDonId(Long id);
}

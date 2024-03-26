package com.fpoly.service.impl;

import com.fpoly.entity.HinhAnhTraHang;
import com.fpoly.repository.HinhAnhTraHangRepository;
import com.fpoly.service.HinhAnhTraHangService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HinhAnhTraHangServiceImpl implements HinhAnhTraHangService {
    @Autowired
    private HinhAnhTraHangRepository hinhAnhTraHangRepository;
    @Override
    public <S extends HinhAnhTraHang> S save(S entity) {
        return hinhAnhTraHangRepository.save(entity);
    }

    @Override
    public List<HinhAnhTraHang> findAll() {
        return hinhAnhTraHangRepository.findAll();
    }

    @Override
    public List<HinhAnhTraHang> findAllByHoaDonId(Long id) {
        return hinhAnhTraHangRepository.findByHoaDonId(id);
    }
}

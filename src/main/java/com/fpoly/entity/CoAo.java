package com.fpoly.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "co_ao")
public class CoAo extends BaseEntity implements Serializable {
    @OneToMany(mappedBy = "coAo", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<SanPhamChiTiet> sanPhamChiTiets;

    @Column(columnDefinition = "nvarchar(50) not null")
    private String tenCoAo;

    @Column
    private Boolean daXoa;
}

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
@Table(name = "tui_ao")
public class TuiAo extends BaseEntity implements Serializable {
    @OneToMany(mappedBy = "tuiAo", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<SanPhamChiTiet> sanPhamChiTiets;

    @Column(columnDefinition = "nvarchar(50) not null")
    private String tenTuiAo;

    @Column
    private Boolean daXoa;
}

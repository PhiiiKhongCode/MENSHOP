package com.fpoly.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "hoa_tiet")
@EntityListeners(AuditingEntityListener.class)
public class HoaTiet extends BaseEntity implements Serializable {

    @Column(name = "ten_hoa_tiet", columnDefinition = "nvarchar(30) not null")
    private String tenHoaTiet;

    @Column(name = "da_xoa")
    private Boolean daXoa;

    @OneToMany(mappedBy = "hoaTiet", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<SanPhamChiTiet> sanPhamChiTiets;
}

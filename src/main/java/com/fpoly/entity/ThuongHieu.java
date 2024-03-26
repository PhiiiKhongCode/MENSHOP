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
@Table(name = "thuong_hieu")
@EntityListeners(AuditingEntityListener.class)
public class ThuongHieu extends BaseEntity implements Serializable {

    @Column(name = "ten_thuong_hieu", columnDefinition = "nvarchar(30) not null")
    private String tenThuongHieu;

    @Column(name = "da_xoa")
    private Boolean daXoa;

    @OneToMany(mappedBy = "thuongHieu", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<SanPham> sanPham;
}

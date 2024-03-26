package com.fpoly.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.FetchMode;
import org.hibernate.annotations.Fetch;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tra_hang")
@EntityListeners(AuditingEntityListener.class)
public class TraHang  extends BaseEntity implements Serializable {

    @Column(name = "ly_do")
    private String lyDo;

    @Column(name="ngay_tra")
    private Date ngayTra ;

//    @Column(name="so_luong")
//    private Integer soLuong ;

    @Column(name="hinh_anh")
    private String hinhAnh ;

    @Column(name="status")
    private int status ;

    @OneToOne
    @JoinColumn(name="san_pham_chi_tiet_id")
    private SanPhamChiTiet sanPhamChiTiet ;

    @OneToOne
    @JoinColumn(name="hoa_don_chi_tiet_id")
    private HoaDonChiTiet hoaDonChiTiet ;

    @Column(name="da_xoa")
    private Boolean daXoa;

}
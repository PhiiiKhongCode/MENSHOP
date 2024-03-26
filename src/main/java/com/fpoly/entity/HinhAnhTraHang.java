package com.fpoly.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "hinh_anh_tra_hang")
public class HinhAnhTraHang {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "hoa_don_id")
    private Long hoaDonId;

    @Column(name = "name")
    private String name;
}

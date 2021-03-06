package com.example.ffbackend.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.example.ffbackend.vo.RegularAdjustmentIndexVo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table
@AllArgsConstructor
@NoArgsConstructor
public class RegularAdjustmentIndex {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;
    Integer userId;

    String indexCode;
    Double num1;
    Double num2;
    Double num3;

    public RegularAdjustmentIndexVo createVo () {
        return new RegularAdjustmentIndexVo(indexCode, num1, num2, num3);
    }
}
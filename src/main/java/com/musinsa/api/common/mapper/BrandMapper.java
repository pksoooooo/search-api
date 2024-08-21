package com.musinsa.api.common.mapper;

import com.musinsa.api.brand.dto.request.BrandSaveRequestDto;
import com.musinsa.api.brand.dto.request.BrandUpdateRequestDto;
import com.musinsa.api.brand.model.Brand;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface BrandMapper {

    List<Brand> fetchAllBrand();
    Brand fetchBrandById(int brandId);
    Brand fetchBrandByName(String brandName);
    int saveBrand(BrandSaveRequestDto brandSaveRequestDto);
    int updateBrand(BrandUpdateRequestDto brandUpdateRequestDto);
    int deleteBrand(int brandId);

}

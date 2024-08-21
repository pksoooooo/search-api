package com.musinsa.api.brand.service;

import com.musinsa.api.brand.dto.request.BrandSaveRequestDto;
import com.musinsa.api.brand.dto.request.BrandUpdateRequestDto;
import com.musinsa.api.brand.dto.response.BrandResponseDto;
import com.musinsa.api.brand.model.Brand;
import com.musinsa.api.common.convert.CommonConverter;
import com.musinsa.api.common.exception.NoSuchDataException;
import com.musinsa.api.common.mapper.BrandMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BrandService {

    private final BrandMapper brandMapper;
    private final CommonConverter commonConverter;

    public BrandService(BrandMapper brandMapper, CommonConverter commonConverter) {
        this.brandMapper = brandMapper;
        this.commonConverter = commonConverter;
    }

    public boolean saveBrand(BrandSaveRequestDto brandSaveRequestDto) {
        return brandMapper.saveBrand(brandSaveRequestDto) > 0;
    }

    public boolean updateBrand(BrandUpdateRequestDto brandUpdateRequestDto) {

        try {
            getBrandById(brandUpdateRequestDto.getBrandId());
        } catch (NoSuchDataException e) {
            throw new NoSuchDataException("수정하려는 데이터가 없습니다.");
        }

        return brandMapper.updateBrand(brandUpdateRequestDto) > 0;
    }

    public boolean deleteBrand(int brandId) {

        try {
            getBrandById(brandId);
        } catch (NoSuchDataException e) {
            throw new NoSuchDataException("삭제하려는 데이터가 없습니다.");
        }

        return brandMapper.deleteBrand(brandId) > 0;
    }

    public List<BrandResponseDto> getAllBrand() {
        return commonConverter.convertBrandListToBrandListResponseDto(brandMapper.fetchAllBrand());
    }

    public BrandResponseDto getBrandById(int brandId) {
        Brand brand = Optional.ofNullable(brandMapper.fetchBrandById(brandId))
                .orElseThrow(() -> new NoSuchDataException("조회하려는 브랜드가 없습니다."));

        return commonConverter.convertBrandToBrandResponseDto(brand);
    }

    public BrandResponseDto getBrandByName(String brandName) {

        Brand brand = Optional.ofNullable(brandMapper.fetchBrandByName(brandName))
                .orElseThrow(() -> new NoSuchDataException("조회하려는 브랜드가 없습니다."));

        return commonConverter.convertBrandToBrandResponseDto(brand);
    }
}

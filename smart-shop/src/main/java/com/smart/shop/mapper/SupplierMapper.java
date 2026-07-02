package com.smart.shop.mapper;

import com.smart.shop.entity.Supplier;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface SupplierMapper {

    @Select("SELECT * FROM t_supplier ORDER BY id")
    List<Supplier> findAll();

    @Select("SELECT * FROM t_supplier WHERE id = #{id}")
    Supplier findById(Integer id);

    @Insert("INSERT INTO t_supplier(name, contact, phone, address) VALUES(#{name}, #{contact}, #{phone}, #{address})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    int insert(Supplier supplier);

    @Update("UPDATE t_supplier SET name=#{name}, contact=#{contact}, phone=#{phone}, address=#{address} WHERE id=#{id}")
    int update(Supplier supplier);

    @Delete("DELETE FROM t_supplier WHERE id=#{id}")
    int delete(Integer id);
}

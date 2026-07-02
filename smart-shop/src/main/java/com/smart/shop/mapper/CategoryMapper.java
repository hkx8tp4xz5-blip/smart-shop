package com.smart.shop.mapper;

import com.smart.shop.entity.Category;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface CategoryMapper {

    @Select("SELECT * FROM t_category")
    List<Category> findAll();

    @Select("SELECT * FROM t_category WHERE id = #{id}")
    Category findById(Integer id);

    @Insert("INSERT INTO t_category(name, descp) VALUES(#{name}, #{descp})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    int insert(Category category);

    @Update("UPDATE t_category SET name=#{name}, descp=#{descp} WHERE id=#{id}")
    int update(Category category);

    @Delete("DELETE FROM t_category WHERE id=#{id}")
    int delete(Integer id);

    @Select("SELECT COUNT(*) FROM t_category")
    int getCount();

    @Select("SELECT c.id, c.name, COUNT(p.id) AS productCount FROM t_category c LEFT JOIN t_product p ON p.cat_id = c.id GROUP BY c.id, c.name ORDER BY c.id")
    List<java.util.Map<String, Object>> getCategoryProductStats();
}

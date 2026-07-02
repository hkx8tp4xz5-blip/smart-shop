package com.smart.shop.mapper;

import com.smart.shop.entity.Product;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface ProductMapper {

    @Select("SELECT * FROM t_product WHERE id = #{id}")
    Product findById(Integer id);

    List<Product> findByCondition(@Param("categoryId") Integer categoryId,
                                  @Param("productName") String productName,
                                  @Param("minPrice") Double minPrice,
                                  @Param("maxPrice") Double maxPrice);

    @Insert("INSERT INTO t_product(name, photo_url, price, descp, release_date, cat_id) VALUES(#{name}, #{photoUrl}, #{price}, #{descp}, #{releaseDate}, #{catId})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    int insert(Product product);

    @Update("UPDATE t_product SET name=#{name}, photo_url=#{photoUrl}, price=#{price}, descp=#{descp}, release_date=#{releaseDate}, cat_id=#{catId} WHERE id=#{id}")
    int update(Product product);

    @Delete("DELETE FROM t_product WHERE id=#{id}")
    int delete(Integer id);

    @Select("SELECT COUNT(*) FROM t_product")
    int getCount();

    @Select("SELECT p.*, c.name AS categoryName FROM t_product p LEFT JOIN t_category c ON p.cat_id = c.id ORDER BY p.id DESC LIMIT 8")
    List<Product> findRecent();

    @Select("SELECT DATE_FORMAT(release_date, '%Y-%m') AS monthStr, COUNT(*) AS monthCount FROM t_product WHERE release_date IS NOT NULL GROUP BY monthStr ORDER BY monthStr")
    List<java.util.Map<String, Object>> getMonthlyTrend();
}

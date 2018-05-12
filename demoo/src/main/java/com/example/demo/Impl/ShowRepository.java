package com.example.demo.Impl;

import com.example.demo.Model.Showsth;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ShowRepository extends JpaRepository<Showsth, Object> {

    //年龄
    List<Showsth> findByAge(Integer age);

    //Id模糊查询
    @Query(value="select * from showsth a where a.id like CONCAT('%',:Id,'%')",nativeQuery=true)
    List<Showsth> findByIdLike(@Param("Id") Integer id);

    //name模糊查询
    @Query(value="select * from showsth a where a.name like CONCAT('%',:name,'%')",nativeQuery=true)
    List<Showsth> findByNameLike(@Param("name") String name);

//    @Query(value="select * from showsth  where id AND name like CONCAT('%',:id ,'%') AND ('%',:name,'%')",nativeQuery=true)
//    上下两种方法都不能模糊查询,需要自己手写方法实体
//    List<Showsth> findAllByIdContainingAndNameContaining(@Param("id") Integer id,@Param("name") String name);
    // 所以同时查询两个字段目前不支持模糊
    List<Showsth> findAllByIdAndName(@Param("id") Integer id,@Param("name") String name);

}

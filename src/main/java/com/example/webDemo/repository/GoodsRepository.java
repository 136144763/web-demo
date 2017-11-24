package com.example.webDemo.repository;

import com.example.webDemo.domain.Goods;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by Administrator on 2017/11/23.
 */
public interface GoodsRepository extends JpaRepository<Goods,String> {
}

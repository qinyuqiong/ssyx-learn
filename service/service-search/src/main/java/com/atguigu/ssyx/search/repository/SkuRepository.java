package com.atguigu.ssyx.search.repository;

import com.atguigu.ssyx.model.search.SkuEs;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.List;

/**
 * @author user
 * @date 2023/10/19
 */
public interface SkuRepository extends ElasticsearchRepository<SkuEs, Long> {
    Page<SkuEs> findByOrderByHotScoreDesc(Pageable esPage);
}

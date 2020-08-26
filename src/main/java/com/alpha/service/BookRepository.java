package com.alpha.service;

import com.alpha.entity.BookBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @ClassName BookRepository
 * @Description TODO
 * @Author Tanger
 * @Date 2020/8/18 17:41
 */

public interface BookRepository extends ElasticsearchRepository<BookBean, String> {
    //Optional<BookBean> findById(String id);

    Page<BookBean> findByAuthor(String author, Pageable pageable);

    Page<BookBean> findByTitle(String title, Pageable pageable);

    List<BookBean> findAllByAuthor(String name);

}

package com.alpha.service.impl;

import com.alpha.entity.BookBean;
import com.alpha.service.BookRepository;
import com.alpha.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * @ClassName BookServiceImpl
 * @Description TODO
 * @Author Tanger
 * @Date 2020/8/18 17:46
 */
@Service("bookService")
public class BookServiceImpl implements BookService {

    @Autowired
    private BookRepository bookRepository;

    @Override
    public Optional<BookBean> findById(String id) {
        //CrudRepository中的方法
        return bookRepository.findById(id);
    }

    @Override
    public BookBean save(BookBean blog) {
        return bookRepository.save(blog);
    }

    public void saveAll(List<BookBean> list){
        bookRepository.saveAll(list);
    }

    @Override
    public void delete(BookBean blog) {
        bookRepository.delete(blog);
    }

    @Override
    public Optional<BookBean> findOne(String id) {
        return bookRepository.findById(id);
    }

    @Override
    public List<BookBean> findAll() {
        List<BookBean> list = new ArrayList<>();
        Iterable<BookBean> all = bookRepository.findAll();
        for (BookBean bookBean : all){
            list.add(bookBean);
        }
        return list;
    }

    @Override
    public Page<BookBean> findByAuthor(String author, PageRequest pageRequest) {
        return bookRepository.findByAuthor(author, pageRequest);
    }

    @Override
    public Page<BookBean> findByTitle(String title, PageRequest pageRequest) {
        return bookRepository.findByTitle(title, pageRequest);
    }

    @Override
    public List<BookBean> findAllByAuthor(String name) {
        return bookRepository.findAllByAuthor(name);
    }
}

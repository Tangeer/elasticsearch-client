package com.alpha;

import com.alpha.entity.BookBean;
import com.alpha.entity.JobServerLog;
import com.alpha.service.BookService;
import com.alpha.service.JobServerLogService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Stream;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @ClassName ESClientApplicationTests
 * @Description TODO
 * @Author Tanger
 * @Date 2020/8/18 17:58
 */

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ESClientApplication.class)//这里的Application是springboot的启动类名
@WebAppConfiguration
public class ESClientApplicationTests {

    @Autowired
    private BookService bookService;

    @Autowired
    JobServerLogService jobServerLogService;


    @Test
    public void testSend() throws Exception {
//        testFinaAll();
//        testFindById();
//        Save();
//        testFindByCondition();
//        testFindAllByAuthor();

        testJobServerLog();
        List<JobServerLog> list = jobServerLogService.findByJobInstanceCodeWithSort("681e897633454ec6896e900cd3c7649e", "timestamp");
//        List<JobServerLog> list = jobServerLogService.findByJobInstanceCodeWithSort("9C1e2fbdc7a2a5C5e5f03Bf9BC5f615a", "sortNum");
        System.out.println(list.size());

    }

    private void testJobServerLog() {
        JobServerLog jobServerLog1 = new JobServerLog(UUID.randomUUID().toString().replace("-", ""), "681e897633454ec6896e900cd3c7649e", "1", "1", "1", 25855288,"1", "1", "1");
        JobServerLog jobServerLog2 = new JobServerLog(UUID.randomUUID().toString().replace("-", ""), "681e897633454ec6896e900cd3c7649e", "2", "2", "2", 25855288,"2", "2", "2");
        JobServerLog jobServerLog3 = new JobServerLog(UUID.randomUUID().toString().replace("-", ""), "681e897633454ec6896e900cd3c7649e", "3", "3", "3", 35855388,"3", "3", "3");
        List<JobServerLog> list = new ArrayList<>();
        list.add(jobServerLog1);
        list.add(jobServerLog2);
        list.add(jobServerLog3);




        String info = jobServerLogService.saveAll(list);
        System.out.println(info);
    }

    private void testFindAllByAuthor() {
        List<BookBean> list = bookService.findAllByAuthor("程裕强");
        System.out.println(list);
    }

    private void testFindByCondition() {
        PageRequest pg = PageRequest.of(0,5);
        Page<BookBean> page = bookService.findByAuthor("程裕强", pg);
        Stream<BookBean> bookBeanStream = page.get();
        Object[] objects = bookBeanStream.toArray();
        for (Object obj : objects){
            System.out.println(obj);
        }
    }

    private void testFindById() {
        Optional<BookBean> op = bookService.findById("99");
        BookBean bookBean = op.get();
        System.out.println(bookBean);
    }

    private void testFinaAll() {
        List<BookBean> all = bookService.findAll();
        System.out.println(all);
    }

    private void Save(){
        BookBean book=new BookBean("ES入门教程","程裕强","2018-10-01");
        System.out.println(book);
        bookService.save(book);
    }


}

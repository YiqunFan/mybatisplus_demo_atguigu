package com.atguigu.demomptest;

import com.atguigu.demomptest.entity.User;
import com.atguigu.demomptest.mapper.UserMapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

@SpringBootTest
class DemomptestApplicationTests {

    @Autowired
    private UserMapper userMapper;

    @Test
    public void testFindAll() {
        List<User> users = userMapper.selectList(null);
        System.out.println(users);
    }

    @Test
    public void testAdd() {
        User user = new User();
        user.setName("王五");
        user.setAge(20);
        user.setEmail("1243@qq.com");
        int count = userMapper.insert(user);
        System.out.println(count);
    }

    @Test
    public void testUpdate() {
        User user = new User();
        user.setId(1511745308011651074L);
        user.setName("Lucymarypop");
        int count = userMapper.updateById(user);
        System.out.println(count);
    }

    // 测试乐观锁版本号
    @Test
    public void testOptimisticLocker() {
        User user = userMapper.selectById(1511756333825982466L);
        user.setName("张三");
        userMapper.updateById(user);
    }

    // 测试多个ID批量查询
    @Test
    public void testSelect1() {
        List<User> users = userMapper.selectBatchIds(Arrays.asList(1, 2, 3));
        System.out.println(users);
    }

    // 测试简单多条件查询
    @Test
    public void testSelect2() {
        HashMap<String, Object> columnMap = new HashMap<>();
        columnMap.put("name", "Jack");
        columnMap.put("age", 20);
        List<User> users = userMapper.selectByMap(columnMap);
        System.out.println(users);
    }

    // 测试分页查询
    @Test
    public void testSelect3() {
        Page<User> page = new Page<>(1, 3);
        Page<User> userPage = userMapper.selectPage(page, null);
        long pages = userPage.getPages();// 总页数
        long current = userPage.getCurrent();// 当前页号
        long total = userPage.getTotal();// 总记录数
        boolean hp = userPage.hasPrevious();// 是否有上一页
        boolean hn = userPage.hasNext();// 是否有下一页
        List<User> records = userPage.getRecords();// 该页内容
        System.out.println(pages);
        System.out.println(current);
        System.out.println(total);
        System.out.println(hp);
        System.out.println(hn);
        System.out.println(records);
    }

    // 测试根据id删除，逻辑删除
    @Test
    public void testDelete1() {
        int count = userMapper.deleteById(1511763307607113729L);
        System.out.println(count);
    }

    // 测试多个ID批量删除
    @Test
    public void testDelete2() {
        int count = userMapper.deleteBatchIds(Arrays.asList(2, 3, 4));
        System.out.println(count);
    }

    // 测试简单多条件删除
    @Test
    public void testDelete3() {
        HashMap<String, Object> columnMap = new HashMap<>();
        columnMap.put("name", "Jack");
        columnMap.put("age", 20);
        int count = userMapper.deleteByMap(columnMap);
        System.out.println(count);
    }

    // mp复杂查询
    @Test
    public void testSelect4() {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.ge("age", 21);
        List<User> users = userMapper.selectList(queryWrapper);
        System.out.println(users);
    }


}

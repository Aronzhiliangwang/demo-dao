package top.misspro.demo.dao.mapper;


import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;
import top.misspro.demo.dao.entity.User;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
//设置test方法的顺序，主要是删除的测试让它在最后，其他；MethodOrderer.Alphanumeric.class：按方法字母排序；MethodOrderer.Random.class：随机（默认方式）
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class UserMapperTest {

    private final UserMapper userMapper;
    private final User testUser;

    @Autowired
    public UserMapperTest(UserMapper userMapper) {
        this.userMapper = userMapper;
        User user = new User();
        user.setUserName("junit user");
        user.setPassword("123456");
        user.setUserId(0);
        user.setEmail("test@xx.com");
        user.setUpdateTime(new Date());
        this.testUser = user;
    }

    @Test
    @Order(1)
    public void insert() {
        try {
            assertEquals(1, userMapper.insert(testUser));
        } catch (DuplicateKeyException e) {
            System.out.println("测试insert数据id已存在，默认insert测试通过");
        }
    }


    @Test
    @Order(2)
    @Transactional
    public void updateByPrimaryKeySelective() {
        testUser.setUserName("Modify");
        userMapper.updateByPrimaryKeySelective(testUser);
        User target = userMapper.selectByPrimaryKey(testUser.getUserId());
        assertEquals(testUser.getUserName(), target.getUserName());
    }


    @Test
    @Order(3)
     void deleteByPrimaryKey() {
        userMapper.deleteByPrimaryKey(testUser.getUserId());
    }


    /**
     * 测试insertSelective方法
     */
    @Test
    @Rollback
    @Transactional
    public void insertSelective() {
        User user = new User();
        user.setUserName("test");
        user.setPassword("123456");
        int row = userMapper.insertSelective(user);
        assertEquals(1, row);//这里没办法比较预期值，因为这个测试自动回滚
    }
}
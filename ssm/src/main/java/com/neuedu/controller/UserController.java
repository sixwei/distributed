package com.neuedu.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.neuedu.pojo.Result;
import com.neuedu.pojo.User;
import com.neuedu.service.UserService;
import com.neuedu.util.FenPage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
public class UserController {


    @Autowired
    private UserService userService;

    private FenPage fenpage = new FenPage();

    /**
     * 对于页面的展示以及对于分页的处理
     * @param pageNum
     * @return
     */
    @GetMapping("list")
    public FenPage listUser(@RequestParam(name = "pageNum",defaultValue = "1") Integer pageNum){
        int size = 5;
        int total = 0;
        PageHelper.startPage(pageNum,size);
        List<User> users = userService.listUser();
        PageInfo<User> info =new PageInfo<>(users);
        int count = (int)info.getTotal();
            if (count%size!=0){
                total=count/size+1;
            }else{
                total=count/size;
            }
        fenpage.setTotal(total);;
        fenpage.setUsers(users);
        return fenpage;
    }
    @DeleteMapping("list/{id}")
    public Result deleteUser(@PathVariable Integer id){
        System.out.println(id);
        Result result = new Result();
        int n = userService.deleteUser(id);
         if (n!=0){
             result.setCode(200);
             result.setReason("删除成功");
         }else{
             result.setCode(404);
             result.setReason("删除失败");
         }

       return result;

    }
}

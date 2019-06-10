package me.freelee.betterday.controller;

import me.freelee.betterday.dto.LoginParam;
import me.freelee.betterday.model.User;
import me.freelee.betterday.service.UserService;
import me.freelee.betterday.util.AliyunOSSClientUtil;
import me.freelee.betterday.util.DateUtil;
import me.freelee.betterday.util.ResultUtil;
import me.freelee.betterday.util.TransferUtil;
import me.freelee.betterday.vo.UserLoginVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;

/**
 * Description:
 * Date:2019/5/30
 *
 * @author:Lee
 */
@RestController
@CrossOrigin
@RequestMapping("/user")
public class UserController {

    private Logger logger= LoggerFactory.getLogger(UserController.class);

    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    UserService userService;


    /** 
    * @Description: 用户注册 
    * @Param: [user] 
    * @return: me.freelee.betterday.util.ResultUtil 
    * @Author: freelee 
    * @Date: 2019/6/5 
    */ 
    @PostMapping("/register")
    public ResultUtil register(@RequestBody User user){
        Boolean isSameName=userService.selectCountByName(user.getName())==0?false:true;
        if(isSameName){
            return ResultUtil.fail(10001,"注册失败，用户名已存在，请重试",user);
        }else{
            user.setCreateTime(DateUtil.getCurrentTime());
            user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
            int affectRow=userService.insertUser(user);
            if(affectRow>=1){
                return ResultUtil.success("注册成功",user,null);
            }else{
                return ResultUtil.fail(10002,"注册失败，系统出现错误，请重试",user);
            }
        }
    }

    @PutMapping("/updatePwd")
    public ResultUtil updatePassword(@RequestParam("username") String username,@RequestParam("oldPassword") String oldPassword,@RequestParam("newPassword") String newPassword){
        User user =userService.selectUserByName(username);
        if(!bCryptPasswordEncoder.matches(oldPassword,user.getPassword())){
            return ResultUtil.fail(10005,"修改失败，原密码错误",null);
        }else{
            user.setPassword(bCryptPasswordEncoder.encode(newPassword));
            userService.updateUser(user);
            return ResultUtil.success("修改成功，请重新登录",user,null);
        }
    }

//    /**
//    * @Description: 用户登录
//    * @Param: [loginParam]
//    * @return: me.freelee.betterday.util.ResultUtil
//    * @Author: freelee
//    * @Date: 2019/6/5
//    */
//    @PostMapping("/login")
//    public ResultUtil login(@RequestBody LoginParam loginParam){
//        logger.info("####loginParam:"+loginParam.getUsername());
//        User user =userService.selectUserByName(loginParam.getUsername());
//        if(user==null){
//            return ResultUtil.fail(10003,"登录失败，用户名不存在",null);
//        }else{
//            if(!bCryptPasswordEncoder.matches(loginParam.getPassword(),user.getPassword())){
//                return ResultUtil.fail(10004,"登录失败，密码错误",null);
//            }else{
//                UserLoginVO userLoginVO=userToUserVo(user);
//                return ResultUtil.success("登录成功",userLoginVO,null);
//            }
//        }
//    }

    @PostMapping("/avatar")
    public ResultUtil uploadAvatar(@RequestParam("file") MultipartFile multipartFile){
        if (null == multipartFile) {
            return ResultUtil.fail(10004,"上传失败，无法找到文件！",null);
        }
        String fileName = multipartFile.getOriginalFilename().toLowerCase();
        if (!fileName.endsWith(".bmp") && !fileName.endsWith(".jpg")
                && !fileName.endsWith(".jpeg") && !fileName.endsWith(".png")
                && !fileName.endsWith(".gif")) {
            return ResultUtil.fail(10004,"上传失败，请选择BMP、JPG、JPEG、PNG、GIF文件！",null);
        }
        InputStream in=null;
        try {
            in = multipartFile.getInputStream();
        } catch (IOException e) {
            e.printStackTrace();
        }
        String url=AliyunOSSClientUtil.uploadObject2OSS(in,"avatar",fileName);
        return ResultUtil.success("",url,null);
    }

    /** 
    * @Description: 更新用户状态
    * @Param: [userId, status] 
    * @return: me.freelee.betterday.util.ResultUtil 
    * @Author: freelee 
    * @Date: 2019/6/5 
    */ 
    @PutMapping("/status/update")
    public ResultUtil updateStatus(@RequestParam("userId") Integer userId,@RequestParam("status") String status){
        User user=userService.selectUserById(userId);
        user.setStatusstr(status);
        userService.updateUser(user);
        return ResultUtil.success("",null,null);
    }

    @GetMapping("")
    public ResultUtil getUserByName(@RequestParam("username") String username){
        User user=userService.selectUserByName(username);
        return ResultUtil.success("",user,null);
    }

    /**
    * @Description:  user类转为userVO类
    * @Param: [user]
    * @return: me.freelee.betterday.vo.UserLoginVO
    * @Author: freelee
    * @Date: 2019/6/5
    */
    private UserLoginVO userToUserVo(User user) {
        UserLoginVO userLoginVO=new UserLoginVO();
        userLoginVO.setEmail(user.getEmail());
        userLoginVO.setName(user.getName());
        userLoginVO.setPassword(user.getPassword());
        userLoginVO.setPhone(user.getPhone());
        if(user.getId()!=null){
            userLoginVO.setId(user.getId());
        }
        if(user.getAvatar()!=null){
            userLoginVO.setAvatar(user.getAvatar());
        }
        if(user.getRealname()!=null){
            userLoginVO.setRealname(user.getRealname());
        }
        if(user.getSex()!=null){
            userLoginVO.setSex(user.getSex());
        }
        if(user.getStatusstr()!=null){
            userLoginVO.setStatusstr(user.getStatusstr());
        }
        return userLoginVO;
    }
}

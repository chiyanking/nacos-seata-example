package com.xd.example.seata.controller;


import com.xd.example.seata.service.IAccountService;
import com.xd.example.seata.util.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author lihaodong
 * @since 2019-11-25
 */
@RestController
@RequestMapping("/account")
public class AccountController {

    @Autowired
    private IAccountService accountService;


    @PostMapping("/reduceBalance")
    @ResponseBody
    public R reduceBalance(@RequestParam("userId") Integer userId, @RequestParam("money") BigDecimal money) {
        return R.builder().success(accountService.reduceBalance(userId, money)).msg("扣款成功").build();
    }

    @PostMapping("/resetBalance")
    @ResponseBody
    public R resetBalance(@RequestParam("userId") Integer userId, @RequestParam("balance") BigDecimal balance) {
        return R.builder().success(accountService.resetBalance(userId, balance)).msg("重置成功").build();
    }
}


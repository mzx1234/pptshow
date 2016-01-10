package com.mzx.pptserver.service.impl;


import com.mzx.pptserver.application.GlobalApplication;
import com.mzx.pptserver.exception.ServiceException;
import com.mzx.pptserver.service.OptionService;
import com.mzx.pptserver.utility.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * ppt相关操作服务实现类
 * Created by zison on 2015/12/31.
 */
@Service
public class OptionServiceImpl implements OptionService {

    @Autowired
    private RedisUtil redisUtil;
    @Autowired
    private GlobalApplication globalApplication;

    @Override
    public byte[] swichPPTPage(int cur) {
        byte[] result = redisUtil.hGetBytes(globalApplication.getKey(), cur+"");
        if(result == null) {
            throw new ServiceException();
        }
        return result;
    }
}


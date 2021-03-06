package com.mzx.pptserver.service.impl;


import com.mzx.pptcommon.constant.SystemConstant.ResponseStatusCode;
import com.mzx.pptcommon.exception.PPTshowException;
import com.mzx.pptserver.application.GlobalApplication;
import com.mzx.pptserver.monitor.task.SendMsgTask;
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
    @Autowired
    private SendMsgTask sendMsgTask;

    @Override
    public byte[] swichPPTPage(int cur) throws PPTshowException {
        byte[] result = redisUtil.hGetBytes(globalApplication.getKey(), cur+"");
        if(result == null) {
            throw new PPTshowException(ResponseStatusCode.ABNORMAL.getCode(), "你所要求的页面缓存中不存在，请重新加载");
        }
        globalApplication.setTargetBytes(result);
        sendMsgTask.execute();
        return result;
    }
}


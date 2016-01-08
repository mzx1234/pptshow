package com.mzx.pptserver.service;


import com.mzx.pptserver.exception.ServiceException;

/**
 * 解析PPT文件服务
 * Created by zison on 2015/12/30.
 */
public interface ParseService {


    /**
     * 解析对应的PPT文件
     * @param file 文件路径
     * @throws ServiceException
     */
    byte[] parsePPTFile(String file) throws ServiceException;


    byte[] parsePPTXFile(String file) throws ServiceException;
}

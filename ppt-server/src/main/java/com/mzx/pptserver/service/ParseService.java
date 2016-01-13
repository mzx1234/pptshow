package com.mzx.pptserver.service;


import com.mzx.pptcommon.exception.PPTshowException;

/**
 * 解析PPT文件服务
 * Created by zison on 2015/12/30.
 */
public interface ParseService {


    /**
     * 解析对应的PPT文件
     * @param file 文件路径
     * @throws PPTshowException
     */
    byte[] parsePPTFile(String file) throws PPTshowException;


    /**
     * 解析对应的PPTX文件
     * @param file 文件路径
     * @return
     * @throws PPTshowException
     */
    byte[] parsePPTXFile(String file) throws PPTshowException;
}

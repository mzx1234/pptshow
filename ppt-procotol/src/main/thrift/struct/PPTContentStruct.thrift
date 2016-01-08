namespace java com.mzx.pptprocotol.thrift.struct

/**
*调用成功响应码
*/
const string RES_SUCCESS_CODE = "00000";

    struct PPTDetail {
        #文件路径
        1:string path;
        #文件名
        2:string fileName;
        #当前页
        3:i32 curPage;
    }

    struct PPTBytes {
        #当前页
        1:PPTDetail pptDetail;
        #内容二进制流
        2:binary bytes;
    }
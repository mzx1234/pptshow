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
        #总页数
        4:i32 len;
    }


    #相应体通用结构
    struct ResponseStatus {
        # 响应码 ，00000表示成功，默认值成功
        1: string code = RES_SUCCESS_CODE;
        # 错误时，错误描述信息
        2: string msg = "";
    }

    struct PPTBytes {
        #回复状态
        1:ResponseStatus resopnseStatus;
        #当前页
        2:PPTDetail pptDetail;
        #内容二进制流
        3:binary bytes;
    }


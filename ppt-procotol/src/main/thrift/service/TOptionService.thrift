include "../struct/PPTContentStruct.thrift"
namespace java com.mzx.pptprocotol.thrift.service

#客户端操作ppt页数服务接口
service TOptionService {
    PPTContentStruct.PPTBytes swichPPTPage(1:PPTContentStruct.PPTDetail parm),
}
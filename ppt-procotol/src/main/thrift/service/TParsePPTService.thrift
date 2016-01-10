include "../struct/PPTContentStruct.thrift"
namespace java com.mzx.pptprocotol.thrift.service

#客户端解析ppt文件服务接口
service TParsePPTService {
    PPTContentStruct.PPTBytes parsePPTAndGetFirst(1:PPTContentStruct.PPTDetail parm),
}
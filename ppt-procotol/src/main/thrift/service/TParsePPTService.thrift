include "../struct/PPTContentStruct.thrift"
namespace java com.mzx.pptprocotol.thrift.service

#�ͻ��˽���ppt�ļ�����ӿ�
service TParsePPTService {
    PPTContentStruct.PPTBytes parsePPTAndGetFirst(1:PPTContentStruct.PPTDetail parm),
}
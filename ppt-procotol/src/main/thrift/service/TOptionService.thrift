include "../struct/PPTContentStruct.thrift"
namespace java com.mzx.pptprocotol.thrift.service

#�ͻ��˲���pptҳ������ӿ�
service TOptionService {
    PPTContentStruct.PPTBytes swichPPTPage(1:PPTContentStruct.PPTDetail parm),
}
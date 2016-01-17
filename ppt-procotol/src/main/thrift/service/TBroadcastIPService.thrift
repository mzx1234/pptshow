include "../struct/PPTContentStruct.thrift"
namespace java com.mzx.pptprocotol.thrift.service

service TBroadcastIPService {
    void broadcastIP(1:PPTContentStruct.IPDetail ipDetail),
}
namespace java com.mzx.pptprocotol.thrift.struct

/**
*调用成功响应码
*/
const string RES_SUCCESS_CODE = "00000";

	#分页结构体
	struct TPagingStruct {
		#页大小
		1: i32 pageSize=20;
		#页码
		2: i32 pageNo;
	}
	
	#范围结构体
	struct TRangeStruct {
		#范围下限
		1: string min;
		#范围上限
		2: string max;
	}
	
	#排序结构体，sortedField的枚举值参考接口请求参数的定义，各个接口之间的枚举值相互独立
	struct TSortStruct {
		#排序字段
		1: string sortedField;
		#升序还是降序，默认值为0（升序），1为降序
		2: i32 desc;
	}

    #相应体通用结构
	struct TResponseStatusStruct {
	    # 交易渠道(001-,002-, 003-)
	    1: string channel;
	    # 响应码 ，00000表示交易成功，默认值交易成功
	    2: string code = RES_SUCCESS_CODE;
	    # 错误时，错误描述信息
	    3: string msg = "";
	}
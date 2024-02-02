package com.hgg.upcmpt;

import com.hgg.upcmpt.onenet.api.datastreams.FindDatastreamListApi;
import com.hgg.upcmpt.onenet.response.BasicResponse;
import com.hgg.upcmpt.onenet.response.datastreams.DatastreamsResponse;
import org.junit.Test;
import java.util.List;

public class ApiTest {

	@Test
	public void testFindDatastreamsListApi() {
		String datastreamids = "wendu,shidu,guang,led,fan,heating,curtain";
		String devid = "1185537350";
		String key = "soRab9XyC82UlDatDB7W2iF=E78=";
		/**
		 * 查询多个数据流
		 * @param datastreamids:数据流名称 ,String
		 * @param devid:设备ID,String
		 * @param key:masterkey 或者 设备apikey
		 */
		FindDatastreamListApi api = new FindDatastreamListApi(datastreamids, devid, key);
		BasicResponse<List<DatastreamsResponse>> response = api.executeApi();
		System.out.println("errno:"+response.errno+" error:"+response.error);
		System.out.println(response.getJson());
	}

}

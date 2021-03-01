package com.yc.flower.util;

import com.yc.flower.biz.BizException;

public class Utils {
/**
 * @throws BizException 
 * 
 */
	public static void checkNull(Object value,String msg) throws BizException {
		if(value==null) {
			throw new BizException(msg);
		}
		if(value instanceof String) {
			String svalue=(String)value;
			if(svalue.trim().isEmpty()) {
				throw new BizException(msg);
			}
		}
		
	}
	
	
	
	
	

}

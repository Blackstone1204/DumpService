package me.backservice;

import java.util.HashMap;
import java.util.Map;

public class Configurator {
	
	public static final String TZMSTR="blackstone@%s##%s##%s##%s##%s##%s##%s##%s##%s##%s##%s##%s##%s##%s##%s##%s##%s";
	public static final String TAG="BackService";
	public static final Map<Integer,String> map=new HashMap<Integer,String>();
	public static final int[] DUMP_ALLOW_EVENT= {1,2048};
	public static final String KEY="blackstone";
	static {
		map.put(-1,"不能识别的位置");
		map.put(0,"未定义的事件0");
		map.put(1,"点击1");
		map.put(2, "长按事件2");
		map.put(8,"view聚焦8");
		map.put(16,"文本内容变化16");
		map.put(32,"窗体状态改变32");
		map.put(64,"通知栏事件64");
		map.put(2048, "窗体内容变化2048");
		map.put(4096, "滚动4096");
		map.put(8192, "选中文本变化8192");
		
	}

}

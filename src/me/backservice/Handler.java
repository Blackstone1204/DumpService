package me.backservice;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;

import com.thoughtworks.xstream.XStream;

import android.accessibilityservice.AccessibilityService;
import android.graphics.Rect;
import android.os.Build;
import android.util.Log;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeInfo;
import android.view.accessibility.AccessibilityWindowInfo;

public class Handler {
	
	public static void filter(AccessibilityEvent event) {
		List< ?> list=Arrays.asList(Configurator.DUMP_ALLOW_EVENT);
		
    	if(!list.contains(event.getEventType()))
    		Log.i(Configurator.TAG+"-事件过滤",Configurator.map.get(event.getEventType()));
    		return;
    	
	}
	
	
	private static void printBasic(AccessibilityEvent e) {
		AccessibilityNodeInfo n=e.getSource();
		AccessibilityEvent evn=e;
		StringBuffer sb=new StringBuffer();
		int type=e.getEventType();
		String mg=Configurator.map.get(type);
		if(null==n)return;
		String id=n.getViewIdResourceName();
		
		CharSequence clasz=n.getClassName();
		
		CharSequence text = n.getText();
		List<CharSequence> list=evn.getText();
		
		CharSequence pkg = n.getPackageName();
		int windowId = n.getWindowId();
		int cnt=n.getChildCount();

		
		sb.append("########事件监听=> "+mg);
		sb.append("event=");
		sb.append(mg);
		sb.append("\n");
		sb.append("id=");
		sb.append(id);
		sb.append("\n");
		sb.append("class=");
		sb.append(clasz);

		sb.append("\n");
		sb.append("text=");
		sb.append(text);
//		sb.append("=>");
//		sb.append(list.toString());
//		sb.append("\n");
//		sb.append("pkg=");
//		sb.append(pkg);
//
//		sb.append("\n");
//		sb.append("windowId=");
//		sb.append(windowId);
//
//		sb.append("\n");
//		sb.append("child count=");
//		sb.append(cnt);
		
//		sb.append("\nsource=>");
//		sb.append(e.getSource().);

		
	
		 Log.i(Configurator.TAG+"-事件源监听", sb.toString());
		
	}
	
	public static void pd(AccessibilityEvent e) {
		if(null==e)return;
		if(null==e.getSource())return;
        int eventType = e.getEventType();
        String msg=Configurator.map.get(eventType);
        
        if(null==msg)msg="程序未定义=>"+eventType+"=>"+e.getSource().getViewIdResourceName();
    	        
    	       // Log.i(Configurator.TAG, "################"+msg+"=>"+e.getSource().getViewIdResourceName());
                printBasic(e);
    	        switch (eventType) {
    	            case AccessibilityEvent.TYPE_NOTIFICATION_STATE_CHANGED:// 通知栏事件64
    	                break;
    	            case AccessibilityEvent.TYPE_WINDOW_STATE_CHANGED://窗体状态改变32
    	                break;
    	            case AccessibilityEvent.TYPE_VIEW_ACCESSIBILITY_FOCUSED://View获取到焦点32768
    	                break;
    	                
    	            case AccessibilityEvent.TYPE_VIEW_FOCUSED://8
    	            	break;
    	            case AccessibilityEvent.TYPE_GESTURE_DETECTION_START: //262144
    	                break;
    	            case AccessibilityEvent.TYPE_GESTURE_DETECTION_END://手势事件 524288
    	                break;
    	            case AccessibilityEvent.TYPE_WINDOW_CONTENT_CHANGED://窗口内容变化 2048
    	                break;
    	            case AccessibilityEvent.TYPE_VIEW_CLICKED://1
    	                break;
    	            case AccessibilityEvent.TYPE_VIEW_LONG_CLICKED://2f
    	            	break;
    	            case AccessibilityEvent.TYPE_VIEW_TEXT_CHANGED://16
    	                break;
    	            case AccessibilityEvent.TYPE_VIEW_SCROLLED://4096
    	                break;
    	            case AccessibilityEvent.TYPE_VIEW_TEXT_SELECTION_CHANGED://8192
    	            	break;
    	            case AccessibilityEvent.CONTENT_CHANGE_TYPE_CONTENT_DESCRIPTION:
    	            	break;

    	            	
    	            case AccessibilityEvent.CONTENT_CHANGE_TYPE_UNDEFINED://0
    	            	break;
    	            case AccessibilityEvent.INVALID_POSITION://-1
    	            	break;
    	            	

    	            case AccessibilityEvent.TYPE_ANNOUNCEMENT:
    	            	break;
    	            	
    	           	            
    	      
    	        }
	
	}
	
	
	/*
	 * 根据getwindows遍历窗口
	 */
	public static void transfromToNode(AccessibilityService service){
		AccessibilityNodeInfo rootInfo = service.getRootInActiveWindow();
		
		if(rootInfo==null)return;
		//Log.i(Configurator.TAG+"-root节点",rootInfo.getClassName().toString());
		
		Node root=new Node();
		
		if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.JELLY_BEAN_MR2) {
			root.setResourceId(String.valueOf(rootInfo.getViewIdResourceName()));
			
		}
		
		Rect r=new Rect();
		rootInfo.getBoundsInScreen(r);
		
		root.setBounds("["+r.left+","+r.top+"]"+"["+r.right+","+r.bottom+"]");

		root.setClassName(String.valueOf(rootInfo.getClassName()));
		root.setClickable(String.valueOf(rootInfo.isClickable()));
		root.setContentDesc(String.valueOf(rootInfo.getContentDescription()));
		root.setLongClickable(String.valueOf(rootInfo.isLongClickable()));
		root.setPackageName(String.valueOf(rootInfo.getPackageName()));
		root.setPassword(String.valueOf(rootInfo.isPassword()));
		root.setText(String.valueOf(rootInfo.getText()));
		root.setEnabled(String.valueOf(rootInfo.isEnabled()));
		
		root.setCheckable(String.valueOf(rootInfo.isCheckable()));
		root.setChecked(String.valueOf(rootInfo.isChecked()));
		root.setFocusable(String.valueOf(rootInfo.isFocusable()));
		root.setFocused(String.valueOf(rootInfo.isFocused()));
		root.setScrollable(String.valueOf(rootInfo.isScrollable()));
		root.setSelected(String.valueOf(rootInfo.isSelected()));

		transfromToNode(rootInfo,root);
		
		
		dumpToXml(root);
		
	}
	
	public static void transfromToNode(AccessibilityNodeInfo nodeInfo,Node node) {
		int cnt=nodeInfo.getChildCount();
		//Log.i(Configurator.TAG+"_子节点个数",node.toString()+"=>"+cnt+"");
		
		if(cnt<1)return;
	
		for(int i=0;i<cnt;i++) {
			
			AccessibilityNodeInfo childInfo=nodeInfo.getChild(i);
		
			if(null==childInfo)continue;
			
			boolean visible=childInfo.isVisibleToUser();
			
			if(!visible) {
				Log.w(Configurator.TAG+"-元素不可见","元素不可见 继续下一个..");
				continue;
				
			}
			

			//Log.i(Configurator.TAG+"子节点信息",childInfo.toString());
			Node child=new Node();
			node.addChild(child);
			
			if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.JELLY_BEAN_MR2) {
				child.setResourceId(String.valueOf(nodeInfo.getChild(i).getViewIdResourceName()));
				
			}
			
			Rect r=new Rect();
			childInfo.getBoundsInScreen(r);
			
			child.setTzm("");
			child.setBounds("["+r.left+","+r.top+"]"+"["+r.right+","+r.bottom+"]");
			child.setClassName(String.valueOf(childInfo.getClassName()));
			child.setClickable(String.valueOf(childInfo.isClickable()));
			child.setContentDesc(String.valueOf(childInfo.getContentDescription()));
			child.setLongClickable(String.valueOf(childInfo.isLongClickable()));
			child.setPackageName(String.valueOf(childInfo.getPackageName()));
			child.setPassword(String.valueOf(childInfo.isPassword()));
			child.setText(String.valueOf(childInfo.getText()));
			child.setEnabled(String.valueOf(childInfo.isEnabled()));
			child.setCheckable(String.valueOf(childInfo.isCheckable()));
			child.setChecked(String.valueOf(childInfo.isChecked()));
			child.setFocusable(String.valueOf(childInfo.isFocusable()));
			child.setFocused(String.valueOf(childInfo.isFocused()));
			child.setScrollable(String.valueOf(childInfo.isScrollable()));
			child.setSelected(String.valueOf(childInfo.isSelected()));

			//
			
	        transfromToNode(childInfo,child);
			
		}
	
		
	}
	/*
	 * 
	 */
	
	public void recycleNode(AccessibilityEvent e) {
		e.getItemCount();
		
		
	}
	
	public static void dumpToXml(Node node)  {
		
		new Thread(new DumpRunnable(node)).start();	
	}
	
	public static void canSeeLoginButton(AccessibilityService s) {
		boolean flag = false;
		AccessibilityNodeInfo rootInfo = s.getRootInActiveWindow();
		List<AccessibilityNodeInfo> target = rootInfo.findAccessibilityNodeInfosByText("获取验证码");
		
		Log.i(Configurator.TAG+"-查找登陆按钮",String.valueOf(target.toString()));
		
	}
	

	public static String getDeviceSerial() {
		  String serial = "unknown";
		  try {
		   Class<?> clazz = Class.forName("android.os.Build");
		   Class<?> paraTypes = Class.forName("java.lang.String");
		   Method method = clazz.getDeclaredMethod("getString", paraTypes);
		   if (!method.isAccessible()) {
		    method.setAccessible(true);
		   }
		   serial = (String)method.invoke(new Build(), "ro.serialno");
		  } catch (ClassNotFoundException e) {
		   e.printStackTrace();
		  } catch (NoSuchMethodException e) {
		   e.printStackTrace();
		  } catch (InvocationTargetException e) {
		   e.printStackTrace();
		  } catch (IllegalAccessException e) {
		   e.printStackTrace();
		  }
		  return serial;
		}

	
	public static void filter() {
		
	}

}

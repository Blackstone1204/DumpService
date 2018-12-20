package me.backservice;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.List;

import android.graphics.Point;
import android.util.Log;

public class DumpUtils {
	static String OUTER="<UI ext=\"param\">content</UI>";
	static String PREFIX="<node index=\"%s\" text=\"%s\" resource-id=\"%s\" class=\"%s\" package=\"%s\" content-desc=\"%s\" checkable=\"%s\" checked=\"%s\" clickable=\"%s\" enabled=\"%s\" focusable=\"%s\" focused=\"%s\" scrollable=\"%s\" long-clickable=\"%s\" password=\"%s\" selected=\"%s\" bounds=\"%s\" tzm=\"%s\">";
	static String center="";
	static String NEWLINE="\n";
	static String SPACE="";
	static String SUFFIX="</node>";
	static String ext="";
	
	
	
	
	public static String toXmlStr(Node node) {
		
		ext="";
		
		StringBuffer xmlStr=new StringBuffer();

		String index="-1";
		String text=toSpaceString(node.getText());
		String p="-1";
		String resourceId=toSpaceString(node.getResourceId());
		String clasz=node.getClassName();
		String pkg=node.getPackageName();
		String contentDesc=toSpaceString(node.getContentDesc());
		String checkable=node.getCheckable();
		String checked=node.getChecked();
		String clickable=node.getClickable();
		String enable=node.getEnabled();
		String focusable=node.getFocusable();
		String focused=node.getFocused();
		String scrollable=node.getScrollable();
		String longClickable=node.getLongClickable();
		String password=node.isPassword();
		String selected=node.getSelected();
		String bounds=node.getBounds();
		String tzm=String.format(Configurator.TZMSTR, p,index,text,resourceId,clasz,pkg,contentDesc,checkable,checked,clickable,enable,focusable,focused,scrollable,longClickable,password,selected);
		//Log.w(Configurator.TAG+"-tzm",tzm);
		String line=String.format(PREFIX,"-1",text,resourceId,clasz,pkg,contentDesc,checkable,checked,clickable,enable,focusable,focused,scrollable,longClickable,password,selected,bounds,Base64.encode(tzm));
		xmlStr.append(line);
        node.setTzm(tzm);
		nodePackage(node,xmlStr);
	    xmlStr.append(NEWLINE);
		xmlStr.append(SUFFIX);
		
		String out=OUTER.replace("content", xmlStr.toString());
		out=out.replace("param",Base64.encode(ext));		
	
		return out;
	
	}
	
	public static void nodePackage(Node node,StringBuffer xmlStr) {
		List<Node> childs=node.getChild();
		String clickable=node.getClickable();
		int cnt=childs.size();
		if(cnt<1) {
			
			if("false".equals(clickable)&&ext.length()==0) {
				ext=node.getTzm();
				
			}
			
			
			return;
		}
		int index0=0;
		
		for(Node n:childs) {
			//String index0=String.valueOf(index++);
			//Log.w(Configurator.TAG+"-index值",index0+"");
			
			String parentTzm=node.getTzm();
			
			//Log.w(Configurator.TAG+"-父tzm",parentTzm);
		    String pp=parentTzm.split("@")[1].split("##")[0];
		    
		    
			String p0=pp+"_"+index0;
			//Log.w(Configurator.TAG+"-P值","父P=>"+pp+" p=>"+p0);
			String text0=toSpaceString(n.getText());
			
			String resourceId0=toSpaceString(n.getResourceId());
			String clasz0=n.getClassName();
			String pkg0=n.getPackageName();
			String contentDesc0=toSpaceString(n.getContentDesc());
			String checkable0=n.getCheckable();
			String checked0=n.getChecked();
			String clickable0=n.getClickable();
			String enable0=n.getEnabled();
			String focusable0=n.getFocusable();
			String focused0=n.getFocused();
			String scrollable0=n.getScrollable();
			String longClickable0=n.getLongClickable();
			String password0=n.isPassword();
			String selected0=n.getSelected();
			String bounds0=n.getBounds();
			
			String tzm0=String.format(Configurator.TZMSTR, p0,index0,text0,resourceId0,clasz0,pkg0,contentDesc0,checkable0,checked0,clickable0,enable0,focusable0,focused0,scrollable0,longClickable0,password0,selected0);
			//Log.w(Configurator.TAG+"-tzm",tzm0);
			xmlStr.append(NEWLINE);
			String line=String.format(PREFIX,index0++,text0,resourceId0,clasz0,pkg0,contentDesc0,checkable0,checked0,clickable0,enable0,focusable0,focused0,scrollable0,longClickable0,password0,selected0,bounds0,Base64.encode(tzm0));
		   // Log.d(Configurator.TAG+"xml add",line);
			xmlStr.append(line);
			
			n.setTzm(tzm0);
			nodePackage(n,xmlStr);
			xmlStr.append(NEWLINE);
			xmlStr.append(SPACE);
			xmlStr.append(SUFFIX);
			
			
			//
		
		}
		
		
	}
	
	public static void toXmlFile(String path,String xmlStr) {
		
		
		
		PrintWriter pw=null;
		
		File f=null;
	
		try {
			f=new File(path);
			if(f.exists()) {
				f.delete();
			}
			
			
			f.createNewFile();
			Log.i(Configurator.TAG,"新建"+path);

			
			pw=new PrintWriter(path,"utf-8");
			pw.write(xmlStr);
		} catch (FileNotFoundException | UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			if(null!=pw)pw.close();
			Log.i(Configurator.TAG+"-dump","dump xml=>"+path);
			Log.i(Configurator.TAG+"-dump","dump content=>"+xmlStr);
		}
	
	}
	
	public static void main(String[] args) {
		
		Node root=new Node();
		root.setResourceId("xxx:/xx_yu");
		root.setClassName("com.zhexa.fa.view");
		
		Node node=new Node();
		node.setBounds("[][]");
		node.setResourceId("fas.xxxf.f/fja");
		node.setClassName("xx.yy.cfa");
		
		Node node2=new Node();
		node2.setBounds("[][]");
		node2.setResourceId("fas.xxxf.f/fja");
		node2.setClassName("xx.yy.cfa");
		
		Node node22=new Node();
		node22.setBounds("[][]");
		node22.setResourceId("fas.xxxf.f/fja");
		node22.setClassName("xx.yy.cfa");	
		node2.addChild(node22);
		
		
		root.addChild(node);
		root.addChild(node2);
		String res=DumpUtils.toXmlStr(root);
		String path="C:\\Users\\F\\Desktop\\me-test\\aaa.xml";
		toXmlFile(path,res);
		
		
	}
	
	public static String toSpaceString(String t) {
		
		//Log.w(Configurator.TAG+"-字符转换",t+" "+t.length());

		
		if(t instanceof String)
			//Log.d(Configurator.TAG+"-字符转换",t+"是字符串");
		if(t.contains("null"))
			{
			//Log.d(Configurator.TAG+"-字符转换",t+"包含null字符");
			return "N";
			}

		return t;
		
	}
	


}

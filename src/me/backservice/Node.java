package me.backservice;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Node implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -6615080282414641930L;
	private String packageName="";
	private String resourceId="";
	private String text="";
	private String className="";
	private String contentDesc="";
	private String bounds="";
	private String clickable="";
	private String longClickable="";
	private String enabled="";
	private String password="";
	
	private String checkable="";
	private String checked="";
	private String focusable="";
	private String focused="";
	private String scrollable="";
	private String selected="";
	
	private String tzm="";

	
	public String getCheckable() {
		return checkable;
	}

	public void setCheckable(String checkable) {
		this.checkable = checkable;
	}

	public String getChecked() {
		return checked;
	}

	public void setChecked(String checked) {
		this.checked = checked;
	}

	public String getFocusable() {
		return focusable;
	}

	public void setFocusable(String focusable) {
		this.focusable = focusable;
	}

	public String getFocused() {
		return focused;
	}

	public void setFocused(String focused) {
		this.focused = focused;
	}

	public String getScrollable() {
		return scrollable;
	}

	public void setScrollable(String scrollable) {
		this.scrollable = scrollable;
	}

	public String getSelected() {
		return selected;
	}

	public void setSelected(String selected) {
		this.selected = selected;
	}

	private List<Node> child;
	
	public Node() {
		child=new ArrayList<Node>();
		 packageName="";
		 resourceId="";
		 text="";
		 className="";
		 contentDesc="";
		 bounds="";
		 clickable="";
		 longClickable="";
		 enabled="";
		 password="";
		
		 checkable="";
		 checked="";
		 focusable="";
		 focused="";
		 scrollable="";
		 selected="";
		
	}
	
	public String toString() {
		StringBuffer sb=new StringBuffer();
		sb.append("[class="+className);
		sb.append("|text="+text);
		sb.append("]");
		
		return sb.toString();
	}

	public String getPackageName() {
		return packageName;
	}

	public void setPackageName(String packageName) {
		if(packageName==null)packageName="";
		this.packageName = packageName;
	}

	public String getResourceId() {
		return resourceId;
	}

	public void setResourceId(String resourceId) {
		if(resourceId==null)resourceId="";
		this.resourceId = resourceId;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		if(text==null)text="";
		this.text = text;
	}

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		if(className==null)className="";
		this.className = className;
	}

	public String getContentDesc() {
		return contentDesc;
	}

	public void setContentDesc(String contentDesc) {
		if(contentDesc==null)contentDesc="";
		this.contentDesc = contentDesc;
	}

	public String getBounds() {
		return bounds;
	}

	public void setBounds(String bounds) {
		this.bounds = bounds;
	}

	public String getClickable() {
		return clickable;
	}

	public void setClickable(String clickable) {
		this.clickable = clickable;
	}

	public String getLongClickable() {
		return longClickable;
	}

	public void setLongClickable(String longClickable) {
		this.longClickable = longClickable;
	}

	public String getEnabled() {
		return enabled;
	}

	public void setEnabled(String enabled) {
		this.enabled = enabled;
	}

	public String isPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public List<Node> getChild() {
		return child;
	}

	public void addChild(Node child) {
		this.child.add(child);
	}
	
	public static void toSafeString() {
		
	}

	public String getTzm() {
		return tzm;
	}

	public void setTzm(String tzm) {
		this.tzm = tzm;
	}
	
	
	
	

}

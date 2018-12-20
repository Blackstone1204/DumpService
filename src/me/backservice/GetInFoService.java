package me.backservice;

import java.util.Arrays;
import java.util.List;

import android.accessibilityservice.AccessibilityService;
import android.util.Log;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeInfo;

public class GetInFoService extends AccessibilityService {
	String TAG="BackService";

	@Override
    public void onAccessibilityEvent(AccessibilityEvent event) {

    	Handler.pd(event);
    	
        Handler.filter();
    	
    	Handler.transfromToNode(this);

    	//Handler.canSeeLoginButton(this);
        
    	

    	// 获取激活当前窗体的激活点应用的节点
    	        if (getRootInActiveWindow() == null) {
    	            return;
    	        }
    	        
    	        
    	        


    }
    @Override
    public void onInterrupt() {  }

}
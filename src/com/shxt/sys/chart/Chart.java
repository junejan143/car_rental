package com.shxt.sys.chart;

public class Chart {

	public String yaxisname;//y轴显示的名称
	
	public String caption;//标题
	
	public String numberprefix;//前缀
	
	public String xAxisName;//x轴名称
	
	public String useroundedges = "1";
	
	public String bgcolor = "FFFFFF,FFFFFF";//背景颜色
	
	public String showborder = "0";//是否显示边框

	public String getYaxisname() {
		return yaxisname;
	}

	public void setYaxisname(String yaxisname) {
		this.yaxisname = yaxisname;
	}

	public String getCaption() {
		return caption;
	}

	public void setCaption(String caption) {
		this.caption = caption;
	}

	public String getNumberprefix() {
		return numberprefix;
	}

	public void setNumberprefix(String numberprefix) {
		this.numberprefix = numberprefix;
	}

	public String getUseroundedges() {
		return useroundedges;
	}

	public void setUseroundedges(String useroundedges) {
		this.useroundedges = useroundedges;
	}

	public String getBgcolor() {
		return bgcolor;
	}

	public void setBgcolor(String bgcolor) {
		this.bgcolor = bgcolor;
	}

	public String getShowborder() {
		return showborder;
	}

	public void setShowborder(String showborder) {
		this.showborder = showborder;
	}

	public String getxAxisName() {
		return xAxisName;
	}

	public void setxAxisName(String xAxisName) {
		this.xAxisName = xAxisName;
	}
	
	
	
}

package com.shxt.sys.chart;

import java.math.BigInteger;
/**
 * @描述:用于保存数据
 * @作者:徐新伟
 * @版本:1.0
 * @版权所有:凌云6
 * @项目名称:汽车租赁
 * @时间 2015-3-3 上午11:28:29
 */
public class ChartDatas {
	//角色字段类型
	private String label;
	//统计的人数
	private BigInteger value;
	//角色的ID
	private String link;
	
	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public BigInteger getValue() {
		return value;
	}

	public void setValue(BigInteger value) {
		this.value = value;
	}
}

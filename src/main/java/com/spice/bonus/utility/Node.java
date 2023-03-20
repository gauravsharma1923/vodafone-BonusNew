package com.spice.bonus.utility;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;


@Data
public class Node {

	public String text;

	//@JsonInclude(Include.NON_NULL)
	public String uniqueMsisdn;

	//@JsonInclude(Include.NON_NULL)
	public String totalTrasactions;
	List<Node> nodes = new ArrayList<>();

	/*
	 * public String getText() { return text; }
	 * 
	 * public void setText(String text) { this.text = text; }
	 * 
	 * public List<Node> getNodes() { return nodes; }
	 * 
	 * public void setNodes(List<Node> nodes) { this.nodes = nodes; }
	 * 
	 * public String getUniqueMsisdn() { return uniqueMsisdn; }
	 * 
	 * public void setUniqueMsisdn(String uniqueMsisdn) { this.uniqueMsisdn =
	 * uniqueMsisdn; }
	 * 
	 * public String getTotalTrasactions() { return totalTrasactions; }
	 * 
	 * public void setTotalTrasactions(String totalTrasactions) {
	 * this.totalTrasactions = totalTrasactions; }
	 */

}

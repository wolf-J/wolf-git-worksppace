package com.aia.coast.testcase;

import java.util.List;

import org.apache.commons.lang3.builder.HashCodeBuilder;

public class CoastCase {

	public Client getClient() {
		return client;
	}


	public void setClient(Client client) {
		this.client = client;
	}


	public Policy getPolicy() {
		return policy;
	}


	public void setPolicy(Policy policy) {
		this.policy = policy;
	}


	public IndividualBilling getIndivBilling() {
		return indivBilling;
	}


	public void setIndivBilling(IndividualBilling indivBilling) {
		this.indivBilling = indivBilling;
	}
	public List<PolicyProduct> getProducts() {
		return products;
	}


	public void setProducts(List<PolicyProduct> products) {
		this.products = products;
	}
	public List<ProductPlan> getProductPlans() {
		return productPlans;
	}


	public void setProductPlans(List<ProductPlan> productPlans) {
		this.productPlans = productPlans;
	}

	public List<DistributionChannel> getDistributionChannel() {
		return distributionChannel;
	}


	public void setDistributionChannel(List<DistributionChannel> distributionChannel) {
		this.distributionChannel = distributionChannel;
	}
	
	public List<PackageScheme> getPackageScheme() {
		return packageScheme;
	}


	public void setPackageScheme(List<PackageScheme> packageScheme) {
		this.packageScheme = packageScheme;
	}

	public Case getCas() {
		return cas;
	}


	public void setCas(Case cas) {
		this.cas = cas;
	}

	private Case cas;
	private Client client;
	public PolicyYear getPolicyYear() {
		return policyYear;
	}


	public void setPolicyYear(PolicyYear policyYear) {
		this.policyYear = policyYear;
	}

	public MemberFile getMemberFile() {
		return memberFile;
	}


	public void setMemberFile(MemberFile memberFile) {
		this.memberFile = memberFile;
	}


	public BillResult getBillResult() {
		return billResult;
	}


	public void setBillResult(BillResult billResult) {
		this.billResult = billResult;
	}
	
	private PolicyYear policyYear;
	private Policy policy;
	private IndividualBilling indivBilling;
	private List<DistributionChannel> distributionChannel;
	private List<PackageScheme> packageScheme;
	private List<PolicyProduct> products;
	private List<ProductPlan> productPlans;
	private MemberFile memberFile;
	private BillResult billResult;
	private PMChange pmChange;
	
	public PMChange getPmChange() {
		return pmChange;
	}


	public void setPmChange(PMChange pmChange) {
		this.pmChange = pmChange;
	}


	@Override
	public int hashCode()
	{
		HashCodeBuilder builder = new HashCodeBuilder();
		builder.append(cas);
		builder.append(client);
		builder.append(policy);
		builder.append(indivBilling);
		builder.append(distributionChannel);
		builder.append(packageScheme);
		builder.append(products).append(productPlans);
		return builder.toHashCode();
	}
	
	@Override
	public String toString()
	{
		return "[CoastCase: Policy Number: "+this.cas.getPolicyNum()+", Case Category: "+this.cas.getPolicyCategory()+"]";
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}

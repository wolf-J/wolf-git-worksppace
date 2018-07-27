package com.aia.coast.entity;

public class Scheme {
	public String getPackageProduct() {
		return packageProduct;
	}
	public void setPackageProduct(String packageProduct) {
		this.packageProduct = packageProduct;
	}
	public String getPackagePlan() {
		return packagePlan;
	}
	public void setPackagePlan(String packagePlan) {
		this.packagePlan = packagePlan;
	}
	public String getPlanDesc() {
		return planDesc;
	}
	public void setPlanDesc(String planDesc) {
		this.planDesc = planDesc;
	}
	
	
	private String packageProduct;
	private String packagePlan;
	private String planDesc;
	
	
	public static Scheme[] generatePackageSchemes()
	{
		Scheme[] schemes=new Scheme[2];
		Scheme s1=new Scheme();
		s1.setPackagePlan("001");
		s1.setPackageProduct("VOLML - VOL GL");
		s1.setPlanDesc("staff");
		schemes[0]=s1;
		
		Scheme s2=new Scheme();
		s2.setPackagePlan("002");
		s2.setPackageProduct("VOLML - VOL GL");
		s2.setPlanDesc("manager");
		schemes[1]=s2;
		return schemes;
	}
}

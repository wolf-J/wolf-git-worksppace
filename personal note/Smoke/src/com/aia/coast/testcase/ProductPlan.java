package com.aia.coast.testcase;

public class ProductPlan {
	public String getBenefitPlanScheme() {
		return BenefitPlanScheme;
	}

	public void setBenefitPlanScheme(String benefitPlanScheme) {
		BenefitPlanScheme = benefitPlanScheme;
	}

	public String getCoverageCode() {
		return CoverageCode;
	}

	public void setCoverageCode(String coverageCode) {
		CoverageCode = coverageCode;
	}

	public String getEffectiveDate() {
		return EffectiveDate;
	}

	public void setEffectiveDate(String effectiveDate) {
		EffectiveDate = effectiveDate;
	}

	public String getModalPremiumRate() {
		return ModalPremiumRate;
	}

	public void setModalPremiumRate(String modalPremiumRate) {
		ModalPremiumRate = modalPremiumRate;
	}

	public String getUnitPerValue() {
		return UnitPerValue;
	}

	public void setUnitPerValue(String unitPerValue) {
		UnitPerValue = unitPerValue;
	}

	public Benefit getBenefit() {
		return benefit;
	}

	public void setBenefit(Benefit benefit) {
		this.benefit = benefit;
	}

	private String BenefitPlanScheme;
	private String CoverageCode;
	private String EffectiveDate;
	private String ModalPremiumRate;
	private String UnitPerValue;
	
	private Benefit benefit;
	
}

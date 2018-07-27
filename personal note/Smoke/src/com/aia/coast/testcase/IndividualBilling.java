package com.aia.coast.testcase;

public class IndividualBilling {
	public String getDefaultPaySchedule() {
		return DefaultPaySchedule;
	}
	public void setDefaultPaySchedule(String defaultPaySchedule) {
		DefaultPaySchedule = defaultPaySchedule;
	}
	public String getPayByChequeInFirstMonth() {
		return PayByChequeInFirstMonth;
	}
	public void setPayByChequeInFirstMonth(String payByChequeInFirstMonth) {
		PayByChequeInFirstMonth = payByChequeInFirstMonth;
	}
	public String getWaivePremiumOnMonth() {
		return WaivePremiumOnMonth;
	}
	public void setWaivePremiumOnMonth(String waivePremiumOnMonth) {
		WaivePremiumOnMonth = waivePremiumOnMonth;
	}
	public String isAutoRenew() {
		return AutoRenew;
	}
	public void setAutoRenew(String autoRenew) {
		AutoRenew = autoRenew;
	}
	public String getRenewBillingDayMonth() {
		return RenewBillingDayMonth;
	}
	public void setRenewBillingDayMonth(String renewBillingDayMonth) {
		RenewBillingDayMonth = renewBillingDayMonth;
	}
	
	public String isFloatRate() {
		return FloatRate;
	}
	public void setFloatRate(String floatRate) {
		FloatRate = floatRate;
	}
	private String DefaultPaySchedule;
	private String PayByChequeInFirstMonth;
	private String WaivePremiumOnMonth;
	private String AutoRenew;
	private String RenewBillingDayMonth;
	private String FloatRate;
	
}

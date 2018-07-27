package com.aia.sql;

import java.util.List;

public interface MailDao {
	public long insertMail(Mail mail);
	
	public int deleteMail(long id);
	
	public int updateMail(Mail mail);
	
	public List<Mail> selectMailList();
	
	public Mail selectMailById(long id);
}

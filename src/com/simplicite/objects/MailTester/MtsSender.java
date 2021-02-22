package com.simplicite.objects.MailTester;

import java.util.*;
import com.simplicite.util.*;
import com.simplicite.util.tools.*;

/**
 * Business object MtsSender
 */
public class MtsSender extends ObjectDB {
	private static final long serialVersionUID = 1L;
	
	@Override
	public void postLoad() {
		List<ObjectCtxHelp> hlp = new ArrayList<>();
		hlp.add(new ObjectCtxHelp(getName(), new String[]{ObjectCtxHelp.CTXHELP_LIST}, "MAIL_SERVICE : "+ getGrant().getParameter("MAIL_SERVICE")));
		setCtxHelps(hlp);
	}
	
	public String send(){
		MailTool mail = new MailTool(); 
		mail.addRcpt(getFieldValue("mtsSndMail")); 
		mail.setSubject("Simplicité SMTP Service Test"); 
		mail.setContent("This is a test email");
		mail.send();
		return Message.formatSimpleInfo("Email sent.");
	}
}

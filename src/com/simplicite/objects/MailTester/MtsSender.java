package com.simplicite.objects.MailTester;

import java.util.*;
import com.simplicite.util.*;
import com.simplicite.util.tools.*;
import org.apache.commons.text.TextStringBuilder;

/**
 * Business object MtsSender
 */
public class MtsSender extends ObjectDB {
	private static final long serialVersionUID = 1L;
	
	@Override
	public void postLoad() {
		Grant g = getGrant();
		TextStringBuilder sb = new TextStringBuilder();
		sb.setNewLineText("<br/>");
		sb.appendln("<h3>Email system parameters</h3>");
		sb.appendln("<strong>MAIL_SERVICE</strong> : "+ Tool.toHTML(g.getParameter("MAIL_SERVICE")));
		sb.appendln("<strong>EMAIL_DEFAULT_SENDER</strong> : "+ Tool.toHTML(g.getParameter("EMAIL_DEFAULT_SENDER")));
		sb.appendln("<strong>EMAIL_SEND_ASYNC</strong> : "+ Tool.toHTML(g.getParameter("EMAIL_SEND_ASYNC")));
		sb.appendln("<strong>BPMALERT_FROM</strong> : "+ Tool.toHTML(g.getParameter("BPMALERT_FROM")));
		
		List<ObjectCtxHelp> hlp = new ArrayList<>();
		hlp.add(new ObjectCtxHelp(getName(), new String[]{ObjectCtxHelp.CTXHELP_LIST}, sb.toString()));
		setCtxHelps(hlp);
	}
	
	public String send(){
		MailTool mail = new MailTool(); 
		mail.addRcpt(getFieldValue("mtsSndMail")); 
		mail.setSubject("Simplicité SMTP Service Test"); 
		mail.setContent(getFieldValue("mtsSndContent"));
		if(!Tool.isEmpty(getFieldValue("mtsSndAttachment")))
			mail.addAttach(this, getField("mtsSndAttachment"));
		mail.send();
		
		return Message.formatSimpleInfo("Email sent.");
	}
}

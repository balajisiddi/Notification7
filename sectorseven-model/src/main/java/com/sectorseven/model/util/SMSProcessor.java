package com.sectorseven.model.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

import com.sectorseven.model.common.SMS;

@Component
public class SMSProcessor implements InitializingBean {

	public String sendSms(String sToPhoneNo, String sMessage) {

        try {

            String data = "user=" + URLEncoder.encode("SectorSeven", "UTF-8");
            data += "&password=" + URLEncoder.encode("8663879", "UTF-8");
            data += "&message=" + URLEncoder.encode(sMessage, "UTF-8");
            data += "&sender=" + URLEncoder.encode("Envton", "UTF-8");
            data += "&mobile=" + URLEncoder.encode(sToPhoneNo, "UTF-8");
            data += "&type=" + URLEncoder.encode("3", "UTF-8");

            URL url = new URL("http://login.bulksmsgateway.in/sendmessage.php?" + data);
            URLConnection conn = url.openConnection();
            conn.setDoOutput(true);
            BufferedReader rd;
            String sResult;
            try (OutputStreamWriter wr = new OutputStreamWriter(conn.getOutputStream())) {
                wr.write(data);
                wr.flush();
                rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                String line;
                sResult = "";
                while ((line = rd.readLine()) != null) {
                    sResult = sResult + line + " ";
                }
                wr.close();
            }
            rd.close();
            return sResult;
        } catch (IOException e) {
            System.out.println("Error SMS " + e);
            e.printStackTrace();
            return "Error " + e;
        }
    }

	 public void sendSMS(final SMS sms) {
	        this.sendSms(sms.getsToPhoneNo(), sms.getsMessage());
	    }
	 
	@Override
	public void afterPropertiesSet() throws Exception {
        //new Thread(new SMSWorker(this)).start();
	}

}

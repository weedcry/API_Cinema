package com.QCINE.Main.Service;

import com.QCINE.Main.Entity.HoaDon_Entity;
import com.QCINE.Main.Entity.Lich_Entity;
import com.QCINE.Main.Entity.Phim_Entity;
import com.QCINE.Main.Repository.Lich_Repository;
import com.QCINE.Main.Repository.Phim_Repository;
import com.amazonaws.services.opsworks.model.SelfUserProfile;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.sendgrid.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@Service
public class SendGridMail_Service {

    @Autowired
    Lich_Repository lich_repository;


    private static final Logger logger = LoggerFactory.getLogger(SendGridMail_Service.class);

    @Value("${send_grid.api_key}")
    private String sendGridApiKey;

    @Value("${send_grid.from_email}")
    private String sendGridFromEmail;

    @Value("${send_grid.from_name}")
    private String sendGridFromName;

    public String sendTextEmail() throws IOException {
        // the sender email should be the same as we used to Create a Single Sender Verification
        Email from = new Email(sendGridFromEmail);
        String subject = "The subject";
        Email to = new Email("phongnguyen9111999@gmail.com");
        Content content = new Content("text/plain", "This is a test email");
        Mail mail = new Mail(from, subject, to, content);

        SendGrid sg = new SendGrid(sendGridApiKey);
        Request request = new Request();
        try {
            request.setMethod(Method.POST);
            request.setEndpoint("mail/send");
            request.setBody(mail.build());
            Response response = sg.api(request);
            logger.info(response.getBody());
            return response.getBody();
        } catch (IOException ex) {
            throw ex;
        }
    }


    // send  template

    public String sendTemplateMail(String status, String tomail, String name, String codeS,int lichid,
                                   String time, String ghe,String sl, String pricedat, String pricegiam, String tt) throws IOException {
        // the sender email should be the same as we used to Create a Single Sender Verification
        Email from = new Email(sendGridFromEmail);
        Email to = new Email(tomail);
        Mail mail = new Mail();
        // we create an object of our static class feel free to change the class on it's own file
        // I try to keep every think simple
        DynamicTemplatePersonalization personalization = new DynamicTemplatePersonalization();
        personalization.addTo(to);
        mail.setFrom(from);
        mail.setSubject("Subject");
        // This is the first_name variable that we created on the template
        if(status.equals("welcome")){
            personalization.addDynamicTemplateData("welcome", "welcome");
            personalization.addDynamicTemplateData("name",name);
            mail.addPersonalization(personalization);
            mail.setTemplateId("d-a8b0101b6bbb478185ad7b5f543ccd44");
        }else{
            if(status.equals("code")){
                personalization.addDynamicTemplateData("code reset password", "code reset password");
                personalization.addDynamicTemplateData("code", codeS);
                mail.addPersonalization(personalization);
                mail.setTemplateId("d-86e5ab0a75ef4dcd95c57eea59e11b21");
            }else{ // hoa don

                Lich_Entity lich = lich_repository.findByIdLich(lichid);
                if(ObjectUtils.isEmpty(lich)){
                    System.out.println(" lich null");
                    return "null";
                }
                personalization.addDynamicTemplateData("Hoadon", "Hoadon");
                personalization.addDynamicTemplateData("name",name);
                personalization.addDynamicTemplateData("timedatve",time);
                personalization.addDynamicTemplateData("tenphim",lich.getPhimEntity().getTenPhim());
                personalization.addDynamicTemplateData("theloai",lich.getPhimEntity().getLoaiPhimEntity().getTenLoai());
                personalization.addDynamicTemplateData("loaichieu",lich.getPhimEntity().getPhanLoai());

                SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
                String dateChieu = formatter.format(lich.getNgay());
                personalization.addDynamicTemplateData("daychieu",dateChieu);
                personalization.addDynamicTemplateData("timechieu",lich.getGio().toString());
                personalization.addDynamicTemplateData("rapchieu",lich.getPhongEntity().getRapEntity().getTenRap() +", "+lich.getPhongEntity().getTenPhong());
                personalization.addDynamicTemplateData("sove",sl);
                personalization.addDynamicTemplateData("ghengoi",ghe);
                personalization.addDynamicTemplateData("pricedat",pricedat);
                personalization.addDynamicTemplateData("pricegiam",pricegiam);
                personalization.addDynamicTemplateData("pricett",tt);
                personalization.addDynamicTemplateData("hinhanh",lich.getPhimEntity().getAnh());
                mail.addPersonalization(personalization);
//                mail.setTemplateId("d-9dac532ef22340ab9c3b9cdfcf0a86ce");
                mail.setTemplateId("d-b321081c8084434c95fee7e8e13bc2ed");
            }
        }

        // this is the api key
        SendGrid sg = new SendGrid(sendGridApiKey);
        Request request = new Request();

        try {
            request.setMethod(Method.POST);
            request.setEndpoint("mail/send");
            request.setBody(mail.build());
            Response response = sg.api(request);
            logger.info(response.getBody());
            return response.getBody();
        } catch (IOException ex) {
            System.out.println("Excep loi "+ex);
            throw ex;
        }
    }

    // This class handels the dynamic data for the template
    // Feel free to customise this class our to putted on other file
    private static class DynamicTemplatePersonalization extends Personalization {

        @JsonProperty(value = "dynamic_template_data")
        private Map<String, String> dynamic_template_data;

        @JsonProperty("dynamic_template_data")
        public Map<String, String> getDynamicTemplateData() {
            if (dynamic_template_data == null) {
                return Collections.<String, String>emptyMap();
            }
            return dynamic_template_data;
        }

        public void addDynamicTemplateData(String key, String value) {
            if (dynamic_template_data == null) {
                dynamic_template_data = new HashMap<String, String>();
                dynamic_template_data.put(key, value);
            } else {
                dynamic_template_data.put(key, value);
            }
        }

    }

}

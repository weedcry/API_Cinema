package com.QCINE.Main.PaypalController;

import javax.servlet.http.HttpServletRequest;
import com.QCINE.Main.Service.PaypalService;
import com.QCINE.Main.security.config.PaypalPaymentIntent;
import com.QCINE.Main.security.config.PaypalPaymentMethod;
import com.QCINE.Main.security.config.PaypalUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import com.paypal.api.payments.Links;
import com.paypal.api.payments.Payment;
import com.paypal.base.rest.PayPalRESTException;

import java.net.URI;
import java.net.URISyntaxException;

@CrossOrigin(origins = "*", maxAge = 3600)
@Controller
@RequestMapping("/api/payment")
public class PaypalController {
    public static final String URL_PAYPAL_SUCCESS = "api/payment/success";
    public static final String URL_PAYPAL_CANCEL = "api/payment/cancel";
    private Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    private PaypalService paypalService;


    @GetMapping("/pay")
    public ResponseEntity<?>  pay(HttpServletRequest request,@RequestParam("price") double price) throws URISyntaxException {
        String cancelUrl = PaypalUtils.getBaseURL(request) + "/" + URL_PAYPAL_CANCEL;
        String successUrl = PaypalUtils.getBaseURL(request) + "/" + URL_PAYPAL_SUCCESS;
        try {

            Payment payment = paypalService.createPayment(
                    price,
                    "USD",
                    PaypalPaymentMethod.paypal,
                    PaypalPaymentIntent.sale,
                    "payment description",
                    cancelUrl,
                    successUrl);

            for(Links links : payment.getLinks()){
                if(links.getRel().equals("approval_url")){
                    URI yahoo = new URI(links.getHref());
                    HttpHeaders httpHeaders = new HttpHeaders();
                    httpHeaders.setLocation(yahoo);
                    return new ResponseEntity<>(httpHeaders, HttpStatus.SEE_OTHER);
                }
            }
        } catch (PayPalRESTException | URISyntaxException e) {
            log.error(e.getMessage());
            System.out.println("err "+e);
        }

        URI yahoo = new URI("http://localhost:3000/checkout");
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setLocation(yahoo);
        return new ResponseEntity<>(httpHeaders, HttpStatus.SEE_OTHER);
    }

    @GetMapping(URL_PAYPAL_CANCEL)
    public ResponseEntity<?> cancelPay() throws URISyntaxException {
        URI yahoo = new URI("http://localhost:3000/checkout");
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setLocation(yahoo);
        return new ResponseEntity<>(httpHeaders, HttpStatus.SEE_OTHER);
    }

    @GetMapping("/success")
    public ResponseEntity<?>  successPay(@RequestParam("paymentId") String paymentId, @RequestParam("PayerID") String payerId) throws URISyntaxException {
        try {
            Payment payment = paypalService.executePayment(paymentId, payerId);
            if(payment.getState().equals("approved")){
                URI yahoo = new URI("http://localhost:3000/ticket/checkout?success=true&paymentid="+paymentId);
                HttpHeaders httpHeaders = new HttpHeaders();
                httpHeaders.setLocation(yahoo);
                return new ResponseEntity<>(httpHeaders, HttpStatus.SEE_OTHER);
            }
        } catch (PayPalRESTException e) {
            log.error(e.getMessage());
        }
        URI yahoo = new URI("http://localhost:3000/ticket/checkout?success=fail");
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setLocation(yahoo);
        return new ResponseEntity<>(httpHeaders, HttpStatus.SEE_OTHER);
    }
}
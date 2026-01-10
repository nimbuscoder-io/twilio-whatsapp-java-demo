package io.nimbuscoder.twilio.whatsapp.java.demo;

import static spark.Spark.get;
import static spark.Spark.post;

import com.twilio.twiml.MessagingResponse;
import com.twilio.twiml.messaging.Body;
import com.twilio.twiml.messaging.Message;

public class WhatsAppWebhookDemoApp {

  public static void main(String[] args) {
    get("/", (req, res) -> "Hello Web");

    post("/whatsapp", (req, res) -> {
      res.type("application/xml");

      /**
       * Sample request body is below. Each field is URL-encoded key-value pair.
       * <pre>
       *    SmsMessageSid=SMc5407f8f5bb5f1ce0e50f41c3b854xxx
       *    NumMedia=0
       *    ProfileName=Change+Me
       *    MessageType=text
       *    SmsSid=SMc5407f8f5bb5f1ce0e50f41c3b854xxx
       *    WaId=6593883xxx
       *    SmsStatus=received
       *    Body=This+is+the+third+message
       *    To=whatsapp%3A%2B14155238886
       *    NumSegments=1
       *    ReferralNumMedia=0
       *    MessageSid=SMc5407f8f5bb5f1ce0e50f41c3b854xxx
       *    AccountSid=AC6f8072f30b7392eaeca180a9cc65ac6b
       *    ChannelMetadata=%7B%22type%22%3A%22whatsapp%22%2C%22data%22%3A%7B%22context%22%3A%7B%22ProfileName%22%3A%22Change+Me%22%2C%22WaId%22%3A%226593883xxx%22%7D%7D%7D
       *    From=whatsapp%3A%2B6593883xxx
       *    ApiVersion=2010-04-01
       * </pre>
       */

      System.out.println("Received request=\n" + req.body().replaceAll("&", "\n"));

      final var messageBody = "Message received! Hello again from the Twilio Sandbox on behalf of Nimbus Coder.";
      final var whatsapp = new Message.Builder().body(new Body.Builder(messageBody).build()).build();
      final var twiml = new MessagingResponse.Builder().message(whatsapp).build();

      return twiml.toXml();
    });
  }
}

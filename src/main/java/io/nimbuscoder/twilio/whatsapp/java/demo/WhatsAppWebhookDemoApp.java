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

      System.out.println(
          "Received request=\n" + req.body() + "\nFields:\n" + req.body().replaceAll("&", "\n") + "\n-----\n");

      final var messageBody = "Message received! Hello again from the Twilio Sandbox on behalf of Nimbus Coder.";
      final var whatsapp = new Message.Builder().body(new Body.Builder(messageBody).build()).build();
      final var twiml = new MessagingResponse.Builder().message(whatsapp).build();

      return twiml.toXml();
    });
  }
}

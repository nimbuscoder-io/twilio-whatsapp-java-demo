package io.nimbuscoder.twilio.whatsapp.java.demo;

import static spark.Spark.get;
import static spark.Spark.post;

import com.twilio.twiml.MessagingResponse;
import com.twilio.twiml.messaging.Body;
import com.twilio.twiml.messaging.Message;
import java.util.stream.Collectors;

public class WhatsAppWebhookDemoApp {

  public static void main(String[] args) {
    get("/", (req, res) -> "Hello Web");

    post("/whatsapp", (req, res) -> {
      res.type("application/xml");

      final var headerValues = req.headers()
          .stream()
          .map(h -> h + "=" + req.headers(h))
          .collect(Collectors.joining("\n\t\t"));

      System.out.println("Received request:\n"
          + "\tcontent-type: " + req.contentType() + "\n"
          + "\theaders: " + headerValues + "\n"
          + "\tbody:\n\t\t" + req.body().replaceAll("&", "\n\t\t")
          + "\n-----\n");

      final var messageBody = "Message received! Hello again from the Twilio Sandbox on behalf of Nimbus Coder.";
      final var whatsapp = new Message.Builder().body(new Body.Builder(messageBody).build()).build();
      final var twiml = new MessagingResponse.Builder().message(whatsapp).build();
      // Return the TwiML as XML
      return twiml.toXml();
    });
  }
}

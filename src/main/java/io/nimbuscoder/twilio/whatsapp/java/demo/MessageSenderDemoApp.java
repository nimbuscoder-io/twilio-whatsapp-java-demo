package io.nimbuscoder.twilio.whatsapp.java.demo;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import java.util.Map;
import org.json.JSONObject;

/**
 * A simple demo application to send WhatsApp messages using Twilio Java SDK.
 * <p>
 * Before running this application, ensure that you have set the following environment variables:
 * <pre>
 * - TWILIO_ACCOUNT_SID: Your Twilio Account SID
 * - TWILIO_AUTH_TOKEN: Your Twilio Auth Token
 * </pre>
 * Also, make sure to replace the placeholder phone numbers and content SID with actual values. You can run this
 * application from the command line or your favorite IDE.
 * <p>
 * <b>Note:</b> This application uses Twilio's WhatsApp sandbox. To use it, you need to join the sandbox by sending a WhatsApp
 * message to the sandbox number provided in your Twilio console.
 * <p>
 * For more information, visit:
 * <a href="https://www.twilio.com/docs/whatsapp/sandbox">Twilio Sandbox</a>
 *
 * @author Nimbus Coder
 * @version 1.0
 * @since 2024-06-01
 */
public class MessageSenderDemoApp {

  public static final String ACCOUNT_SID = System.getenv("TWILIO_ACCOUNT_SID");
  public static final String AUTH_TOKEN = System.getenv("TWILIO_AUTH_TOKEN");

  public static void main(String[] args) {
    Twilio.init(ACCOUNT_SID, AUTH_TOKEN);

    final var contentVariables = Map.of(
        "1", "13 December 1982",
        "2", "11:10pm"
    );
    final var contentSid = "HXb5b62575e6e4ff6129ad7c8efe1f983e";
    final var toPhoneNumber = new com.twilio.type.PhoneNumber("whatsapp:+6597488987");
    final var fromPhoneNumber = new com.twilio.type.PhoneNumber("whatsapp:+14155238886");
    final var messageBody = "Hello from Twilio WhatsApp Java SDK!";
    final var message = Message.creator(toPhoneNumber, fromPhoneNumber, messageBody)
        .setContentSid(contentSid)
        .setContentVariables(new JSONObject(contentVariables).toString())
        .create();

    System.out.println("Message SID = " + message.getSid());
  }
}

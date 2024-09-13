import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.*;

public class CommandLineEmailClient {
    private static final String mailStoreType = "imap";
    private static String user;
    private static String password;

    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Welcome to the Command-Line Email Client");
        System.out.println("Please configure your email account:");

        System.out.print("Enter your email: ");
        user = scanner.nextLine();

        System.out.print("Enter your password: ");
        password = scanner.nextLine();

        System.out.println("Configuration complete!");

        while (true) {
            System.out.println("\nOptions:");
            System.out.println("1. Send Email");
            System.out.println("2. Read Emails");
            System.out.println("3. Delete Email");
            System.out.println("4. Exit");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine();  // consume newline

            switch (choice) {
                case 1:
                    sendEmail(scanner);
                    break;
                case 2:
                    readEmails();
                    break;
                case 3:
                    deleteEmail(scanner);
                    break;
                case 4:
                    System.out.println("Exiting...");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid option.");
            }
        }
    }

    private static void sendEmail(Scanner scanner) {
        try {
            Properties props = new Properties();
            // SMTP server
            String host = "smtp.gmail.com";
            props.put("mail.smtp.host", host);
            props.put("mail.smtp.port", "465");
            props.put("mail.smtp.auth", "true");
            props.put("mail.smtp.starttls.enable", "true");
            props.put("mail.smtp.ssl.enable", "true");

            Session session = Session.getInstance(props, new Authenticator() {
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(user, password);
                }
            });

            System.out.print("Enter recipient email: ");
            String to = scanner.nextLine();

            System.out.print("Enter subject: ");
            String subject = scanner.nextLine();

            System.out.print("Enter email content: ");
            String content = scanner.nextLine();

            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(user));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
            message.setSubject(subject);
            message.setText(content);

            Transport.send(message);

            System.out.println("Email sent successfully.");
        } catch (Exception e) {
            System.out.println("Error in sending email: " + e.getMessage());
        }
    }

    private static void readEmails() {
        try {
            Properties props = new Properties();
            props.put("mail.store.protocol", mailStoreType);
            props.put("mail.imap.host", "imap.gmail.com");
            props.put("mail.imap.port", "993");
            props.put("mail.imap.ssl.enable", "true");

            Session session = Session.getDefaultInstance(props, null);

            Store store = session.getStore(mailStoreType);
            store.connect("imap.gmail.com", user, password);

            Folder inbox = store.getFolder("INBOX");
            inbox.open(Folder.READ_ONLY);

            Message[] messages = inbox.getMessages();
            System.out.println("You have " + messages.length + " messages.");

            for (int i = 0; i < messages.length; i++) {
                Message message = messages[i];
                System.out.println("\nEmail " + (i + 1) + ":");
                System.out.println("From: " + Arrays.toString(message.getFrom()));
                System.out.println("Subject: " + message.getSubject());
                System.out.println("Content: " + message.getContent().toString());
            }

            inbox.close(false);
            store.close();
        } catch (Exception e) {
            System.out.println("Error in reading emails: " + e.getMessage());
        }
    }

    private static void deleteEmail(Scanner scanner) {
        try {
            Properties props = new Properties();
            props.put("mail.store.protocol", mailStoreType);
            props.put("mail.imap.host", "imap.gmail.com");
            props.put("mail.imap.port", "993");
            props.put("mail.imap.ssl.enable", "true");

            Session session = Session.getDefaultInstance(props, null);

            Store store = session.getStore(mailStoreType);
            store.connect("imap.gmail.com", user, password);

            Folder inbox = store.getFolder("INBOX");
            inbox.open(Folder.READ_WRITE);

            Message[] messages = inbox.getMessages();
            System.out.println("You have " + messages.length + " messages.");

            for (int i = 0; i < messages.length; i++) {
                System.out.println("\nEmail " + (i + 1) + ":");
                System.out.println("From: " + Arrays.toString(messages[i].getFrom()));
                System.out.println("Subject: " + messages[i].getSubject());
            }

            System.out.print("\nEnter the number of the email to delete: ");
            int emailToDelete = scanner.nextInt();
            scanner.nextLine();  // consume newline

            messages[emailToDelete - 1].setFlag(Flags.Flag.DELETED, true);
            System.out.println("Email deleted successfully.");

            inbox.close(true);
            store.close();
        } catch (Exception e) {
            System.out.println("Error in deleting email: " + e.getMessage());
        }
    }
}

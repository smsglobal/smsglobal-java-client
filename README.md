#...... SMSGlobal Java Client

## Requirements

- JDK 7 or 8
- Maven 3.x

## How to build the jar

```bash
mvn clean package
```

## Sending a message using the HTTP transport

```java
Message message = new Message("SGTest", "614xxxxxx", "Sending a message using the HTTP transport");
HttpTransport httpTransport = new HttpTransport("xxxxxxxx", "xxxxxxx", "https://www.smsglobal.com/http-api.php");
System.out.println(httpTransport.buildUrl(message));
Client client = new Client(httpTransport);
String response = client.sendMessage(message);
```````

## Sending a message using the REST transport

```java
Message message = new Message("SGTest", "614ccc", "Sending a message using the REST transport");
RestTransport restTransport = new RestTransport("aaa", "bbb", "https://api.smsglobal.com:443/v2");
restTransport.setPath("/sms/");
System.out.println(restTransport.getBaseUrl() + restTransport.getPath());
System.out.println(restTransport.toXml(message));
Client client = new Client(restTransport);
String response = client.sendMessage(message);
System.out.println(response);
```

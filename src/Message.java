public class Message {
    private String encryptedMessage;
    private String decryptedMessage;
    public Message() {
        clearMessage();
    }
    public String getEncryptedMessage() {
        return encryptedMessage;
    }
    public void setEncryptedMessage(String encryptedMessage) {
        this.encryptedMessage = encryptedMessage;
    }
    public String getDecryptedMessage() {
        return decryptedMessage;
    }
    public void setDecryptedMessage(String decryptedMessage) {
        this.decryptedMessage = decryptedMessage;
    }
    public void clearMessage() {
        encryptedMessage = "";
        decryptedMessage = "";
    }
}

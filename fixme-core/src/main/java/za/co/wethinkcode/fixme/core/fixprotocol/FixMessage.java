package za.co.wethinkcode.fixme.core.fixprotocol;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;

public class FixMessage {
    HashMap<Integer, String> map = new HashMap<>();

    public String targetComputer;
    public String msgType;
    public String instrument;
    public double price;
    public String quantity;
    public String senderCompId;
    public String text;

    public void setTargetComputer(String targetComputer) {
        this.targetComputer = targetComputer;
        map.put(45, targetComputer);
    }

    public void setMessageType(String msgType) {
        this.msgType = msgType;
        map.put(35, msgType);
    }

    public void setInstrument(String instrument) {
        this.instrument = instrument;
        map.put(67, instrument);
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
        map.put(78, quantity);
    }


    public void setText(String text) {
        this.text = text;
        map.put(107, text);
    }

    public void setPrice(double price) {
        this.price = price;
        map.put(102, String.valueOf(price));
    }
    public void setSenderCompID(String senderCompId) {
        this.senderCompId = senderCompId;
        map.put(56, senderCompId);
    }

    public String encode(){
        StringBuilder builder = new StringBuilder();

        for (int key : map.keySet()) {
            String value = map.get(key);
            builder.append(key).append("=").append(value).append("|");
        }
        return builder.toString();
    }

    public static FixMessage fromString(String message ){
        FixMessage fixMessage = new FixMessage();
        String[] array = message.split("\\|");

        for (String line : array) {
            String[] tagAndValue = line.split("=");
            int tag = Integer.parseInt(tagAndValue[0]);
            String value = tagAndValue[1];

            switch (tag){
                case 35 : fixMessage.setMessageType(value); break;
                case 45 : fixMessage.setTargetComputer(value); break;
                case 56 : fixMessage.setSenderCompID(value); break;
                case 67 : fixMessage.setInstrument(value); break;
                case 78 : fixMessage.setQuantity(value); break;
                case 102 : fixMessage.setPrice(Double.parseDouble(value));
                case 107 : fixMessage.setText(value);

            }
        }
        return fixMessage;
    }


}

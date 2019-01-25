import io.netty.channel.Channel;

import java.util.HashMap;

public class GlobalData {
   static HashMap<Integer, Channel> brokerChannels = new HashMap<>();
   static HashMap<Integer, Channel> marketChannels = new HashMap<>();
}


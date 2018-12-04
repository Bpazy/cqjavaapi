# cqjavaapi
[![Maven Central](https://maven-badges.herokuapp.com/maven-central/com.github.bpazy/cqjavaapi/badge.svg)](https://maven-badges.herokuapp.com/maven-central/com.github.bpazy/cqjavaapi)

酷Q Java API base on cqsocketapi  


Gradle:
```
compile 'com.github.bpazy:cqjavaapi:0.2.1'
```
Maven:
```
<dependency>
  <groupId>com.github.bpazy</groupId>
  <artifactId>cqjavaapi</artifactId>
  <version>0.2.2</version>
  <type>pom</type>
</dependency>
```

### 注意
请先安装`酷Q`并启用`cqsocketapi`插件

### 一分钟使用

```
// Create CqServer, this is the entrance.
CqServer server = new CqServer();

// Add your custom code with MessageHandler or CqMessageHandler.
server.addMessageHandler(new CqMessageHandler() {

    // Discuss message handler
    @Override
    public boolean discussMessage(DiscussMessage msg) {
        if (msg.getText().equals("我爱你")) {
            // Obtain default message sender and then send discuss message.
            CqSender.getDefaultSender().sendDiscussMsg(msg.getDiscussID(), "我也爱你");
        }
        // Return false if you have not completed the handler.
        // And pass the message to the next handler until true.
        return false;
    }
});
// Start server with your favorite port.
server.listenAndServe(1994);
```

### 说明
`CqServer` 主服务类，    
`CqMessageHandler` 信息处理器，实现了`MessageHandler`接口，    
`MessageHandler` 信息处理接口，包含:
> privateMessage (私人信息处理器),    
groupMessage (群信息处理器),     
discussMessage (讨论组信息处理器),    
groupMemberDecrease (群组减员信息处理器),     
groupMemberIncrease (群组增员信息处理器)

`CqSender` 信息发送类

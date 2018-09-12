package  com.leslia.java.proto;
import com.google.protobuf.util.JsonFormat;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Date;
import java.util.List;

public class ProtoTest {


    public static void main(String[] args) throws IOException {

        // 按照定义的数据结构，创建一个Person
        PersonMsg.Person.Builder personBuilder = PersonMsg.Person.newBuilder();
        personBuilder.setId(1);
        personBuilder.setName("叉叉哥");
        personBuilder.setEmail("xxg@163.com");
        personBuilder.addFriends("Friend A");
        personBuilder.addFriends("Friend B");
        PersonMsg.Person xxg = personBuilder.build();

        // 将数据写到输出流，如网络输出流，这里就用ByteArrayOutputStream来代替
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        xxg.writeTo(output);

        // -------------- 分割线：上面是发送方，将数据序列化后发送 ---------------

        byte[] byteArray = output.toByteArray();

        // -------------- 分割线：下面是接收方，将数据接收后反序列化 ---------------

        // 接收到流并读取，如网络输入流，这里用ByteArrayInputStream来代替
        ByteArrayInputStream input = new ByteArrayInputStream(byteArray);

        // 反序列化
        PersonMsg.Person xxg2 = PersonMsg.Person.parseFrom(input);
        System.out.println("ID:" + xxg2.getId());
        System.out.println("name:" + xxg2.getName());
        System.out.println("email:" + xxg2.getEmail());
        System.out.println("friend:");
        List<String> friends = xxg2.getFriendsList();
        for(String friend : friends) {
            System.out.println(friend);
        }
    }

    @Test
    public void test1() throws IOException{
        PersonMsg.Person.Builder personBuilder=PersonMsg.Person.newBuilder();
        personBuilder.setId(2);
        personBuilder.setName("leslia");
        personBuilder.setEmail("xxg@163.com");
        personBuilder.addFriends("friendA");
        personBuilder.addFriends("friendB");
        PersonMsg.Person xxg = personBuilder.build();
        System.out.println(xxg.toString());
        System.out.println(xxg.toByteString().size());
        PersonMsg.Person xxg2=PersonMsg.Person.parseFrom(xxg.toByteArray());
        String jsonFormatM = JsonFormat.printer().print(xxg);
        System.out.println(jsonFormatM);
        System.out.println(jsonFormatM.getBytes().length);
    }


    @Test
    public void test2() {
        GpsData.gps_data.Builder builder = GpsData.gps_data.newBuilder();
        builder.setId(1);
        builder.setAltitude(1);
        builder.setDataTime(new Date().toString());
        GpsData.gps_data xxg = builder.build();
        System.out.println(xxg.toString());
    }

}

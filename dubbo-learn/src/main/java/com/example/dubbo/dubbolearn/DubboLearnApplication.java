package com.example.dubbo.dubbolearn;

import com.caucho.hessian.io.HessianOutput;
import com.example.dubbo.dubbolearn.domain.Group;
import com.example.dubbo.dubbolearn.domain.User;
import com.example.dubbo.dubbolearn.util.ProtostuffUtils;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.*;
import java.util.Arrays;

@SpringBootApplication
public class DubboLearnApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(DubboLearnApplication.class, args);
    }
    @Override
    public void run(String... strings) throws Exception{
        User user = new User();
        user.setId("1");
        user.setAge(20);
        user.setName("张三");
        user.setDesc("programmer");

        Group group = new Group();
        group.setId("1");
        group.setName("分组1");
        group.setUser(user);

        byte[] data = ProtostuffUtils.serialize(group);
//        byte[] data1 =
        serializeFlyPig();
        HessianSerialize();
        System.out.println("count is:"+data.length);
        System.out.println("序列化后:"+ Arrays.toString(data));
        Group result = ProtostuffUtils.deserialize(data, Group.class);
        System.out.println("反序列化后:"+result.toString());
    }

    private static void serializeFlyPig() throws IOException {
        User user = new User();
        user.setId("1");
        user.setAge(20);
        user.setName("张三");
        user.setDesc("programmer");

        Group group = new Group();
        group.setId("1");
        group.setName("分组1");
        group.setUser(user);

        // ObjectOutputStream 对象输出流，将 flyPig 对象存储到E盘的 flyPig.txt 文件中，完成对 flyPig 对象的序列化操作
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(new File("d:/flyPig.txt")));
        oos.writeObject(group);
        oos.flush();
        oos.close();

        System.out.println("FlyPig 对象序列化成功！");
        InputStream oi=new FileInputStream(new File("d:/flyPig - 副本.txt"));
        byte[] bytes=new byte[oi.available()];
        oi.read(bytes);
        int i=0;
        for(byte b:bytes){
            i++;
            System.out.print(b+",");
        }

        System.out.println();
        System.out.println("count is:"+i);
    }
    public static void HessianSerialize() {
        User user = new User();
        user.setId("1");
        user.setAge(20);
        user.setName("张三");
        user.setDesc("programmer");

        Group group = new Group();
        group.setId("1");
        group.setName("分组1");
        group.setUser(user);

        ByteArrayOutputStream byteArrayOutputStream = null;
        HessianOutput hessianOutput = null;
        try {
            byteArrayOutputStream = new ByteArrayOutputStream();
            // Hessian的序列化输出
            hessianOutput = new HessianOutput(byteArrayOutputStream);
            hessianOutput.writeObject(group);
            byte[] bytes = byteArrayOutputStream.toByteArray();
            System.out.println("hessian serialiable result:");
            int i=0;
            for(byte b:bytes){
                i++;
                System.out.print(b+",");
            }
            System.out.println();
            System.out.println("count is:"+i);
//            return byteArrayOutputStream.toByteArray();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                byteArrayOutputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                hessianOutput.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
//        return null;
    }
}

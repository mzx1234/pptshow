package com.mzx.pptserver.monitor.task;

import com.newppt.android.entity.DMT;
import org.springframework.stereotype.Service;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.SocketAddress;
import java.net.SocketException;
import java.util.Map;
import java.util.concurrent.Executors;

/**
 * Created by zison on 2016/1/17.
 */
@Service
public class SendMsgTask extends BaseTask{


    @Override
    public void execute() {
        Executors.newSingleThreadExecutor().submit(this);
    }

    @Override
    public String getTaskName() {
        return null;
    }

    @Override
    public Boolean call() throws Exception {
        for(Map.Entry<SocketAddress, Socket> e: globalApplication.getSocketPoolMap().entrySet() ){
            sendMessage(e.getValue());
        }
        return true;

    }


    /**
     * 设置DMT
     *
     * @return
     */
    DMT setDMT() {
        DMT dmt = new DMT();
        dmt.setCurrentPage(globalApplication.getCurPage());
        dmt.setPage(globalApplication.getLen());
        dmt.setFilename(globalApplication.getFileName());
        return dmt;
    }

    /**
     * send message
     *
     * @param s
     * @throws IOException
     */
    public  void sendMessage(Socket s)  {

        try {
            s.setSoTimeout(5000);
        } catch (SocketException e1) {
            // TODO Auto-generated catch block

            System.out.println("读取超时");
            try {
                s.close();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            System.out.println("有客户端退出");

//			e1.printStackTrace();
        }

        DMT dmt = setDMT();
        System.out.println("---currentpage------" + dmt.getCurrentPage());
        ObjectOutputStream objectOutputStream;
        try {
            objectOutputStream = new ObjectOutputStream(
                    s.getOutputStream());

            objectOutputStream.writeObject(dmt);


            ObjectInputStream objectInputStream = new ObjectInputStream(
                    s.getInputStream());
//		objectInputStream.wait(5*1000);
            System.out.println("---------2222" + "\n");
            try {
                DMT messageDmt = (DMT) objectInputStream.readObject();
            } catch (ClassNotFoundException e) {
                // TODO Auto-generated catch block
//			s.close();
                e.printStackTrace();
            }
            DataInputStream is = null;
            is = new DataInputStream(s.getInputStream());
            String date = is.readUTF();
            System.out.println(date);
        } catch (IOException e1) {
            // TODO Auto-generated catch block

            try {
                s.close();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            e1.printStackTrace();
        }

    }
}

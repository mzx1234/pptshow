package com.mzx.pptserver.monitor.task;

import com.mzx.pptserver.utility.FileToByte;
import com.mzx.pptserver.utility.RedisUtil;
import org.springframework.stereotype.Service;

import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.net.SocketAddress;
import java.util.Map;
import java.util.concurrent.Executors;

/**
 * Created by zison on 2016/1/17.
 */
@Service
public class SendByteTask extends BaseTask {


    private Socket socket ;

    public Socket getSocket() {
        return socket;
    }

    public void setSocket(Socket socket) {
        this.socket = socket;
    }



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
        for(Map.Entry<SocketAddress, Socket> e: globalApplication.getByteSocketPoolMap().entrySet() ){
        senByteToClient(e.getValue());
    }
        return true;

    }


    /**
     * send File to Client
     * @param s
     */
    private void senByteToClient(Socket s) {

        DataInputStream is = null;
        OutputStream os = null;
        String fileName = null;
        String path = null;
        FileInputStream fins = null;
        try {

            is = new DataInputStream(s.getInputStream());
            String typeString = is.readUTF();
            if (typeString.equals("PPT")) {
                fileName = globalApplication.getFileName();
                path = globalApplication.getPath();
            }
            else {
                os = s.getOutputStream();
                sendImageBytes(os);
                return ;

            }

            os = s.getOutputStream();
            System.out.println("将文件名:" + fileName + "传输过去");
            // 先将文件名传输过去
            os.write(fileName.getBytes());
            System.out.println("开始传输文件");

            sendFileBytes(os);
            System.out.println("文件传输结束");

        } catch (Exception e) {
            // TODO Auto-generated catch block
            // e.printStackTrace();
            globalApplication.getByteSocketPoolMap().remove(s.getRemoteSocketAddress());
        } finally {
            try {
                if (fins != null)
                    fins.close();
                if (os != null)
                    os.close();
                s.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }



    private void sendImageBytes(OutputStream os) throws Exception{
        os.write(globalApplication.getTargetBytes());
    }

    private void sendFileBytes (OutputStream os) throws Exception {
        os.write(FileToByte.FileToByte(globalApplication.getPath()));
    }
}

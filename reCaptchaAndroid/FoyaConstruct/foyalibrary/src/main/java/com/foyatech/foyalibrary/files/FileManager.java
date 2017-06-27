package com.foyatech.foyalibrary.files;

import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Environment;
import android.util.Log;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.FileWriter;

public class FileManager {


    private boolean Permission = false;
    private Context mContext;
    private File mSDCard = null;

    public FileManager(Context context) {
        mContext = context;
        //判斷權限是否設置
        String permission = "android.permission.WRITE_EXTERNAL_STORAGE";
        int res = mContext.checkCallingOrSelfPermission(permission);
        if (res == PackageManager.PERMISSION_GRANTED) ;
        Permission = true;

        if(!Permission) {
            Log.e( this.getClass().toString() , "No Set Permission  : WRITE_EXTERNAL_STORAGE");
            return;
        }

        try
        {
            if(Environment.getExternalStorageDirectory().equals( Environment.MEDIA_REMOVED))
            {
                return ;
            }
            else
            {
                //取得SD卡儲存路徑
                mSDCard = Environment.getExternalStoragePublicDirectory( Environment.DIRECTORY_DOCUMENTS );
            }
        }
        catch (Exception ex)
        {
            Log.e( this.getClass().toString() ,ex.toString());
        }
    }

    /** 產生檔案於外部儲存空間Document
     *  目前僅支援TXT檔案的產生
     * @param Path 路徑 Ex :( Dir1/Dir2/../DirN )
     * @param Name 檔名 Ex :( FileText )
     * @param Type 檔案類型 Ex :( TXT )
     * @param Data 檔案資料 Ex :( MsgHere )
     */
    public boolean CreatFile(String Path,String Name ,String Type,String Data) {

        if(!Permission) return false;
        //Set check fileType.
        Type = Type.toUpperCase();
        String fileType = FileTypeAccpect(Type);
        if(!CheckType(fileType))
            return false;

        //此規定了輸出的位置，可於此修改。
        String DirPath = mSDCard.getParent() + "/" + mSDCard.getName() + "/" + Path ;
        String FilePaht = DirPath + Name + fileType ;

        try{
            File mFile = new File( DirPath );
            //若沒有檔案儲存路徑時則建立此檔案路徑
            if(!mFile.exists())
                mFile.mkdirs();
            FileWriter mFileWriter = new FileWriter( FilePaht );
            mFileWriter.write(Data);
            mFileWriter.close();
            return  true;
        }catch (Exception ex){
            Log.e("FilesManager",ex.toString());
            return  false;
        }

    }

    /** 讀取檔案於外部儲存空間Document
         *  目前僅支援TXT檔案的產生
         * @param Path 路徑 Ex :(  檔案實體路徑  )
         */
    public String ReadFile(String Path) {
        String mReadRes = "";
        try{
            FileReader mFileReader = new FileReader(Path);
            BufferedReader mBufferedReader = new BufferedReader(mFileReader);
            String mTextLine = mBufferedReader.readLine();

            while (mTextLine!=null)
            {
                mReadRes += mTextLine + "\n";
                mTextLine = mBufferedReader.readLine();
            }
        } catch (Exception ex){
            Log.e("FilesManager",ex.toString());
            mReadRes = "";
        }
        return  mReadRes;
    }
    private String FileTypeAccpect(String Type) {
        switch (Type)
        {
            case "TXT" :
                return  ".txt";
            default:
                return "NoAccpect";
        }
    }
    private boolean CheckType(String Type) {
        if(Type.equals("NoAccpect"))
            return  false;
        return true;
    }
}

package com.example.myapplication;

import android.content.Context;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class SaveLoad
{

    // for future reference https://stackoverflow.com/questions/4118751/how-do-i-serialize-an-object-and-save-it-to-a-file-in-android

    private static String filename = "savefile";

    static void save(Context context, DataForSaveLoad data)
    {
        try
        {
            FileOutputStream fos = context.openFileOutput(filename, Context.MODE_PRIVATE);
            ObjectOutputStream os = new ObjectOutputStream(fos);
            os.writeObject(data);
            os.close();
            fos.close();
        }
        catch(java.io.FileNotFoundException e){}
        catch(java.io.IOException e){}
    }

    static DataForSaveLoad load(Context context)
    {
        DataForSaveLoad ret = null;

        try
        {
            FileInputStream fis = context.openFileInput(filename);
            ObjectInputStream is = new ObjectInputStream(fis);
            ret = (DataForSaveLoad) is.readObject();
            is.close();
            fis.close();
        }
        catch(java.io.FileNotFoundException e){}
        catch(java.io.IOException e){}
        catch(java.lang.ClassNotFoundException e){}

        return ret;
    }

    private static boolean fileExists(Context context)
    {
        String[] files = context.fileList();
        for (String name : files)
        {
            if(name.equals(filename))
            {
                return true;
            }
        }
        return false;
    }
}

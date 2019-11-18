package com.example.myapplication;

import android.content.Context;

import java.io.FileOutputStream;
import java.io.ObjectOutputStream;

public class SaveLoad
{

    // for future reference https://stackoverflow.com/questions/4118751/how-do-i-serialize-an-object-and-save-it-to-a-file-in-android

    private static String filename = "savefile";

    static void save(Context context)
    {
        //TODO FileOutputStream fos = context.openFileOutput(filename, Context.MODE_PRIVATE)
    }

    static DataForSaveLoad load(Context context)
    {
        if(!fileExists(context))
            return null;

        //TODO FileInputStream fis = context.openFileInput(filename)

        return null;
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

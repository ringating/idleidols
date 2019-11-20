package com.example.myapplication;

import java.io.Serializable;

public class DataForSaveLoad implements Serializable // serializable is the point of this class
{
    public Agency agency; // this may not work; may need to store all values individually

    DataForSaveLoad(Agency agency)
    {
        this.agency = agency;
    }
}

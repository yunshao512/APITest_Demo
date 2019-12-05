package com.server.api.manager;


import com.server.api.dataobject.TestObject;

public interface IManager
{
   public TestObject getItem(String ID) throws Exception;
}

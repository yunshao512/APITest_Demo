package com.server.api.common;


import org.simpleframework.xml.Serializer;
import org.simpleframework.xml.core.Persister;

import java.io.File;

public class XMLSerializer {

    private static final ReporterLogger LOGGER = new ReporterLogger();
    /**
     *
     * @param targetInstance targetInstance
     * @param xmlFileName xmlFileName
     */
    public static void ObjectToXML(Object targetInstance,String xmlFileName)
    {
        try
        {
            Serializer serializer = new Persister();
            File file = new File(xmlFileName);
            serializer.write(targetInstance, file);
        }
        catch (Exception e1)
        {
            LOGGER.ExceptionToString(e1);
        }

    }

    /// serialize a xmlstring to object

    /**
     *
     * @param targetInstance target instance
     * @param objectXmlString xml string
     * @return
     */
    public static Object XMLToObject(Object targetInstance,String objectXmlString)
    {
        Object targeResult=null;
        try
        {
            Serializer serializer = new Persister();
            targeResult=serializer.read(targetInstance.getClass(),objectXmlString);
        }
        catch (Exception ex)
        {
            LOGGER.ExceptionToString(ex);
        }
        return targeResult;
    }
}

package com.tresbu.nvidia.json.pojo;

import java.io.Serializable;

public class AlertPojo implements Serializable
{
	private static final long serialVersionUID = 1L;
    
	private Groups Groups;

    private String[] TimeAndDate;

    private int UnclosedErrors;

    private int FailingAlerts;

    public Groups getGroups ()
    {
        return Groups;
    }

    public void setGroups (Groups Groups)
    {
        this.Groups = Groups;
    }

    public String[] getTimeAndDate ()
    {
        return TimeAndDate;
    }

    public void setTimeAndDate (String[] TimeAndDate)
    {
        this.TimeAndDate = TimeAndDate;
    }

    public int getUnclosedErrors ()
    {
        return UnclosedErrors;
    }

    public void setUnclosedErrors (int UnclosedErrors)
    {
        this.UnclosedErrors = UnclosedErrors;
    }

    public int getFailingAlerts ()
    {
        return FailingAlerts;
    }

    public void setFailingAlerts (int FailingAlerts)
    {
        this.FailingAlerts = FailingAlerts;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [Groups = "+Groups+", TimeAndDate = "+TimeAndDate+", UnclosedErrors = "+UnclosedErrors+", FailingAlerts = "+FailingAlerts+"]";
    }
}
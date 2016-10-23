package com.nvidia.cosmos.cloud.dtos;

import java.io.Serializable;

public class ChildrenDTO implements Serializable
{
	private static final long serialVersionUID = 1L;
   
	private boolean Silenced;

    private String Status;

    private boolean Active;

    private String AlertKey;

    private State State;

    private String Alert;

    private String Ago;

    private String CurrentStatus;

    public boolean getSilenced ()
    {
        return Silenced;
    }

    public void setSilenced (boolean Silenced)
    {
        this.Silenced = Silenced;
    }

    public String getStatus ()
    {
        return Status;
    }

    public void setStatus (String Status)
    {
        this.Status = Status;
    }

    public boolean getActive ()
    {
        return Active;
    }

    public void setActive (boolean Active)
    {
        this.Active = Active;
    }

    public String getAlertKey ()
    {
        return AlertKey;
    }

    public void setAlertKey (String AlertKey)
    {
        this.AlertKey = AlertKey;
    }

    public State getState ()
    {
        return State;
    }

    public void setState (State State)
    {
        this.State = State;
    }

    public String getAlert ()
    {
        return Alert;
    }

    public void setAlert (String Alert)
    {
        this.Alert = Alert;
    }

    public String getAgo ()
    {
        return Ago;
    }

    public void setAgo (String Ago)
    {
        this.Ago = Ago;
    }

    public String getCurrentStatus ()
    {
        return CurrentStatus;
    }

    public void setCurrentStatus (String CurrentStatus)
    {
        this.CurrentStatus = CurrentStatus;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [Silenced = "+Silenced+", Status = "+Status+", Active = "+Active+", AlertKey = "+AlertKey+", State = "+State+", Alert = "+Alert+", Ago = "+Ago+", CurrentStatus = "+CurrentStatus+"]";
    }
}
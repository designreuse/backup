package com.nvidia.cosmos.cloud.rest.util;

public class GrafanaUserKey
{
    private String id;

    private String name;

    private String role;

    public String getId ()
    {
        return id;
    }

    public void setId (String id)
    {
        this.id = id;
    }

    public String getName ()
    {
        return name;
    }

    public void setName (String name)
    {
        this.name = name;
    }

    public String getRole ()
    {
        return role;
    }

    public void setRole (String role)
    {
        this.role = role;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [id = "+id+", name = "+name+", role = "+role+"]";
    }
}
	
package com.locycommand.util;

public class BukkitVersionManager
{
  public String version;

  public BukkitVersionManager()
  {
	  this.version = org.bukkit.Bukkit.getServer().getClass().getPackage().getName().replace(".", ",").split(",")[3];
  }
}
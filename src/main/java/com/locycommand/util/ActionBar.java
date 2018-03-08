package com.locycommand.util;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class ActionBar {
	public static boolean sendActionBar(Player user, String msg) {
		if (msg == null) {
			throw new NullPointerException("Msg cannot be null.");
		}
		msg = ChatColor.translateAlternateColorCodes('&', msg);
		Class<?> chatPacket = getNMSClass("PacketPlayOutChat");
		Class<?> iChatBaseClass = getNMSClass("IChatBaseComponent"); 
		try {
			Method iChatBaseClass_a = iChatBaseClass.getDeclaredClasses()[0].getMethod("a", String.class);
			iChatBaseClass_a.setAccessible(true);
			try {
				Object iChatBase = iChatBaseClass_a.invoke(null,  new Object[] { "{\"text\":\"" + msg + "\"}" });
				Constructor<?> packetConstructor = chatPacket.getDeclaredConstructor(iChatBaseClass, byte.class);
				packetConstructor.setAccessible(true);
				try {
					Object packet = packetConstructor.newInstance(new Object[] {iChatBase,(byte)2});
					sendPacket(user, packet);
				} catch (InstantiationException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return true;
	}
	public static Class<?> getNMSClass(String name) {
        String version = org.bukkit.Bukkit.getServer().getClass().getPackage().getName().split("\\.")[3];
        try {
                return Class.forName("net.minecraft.server." + version + "." + name);
        } catch (ClassNotFoundException e) {
                e.printStackTrace();
        }return null;
}
public static void sendPacket(Player player, Object packet) {
        try {
                Object handle = player.getClass().getMethod("getHandle", new Class[0]).invoke(player, new Object[0]);
                Object playerConnection = handle.getClass().getField("playerConnection").get(handle);
                playerConnection.getClass().getMethod("sendPacket", new Class[] { getNMSClass("Packet") }).invoke(playerConnection, new Object[] { packet });
        } catch (Exception e) {
                e.printStackTrace();
        }
}
}

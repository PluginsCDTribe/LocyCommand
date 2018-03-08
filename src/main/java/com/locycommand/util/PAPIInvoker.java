package com.locycommand.util;

import org.bukkit.entity.Player;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class PAPIInvoker {
    private static Class<?> papi;
    private static Method targetMethod;
    private static boolean empty = false;

    private PAPIInvoker() {
    }

    public static String doInvoke(Player player, String before) {
        try {
            if (empty) {
                return (String) targetMethod.invoke(null, player, before);
            }
            if (papi == null) {
                papi = Class.forName("me.clip.placeholderapi.PlaceholderAPI");
            }
            if (targetMethod == null) {
                targetMethod = papi.getMethod("setPlaceholders", Player.class, String.class);
            }
            if (!(targetMethod == null && papi == null)) {
                empty = true;
            }
            return (String) targetMethod.invoke(null, player, before);
        } catch (ClassNotFoundException exc) {
            return before;
        } catch (NoSuchMethodException exc) {
            return before;
        } catch (InvocationTargetException exc) {
            return before;
        } catch (IllegalAccessException exc) {
            return before;
        }
    }

    public static boolean hasPAPI() {
        try {
            Class.forName("me.clip.placeholderapi.PlaceholderAPI");
            return true;
        } catch (ClassNotFoundException exc) {
            return false;
        }
    }
}

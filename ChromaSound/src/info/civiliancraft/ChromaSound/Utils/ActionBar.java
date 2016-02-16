package info.civiliancraft.ChromaSound.Utils;

import java.lang.reflect.InvocationTargetException;

import net.md_5.bungee.api.ChatColor;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class ActionBar {

	public static Class<?> getNmsClass(String nmsClassName)
			throws ClassNotFoundException {
		return Class.forName("net.minecraft.server."
				+ Bukkit.getServer().getClass().getPackage().getName()
						.replace(".", ",").split(",")[3] + "." + nmsClassName);
	}

	public static String getServerVersion() {
		return Bukkit.getServer().getClass().getPackage().getName()
				.substring(23);
	}

	public static void sendAction(Player p, String msg) {
		msg = ChatColor.translateAlternateColorCodes('&', msg);
		try {
			if ((getServerVersion().equalsIgnoreCase("v1_8_R2"))
					|| (getServerVersion().equalsIgnoreCase("v1_8_R3"))) {
				Object icbc = getNmsClass("IChatBaseComponent$ChatSerializer")
						.getMethod("a", new Class[] { String.class }).invoke(
								null,
								new Object[] { "{'text': '" + msg + "'}" });

				Object ppoc = getNmsClass("PacketPlayOutChat").getConstructor(
						new Class[] { getNmsClass("IChatBaseComponent"),
								Byte.TYPE }).newInstance(
						new Object[] { icbc, Byte.valueOf((byte) 2) });

				Object nmsp = p.getClass().getMethod("getHandle", new Class[0])
						.invoke(p, new Object[0]);

				Object pcon = nmsp.getClass().getField("playerConnection")
						.get(nmsp);

				pcon.getClass()
						.getMethod("sendPacket",
								new Class[] { getNmsClass("Packet") })
						.invoke(pcon, new Object[] { ppoc });
			} else {
				Object icbc = getNmsClass("ChatSerializer").getMethod("a",
						new Class[] { String.class }).invoke(null,
						new Object[] { "{'text': '" + msg + "'}" });

				Object ppoc = getNmsClass("PacketPlayOutChat").getConstructor(
						new Class[] { getNmsClass("IChatBaseComponent"),
								Byte.TYPE }).newInstance(
						new Object[] { icbc, Byte.valueOf((byte) 2) });

				Object nmsp = p.getClass().getMethod("getHandle", new Class[0])
						.invoke(p, new Object[0]);

				Object pcon = nmsp.getClass().getField("playerConnection")
						.get(nmsp);

				pcon.getClass()
						.getMethod("sendPacket",
								new Class[] { getNmsClass("Packet") })
						.invoke(pcon, new Object[] { ppoc });
			}
		} catch (IllegalAccessException | IllegalArgumentException
				| InvocationTargetException | NoSuchMethodException
				| SecurityException | ClassNotFoundException
				| InstantiationException | NoSuchFieldException e) {
			e.printStackTrace();
		}
	}
}

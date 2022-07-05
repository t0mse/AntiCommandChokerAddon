package de.t0mse.labymod.addon.anticommandchoker.util;

import net.labymod.core.LabyModCore;
import net.minecraft.event.ClickEvent;
import net.minecraft.event.HoverEvent;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.ChatStyle;
import net.minecraft.util.IChatComponent;

public final class ChatMessageUtil {
    /**
     * Displays message for client
     *
     * @param message as displayed text
     */
    public static void sendMessage(String message) {
        sendMessage(message, null, null);
    }

    /**
     * Displays message with hover and command for client
     *
     * @param message as displayed text
     * @param hover as hover for displayed text
     * @param command as command to run on click
     */
    public static void sendMessage(String message, String hover, String command) {
        LabyModCore.getMinecraft()
                .getPlayer()
                .addChatComponentMessage(getIChatComponent(message, hover, command));
    }

    /**
     * Creates and returns chat component object out of parameters
     *
     * @param message as displayed text
     * @param hover as hover for displayed text
     * @param command as command to run on click
     * @return IChatComponent object
     */
    private static IChatComponent getIChatComponent(String message, String hover, String command) {
        final IChatComponent iChatComponent = new ChatComponentText(message);
        final ChatStyle chatStyle = new ChatStyle();
        if (hover != null) {
            chatStyle.setChatHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT,
                    new ChatComponentText(hover)));
        }
        if (command != null) {
            chatStyle.setChatClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND,
                    command));
        }
        iChatComponent.setChatStyle(chatStyle);
        return iChatComponent;
    }
}
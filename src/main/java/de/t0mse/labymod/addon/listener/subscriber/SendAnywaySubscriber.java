package de.t0mse.labymod.addon.listener.subscriber;

import de.t0mse.labymod.addon.listener.IMessageSendSubscriber;
import de.t0mse.labymod.addon.util.ChatMessageUtil;
import net.labymod.core.LabyModCore;

public final class SendAnywaySubscriber implements IMessageSendSubscriber {
    public static final String COMMAND_KEY = "$acc$send-anyway";
    private String lastSent;

    @Override
    public boolean onSend(String command, String wholeMessage) {
        if (command.equalsIgnoreCase(COMMAND_KEY)) {
            final String toSend = wholeMessage
                    .replace(COMMAND_KEY, "").trim();
            if (toSend.equals(lastSent)) {
                ChatMessageUtil.sendMessage("§cThe message has already been sent!");
                return true;
            }
            lastSent = toSend;
            LabyModCore.getMinecraft().getPlayer().sendChatMessage(toSend);
            return true;
        }
        return false;
    }
}

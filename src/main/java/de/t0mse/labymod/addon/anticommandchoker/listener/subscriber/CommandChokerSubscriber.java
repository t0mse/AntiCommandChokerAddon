package de.t0mse.labymod.addon.anticommandchoker.listener.subscriber;

import de.t0mse.labymod.addon.anticommandchoker.listener.IMessageSendSubscriber;
import de.t0mse.labymod.addon.anticommandchoker.util.ChatMessageUtil;

public final class CommandChokerSubscriber implements IMessageSendSubscriber {
    @Override
    public boolean onSend(String command, String wholeMessage) {
        if (command.length() > 1 && command.startsWith("7")) {
            ChatMessageUtil.sendMessage("§cRecognized choked command!");
            ChatMessageUtil.sendMessage("§7[SEND MESSAGE ANYWAY]",
                    String.format("§7Send message ('%s')", wholeMessage),
                    String.format(SendAnywaySubscriber.COMMAND_KEY + " %s", wholeMessage));
            return true;
        }
        return false;
    }
}

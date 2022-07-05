package de.t0mse.labymod.addon.anticommandchoker.listener.subscriber;

import de.t0mse.labymod.addon.anticommandchoker.listener.IMessageSendSubscriber;
import de.t0mse.labymod.addon.anticommandchoker.util.ChatMessageUtil;

public final class CommandChokerSubscriber implements IMessageSendSubscriber {
    @Override
    public boolean onSend(String command, String wholeMessage) {
        if (command.length() > 1 && command.startsWith("7")) {
            SendAnywaySubscriber.redefineCommandTriggerSuffix();
            ChatMessageUtil.sendMessage("§cRecognized §cchoked §ccommand!");
            ChatMessageUtil.sendMessage("§7[SEND §7MESSAGE §7ANYWAY]",
                    String.format("§7Send §7message §7('%s')", wholeMessage),
                    String.format(SendAnywaySubscriber.getCommandTriggerSuffix() + " %s", wholeMessage));
            return true;
        }
        return false;
    }
}

package de.t0mse.labymod.addon.anticommandchoker.listener.subscriber;

import de.t0mse.labymod.addon.anticommandchoker.listener.IMessageSendSubscriber;
import de.t0mse.labymod.addon.anticommandchoker.util.ChatMessageUtil;
import net.labymod.core.LabyModCore;

import java.util.Random;

public final class SendAnywaySubscriber implements IMessageSendSubscriber {
    private static final String commandTriggerPrefix = "$acc$send-anyway-";
    private static String commandTriggerSuffix;
    private String lastSent;

    /**
     * Builds whole trigger command by command trigger prefix and random
     * generated trigger command suffix
     *
     * @return built command
     */
    public static String getCommandTriggerSuffix() {
        return commandTriggerPrefix + commandTriggerSuffix;
    }

    /**
     * Replaces current command trigger with new random generated number
     * value (int) in range of 100 and 999 so it's length is always 3
     * <p>
     * This helps to prevent the player from entering the intern trigger command
     * manually because it's different for every "send anyway" click action
     * on a choked command
     */
    public static void redefineCommandTriggerSuffix() {
        final int min = 100;
        final int max = 999;
        final String newSuffix = String.valueOf(new Random().nextInt(max - min + 1) + min);
        if (commandTriggerSuffix != null && commandTriggerSuffix.equalsIgnoreCase(newSuffix)) {
            redefineCommandTriggerSuffix();
            return;
        }
        commandTriggerSuffix = newSuffix;
    }

    @Override
    public boolean onSend(String command, String wholeMessage) {
        if (!command.startsWith(commandTriggerPrefix)) {
            return false;
        }
        if (command.equalsIgnoreCase(getCommandTriggerSuffix())) {
            final String toSend = wholeMessage.replace(getCommandTriggerSuffix(), "").trim();
            if (toSend.equals(lastSent)) {
                ChatMessageUtil.sendMessage("§cThe §cmessage §chas §calready §cbeen §csent!");
                return true;
            }
            lastSent = toSend;
            LabyModCore.getMinecraft().getPlayer().sendChatMessage(toSend);
            return true;
        }
        ChatMessageUtil.sendMessage("§cSend §caction §cfor §cthis §cchoked §ccommand §cis " +
                "§calready §cexpired §cor §ccould §cnot §cbe §cfound");
        return true;
    }
}

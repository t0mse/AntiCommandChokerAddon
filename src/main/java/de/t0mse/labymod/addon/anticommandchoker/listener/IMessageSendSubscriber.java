package de.t0mse.labymod.addon.anticommandchoker.listener;

public interface IMessageSendSubscriber {
    /**
     * Gets called when player sends message and is supposed to check
     * for specific keys in the sent message
     *
     * @param command      first argument - the expected command - of whole sent message
     * @param wholeMessage whole sent message
     * @return cancel sending message
     */
    boolean onSend(String command, String wholeMessage);
}

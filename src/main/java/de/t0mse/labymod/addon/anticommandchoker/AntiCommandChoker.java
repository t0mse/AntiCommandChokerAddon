package de.t0mse.labymod.addon.anticommandchoker;

import com.google.common.util.concurrent.ListeningExecutorService;
import com.google.common.util.concurrent.MoreExecutors;
import de.t0mse.labymod.addon.anticommandchoker.listener.MessageSendListener;
import de.t0mse.labymod.addon.anticommandchoker.listener.subscriber.CommandChokerSubscriber;
import de.t0mse.labymod.addon.anticommandchoker.listener.subscriber.SendAnywaySubscriber;
import net.labymod.api.LabyModAddon;
import net.labymod.settings.elements.SettingsElement;

import java.util.List;
import java.util.concurrent.Executors;

public class AntiCommandChoker extends LabyModAddon {
    private static AntiCommandChoker instance;
    private ListeningExecutorService asyncListeningExecutor;

    public static AntiCommandChoker getInstance() {
        return instance;
    }

    public ListeningExecutorService getAsyncListeningExecutor() {
        return asyncListeningExecutor;
    }

    @Override
    public void onEnable() {
        instance = this;
        asyncListeningExecutor = MoreExecutors.listeningDecorator(Executors.newCachedThreadPool());
        getApi().getEventManager().register(MessageSendListener
                .create(new SendAnywaySubscriber(),
                        new CommandChokerSubscriber()));
    }

    @Override
    public void loadConfig() {
    }

    @Override
    protected void fillSettings(List<SettingsElement> list) {
    }
}

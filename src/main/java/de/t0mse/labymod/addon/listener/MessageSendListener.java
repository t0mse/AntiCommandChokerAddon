package de.t0mse.labymod.addon.listener;

import com.google.common.util.concurrent.ListenableFuture;
import de.t0mse.labymod.addon.AntiCommandChocker;
import net.labymod.api.events.MessageSendEvent;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;

public final class MessageSendListener implements MessageSendEvent {
    private final List<IMessageSendSubscriber> messageSendSubscribers;

    private MessageSendListener(List<IMessageSendSubscriber> messageSendSubscribers) {
        this.messageSendSubscribers = messageSendSubscribers;
    }

    /**
     * Static factory for instantiation of class and optional
     * registration of subscribers (instance of IMessageSendSubscriber)
     *
     * @param messageSendSubscribers optional subscribers
     * @return MessageSendListener class object
     */
    public static MessageSendListener create(IMessageSendSubscriber... messageSendSubscribers) {
        final MessageSendListener messageSendListener
                = new MessageSendListener(new LinkedList<IMessageSendSubscriber>());
        for (IMessageSendSubscriber messageSendSubscriber : messageSendSubscribers) {
            messageSendListener.registerSubscriber(messageSendSubscriber);
        }
        return messageSendListener;
    }

    /**
     * Registers a subscriber (instance of IMessageSendSubscriber)
     *
     * @param messageSendSubscriber subscriber object
     */
    private void registerSubscriber(IMessageSendSubscriber messageSendSubscriber) {
        messageSendSubscribers.add(messageSendSubscriber);
    }

    @Override
    public boolean onSend(final String s) {
        if (messageSendSubscribers == null || messageSendSubscribers.isEmpty()) {
            return false;
        }
        final String command = s.split(" ")[0].trim();
        for (final IMessageSendSubscriber messageSendSubscriber : messageSendSubscribers) {
            final ListenableFuture<Boolean> subscriberFuture = AntiCommandChocker.getInstance()
                    .getAsyncListeningExecutor().submit(new Callable<Boolean>() {
                        @Override
                        public Boolean call() {
                            return messageSendSubscriber.onSend(command, s);
                        }
                    });
            try {
                boolean isCancelled = subscriberFuture.get();
                if (isCancelled) {
                    return true;
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        }
        return false;
    }
}

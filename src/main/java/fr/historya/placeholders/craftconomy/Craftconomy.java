package fr.historya.placeholders.craftconomy;

import com.greatmancode.craftconomy3.Common;
import com.greatmancode.craftconomy3.account.Balance;
import me.clip.placeholderapi.expansion.PlaceholderExpansion;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.jetbrains.annotations.NotNull;

public class Craftconomy extends PlaceholderExpansion {

    @Override
    public @NotNull String getAuthor() {
        return "Historya";
    }

    @Override
    public @NotNull String getIdentifier() {
        return "craftconomy";
    }

    @Override
    public @NotNull String getVersion() {
        return "1.0.0";
    }

    @Override
    public @NotNull String getRequiredPlugin() {
        return "Craftconomy3";
    }

    @Override
    public boolean canRegister() {
        return Bukkit.getServer().getPluginManager().getPlugin(this.getRequiredPlugin()) != null;
    }

    @Override
    public String onRequest(final OfflinePlayer player, @NotNull String placeholder) {
        if (player == null) {
            return "";
        } else {

            try {
                for (Balance balance : Common.getInstance().getAccountManager().getAccount(player.getName(), false).getAllBalance()) {
                    if (balance.getCurrency().getName().equalsIgnoreCase(placeholder)) {
                        return String.valueOf((int) balance.getBalance());
                    }
                }
            } catch(Exception e) {
                Bukkit.getLogger().warning(e.getMessage());
                return "";
            }

            return placeholder;
        }
    }

}
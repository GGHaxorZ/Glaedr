package examples;

import org.bukkit.event.Listener;
import org.bukkit.event.EventHandler;

public class Example extends JavaPlugin implements Listener //did all that shit using notepad, so please do the imports yourself xD
{
  public Example()
  {
    super();
  }

  private Glaedr glaedr;
  private List<Player> cooldownMap;

  public void onEnable()
  {
    Bukkit.getServer().getPluginManager().registerEvents(this, this);
    this.glaedr = new Glaedr(this, "§lHCMemez §r[Map 420 Omega]", true, true);
    glaedr.getTopWrappers().clear();
    glaedr.getBottomWrappers().clear();
    glaedr.getTopWrappers().add("   ");
    this.cooldownMap = new ArrayList<Player>();
  }

  @EventHandler(ignoreCanelled=true)
  public void listen(org.bukkit.event.player.PlayerInteractEvent e)
  {
    PlayerScoreboard sb = PlayerScoreboard.getScoreboard(e.getPlayer());
    new Entry("pearlCooldown").setText("§lEnderparl§r: §3");
    Entry pc = sb.getEntry("pearlCooldown");
    if (!cooldownMap.contains(e.getPlayer()))
    {
      cooldownMap.add(e.getPlayer());
      pc.setTime(16.0D);
      new BukkitRunnable()
      {
        public void run()
        {
          cooldownMap.remove(e.getPlayer());
          e.getPlayer().sendMessage("Cooldown expired!");
        }
      }.runTaskLater(this, 320L);
    }
    else
    {
      e.getPlayer().sendMessage("Still on cooldown!");
    }
  }
}

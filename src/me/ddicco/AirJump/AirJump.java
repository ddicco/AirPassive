package me.ddicco.AirJump;

import org.bukkit.Location;
import org.bukkit.entity.Player;
import com.projectkorra.projectkorra.ProjectKorra;
import com.projectkorra.projectkorra.ability.AddonAbility;
import com.projectkorra.projectkorra.ability.AirAbility;
import com.projectkorra.projectkorra.ability.PassiveAbility;
import com.projectkorra.projectkorra.configuration.ConfigManager;

public class AirJump extends AirAbility implements AddonAbility, PassiveAbility {
	
	private long duration;
	private long starttime;
	private long cooldown;
	
	public AirJump(final Player player) {
		super(player);
		
		start();
	}
	
	@Override
	public void progress() {
		if(bPlayer.isOnCooldown(this)) {
			return;
		}
		if(player.isSprinting()) {
			if(player.isFlying()) {
				if(System.currentTimeMillis() > starttime + duration) {
					bPlayer.addCooldown(this);
            		player.setAllowFlight(false);
            		player.setFlying(false);
            		setFields();
            		return;
				}
			}
			if (!player.isOnGround()) {
				player.setAllowFlight(true);
	            if(player.isSprinting()) {
	            	if(System.currentTimeMillis() > starttime + duration) {
	            		bPlayer.addCooldown(this);
	            		player.setAllowFlight(false);
	            		player.setFlying(false);
	            		setFields();
	            		return;
	            	}
	            } if(System.currentTimeMillis() > starttime + duration) {
	            	bPlayer.addCooldown(this);
	            	player.setAllowFlight(false);
	            	player.setFlying(false);
	            	setFields();
	            	return;
	            }
			} else {
				if(System.currentTimeMillis() > starttime + duration) {
					bPlayer.addCooldown(this);
					player.setAllowFlight(false);
					player.setFlying(false);
					setFields();
					return;
				}
				return;
			}
		} else {
			return;
		}
	}
	@Override
	public boolean isSneakAbility() {
		return false;
	}
	
	public void setFields() {
		starttime = System.currentTimeMillis();
		duration = ConfigManager.getConfig().getLong("ExtraAbilities.Air.AirPassive.Duration");
		cooldown = ConfigManager.getConfig().getLong("ExtraAbilities.Air.AirPassive.Cooldown");
	}
	
	@Override
	public boolean isHarmlessAbility() {
		return false;
	}
	
	@Override
	public long getCooldown() {
		return cooldown;
	}
	
	@Override
	public String getName() {
		return "AirJump";
	}
	
	@Override
	public Location getLocation() {
		return this.player !=null ? this.player.getLocation() : null;
	}
	
	@Override
	public boolean isProgressable() {
		return true;
	}
	
	@Override
	public boolean isInstantiable() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public String getAuthor() {
		// TODO Auto-generated method stub
		return "ddicco";
	}

	@Override
	public String getVersion() {
		// TODO Auto-generated method stub
		return "2.0.4";
	}

	@Override
	public void load() {
		// TODO Auto-generated method stub
		 ProjectKorra.log.info("Successfully enabled " + getName() + " by " + getAuthor() + " Version " + getVersion());
		 ConfigManager.getConfig().addDefault("ExtraAbilities.Air.AirPassive.Duration", 2000);
		 ConfigManager.getConfig().addDefault("ExtraAbilities.Air.AirPassive.Cooldown", 750);
		 ConfigManager.defaultConfig.save();
	}

	@Override
	public void stop() {
		// TODO Auto-generated method stub
		 ProjectKorra.log.info("Successfully disabled " + getName() + " by " + getAuthor());
	}
}

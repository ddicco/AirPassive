package me.ddicco.AirJump;

import org.bukkit.Location;
import org.bukkit.entity.Player;

import com.projectkorra.projectkorra.ability.AirAbility;
import com.projectkorra.projectkorra.ability.PassiveAbility;

public class AirJump extends AirAbility implements PassiveAbility {
	
	private int t;
	
	public AirJump(final Player player) {
		super(player);
		this.setFields();
	}
	
	public void setFields() {
	}
	
	@Override
	public void progress() {
		if (!this.player.isSprinting() || !this.bPlayer.canBendPassive(this)) {
			return;
		}
		
		if (!player.isOnGround()) {
			player.setAllowFlight(true);
			t = t+1;
			
			if(t < 40) {
				return;
				}
			}
	}
	@Override
	public boolean isSneakAbility() {
		return false;
	}
	
	@Override
	public boolean isHarmlessAbility() {
		return false;
	}
	
	@Override
	public long getCooldown() {
		return 0;
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
}

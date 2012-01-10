/*
 * This file is part of SpoutAPI (http://wwwi.getspout.org/).
 * 
 * Spout API is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * Spout API is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.spout.api.gui;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.spout.api.Spout;
import org.spout.api.player.Player;

public abstract class GenericScreen extends GenericContainer implements Screen {

	private int playerId = -1;
	private boolean bg = true;

	public GenericScreen() {
	}

	public GenericScreen(int playerId) {
		this.playerId = playerId;
	}

	@Override
	public int getVersion() {
		return super.getVersion() + 0;
	}

	@Override
	public void onTick() {
		super.onTick();
		Player player = getPlayer();
		if (player != null) {
			for (Widget widget : getChildren()) {
				if (widget.isDirty()) {
					if (!widget.hasSize()/* || !widget.hasPosition()*/) {
						String type = "Unknown";
						try {
							type = widget.getType().getWidgetClass().getSimpleName();
						} catch (Exception e) {
						}
						Logger.getLogger("Minecraft").log(Level.WARNING,
								type
								+ " belonging to " + widget.getPlugin().getDescription().getName()
								+ " does not have a default "
								+ (!widget.hasSize() ? "size" : "") + (!widget.hasSize() && !widget.hasPosition() ? " or " : "") + (!widget.hasPosition() ? "position" : "")
								+ "!");
						widget.setX(widget.getX());
						widget.setHeight(widget.getHeight());
					}
					if (!widget.getType().isServerOnly()) {
						player.sendPacket(new PacketWidget(widget, getId()));
					}
					widget.setDirty(false);
				}
			}
		}
	}

	@Override
	public Screen insertChild(int index, Widget child) {
		if (child instanceof Screen) {
			throw new UnsupportedOperationException("Unsupported widget type");
		}
		super.insertChild(index, child);
		return this;
	}

	@Override
	public Screen setBgVisible(boolean enable) {
		bg = enable;
		return this;
	}

	@Override
	public boolean isBgVisible() {
		return bg;
	}

	@Override
	public Player getPlayer() {
		return playerId == -1 ? null : Spout.getPlayerFromId(playerId);
	}

	@Override
	public int getNumBytes() {
		return super.getNumBytes() + 1;
	}

	@Override
	public void readData(DataInputStream input) throws IOException {
		super.readData(input);
		setBgVisible(input.readBoolean());
	}

	@Override
	public void writeData(DataOutputStream output) throws IOException {
		super.writeData(output);
		output.writeBoolean(isBgVisible());
	}

	@Override
	public Widget copy() {
		throw new UnsupportedOperationException("You can not create a copy of a screen");
	}
}
/*
 * This file is part of SpoutAPI.
 *
 * Copyright (c) 2011-2012, Spout LLC <http://www.spout.org/>
 * SpoutAPI is licensed under the Spout License Version 1.
 *
 * SpoutAPI is free software: you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation, either version 3 of the License, or (at your option)
 * any later version.
 *
 * In addition, 180 days after any changes are published, you can use the
 * software, incorporating those changes, under the terms of the MIT license,
 * as described in the Spout License Version 1.
 *
 * SpoutAPI is distributed in the hope that it will be useful, but WITHOUT ANY
 * WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE.  See the GNU Lesser General Public License for
 * more details.
 *
 * You should have received a copy of the GNU Lesser General Public License,
 * the MIT license and the Spout License Version 1 along with this program.
 * If not, see <http://www.gnu.org/licenses/> for the GNU Lesser General Public
 * License and see <http://spout.in/licensev1> for the full license, including
 * the MIT license.
 */
package org.spout.api.gui;

import java.util.Collection;
import java.util.Set;

import org.spout.api.math.IntVector2;
import org.spout.api.plugin.Plugin;

public interface Container {
	public abstract void removeWidgets();

	public abstract void removeWidgets(Plugin plugin);

	public abstract void removeWidgets(Widget... widgets);

	public abstract void removeWidget(Widget widget);

	public abstract void attachWidget(Plugin plugin, Widget widget);

	public abstract Collection<Widget> getWidgets();

	public abstract Widget getWidgetAt(int x, int y);

	public abstract Widget getWidgetAt(IntVector2 pos);
}

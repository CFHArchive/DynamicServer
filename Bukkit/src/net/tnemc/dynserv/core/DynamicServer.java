package net.tnemc.dynserv.core;

import com.google.common.io.ByteArrayDataOutput;
import com.google.common.io.ByteStreams;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * Created by Daniel.
 *
 * Dynamic Server
 *
 * Copyright (C) 2018 creatorfromhell
 *
 * This program is free software: you can redistribute it and/or modify it under the terms of the
 * GNU Affero General Public License as published by the Free Software Foundation, either version 3
 * of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without
 * even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License along with this program.
 * If not, see <http://www.gnu.org/licenses/>.
 **/
public class DynamicServer extends JavaPlugin {

  @Override
  public void onEnable() {
    this.getServer().getMessenger().registerOutgoingPluginChannel(this, "DynamicServer");

    ByteArrayDataOutput out = ByteStreams.newDataOutput();
    out.writeUTF("add");
    out.writeUTF(getServer().getServerName());
    out.writeUTF(getServer().getMotd());
    out.writeUTF(getServer().getIp());
    out.writeInt(getServer().getPort());

    getServer().sendPluginMessage(this, "DynamicServer", out.toByteArray());
  }
}
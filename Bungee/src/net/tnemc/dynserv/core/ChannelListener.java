package net.tnemc.dynserv.core;

import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.config.ServerInfo;
import net.md_5.bungee.api.event.PluginMessageEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.net.InetSocketAddress;

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
public class ChannelListener implements Listener {
  @EventHandler
  public void onPluginMessage(PluginMessageEvent event){
    if(event.getTag().equalsIgnoreCase("DynamicServer")) {
      DataInputStream in = new DataInputStream(new ByteArrayInputStream(event.getData()));
      try {
        final String sub = in.readUTF();
        if(sub.equalsIgnoreCase("add")) {
          final String name = in.readUTF();
          final String motd = in.readUTF();
          final String ip = in.readUTF();
          final int port = in.readInt();

          final ServerInfo info = ProxyServer.getInstance().constructServerInfo(name, new InetSocketAddress(ip, port), motd, false);
          ProxyServer.getInstance().getServers().put(name, info);
        }
      } catch(IOException e) {
        e.printStackTrace();
      }
    }
  }
}
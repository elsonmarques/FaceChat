package pacote;

//package com.fb.xmppchat.app;

import pacote.CustomSASLDigestMD5Mechanism;
import pacote.FBMessageListener;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

import org.apache.commons.collections.BidiMap;
import org.apache.commons.collections.bidimap.DualHashBidiMap;

import org.jivesoftware.smack.ConnectionConfiguration;
import org.jivesoftware.smack.Chat;
import org.jivesoftware.smack.ChatManager;
import org.jivesoftware.smack.MessageListener;
import org.jivesoftware.smack.Roster;
import org.jivesoftware.smack.RosterEntry;
import org.jivesoftware.smack.SASLAuthentication;
import org.jivesoftware.smack.XMPPConnection;
import org.jivesoftware.smack.XMPPException;
import org.jivesoftware.smack.packet.Message;
import org.jivesoftware.smack.packet.Presence;

public class FacebookMessagerManager {

   public static final String FB_XMPP_HOST = "chat.facebook.com";
   public static final int FB_XMPP_PORT = 5222;

   private ConnectionConfiguration config;
   private XMPPConnection connection;
   private BidiMap friends = new DualHashBidiMap();
   private FBMessageListener fbml;

   public String connect() throws XMPPException {
      config = new ConnectionConfiguration(FB_XMPP_HOST, FB_XMPP_PORT);
     // SASLAuthentication.registerSASLMechanism("DIGEST-MD5"
     //   , CustomSASLDigestMD5Mechanism.class);
      config.setSASLAuthenticationEnabled(true);
      config.setDebuggerEnabled(false);
      connection = new XMPPConnection(config);
      connection.connect();
      fbml = new FBMessageListener(connection);
      return connection.getConnectionID();
   }

   public void disconnect() {
      if ((connection != null) && (connection.isConnected())) {
         Presence presence = new Presence(Presence.Type.unavailable);
         presence.setStatus("offline");
         connection.disconnect(presence);
      }
   }

   public boolean login(String userName, String password)
     throws XMPPException {
      if ((connection != null) && (connection.isConnected())) {
         connection.login(userName, password);
         return true;
      }
      return false;
   }

   public String readInput() throws IOException {
      BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
      return br.readLine();
   }


   public void getFriends() {
      if ((connection != null) && (connection.isConnected())) {
         Roster roster = connection.getRoster();
         int i = 1;
         for (RosterEntry entry : roster.getEntries()) {
            Presence presence = roster.getPresence(entry.getUser());
            if ((presence != null)
               && (presence.getType() != Presence.Type.unavailable)) {
               friends.put("#" + i, entry);
               System.out.println(entry.getName() + "(#" + i + ")");
               i++;
            }
         }
         fbml.setFriends(friends);
      }
   }

   public void sendMessage() throws XMPPException
     , IOException {
      //System.out.println("Type the key number of your friend (e.g. #1) and the text that you wish to send !");
      String friendKey = null;
      String text = null;
    //  System.out.print("Your friend's Key Number: ");
      friendKey = readInput();
     // System.out.print("Your Text message: ");
      text = readInput();
      sendMessage((RosterEntry) friends.get(friendKey), text);
   }

   public void sendMessage(final RosterEntry friend, String text)
     throws XMPPException {
      if ((connection != null) && (connection.isConnected())) {
         ChatManager chatManager = connection.getChatManager();
         Chat chat = chatManager.createChat(friend.getUser(), fbml);
         chat.sendMessage(text);
      //   System.out.println("Your message has been sent to "
       //     + friend.getName());
      }
   }


}
/**
 *  This program is free software; you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation; either version 2 of the License, or
 *  (at your option) any later version.
 *
 *  This program is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU Library General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with this program; if not, write to the Free Software
 *  Foundation, Inc., 59 Temple Place - Suite 330, Boston, MA 02111-1307, USA.
 */
package com.injoit.examplechat.jabber;

import com.injoit.examplechat.jabber.roster.Jid;
import com.injoit.examplechat.jabber.conversation.Message;
import com.injoit.examplechat.jabber.conversation.Conversation;
/**
 * Interface for jabber events listeners
 * @author Bianchi Gabriele
 *
 */
public interface JabberListener {
    
    //ROSTER
    public void notifyRoster();
    public void notifyJudInfo(String info);

    //CONVERSATION
    /**
    * A roster (or group of rosters) has set up a conversation with the user
    */
        public void newConversationEvent(Conversation _conversation);

        /**
         * A new message has been received for the given conversation.
         */
        public void newMessageEvent(Conversation _conversation);
        public void newComposingEvent(Conversation _conv);
        /**
         * A new invitation has been received.
         * @author Gabriele Bianchi
         * Modified by Vladimir Slatvinskiy
         */
        public void newInvitationEvent(String from, String room);
        public void newInvitationEvent(String from, String room, String reason);

        /**
         * An error occured on the given conversation
         */
        public void notifyError(Conversation _conversation, Message _errorMessage);
        
        public void disconnectedEvent();

        //PRESENCE

        public void notifyPresence(Jid _roster, String _presence);

        public void notifyPresenceError(String _error);

        //GENERAL
        public void updateScreen();
        
        //CONNECTION
        public void unauthorizedEvent(String reason);
        
        //DISCOVER ROOMS
        public void DiscoverRooms();
        
        //ROOM CONFIGURATION
        public void RoomConfiguration();
        
        // UPDATE ROOM MEMBERS COUNT
        public void UpdateRoomMembersCount(String roomName, int membersCount);
}

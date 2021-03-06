/**
 * MicroJabber, jabber for light java devices. Copyright (C) 2004, Gregoire Athanase
 * This library is free software; you can redistribute it and/or modify it under the 
 * terms of the GNU Lesser General Public License as published by the Free Software 
 * Foundation; either version 2.1 of the License, or (at your option) any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT ANY 
 * WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A 
 * PARTICULAR PURPOSE. See the GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License along with 
 * this library; if not, write to the Free Software Foundation, Inc., 59 Temple Place, 
 * Suite 330, Boston, MA 02111-1307 USA.
 */

package com.injoit.examplechat.jabber.roster;

import com.injoit.examplechat.jabber.presence.Presence;
import com.injoit.examplechat.util.*;
import javax.microedition.lcdui.Image;
/**
 * Jabber ID. It has one of the following form: 
 * - servername
 * - username@servername
 * - username@servername/resource 
 * 
 * Modified by Gabriele Bianchi 04/01/2006
 * Modified by Vladimir Slatvinskiy
 */

public class Jid 
{
    private String servername;
    private String username;
    private String resource;
    private String mail = "user@chat.quickblox.com";
    private String presence;// = "unavailable";
    private byte[] avatar = null;
    public String avatarHash = null;
    public String phone = null;
    public String status_message = ""; 
    public String group = "";
    public String nick = "";
   
    /**
     * Constructs a Jid, given its string representation
     * example: "myserver" or "username@myserver" or "username@myserver/resource"
     */
    public Jid(String _jid) 
    {
        int at = _jid.lastIndexOf('@');
        int slash = _jid.indexOf('/', at);

        if (at==-1) 
        {
            username = null;
        } 
        else 
        {
            username = _jid.substring(0,at);
        }

        if (slash==-1) 
        {
            // no resource
            servername = _jid.substring(at+1);
            resource = null;
        }
        else 
        {
            // resource
            servername = _jid.substring(at+1,slash).trim();
            resource = _jid.substring(slash+1);
        }
        setPresence(Presence.getPresence("unavailable"));
        nick = username;
   }
    
    /**
     * @param _jid
     * @param _presence
     */
    public Jid(String _jid, String _presence) 
    {

        presence = _presence;
        int at = _jid.lastIndexOf('@');
        int slash = _jid.indexOf('/', at);

        if (at==-1) 
        {
            username = null;
        } 
        else 
        {
            username = _jid.substring(0,at);
        }

        if (slash==-1) 
        {
            // no resource
            servername = _jid.substring(at+1);
            resource = null;
        } 
        else 
        {
            // resource
            servername = _jid.substring(at+1,slash);
            resource = _jid.substring(slash+1);
        }
        
        nick = username;
    }
    
    /**
     * 
     * @return String
     */
    public String getServername() {
        return servername;
    }
    /**
     * 
     * @return String
     */
    public String getUsername() 
    {
        if (username == null)
                return servername;
        return username;
    }
    /**
     * 
     * @return Image
     */
    public byte[] getAvatar() {
        return avatar;
    }
    /**
     * 
     * @return String
     */
    public String getResource() {
        return resource;
    }
    /**
     * 
     * @return String
     */
    public String getPresence() {
        return presence;
    }

    /**
     * 
     * @param _presence
     */
    public void setPresence(String _presence) {
                  presence = _presence;
                  status_message = "";
        return;
    }
        /**
        * 
        * @param _presence
        * @param status
        */
        public void setPresence(String _presence, String status)
        {
                presence = _presence;
                status_message = status;
                return;
        }
    /**
     *@return String
     */
     public String getMail() {
        return mail;
    }
    /**
     * 
     * @param _mail
     */
    public void setMail(String _mail) {
                  mail = _mail;
        return;
    }
    /**
     * 
     * @param _val
     */
    public void setResource(String _val) {
        if (_val=="") {
            resource = null;
        } else {
            resource = _val;
        }
    }
    
    /**
     * returns the little jid, that is without the eventual resource information
     */
    public String getLittleJid() {
        String res = "";
        if (username != null) {
            res += username + "@";
        }
        res += servername;
        return res;
    }
    
     public String getNickName() {
        String out = "";
        if(username.indexOf('-')!=-1)
        {
            out = username.substring(username.lastIndexOf('-')+1, username.length());
        }
        
        //return out;
        return username;
    }
    
    public String getNick() 
    {
        String str = "";
        try
        {
            str = Util.unescapeCDATA(nick);
        }
        catch(Exception ex)
        {
            str = nick;
        }
        return str;
    }
    
    public void setNick(String value) 
    {
       nick = value;
    }
    
    /**
     * @return the full jid, that is with the eventual resource information
     */
    public String getFullJid() {
        String res = getLittleJid();
        if (resource!=null) {
            res += "/" + resource;
        }
        return res;
    }
    
    public void setAvatar(byte[] img) { //AVATAR
        this.avatar = img;
    }
    public static Image createAvatar(byte[] img) { //AVATAR
        if (img != null) {
                try {
                        
                        return Image.createImage(img, 0, img.length-1);
                }catch(Exception e) {
                        System.out.println("Error in createAvatar() "+e.getMessage());
                        }
        }
        return null;
    }

   // ...... static methods ...........
    /**
     * returns the jid without the eventual resource.
     */
    public static String getLittleJid(String _jid) {
        String res;
        int at = _jid.lastIndexOf('@');
                if (at == -1)
                        return _jid;
        int slash = _jid.indexOf('/', at); 
        
        if (slash == -1) {
            res = _jid;
        } else {
            res = _jid.substring(0, slash);
        }
        return res;
    }
        /**
         * @param _jid
         * @return username part
         */
        public static String getUsername(String _jid)
        {
                if (_jid.indexOf("@") != -1)
                {
                        return _jid.substring(0, _jid.lastIndexOf('@'));
                }
                else
                        return _jid;
        }
        
        public static String getNickUser(String _jid) {
                if (_jid.indexOf("-") != -1)
                {
                    return _jid.substring(_jid.lastIndexOf('-')+1, _jid.length());                 
                }
             return _jid;
        }
}

